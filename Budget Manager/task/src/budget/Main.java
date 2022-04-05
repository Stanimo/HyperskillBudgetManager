package budget;

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
                UserService.currentBalance();
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