package Input;

import Output.Colors;

import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class Input<I> {
    protected String errorMessage;
    private I value;

    public Input(String errorMessage) {
        this.errorMessage = Colors.toRed(errorMessage);
    }

    public I get() {
        I input = null;
        Scanner sc = new Scanner(System.in);

        do {
            try {
                input = convert(sc.nextLine());

                if(!valid(input)) {
                    input = null;
                    System.out.println(this.errorMessage);
                }
            } catch (IllegalStateException | NoSuchElementException e) {
                System.out.println(Colors.toRed("Erreur de saisie."));
            } catch (Exception e) {
                System.out.println(Colors.toRed("Erreur de conversion."));
            }

        } while(input == null);

        value = input;
        return input;
    }

    public I value() {
        return value;
    }

    public abstract boolean valid(I input);

    public abstract I convert(String input);
}
