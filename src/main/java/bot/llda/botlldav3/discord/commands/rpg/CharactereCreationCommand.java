package bot.llda.botlldav3.discord.commands.rpg;

import bot.llda.botlldav3.discord.commands.SlashCommand;
import bot.llda.botlldav3.discord.utils.*;
import bot.llda.botlldav3.game.entities.UserEntity;
import bot.llda.botlldav3.game.entities.characterPart.CharacterEntity;
import bot.llda.botlldav3.game.model.TypesUser;
import bot.llda.botlldav3.game.model.User;
import bot.llda.botlldav3.game.model.characterPart.CharacterData;
import bot.llda.botlldav3.game.model.characterPart.Guide;
import bot.llda.botlldav3.game.model.otherPart.Families;
import bot.llda.botlldav3.game.model.otherPart.FamiliesFunctions;
import bot.llda.botlldav3.game.model.otherPart.Lvl;
import bot.llda.botlldav3.game.model.worldPart.Place;
import bot.llda.botlldav3.game.model.worldPart.Pnj;
import bot.llda.botlldav3.game.repositories.UserRepository;
import bot.llda.botlldav3.game.repositories.characterPart.CharacterRepository;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.event.domain.interaction.ComponentInteractionEvent;
import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import discord4j.core.object.component.*;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.Webhook;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.http.client.ClientException;
import discord4j.rest.util.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.*;

@Component
public class CharactereCreationCommand implements SlashCommand {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getName() {
        return "create-charactere";
    }

    public CharactereCreationCommand(CharacterRepository characterRepository, UserRepository userRepository, GatewayDiscordClient gateway) {
        this.gateway = gateway;
        this.charactereRepository = characterRepository;
        this.userRepository = userRepository;
    }

    private final GatewayDiscordClient gateway;
    private final CharacterRepository charactereRepository;
    private final UserRepository userRepository;

    EmbedCreateSpec messageDescriptionFamilies = EmbedCreateSpec.builder()
            .color(Color.GRAY_CHATEAU)
            .title("Les familles du Maled")
            .description("La famille du personnage est très importante car selon votre choix, vous n'aurez pas la même aventure, ni les même perspective d'évolution. ....")
            .addField("RUBIS", "``Magie:``feu\n \n``Style de métiers généralement exercé:``\nsoldat, forgeron,...\n \n``Traits de caractère courant:``\ncolérique, impulsif, énergique, franc,...\n \n``Style de combat le plus facile à maitriser:`` combattant offentif (Tank,DPS,...)", true)
            .addField("SAPHIR", "``Magie:`` eau\n \n``Style de métiers généralement exercé:``\nmédecin, archiviste,...\n \n``Traits de caractère courant:``\ncalme, réfléchi, peureux, gentil,...\n \n``Style de combat le plus facile à maitriser:`` soigneur et soutien", true)
            .addField("\t", "\t", false)
            .addField("EMERAUDE", "``Magie:`` plante\n \n``Style de métiers généralement exercé:``\nfermier, batisseur,...\n \n``Traits de caractère courant:``\njoviale, passifique, optimiste, généreux,...\n \n``Style de combat le plus facile à maitriser:`` soutien", true)
            .addField("AMETHYSTE", "``Magie:`` illusion\n \n``Style de métiers généralement exercé:``\ngouverneur, artiste,...\n \n``Traits de caractère courant:``\nfarceur, hypocrite, fermé, méfiant,...\n \n``Style de combat le plus facile à maitriser:`` très divers", true)
            .build();

    SelectMenu select = SelectMenu.of("familyCharacterSelect",
            SelectMenu.Option.of("Rubis", "rubis"),
            SelectMenu.Option.of("Saphir", "saphir"),
            SelectMenu.Option.of("Emeraude", "emeraude"),
            SelectMenu.Option.of("Améthyste", "amethyste")
    );

    Button buttonSelectFamily = Button.primary("familyCharacterChoice", "Valider");

    public record WebhookAndData(Webhook webhook, CharacterData data) {
    }

