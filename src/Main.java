import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] menuChoices = {
                "1. Ägare (visa alla, lägg till ta bort)",
                "2. Anställd (visa alla, lägg till ta bort)",
                "3. Skriv ut sammanställning",
                "0. Avsluta Program"
        };

        for (String menuChoice : menuChoices) {
            System.out.println(menuChoice);
        }

        String restaurantName;
        do {
            System.out.print("Ange restaurangens namn > ");
            restaurantName = scanner.nextLine();
            if (restaurantName.length() <= 10) {
                System.out.println("För kort namn. Prova igen...");
            }
        } while (restaurantName.length() <= 10);

        int totalOwners;
        do {
            System.out.print("Antal ägare? > ");
            totalOwners = scanner.nextInt();
            if (totalOwners <= 0) {
                System.out.println("Det måste finnas minst en ägare...");
            }
        } while (totalOwners <= 0);

        int[] ownersArray = new int[totalOwners];
        if (ownersArray.length == 1) {
            ownersArray[0] = 100;
        } else {
            int i = 0, totalOwnerPercentage = 0;
            do {
                int ownership;
                do {
                    System.out.print("Ange ägare " + (i + 1) + " ägarandel > ");
                    ownership = scanner.nextInt();
                    if (ownership <= 0) {
                        System.out.println("Ägare måste äga något...");
                    }
                } while (ownership <= 0);
                ownersArray[i] = ownership;
                totalOwnerPercentage += ownership;
                i++;

            } while (i < ownersArray.length - 1);
            ownersArray[i] = 100 - totalOwnerPercentage;
        }

        int[] employeesArray = new int[0];
        System.out.println("Välkommen till " + restaurantName + "!");

        int menuChoice;
        do {
            System.out.println("Välj ett av dessa meny-alternativ.");
            for (String menuchoice : menuChoices) {
                System.out.println(menuchoice);

            }
            System.out.print("Ange siffran för menyval > ");
            menuChoice = scanner.nextInt();

            switch (menuChoice) {
                case 1 -> {
                    System.out.println("1. Ägare (visa alla, lägg till, ändra, ta bort)");
                    ownersArray = subChoice(scanner, ownersArray, "ägare");
                }
                case 2 -> {
                    System.out.println("1. Anställd (visa alla, lägg till, ändra, ta bort)");
                    employeesArray = subChoice(scanner, employeesArray, "anställd");
                }
                case 3 -> {
                    System.out.println("Skriv ut sammanställning");
                    printSummary(ownersArray, employeesArray);
                }
                case 0 -> {
                    System.out.println("Programmet avslutas.");
                }
                default -> {
                    System.out.println("Ogiltigt val. försök igen...");
                }
            }
        } while (menuChoice != 0);
    }

    public static int[] subChoice(Scanner scanner, int[] arrayParam, String elementParam) {
        System.out.println("Vad vill du göra med " + elementParam + "?");
        System.out.println("1. Visa alla " + elementParam + ".");
        System.out.println("2. Lägg till en ny " + elementParam + ".");
        System.out.println("3. Ändra en " + elementParam + ".");
        System.out.println("4. Ta bort en " + elementParam + ".");
        System.out.println("0. Gå tillbaka till huvudmenyn.");

        int menuSubChoice = scanner.nextInt();

        switch (menuSubChoice) {
            case 1 -> {
                System.out.println("Visa alla " + elementParam + ":");
                printAll(arrayParam, elementParam);
            }
            case 2 -> {
                System.out.println("Lägg till " + elementParam + ":");
                arrayParam = addNew(scanner,arrayParam, elementParam);
            }
            case 3 -> {
                System.out.println("Ändra en " + elementParam + ":");
                arrayParam = change(scanner, arrayParam, elementParam);
            }
            case 4 -> {
                System.out.println("Ta bort " + elementParam + ":");
                arrayParam = remove(arrayParam, elementParam);
            }
            case 0 -> {
                System.out.println("Gå tillbaka till huvudmenyn.");
                return arrayParam;
            }
            default -> {
                System.out.println("Ogiltigt val. Försök igen...");
            }
        }
        return arrayParam;
    }

    public static int[] change(Scanner scanner, int[] arrayParam, String elementParam) {
        System.out.println("Vilken " + elementParam + " vill du ändra på?");
        printAll(arrayParam, elementParam);
        int inputNumber;
        boolean giveAway = true;
        do {
            System.out.print("Ange siffran på den du vill ändra på > ");
            inputNumber = scanner.nextInt();
            if (inputNumber < 0 || inputNumber >= arrayParam.length) {
                System.out.println("Ogiltigt val. Försök igen...");
            }
        } while (inputNumber < 0 || inputNumber >= arrayParam.length);

        if (elementParam.equals("ägare")) {
            int ownership;
            do {
                System.out.println("Ange ägarens nya ägarandel > ");
                ownership = scanner.nextInt();
                if (ownership <= 0 || ownership >= 100) {
                    System.out.println("Felaktig ägarandel. Det måste vara mer än 0% men mindre än 100%");

                }
            } while (ownership <= 0 || ownership >= 100) ;

                boolean giveaway;
                if (ownership <= arrayParam[inputNumber]) {
                    ownership = arrayParam[inputNumber] - ownership;
                    arrayParam[inputNumber] -= ownership;
                    giveaway = false;
                } else {
                    ownership = ownership - arrayParam[inputNumber];
                    arrayParam[inputNumber] += ownership;
                    giveaway = true;
                }

                int[] tempArray = new int[arrayParam.length - 1];
                int j = 0;
                for (int i = 0; i < arrayParam.length; i++) {
                    if (i != inputNumber) {
                        tempArray[j] = arrayParam[i];
                        j++;
                    }
                }

                tempArray = correctOwnership(scanner, tempArray, ownership, giveaway);
                j = 0;
                for (int i = 0; i < arrayParam.length; i++) {
                    if (i != inputNumber) {
                        arrayParam[i] = tempArray[j];
                        j++;
                    }
                }
            } else {
                int salary = 0;
                do {
                    System.out.println("Ange den anställdes nya timlön >");
                    salary = scanner.nextInt();
                    if (salary <= 0) {
                        System.out.println("Felaktig timlön. Det måste vara mer än 0kr/h");
                    }
                } while (salary <= 0);
                arrayParam[inputNumber] = salary;


            }

            return arrayParam;

        }



        public static void printSummary(int[] ownersArray, int[] employeesArray) {
            int totalOwnership = 0;
            int i = 0;

            while (i < ownersArray.length) {
                System.out.println("Ägare " + (i + 1) + ": " + ownersArray[i] + "%");
                totalOwnership += ownersArray[i];
                i++;
            }
            System.out.println("Total ägande: " + totalOwnership + "%");

            int totalHoursSalaries = 0;
            i = 0;
            while (i < employeesArray.length) {
                System.out.println("Anställd " + (i + 1) + ": " + employeesArray[i] + " kr/h");
                totalHoursSalaries += employeesArray[i];
                i++;

            }
            System.out.println("Total timkonstand anställda: " + totalHoursSalaries + " kr/h");

        }


    public static int[] remove(int[] arrayParam, String elementParam) {
        return arrayParam;
    }

    public static int[] printAll(int[] arrayParam, String elementParam) {
        if (arrayParam.length > 0) {
            String printPrefix;
            if (elementParam.equals("ägare")) {

                printPrefix = "%";

            } else {
                printPrefix = "kr/h";
            }
            for (int i = 0; i < arrayParam.length; i++) {
                System.out.println(elementParam + " " + i + ": " + arrayParam[i] + printPrefix);
            }
        } else {
            System.out.println("Det finns inga " + elementParam + " inlagda...");

        }
        return arrayParam;

    }

    public static int[] addNew(Scanner scanner, int[] arrayParam, String elementParam) {
        //TODO I´m getting owner 0 instead of owner 1 why is that?
        if (elementParam.equals("anställd")) {
            int salary;
            do {
                System.out.println("Ange den anställdes lön > ");
                salary = scanner.nextInt();
                scanner.nextLine();

                if (salary <= 0) {
                    System.out.println("Timlönen måste vara minst 0kr per timme...");
                }
            } while (salary <= 0);

            if (arrayParam.length > 0) {
                int[] newArray = new int[arrayParam.length + 1];
                System.arraycopy(arrayParam, 0, newArray, 0, arrayParam.length);
                newArray[newArray.length - 1] = salary;
                arrayParam = newArray;
            } else {
                arrayParam = new int[]{salary};
            }
        } else {
            int ownership;
            do {
                System.out.println("Ange ägarens ägarandel >");
                ownership = scanner.nextInt();
                if (ownership <= 0 || ownership >= 100) {
                    System.out.println("Felaktig ägarandel. Det måste vara mer än 0 % och mindre än 100%");
                }
            } while (ownership <= 0 || ownership >= 100);
            arrayParam = correctOwnership(scanner, arrayParam, ownership, false);
            int[] newArray = new int[arrayParam.length + 1];

            for (int i = 0; i < arrayParam.length; i++) {
                newArray[i] = arrayParam[i];
            }
                newArray[newArray.length - 1] = ownership;
            arrayParam = newArray;





           }
        return arrayParam;
    }


    public static int[] correctOwnership(Scanner scanner, int[] arrayParam, int wantedOwnership, boolean giveAwayOwnership) {
        String giveOrTake = giveAwayOwnership ? "fördelas ut" : "tas fram";
        String infoGiveOrTake = giveAwayOwnership ? "ge till" : "ta ifrån";

        while (true) {
            System.out.println("Det är " + wantedOwnership + " procentenheter som behöver " + giveOrTake + ".");

            if (giveAwayOwnership) {
                System.out.println("Vilken ägare vill du ge ägarandelar till?");
            } else {
                System.out.println("Vilken ägare vill du ta ägarandelar av?");
            }

            for (int i = 0; i < arrayParam.length; i++) {
                System.out.println("Ägare " + (i + 1) + ": " + arrayParam[i] + "%");
            }
            int ownerIndex;
            do {
                System.out.print("Ange siffran för vilken ägare du vill " + infoGiveOrTake + " > ");
                ownerIndex = scanner.nextInt();

                if (ownerIndex < 0 || ownerIndex >= arrayParam.length) {
                    System.out.println("Felaktigt val. Prova igen...");
                } else if (!giveAwayOwnership && arrayParam[ownerIndex] == 1) {
                    System.out.println("Ägare " + ownerIndex + " har bara 1% kvar. Du kan inte ta bort sista...");
                } else {
                    break;
                }
            } while (true);

            int correctOwnership;
            do {
                System.out.print("Hur många procentenheter vill du " + giveOrTake + " ägare " + ownerIndex + "? >");
                correctOwnership = scanner.nextInt();

                if (correctOwnership > wantedOwnership) {
                    System.out.println("Du kan inte ta mer än " + wantedOwnership + "...");
                } else if (!giveAwayOwnership && correctOwnership >= wantedOwnership) {
                    System.out.println("Du kan endast ta 1-" + wantedOwnership + " procentenheter från ägaren...");
                } else {
                    break;
                }
            } while (true);

            if (giveAwayOwnership) {
                arrayParam[ownerIndex] += correctOwnership;
            } else {
                arrayParam[ownerIndex] -= correctOwnership;
            }

            wantedOwnership -= correctOwnership;

            if (wantedOwnership == 0) {
                return arrayParam;
            }
        }
    }
}