package budget;

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

        System.out.println("Purchase was added!");
    }

    public static void showPurchases(String type) {
        System.out.println("");

        if ("Food".equals(type)) {
            for (Map.Entry<String, Double> entry : foodList.entrySet()) {
                System.out.println(entry.getKey() + " $" + entry.getValue());
            }
        } else if ("Clothes".equals(type)) {
            for (Map.Entry<String, Double> entry : clothesList.entrySet()) {
                System.out.println(entry.getKey() + " $" + entry.getValue());
            }
        } else if ("Entertainment".equals(type)) {
            for (Map.Entry<String, Double> entry : entertainmentList.entrySet()) {
                System.out.println(entry.getKey() + " $" + entry.getValue());
            }
        } else if ("Other".equals(type)) {
            for (Map.Entry<String, Double> entry : otherList.entrySet()) {
                System.out.println(entry.getKey() + " $" + entry.getValue());
            }
        } else {
            for (Map.Entry<String, Double> entry : allList.entrySet()) {
                System.out.println(entry.getKey() + " $" + entry.getValue());
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
        balance += nBalance;
    }

    public static void currentBalance() {
        if ((balance - calcSum(allList)) > 0) {
            System.out.println("\nBalance: $" + String.format("%.2f", balance - calcSum(allList)));
        } else {
            balance = 0;
            System.out.println("\nBalance: $" + balance);
        }
    }

}