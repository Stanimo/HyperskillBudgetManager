package budget;

import java.util.*;

public class SortService {
    static List<String> keyArray = new LinkedList<>();
    static List<Double> entryArray = new LinkedList<>();

    public static Map<String, Double> sortMap(Map<String, Double> mapToSort) {
        Map<String, Double> allSorted = new LinkedHashMap<>();
        List<String> keyArray = new LinkedList<>();
        List<Double> entryArray = new LinkedList<>();

        int switchCount = 1;
        double tempDouble;
        String tempString;


        for (Map.Entry<String, Double> entry : mapToSort.entrySet()) {
            keyArray.add(entry.getKey());
            entryArray.add(entry.getValue());
        }

        while(switchCount >= 1) {
            switchCount = 0;
            for (int i = 0; i < entryArray.size() - 1; i++) {
                if (entryArray.get(i) < entryArray.get(i + 1)) {

                    tempDouble = entryArray.get(i + 1);
                    tempString = keyArray.get(i + 1);

                    entryArray.set(i + 1, entryArray.get(i));
                    keyArray.set(i + 1, keyArray.get(i));

                    entryArray.set(i, tempDouble);
                    keyArray.set(i, tempString);

                    switchCount++;
                }
            }
        }

        for (int i = 0; i <= entryArray.size() - 1; i++) {
            allSorted.put(keyArray.get(i), entryArray.get(i));
        }
        return allSorted;
    }

    public static void sortByType() {
        Map<String, Double> typeToSort = new LinkedHashMap<>();

        typeToSort.put("Food", UserService.calcSum(UserService.foodList));
        typeToSort.put("Clothes", UserService.calcSum(UserService.clothesList));
        typeToSort.put("Entertainment", UserService.calcSum(UserService.entertainmentList));
        typeToSort.put("Other", UserService.calcSum(UserService.otherList));
        Map<String, Double> typeAfterSorted = new LinkedHashMap<>(sortMap(typeToSort));  // Equating maps

        printSortedTypes(typeAfterSorted);
    }

    public static void printSortedTypes(Map<String, Double> mapToPrint) {
        for (Map.Entry<String, Double> entry : mapToPrint.entrySet()) {
            if (entry.getValue() == 0) {
                System.out.printf("%s - $%.0f\n", entry.getKey(), entry.getValue());
            } else {
                System.out.printf("%s - $%.2f\n", entry.getKey(), entry.getValue());
            }
        }
    }

    public static void printSorted(Map<String, Double> mapToPrint) {
        for (Map.Entry<String, Double> entry : mapToPrint.entrySet()) {
            if (entry.getValue() == 0) {
                System.out.printf("%s $%.0f\n", entry.getKey(), entry.getValue());
            } else {
                System.out.printf("%s $%.2f\n", entry.getKey(), entry.getValue());
            }
        }
    }
}
