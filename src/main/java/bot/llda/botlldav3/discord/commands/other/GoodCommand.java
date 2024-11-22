package bot.llda.botlldav3.discord.commands.other;

import bot.llda.botlldav3.discord.commands.SlashCommand;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GoodCommand implements SlashCommand {
    private final String[] goodSentence = {
            "Le secret pour bien vivre : manger la moitié, marcher le double, rire le triple et aimer sans mesure.",
            "Brisez vos limites, faites sauter les barrières de vos contraintes, mobilisez votre volonté, exigez la liberté comme un droit, soyez ce que vous voulez être. Découvrez ce que vous aimeriez faire et faites tout votre possible pour y parvenir.",
            "La vie, c'est comme une bicyclette, il faut avancer pour ne pas perdre l'équilibre",
            "L'obstination est le chemin de la réussite.",
            "Ne pas viser trop haut, trop vite. On risque de ne pas atteindre l'objectif et d'être déçu. Mieux vaut procéder par étapes et s'assigner des buts progressifs. Car chaque petite victoire en prépare une plus grande.",
            "Un homme sans vision, sans projet, sans objectif dans la vie est un homme sans destinée, car c'est l'homme qui prépare sa propre destinée.",
            "Le succès n'est pas final L'echec n'est pas fatal C'est le courage de continuer qui compte.",
            "Il est dur d'échouer ; mais il est pire de n'avoir jamais tenté de réussir.",
            "Votre temps est limité, ne le gâchez pas en menant une existence qui n'est pas la vôtre.",
            "Il est grand temps de rallumer les étoiles.",
            "Avoir du courage ne veut pas dire que nous n'avons pas peur. Avoir du courage et faire preuve de courage signifie que nous affrontons nos peurs. Nous pouvons dire : Je suis tombée, mais je vais me relever.",
            "Il ne faut pas toujours tourner la page, il faut parfois la déchirer.",
            "Celui qui avance avec confiance dans la direction de ses rêves connaîtra un succès inattendu dans la vie ordinaire.",
            "La plus grande victoire, c'est la victoire sur soi.",
            "Soit A un succès dans la vie. Alors A = x + y + z, où x = travailler, y = s'amuser, z = se taire.",
            "Chaque bonne réalisation, grande ou petite, connaît ses périodes de corvées et de triomphes; un début, un combat et une victoire.",
            "Il ne faut jamais désespérer et se battre, car se battre, ne serait-ce que pour une seule personne, suffit à justifier le combat.",
            "Avoir un but trace la voie.",
            "On ne crée pas notre vie, on la vit",
            "Gardez toujours à l'esprit que votre propre décision de réussir est plus importante que n'importe quoi d'autre.",
            "Aimez la vie. Engagez-vous dedans. Donnez tout ce que vous avez. Aimez-la avec passion parce que la vie rend vraiment, plusieurs fois, ce que vous y mettez.",
            "Beaucoup de chemins mènent à la réussite, mais un seul mène immanquablement à l'échec, celui qui consiste à tenter de plaire à tout le monde.",
            "Je préfère l'avenir au passé, car c'est là que j'ai décidé de vivre le restant de mes jours.",
            "Continuez à sourire, car la vie est belle et il y a tellement de choses à quoi sourire.",
            "Ce n'est pas parce que les choses sont difficiles que nous n'osons pas, c'est parce que nous n'osons pas qu'elles sont difficiles.",
            "Réussir c'est être en paix avec soi même, maitriser ses pensées, focaliser ses objectifs et n'attendre rien de personne.",
            "Cette vie est ce que vous en faites. Peu importe ce que vous allez gâcher parfois, c'est une vérité universelle.",
            "Ose ta vie, toi seul la vivra.",
            "Entre possible et impossible, deux lettres et un état d'esprit.",
            "Il arrive souvent de ne rien obtenir parce que l'on ne tente rien.",
            "Il ne faut pas penser à l'objectif à atteindre, il faut seulement penser à avancer. C'est ainsi, à force d'avancer, qu'on atteint ou qu'on double ses objectifs sans même s'en apercevoir.",
            "Le bonheur doit être ton objectif personnel. Il doit transcender tous les autres objectifs que tu auras dans ta vie. Si tu es heureux, ta vie est meilleure à tous égards. Le bonheur est la pensée positive la plus efficace. Grâce à lui, tu peux atteindre tous tes objectifs.",
            "Le commencement est beaucoup plus que la moitié de l'objectif.",
            "Pour réussir, retenez bien ces trois maximes: voir c'est savoir, vouloir c'est pouvoir, oser c'est avoir.",
            "Je peux accepter l'échec, tout le monde échoue dans quelque chose. Mais je ne peux pas accepter de ne pas essayer.",
            "Accepte les défis afin que tu puisses sentir l'euphorie de la victoire.",
            "Celui qui n'a pas d'objectifs ne risque pas de les atteindre.",
            "L'impossible recule toujours quand on marche vers lui.",
            "Celui qui ne progresse pas chaque jour, recule chaque jour.",
            "On regrette rarement d'avoir osé, mais toujours de ne pas avoir essayer."};


    @Override
    public String getName() {
        return "good";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return event.reply()
                .withContent(goodSentence[(int) (Math.random() * (goodSentence.length - 1))]);
    }
}
