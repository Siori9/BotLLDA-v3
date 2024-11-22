package bot.llda.botlldav3.game.model.otherPart;

public abstract class FamiliesFunctions {

    public static String familyToString(Families family){
        switch (family){
            case RUBIS -> {
                return "RUBIS";
            }
            case SAPHIR -> {
                return "SAPHIR";
            }
            case EMERAUDE -> {
                return "EMERAUDE";
            }
            case AMETHYSTE -> {
                return "AMETHYSTE";
            }
            default -> {
                return null;
            }
        }
    }

    public static Families stringToFamily(String family){
        switch (family){
            case "RUBIS" -> {
                return Families.RUBIS;
            }
            case "SAPHIR" -> {
                return Families.SAPHIR;
            }
            case "EMERAUDE" -> {
                return Families.EMERAUDE;
            }
            case "AMETHYSTE" -> {
                return Families.AMETHYSTE;
            }
            default -> {
                return null;
            }
        }
    }
}
