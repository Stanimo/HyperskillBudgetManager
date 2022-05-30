package budget;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        menu00();
    }

    public static void menu00() {
        System.out.println("\nChoose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save\n" +
                "6) Load\n" +
                "7) Analyze (Sort)\n" +
                "0) Exit");
        switch(InputHandler.inputMenuNo()) {
            case 1: {
                System.out.println("\nEnter income:");
                UserService.addBalance(InputHandler.inputDouble());
                System.out.println("Income was added!");
                menu00();
                break;
            }
            case 2: {
                boolean contLoop = true;
                while (contLoop) {
                    System.out.println("\nChoose the type of purchase" + "\n" +
                            "1) Food\n" +
                            "2) Clothes\n" +
                            "3) Entertainment\n" +
                            "4) Other\n" +
                            "5) Back");
                    switch (InputHandler.inputMenuNo()) {
                        case 1: {
                            UserService.addPurchase("Food");
                            break;
                        }
                        case 2: {
                            UserService.addPurchase("Clothes");
                            break;
                        }
                        case 3: {
                            UserService.addPurchase("Entertainment");
                            break;
                        }
                        case 4: {
                            UserService.addPurchase("Other");
                            break;
                        }
                        case 5: {
                            contLoop = false;
                            break;
                        }
                        default: {
                            System.out.println("Please enter a single digit number" +
                                    "in the range of menu items.");
                            break;
                        }
                    }
                }
                menu00();
                break;
            }
            case 3: {
                boolean contLoop = true;
                if (UserService.calcSum(UserService.allList) == 0.0) {
                    contLoop = false;
                    System.out.println("\nThe purchase list is empty!");
                } else {
                    while (contLoop) {
                        System.out.println("\nChoose the type of purchase" + "\n" +
                                "1) Food\n" +
                                "2) Clothes\n" +
                                "3) Entertainment\n" +
                                "4) Other\n" +
                                "5) All\n" +
                                "6) Back");
                        switch (InputHandler.inputMenuNo()) {
                            case 1: {
                                UserService.showPurchases("Food");
                                UserService.totalPurchases("Food");
                                break;
                            }
                            case 2: {
                                UserService.showPurchases("Clothes");
                                UserService.totalPurchases("Clothes");
                                break;
                            }
                            case 3: {
                                UserService.showPurchases("Entertainment");
                                UserService.totalPurchases("Entertainment");
                                break;
                            }
                            case 4: {
                                UserService.showPurchases("Other");
                                UserService.totalPurchases("Other");
                                break;
                            }
                            case 5: {
                                UserService.showPurchases("All");
                                UserService.totalPurchases("All");
                                break;
                            }
                            case 6: {
                                contLoop = false;
                                break;
                            }
                            default: {
                                System.out.println("Please enter a single digit number" +
                                        "in the range of menu items.");
                                break;
                            }
                        }
                    }
                }
                menu00();
                break;
            }
            case 4: {
                System.out.printf("\nBalance: $%.2f\n", UserService.currentBalance());
                menu00();
                break;
            }
            case 5: {
                UserService.save();
                menu00();
                break;
            }
            case 6: {
                UserService.load();
                menu00();
                break;
            }
            case 7: {
                boolean contLoop = true;
                while (contLoop) {
                    System.out.println("\nHow do you want to sort?" + "\n" +
                            "1) Sort all purchases\n" +
                            "2) Sort by type\n" +
                            "3) Sort certain type\n" +
                            "4) Back");
                    switch (InputHandler.inputMenuNo()) {
                        case 1: {
                            if (UserService.calcSum(UserService.allList) == 0) {
                                System.out.println("\nThe purchase list is empty!");
                            }
                            else {
                                System.out.print("\n");
                                Map<String, Double> inputOrderList = new LinkedHashMap<>();
                                inputOrderList.putAll(UserService.foodList);
                                inputOrderList.putAll(UserService.entertainmentList);
                                inputOrderList.putAll(UserService.clothesList);
                                inputOrderList.putAll(UserService.otherList);
                                SortService.printSorted(SortService.sortMap(inputOrderList));
                            }
                            break;
                        }
                        case 2: {
                            System.out.println("\nTypes:");
                            SortService.sortByType();
                            if (UserService.calcSum(UserService.allList) > 0) {
                                System.out.printf("Total sum: $%.2f\n", UserService.calcSum(UserService.allList));
                            }
                            break;
                            }
                        case 3: {
                            System.out.println("\nChoose the type of purchase" + "\n" +
                                    "1) Food\n" +
                                    "2) Clothes\n" +
                                    "3) Entertainment\n" +
                                    "4) Other");
                            switch (InputHandler.inputMenuNo()) {
                                case 1: {
                                    if (UserService.calcSum(UserService.foodList) == 0) {
                                        System.out.println("\nThe purchase list is empty!");
                                    } else {
                                        System.out.println("\nFood:");
                                        SortService.printSorted(SortService.sortMap(UserService.foodList));
                                    }
                                    break;
                                }
                                case 2: {
                                    if (UserService.calcSum(UserService.clothesList) == 0) {
                                        System.out.println("\nThe purchase list is empty!");
                                    } else {
                                        System.out.println("\nClothes:");
                                        SortService.printSorted(SortService.sortMap(UserService.clothesList));
                                    }
                                    break;
                                }
                                case 3: {
                                    if (UserService.calcSum(UserService.entertainmentList) == 0) {
                                        System.out.println("\nThe purchase list is empty!");
                                    } else {
                                        System.out.println("\nEntertainment:");
                                        SortService.printSorted(SortService.sortMap(UserService.entertainmentList));
                                    }
                                    break;
                                }
                                case 4: {
                                    if (UserService.calcSum(UserService.otherList) == 0) {
                                        System.out.println("\nThe purchase list is empty!");
                                    } else {
                                        System.out.println("\nOther:");
                                        SortService.printSorted(SortService.sortMap(UserService.otherList));
                                    }
                                    break;
                                }
                                default: {
                                    System.out.println("\nYou've entered a wrong number!");
                                    break;
                                }
                            }
                            break;
                        }
                        case 4: {
                            contLoop = false;
                            break;
                        }
                    }
                }
                menu00();
                break;
            }
            case 0: {
                System.out.println("\nBye!");
                break;
            }
            default: {
                System.out.println("Wrong input, please insert one of the numbers mentioned");
                menu00();
                break;
            }
        }
    }
}