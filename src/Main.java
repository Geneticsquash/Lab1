import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String[] menuChoices = {
                "1. Ägare (visa alla, lägg till ta bort)  ",
                "2. Anställd (visa alla, lägg till ta bort)",
                "3. Skriv ut sammanställning",
                "0. Avsluta Program"};
        for (String menuChoice : menuChoices) {
            System.out.println(menuChoice);

        }

        // Do, If, While, Hade ej testat det förut 12.09.2024. 09.20,
        // Uppdaterade koden 10.06. 17.30 (Ändrade utifrån flödesdiagramet. Gjorde det inte ordentligt från början. Tex Skrev restaurant på svenska

        String restaurantName;

        do {
            System.out.print("Ange restaurangens namn > ");
            restaurantName = scanner.nextLine();

            if (restaurantName.length() <= 10) {
                System.out.println("För kort namn. Prova igen.");
            }
        } while (restaurantName.length() <= 10);
        System.out.println("Restaurangens namn är längre än 10 tecken.");





        int totalOwners;

        do {
            System.out.print("Antal ägare? > ");
            totalOwners = scanner.nextInt();

            if (totalOwners <= 0) {
                System.out.println("Det måste finnas minst en ägare...");
            }
        } while (totalOwners <= 0);


        //
        int[] ownersArray = new int[totalOwners];


        if (ownersArray.length == 1) {
            ownersArray[0] = 100;
            System.out.println("Enda ägaren har fått värdet: " + ownersArray[0]);

        } else {
            int i=0, totalOwnerPercentage = 0;

            do {
                int ownership;
                do {
                    System.out.print("Ange ägare " + (i + 1) + " ägarandel > ");
                    ownership = scanner.nextInt();

                    if (ownership <= 0) {
                        System.out.println("Ägare måste äga något.");
                    }
                } while (ownership <= 0);

                ownersArray[i] = ownership;
                totalOwnerPercentage += ownership;
                System.out.println("Total ägarandel hittils " + totalOwnerPercentage + "%");
                i++;

            } while (i < ownersArray.length - 1);

            ownersArray[i] = 100 - totalOwnerPercentage;


        }

        int[] employeesArray;
        System.out.println("Välkommen till " + restaurantName + "!");


        // Jag antar man ska utveckla koden i varje case enligt PDF 1 (Fyrkanten "In: menuChoice" 17.09.2024
        // Jag försökte förstå menyuppgiften i första PDF, men det var först när jag gick igenom alla åtta som jag insåg designen. 18.09.2024."
        // Fick göra om hela menyn pga PDF "Undermeny"  10.01. Äntligen fungerar det...

        int menuChoice;
        do {
            System.out.println(" Välj ett av dessa meny-alternativ.");

            // För varje choice i menuChoice[]  10.06. 17:30
            for (String menuchoice : menuChoices) {
                System.out.println(menuchoice); // choice? Felskrivet i pdf?
            }

            System.out.print("Ange siffran för menyval > ");
            menuChoice = scanner.nextInt();


            switch (menuChoice) {
                case 1 -> {
                    System.out.println("1. Ägare (visa alla, lägg till, ändra, ta bort)");
                    subChoice(ownersArray, "ägare");
                    ownersArray = subChoice(ownersArray, "ägare");

                }
                case 2 -> {
                    System.out.println("1. Ägare (visa alla, lägg till, ändra, ta bort)");
                    subChoice(employeesArray, "anställd");
                    employeesArray = subChoice(employeesArray, "anställd");
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

    public static int[] subChoice(int[] arrayParam, String elementParam) {
        Scanner scanner = new Scanner(System.in);
        int ownerChoice = scanner.nextInt();
        // Fick ändra subChoice 10.06
        System.out.println("Vad vill du göra med " + elementParam +  "?" +
                "1. Visa alla " + elementParam + "." + "2. lägg till en ny " + elementParam + "." + "3. Ändra en " + elementParam + "." + "4. Ta bort en" + elementParam + "." + "0. Gå tillbaka till huvudmenyn " + elementParam + ".");


        switch (ownerChoice) {
            case 1 -> {
                System.out.println("Visa alla ägare:");
                printAll(arrayParam, elementParam);
            }

            case 2 -> {
                System.out.println("2. Lägg till ägare:");
                arrayParam = addNew(arrayParam, elementParam);

            }
            case 3 -> {
                System.out.println("3. Ändra en ägare:");
                arrayParam = change(arrayParam, elementParam);

            }
            case 4 -> {
                System.out.println("4. Ta bort ägare:");
                arrayParam = remove(arrayParam, elementParam);
            }
            case 0 -> {
                System.out.println("Gå tillbaka till huvudmeny.");
                return arrayParam;
            }
            default -> {
                System.out.println("Ogiltigt val. Färsök igen...");
            }
        }
        return arrayParam;
    }


    // Kodat fel // Ska ej vara do, while 10.06 Troligen vara en while-loop
    public static void printSummary(int[] ownersArray, int[] employeesArray) {
        Scanner scanner = new Scanner(System.in);
        int totalOwnership = 0;
        int i = 0;

        do {
            System.out.println("Ägare " + (i+1) + ": " + ownersArray[i] + "%"); {
                totalOwnership += ownersArray[i];
                i++;
            } while (i < ownersArray.length);

            System.out.println("total ägande " + totalOwnership + "%");
            int totalHoursSalaries = 0;
            i = 0;
            do {
                System.out.println("Anställd" + i+1 + ": " + employeesArray[i] + "kr/h"); {
                    totalHoursSalaries += employeesArray[i];
                    i++;

                } while (i < employeesArray.length);
                System.out.println("Total timkostnad anställda " + totalHoursSalaries + "kr/h");


            }

        }
    }           // Kodat fel 10.06
    private static int[] remove(int[] arrayParam, String elementParam) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < arrayParam.length; i++)
            if (arrayParam.length > 1 && elementParam.equals("ägare")) {
                System.out.println("Du kan inte ta bort ägaren i företaget...");
                return arrayParam;
            } else {
                System.out.println("vilken " + elementParam + " vill du ta bort?");
            }
        printAll(arrayParam, elementParam);
        int inputNumber = -1;

        do {
            System.out.println("Ange siffran på den" + elementParam + "du vill ta bort >");
            inputNumber = scanner.nextLine();
            inputNumber--;
            if (inputNumber >= 0 && inputNumber < arrayParam.length) {
                System.out.println("Ange en giltig siffra...");

            } while (inputNumber < 0 || inputNumber >= arrayParam.length);

            // Skapa en newArray som är ett element mindre än arrayParan
            int[] newArray = new int[arrayParam.length - 1];

            // Lägg in alla element från arraypram till newArray i samma ordning och samma index förutom newArray input number.

            (inputNumber >= 0 && inputNumber < arrayParam.length) = newArray; // ¯\_(ツ)_/¯

            do {
                elementParam = "ägare";
            }
            if (correctOwnership(newArray, arrayParam[inputNumber], true);
            return correctOwnership(newArray, arrayParam[inputNumber], false); {

            } while (?); // newParam har aldrig deklarerat i denna klass  ¯\_(ツ)_/¯
            return newArray; // (newParam)???



        }
    }

    // Tror jag kodat rätt  10.06
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
            System.out.println("Det finns inga" + elementParam + "inlagda...");
        }
        return arrayParam;
    }



    // 10-01  // Kodat ok 10.06
    public static int[] addNew(int[] arrayParam, String elementParam) {
        Scanner scanner = new Scanner(System.in);

        if (elementParam == ("anställd")) { //Connect
            // Början av Salary
            int salary = 0;
            do {
                System.out.println("Ange den anställdes lön > ");
                salary = scanner.nextInt();
                scanner.nextLine();


                if (salary > 0) {
                    System.out.println("Timlönen måste vara minst 0kr per timme...");
                }
            } while (salary <= 0);


            // Har problem med den här...
            if (arrayParam.length > 0) { // Skapa en ny newArray som ä stärre än arrayParam
                int[] newArray = new int[arrayParam.length + 1];
                // Lägg till alla element
                System.arraycopy(arrayParam, 0, newArray, 0, arrayParam.length);
                newArray[newArray.length - 1] = salary;
                arrayParam = newArray;

            } else {

                int[] newArray = new int[1];
                newArray[0] = salary;
                arrayParam = newArray;


            }
            return arrayParam;


        } else { // Början av ownership // Connect
            int ownership = 0;

            do {
                System.out.println("Ange ägarens ägarandel >");
                ownership = scanner.nextInt();
                if  (ownership > 0 && ownership < 100) {
                    System.out.println("Felaktig ägarandel. Det måste vara mer än 0 % och mindre än 100%");
                } while (ownership <= 0 || ownership >= 100);
                // arrayParam = correctOwnership

                correctOwnership(arrayParam, false);

                arrayParam = correctOwnership();

                // Skapa en ny newArray som är ett element större äm arrayParam
                int[] newArray = new int[arrayParam.length + 1];

                // Lägg till alla element
                System.arraycopy(arrayParam, 0, newArray, 0, arrayParam.length);
                newArray[newArray.length - 1] = ownership;
                arrayParam = newArray;



            }


        }
        return arrayParam;
    }



    public static int[] change(int[] arrayParam, String elementParam) {

        System.out.println("Vilken " + elementParam + " vill du ändra på?");
        Scanner scanner = new Scanner(System.in);
        printAll(arrayParam, elementParam);
        int inputNumber = 0;
        boolean giveaway = true;
        do {
            System.out.println("Ange siffran på den du vill ändra på > ");
            inputNumber = scanner.nextInt();
            giveaway = false;
            int i = inputNumber--;

            if (inputNumber >= 0 && inputNumber < arrayParam.length) {
                System.out.println("Ange en giltig siffra");
            }

        } while (inputNumber >= 0 && inputNumber < arrayParam.length);

        if (elementParam.equals("anställd")) { //Connector
            int ownership = 0;
            while (ownership <= 100) {
                System.out.println("Ange ägarens nya ägarandel >");
                ownership = scanner.nextInt();
                if (ownership <= 0) {
                    System.out.println("Felaktig ägarandel. Det måste vara mer än 0 % och mindre än 100%...");

                    if (ownership <= arrayParam[inputNumber]) {

                        ownership -= arrayParam[inputNumber];
                        arrayParam[inputNumber] += ownership;
                        giveaway = false;

                    } else {
                        ownership = (arrayParam[inputNumber] - ownership); // 10.04
                        arrayParam[inputNumber] -= ownership;
                        giveaway = true;

                        // B
                        int tempArray = arrayParam.length - 1;
                        int i = 0, j = 0;
                        while (i != inputNumber) {
                            if (arrayParam[i] == arrayParam[j]) {
                                j++;
                            }
                            i++;

                        }

                        tempArray = correctOwnership(tempArray, ownership, giveaway); // wht?
                        while (i != inputNumber) {
                        }


                    }

                }


            }

        } else {
            int salary = 0;
            while (salary >= 0) {
                System.out.println("Ange den anställdes nya timlön");
                salary = scanner.nextInt();
                if (salary <= 0) {
                    System.out.println("felaktig");

                    arrayParam[inputNumber] = salary;

                }
            }

            return arrayParam;
        }
    }

    // Extremt felkodat 10.06
    public static int[] correctOwnership(int[] arrayParam, int wantedOwner, boolean giveAwayOwnership) {
        //Jag får ej till den här koden 10-03
        Scanner scanner = new Scanner(System.in);
        int ownerIndex = 0; // PlaceHolder
        String wantedOwnership; // placeHolder
        String giveOrTake = giveAwayOwnership ? "fördelas ut" : "tas fram";
        String infoGiveOrTake = giveAwayOwnership ? "ge till" : "ta ifrån";
        //  wantedOwnership Ska göra en loop här
        while (wantedOwnership != 0) {
            System.out.println("Det är " + wantedOwnership + " procentenheter som behöver " + giveOrTake + ".");

            if (giveAwayOwnership == true) {
                System.out.println("Vilken ägare vill du ge ägarandel till?");
            } else {
                System.out.println("Vilken ägare vill du ta ägarandel till?");
                for (int i = 0; i < arrayParam.length; i++) {
                }

            }
            int i = 0;
            // Ej är korrekt, behöver hjälp med den här koden! 10-06. Kanske det är två do-while man ska göra
            do { // två loops går tillbaka
                System.out.println("Ange siffran för vilken ägare du vill " + infoGiveOrTake + ">");
                ownerIndex = scanner.nextInt();

                if (arrayParam[ownerIndex - 1] == 1 && !giveAwayOwnership) {
                    System.out.println("Ägare " + ownerIndex + " har bara 1% kvar. Du kan inte ta bort sista...");

                } else if (ownerIndex <= 0 || ownerIndex > arrayParam.length) {
                    System.out.println("felaktigt val. Prova igen...");
                } else {
                    // Kanske det är två do-while man ska göra här också 10.06
                    int correctOwnership = 0;
                    System.out.println("Hur många procentenheter vill du " + giveOrTake + "ägare " + ownerIndex + "?");
                    correctOwnership = scanner.nextInt();


                    if (correctOwnership > wantedOwnership) {
                        System.out.println("Du kan inte ta mer än " + wantedOwnership + "...");

                    } else if (correctOwnership < arrayParam[ownerIndex - 1]) {
                        System.out.println("Du kan endast ta 1-" + wantedOwnership + " procentenheter från ägaren...");
                    }
                    if (i >= 0 && i <= wantedOwnership && correctOwnership < arrayParam[ownerIndex]) ;
                    {

                        ownerIndex--;
                    }
                    if (giveAwayOwnership) {
                        arrayParam[ownerIndex] += correctOwnership;
                    } else {
                        arrayParam[ownerIndex] -= correctOwnership;
                    }

                    wantedOwnership -= correctOwnership;

                }


                return arrayParam;

            }
        }


    }
}
