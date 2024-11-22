package bot.llda.botlldav3.game.model;

public abstract class TypesUserFunctions {

    public static String typeToString(TypesUser type){
        switch (type){
            case PLAYER -> {
                return "PLAYER";
            }
            case MODO -> {
                return "MODO";
            }
            case ADMIN -> {
                return "ADMIN";
            }
            default -> {
                return null;
            }
        }
    }

    public static TypesUser stringToType(String type){
        switch (type){
            case "PLAYER" -> {
                return TypesUser.PLAYER;
            }
            case "MODO" -> {
                return TypesUser.MODO;
            }
            case "ADMIN" -> {
                return TypesUser.ADMIN;
            }
            default -> {
                return null;
            }
        }
    }
}
