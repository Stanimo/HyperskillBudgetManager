package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

import static budget.InputHandler.scanner;

public class UserService {
    static List<String> purchaseNames = new ArrayList<>();
    static List<Double> purchasePrices = new ArrayList<>();

    //Type maps:
    static Map<String, Double> foodList = new HashMap<>();
    static Map<String, Double> clothesList = new HashMap<>();
    static Map<String, Double> entertainmentList = new HashMap<>();
    static Map<String, Double> otherList = new HashMap<>();
    static Map<String, Double> allList = new HashMap<>();

    static Map<String, Map<String, Double>> purchaseTypes = new TreeMap<>();

    static double balance = 0.0;
    static double income = 0.0;

    public static void addPurchase(String type) {
        System.out.println("\nEnter purchase name:");
        scanner.nextLine(); //If I don't put this line, the run skips the input I need altogether

        String strA = InputHandler.purchaseInput();
        purchaseNames.add(strA);

        System.out.println("Enter its price:");
        Double strB = InputHandler.inputDouble();
        purchasePrices.add(strB);

        if ("Food".equals(type)) {
            foodList.put(strA, strB);
            allList.put(strA, strB);
        } else if ("Clothes".equals(type)) {
            clothesList.put(strA, strB);
            allList.put(strA, strB);
        } else if ("Entertainment".equals(type)) {
            entertainmentList.put(strA, strB);
            allList.put(strA, strB);
        } else if ("Other".equals(type)) {
            otherList.put(strA, strB);
            allList.put(strA, strB);
        }
        //FUTURE transform this class to a global use by every other class (Single use, single output functions)
        balance = income - calcSum(allList);

        System.out.println("Purchase was added!");
    }

    public static void showPurchases(String type) {
        System.out.print("\n");
        if ("Food".equals(type)) {
            for (Map.Entry<String, Double> entry : foodList.entrySet()) {
                System.out.printf("%s $%.2f\n", entry.getKey(), entry.getValue());
            }
        } else if ("Clothes".equals(type)) {
            for (Map.Entry<String, Double> entry : clothesList.entrySet()) {
                System.out.printf("%s $%.2f\n", entry.getKey(), entry.getValue());
            }
        } else if ("Entertainment".equals(type)) {
            for (Map.Entry<String, Double> entry : entertainmentList.entrySet()) {
                System.out.printf("%s $%.2f\n", entry.getKey(), entry.getValue());
            }
        } else if ("Other".equals(type)) {
            for (Map.Entry<String, Double> entry : otherList.entrySet()) {
                System.out.printf("%s $%.2f\n", entry.getKey(), entry.getValue());
            }
        } else {
            for (Map.Entry<String, Double> entry : allList.entrySet()) {
                System.out.printf("%s $%.2f\n", entry.getKey(), entry.getValue());
            }
        }
    }

