package fi.experis;

import java.util.Scanner;

public class Main {
    static Hero hero = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (promptCharacterCreation()) {
            createCharacter();
        }
        if (hero != null) {
            playTheGame();
        }
    }

    public static boolean promptCharacterCreation() {
        System.out.println("No character found, would you like to create a new one?(press 1 or 2)");
        System.out.println("1. Yes");
        System.out.println("2. No");
        String input = scanner.nextLine();

        switch (Integer.parseInt(input)) {
            case 1 -> {
                return true;
            }
            case 2 -> {
                return false;
            }
            default -> {
                promptCharacterCreation();
                return false;
            }
        }

    }

    public static void createCharacter() {
        PrimaryAttributes primaryAttributes;
        int level = 1;
        System.out.println("Enter character name: ");
        String name = scanner.nextLine();
        String heroClass = chooseClass();

        switch (heroClass) {
            case "Mage" -> primaryAttributes = new PrimaryAttributes(1, 1, 8);
            case "Ranger" -> primaryAttributes = new PrimaryAttributes(1, 7, 1);
            case "Rogue" -> primaryAttributes = new PrimaryAttributes(2, 6, 1);
            case "Warrior" -> primaryAttributes = new PrimaryAttributes(5, 2, 1);
            default -> throw new IllegalStateException("Unexpected value: " + heroClass);
        }

        hero = new Hero(name, level, primaryAttributes, heroClass);

    }

    public static String chooseClass() {
        System.out.println("Choose class: ");
        System.out.println("1. Mage");
        System.out.println("2. Ranger");
        System.out.println("3. Rogue");
        System.out.println("4. Warrior");
        String selection = scanner.nextLine();

        switch (Integer.parseInt(selection)) {
            case 1 -> {
                return "Mage";
            }
            case 2 -> {
                return "Ranger";
            }
            case 3 -> {
                return "Rogue";
            }
            case 4 -> {
                return "Warrior";
            }
            default -> chooseClass();
        }
        return "Invalid";
    }

    public static void playTheGame() {
        System.out.println("What would you like to do: ");
        System.out.println("1. XP");
        System.out.println("2. Loot");
        System.out.println("3. Inspect character");
        System.out.println("0. Quit the Game");
        String selection = scanner.nextLine();

        switch (Integer.parseInt(selection)) {
            case 1 -> {
                hero.getXp();
                System.out.println("Congratulations you have reached level " + hero.getLevel() + "!");
                resume();
            }
            case 2 -> {
                hero.getItems();
                resume();
            }
            case 3 -> {
                hero.inspect();
                resume();
            }
            case 0 -> {
            }
            default -> playTheGame();
        }
    }

    public static void resume() {
        System.out.println("Press enter to continue...");
        scanner.nextLine();
        playTheGame();
    }
 
}
