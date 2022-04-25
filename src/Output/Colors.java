package Output;

public class Colors {
    public static String toGreen(String msg) {
        return "\u001b[1;32m"+msg+"\033[0m";
    }
    public static String toRed(String msg) {
        return "\u001b[1;31m"+msg+"\033[0m";
    }
}