    public static double calcSum(Map<String, Double> calcMap) {
        double sum = 0.0;
        for (Map.Entry<String, Double> entry : calcMap.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }

    public static void totalPurchases(String type) {
        if (calcSum(allList) == 0) {
            System.out.println("\nThe purchase list is empty");
        } else if ("Food".equals(type)){
            System.out.println("Total sum: $" + String.format("%.2f", calcSum(foodList)));
        } else if ("Clothes".equals(type)){
            System.out.println("Total sum: $" + String.format("%.2f", calcSum(clothesList)));
        } else if ("Entertainment".equals(type)){
            System.out.println("Total sum: $" + String.format("%.2f", calcSum(entertainmentList)));
        } else if ("Other".equals(type)){
            System.out.println("Total sum: $" + String.format("%.2f", calcSum(otherList)));
        } else if ("All".equals(type)){
            System.out.println("Total sum: $" + String.format("%.2f", calcSum(allList)));
        }
    }

    public static void addBalance(double nBalance) {
        income += nBalance;
        balance = currentBalance();
        //Think of a better way to deal with income and balance.
    }

    public static double currentBalance() {
        if (balance > income) {
            return balance;
        } else {
            if (income - calcSum(allList) > 0) {
                return income - calcSum(allList);
            } else {
                return 0;
            }
        }
    }

    public static void save() {
        File saveFile = new File("./purchases.txt");
        //FUTURE: Add automatic dating feature to the txt file name.
        try {
            boolean createdNewFile = saveFile.createNewFile();
            if (createdNewFile) {
                try (PrintWriter printWriter = new PrintWriter(saveFile)) {

                    for (Map.Entry<String, Double> entry : foodList.entrySet()) {
                        printWriter.printf("Food, %s, %.2f\n", entry.getKey(), entry.getValue());
                    }

                    for (Map.Entry<String, Double> entry : clothesList.entrySet()) {
                        printWriter.printf("Clothes, %s, %.2f\n", entry.getKey(), entry.getValue());
                    }

                    for (Map.Entry<String, Double> entry : entertainmentList.entrySet()) {
                        printWriter.printf("Entertainment, %s, %.2f\n", entry.getKey(), entry.getValue());
                    }

                    for (Map.Entry<String, Double> entry : otherList.entrySet()) {
                        printWriter.printf("Other, %s, %.2f\n", entry.getKey(), entry.getValue());
                    }

                    for (Map.Entry<String, Double> entry : allList.entrySet()) {
                        printWriter.printf("All, %s, %.2f\n", entry.getKey(), entry.getValue());
                    }

                    printWriter.printf("Income, %.2f\n", income);
                    printWriter.printf("Balance, %.2f\n", currentBalance());

                } catch (IOException e) {
                    System.out.printf("Writing exception %s", e.getMessage());
                }
                System.out.println("Purchases were saved!");
            } else {
                //FUTURE Add overwrite option the file if it already exists
                System.out.println("The file already exists");
            }
        } catch (IOException e) {
            System.out.println("Cannot create the file " + saveFile.getPath());
        }
    }

    public static void load() {
        File loadFile = new File("./purchases.txt");
        String loadedString;
        String[] parts;
        try (Scanner scanner1 = new Scanner(loadFile)) {
            while (scanner1.hasNextLine()) {
                loadedString = scanner1.nextLine();
                if (loadedString.startsWith("Food")) {
                    parts = loadedString.split(",");
                    loadPurchase(parts[0], parts[1].trim(), Double.parseDouble(parts[2]));
                } else if (loadedString.startsWith("Clothes")) {
                    parts = loadedString.split(",");
                    loadPurchase(parts[0], parts[1].trim(), Double.parseDouble(parts[2]));
                } else if (loadedString.startsWith("Entertainment")) {
                    parts = loadedString.split(",");
                    loadPurchase(parts[0], parts[1].trim(), Double.parseDouble(parts[2]));
                } else if (loadedString.startsWith("Other")) {
                    parts = loadedString.split(",");
                    loadPurchase(parts[0], parts[1].trim(), Double.parseDouble(parts[2]));
                } else if (loadedString.startsWith("Income")) {
                    parts = loadedString.split(",");
                    loadPurchase(parts[0], null, Double.parseDouble(parts[1]));
                } else if (loadedString.startsWith("Balance")) {
                    parts = loadedString.split(",");
                    loadPurchase(parts[0], null, Double.parseDouble(parts[1]));
                }
            }
            System.out.println("\nPurchases were loaded!");
        } catch (FileNotFoundException e) {
            System.out.printf("Cannot read the file, %s", e.getMessage());
        }

    }

    public static void loadPurchase(String type, String strA, Double aDouble) {
        if ("Food".equals(type)) {
            foodList.put(strA, aDouble);
            allList.put(strA, aDouble);
        } else if ("Clothes".equals(type)) {
            clothesList.put(strA, aDouble);
            allList.put(strA, aDouble);
        } else if ("Entertainment".equals(type)) {
            entertainmentList.put(strA, aDouble);
            allList.put(strA, aDouble);
        } else if ("Other".equals(type)) {
            otherList.put(strA, aDouble);
            allList.put(strA, aDouble);
        } else if ("Income".equals(type)) {
            income = aDouble;
        } else if ("Balance".equals(type)) {
            balance = aDouble;
        }
        //FUTURE transform this class to a global use by every other class (Single use, single output functions)
    }
}