    public Mono<Void> handle(ChatInputInteractionEvent event) {

        User user = new User(UUID.randomUUID(), event.getInteraction().getUser().getId().toString(), TypesUser.PLAYER);
        UserEntity userEntity = userRepository.findByDiscordId(event.getInteraction().getUser().getId().toString());
        if (userEntity != null) {
            user = userEntity.toUser();
        }
        CharacterData dataCharacter = new CharacterData(UUID.randomUUID(), "", "", Families.RUBIS, null, user, new Lvl(UUID.randomUUID(), 1, 0), 0, new ArrayList<>(), null, false);

        return event.reply().withContent("*Le monde de Maled - Création du personnage de *" + event.getInteraction().getUser().getMention())
                .withEphemeral(true)
                .then(CreateWebhook.getWebhook("Clara",event)
                        .flatMap(webhook -> webhook.execute()
                                .withWaitForMessage(true)
                                .withContent("Bienvenue dans le monde de Maled cher aventurier ou aventurière ! Nous ne nous connaissons pas encore mais j'espère que nous ferons plus ample connaissance pour que ta réincarantion parmi nous se fasse au mieux !\n \nPour commencer, si tu me donnais ton petit nom et ta photo de profil !\n \n")
                                .withAvatarUrl("https://i.pinimg.com/564x/61/68/18/616818d09f2687401e4d67a970792130.jpg")
                                .flatMap(message -> createName(event, webhook, message, dataCharacter))
                        )
                        .flatMap(webhookAndDataCharacter -> choiceFamily(event, webhookAndDataCharacter.webhook, webhookAndDataCharacter.characterData))
                        .map(webhookAndDataCharacter -> {
                            return webhookAndDataCharacter;
                        })
                        .flatMap(webhookAndDataCharacter -> webhookAndDataCharacter.webhook.delete("Deleting webhook").thenReturn(webhookAndDataCharacter.characterData))
                ).map(this::registerCharacter)
                .then(event.getInteraction().getChannel().flatMap(channel -> channel.createMessage("**Personnage créé**")).then());
    }

    public Mono<WebhookAndDataCharacter> choiceFamily(ChatInputInteractionEvent event, Webhook webhook, CharacterData dataCharacter) {
        return webhook.execute().withContent("Enchanté " + dataCharacter.getName() + "! \nMaintenant il te faudra choisir la famille de ton personnage !")
                .withEmbeds(messageDescriptionFamilies)
                .withComponents(ActionRow.of(select), ActionRow.of(buttonSelectFamily))
                .withWaitForMessage(true)
                .flatMap(message -> gateway.on(SelectMenuInteractionEvent.class)
                        .filter(eventMenu -> eventMenu.getCustomId().equals("familyCharacterSelect"))
                        .flatMap(interaction -> interaction.deferEdit().thenReturn(interaction))
                        .takeUntilOther(
                                ListenerButton.getButtonInList(
                                gateway,
                                message,
                                List.of(buttonSelectFamily),
                                Duration.ofHours(1),
                                ignored -> message.delete()
                        ))
                        .last()
                        .map(interaction -> {
                            LOGGER.info(interaction.getValues().getFirst().toUpperCase());
                            dataCharacter.setFamily(
                                    FamiliesFunctions.stringToFamily(interaction.getValues().getFirst().toUpperCase())
                            );
                            return dataCharacter;
                        })
                )
                .map(data -> new WebhookAndDataCharacter(webhook, data))
                .map(data -> { LOGGER.info("end family choice"); return data;});
    }

    Button charactereDataChoice = Button.primary("charactereDataChoice", "Définir le nom et l'avatar de ton personnages");
    Button characterDataValidate = Button.success("characterDataValidate", "Valider les informations");
    Button characterDataModify = Button.primary("characterDataModify", "Modifier les informations");

    Collection<LayoutComponent> modalComponents = new ArrayList<>(
            List.of(
                    ActionRow.of(
                            TextInput.small("name", "Nom du personnage").required(true)
                    ),
                    ActionRow.of(
                            TextInput.small("avatar", "Lien de l'avatar du personnage").required(true)
                    )
            )
    );

    public Mono<List<String>> openModal(Message message, ChatInputInteractionEvent event) {
        return ListenerButton.getButtonInList(
                gateway,
                message,
                List.of(charactereDataChoice),
                Duration.ofHours(1),
                buttonInteractionEvent ->
                        ModalCreator.createModal(buttonInteractionEvent, "Création de ton personnage", "createCharacterModal", modalComponents)
                                .then(GetterInputModal.getAllInputModal(gateway, "createCharacterModal", Duration.ofHours(1), 2))
                                .onErrorComplete()
        ).next();
    }

    public record MessageAndData(Message message, List<String> data) {
    }

    public record MessageAndButton(Message message, Button button) {
    }

    public record DataAndDataCharacter(List<String> data, CharacterData characterData) {
    }

    public record MessageAndDataCharacter(Message message, CharacterData characterData) {
    }

    public record ButtonAndDataCharacter(Button button, CharacterData characterData) {
    }

    public record MessageButtonAndDataCharacter(MessageAndButton messageAndButton, CharacterData characterData) {
    }

    public record StringAndDataCharacter(String string, CharacterData characterData) {
    }

    public record WebhookAndDataCharacter(Webhook webhook, CharacterData characterData) {
    }

