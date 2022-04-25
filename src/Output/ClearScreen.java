package Output;

public class ClearScreen {
    private static String osName = System.getProperty("os.name");

    public static void clear() {
        if(osName.equals("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
}