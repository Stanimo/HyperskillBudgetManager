package budget;

import java.util.Scanner;

public abstract class InputHandler {
    static Scanner scanner = new Scanner(System.in);

    public static Integer inputMenuNo() {
        return scanner.nextInt();
    }

    public static String purchaseInput() {
        return scanner.nextLine();
    }

    public static Double inputDouble() {
        return scanner.nextDouble();
    }
}