    public Mono<WebhookAndDataCharacter> createName(ChatInputInteractionEvent event, Webhook webhook, Message messageStart, CharacterData dataCharacter) {

        return webhook.execute().withWaitForMessage(true)
                .withComponents(ActionRow.of(charactereDataChoice))
                .flatMap(message -> openModal(message, event)
                        .map(data -> new MessageAndData(message, data)))
                .flatMap(MessageAndData -> MessageAndData.message.delete().thenReturn(MessageAndData.data))
                .map(data -> {
                    dataCharacter.setName(data.getFirst());
                    dataCharacter.setAvatarUrl(data.getLast());
                    return new DataAndDataCharacter(data, dataCharacter);
                })
                .flatMap(datas -> webhook.execute()
                        .withEmbeds(EmbedCreateSpec.builder()
                                .color(Color.DISCORD_WHITE)
                                .author(datas.data.getFirst(), datas.data.getLast(), datas.data.getLast())
                                .build()
                        )
                        .withAvatarUrl("https://i.pinimg.com/564x/61/68/18/616818d09f2687401e4d67a970792130.jpg")
                        .withWaitForMessage(true)
                        .withComponents(ActionRow.of(characterDataValidate, characterDataModify))
                        .map(message -> new MessageAndDataCharacter(message, datas.characterData))
                        .onErrorResume(ClientException.class, error -> {
                            datas.characterData.setAvatarUrl("https://st5.depositphotos.com/80336932/67743/i/380/depositphotos_677430982-stock-photo-black-square-middle-white-circle.jpg");
                            return webhook.execute()
                                    .withEmbeds(EmbedCreateSpec.builder()
                                            .color(Color.DISCORD_WHITE)
                                            .author(datas.characterData.getName(), datas.characterData.getAvatarUrl(), datas.characterData.getAvatarUrl())
                                            .build()
                                    )
                                    .withAvatarUrl("https://i.pinimg.com/564x/61/68/18/616818d09f2687401e4d67a970792130.jpg")
                                    .withWaitForMessage(true)
                                    .withComponents(ActionRow.of(characterDataValidate, characterDataModify))
                                    .map(message -> new MessageAndDataCharacter(message, datas.characterData));
                        })
                )
                .flatMap(
                        messageAndDataCharacter -> ListenerButton.getButtonActivate(
                                gateway,
                                messageAndDataCharacter.message,
                                List.of(characterDataValidate, characterDataModify),
                                Duration.ofSeconds(10),
                                ComponentInteractionEvent::deferEdit
                        ).map(button -> new MessageButtonAndDataCharacter(new MessageAndButton(messageAndDataCharacter.message, button), messageAndDataCharacter.characterData))
                )
                .flatMap(messageButtonAndDataCharacter -> messageButtonAndDataCharacter.messageAndButton.message.delete().thenReturn(new ButtonAndDataCharacter(messageButtonAndDataCharacter.messageAndButton.button, messageButtonAndDataCharacter.characterData)))
                .flatMap(buttonAndDataCharacter -> Mono.justOrEmpty(new StringAndDataCharacter(buttonAndDataCharacter.button.getCustomId().get(), buttonAndDataCharacter.characterData)))
                .flatMap(idAndCharacterData ->
                        switch (idAndCharacterData.string) {
                            case "characterDataValidate" ->
                                    messageStart.delete().thenReturn(idAndCharacterData.characterData);
                            case "characterDataModify" ->
                                    createName(event, webhook, messageStart, idAndCharacterData.characterData).thenReturn(idAndCharacterData.characterData);
                            default ->
                                    Mono.error(new IllegalStateException("Unexpected value: " + idAndCharacterData.string));
                        })
                .map(data -> new WebhookAndDataCharacter(webhook, data));
    }

    public Mono<String> registerCharacter(CharacterData data) {

        LOGGER.info("Register character");
        data.setGuide(new Guide(UUID.randomUUID(),new Pnj(UUID.randomUUID(), "Amelia","https://letsenhance.io/static/8f5e523ee6b2479e26ecc91b9c25261e/1015f/MainAfter.jpg",data.getFamily(),"le description"),new Lvl(UUID.randomUUID(),1,0),0,0,0,new ArrayList<>(),new ArrayList<>()));
        data.setLocation(UUID.fromString("f9c489ff-3e0d-4a9d-a9b2-f206c7980bf7"));

        var charactersWithSameOwner = charactereRepository.findAllByOwner(new UserEntity(data.getOwner()));
        for (CharacterEntity character : charactersWithSameOwner) {
            character.setSelected(false);
            charactereRepository.save(character);
        }

        data.setSelected(true);
        charactereRepository.save(new CharacterEntity(data));
        return Mono.just("Character created");
    }


    @Override
    public Optional<ApplicationCommandRequest> data() {
        return Optional.of(ApplicationCommandRequest.builder()
                .name("create-charactere")
                .description("Creation of charactere")
                .build());
    }
}
