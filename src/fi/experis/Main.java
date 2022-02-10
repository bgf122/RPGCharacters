package fi.experis;

import java.util.Scanner;

public class Main {
    static Character character = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (promptCharacterCreation()) {
            createCharacter();
        }
        if (character != null) {
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
        System.out.println("Enter character name: ");
        String name = scanner.nextLine();
        String heroClass = chooseClass();
        int level = 1;

        switch (heroClass) {
            case "Mage" -> primaryAttributes = new PrimaryAttributes(1, 1, 8);
            case "Ranger" -> primaryAttributes = new PrimaryAttributes(1, 7, 1);
            case "Rogue" -> primaryAttributes = new PrimaryAttributes(2, 6, 1);
            case "Warrior" -> primaryAttributes = new PrimaryAttributes(5, 2, 1);
            default -> throw new IllegalStateException("Unexpected value: " + heroClass);
        }

        character = new Character(name, level, heroClass, primaryAttributes);
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
            case 1 -> getMoreXp();
            case 2 -> getNewItems();
            case 3 -> inspect();
            case 0 -> {
            }
            default -> playTheGame();
        }
    }

    public static void getMoreXp() {
        PrimaryAttributes primaryAttributes = character.getBasePrimaryAttributes();
        String heroClass = character.getHeroClass();
        levelUp(primaryAttributes, heroClass);
        character.setLevel(character.getLevel() + 1);
        System.out.println("Congratulations you have reached level " + character.getLevel() + "!");
        resume();
    }

    public static void getNewItems() {
        System.out.println("You have found some new item!");
        resume();
    }

    public static void inspect() {
        System.out.println(character);
        resume();
    }

    public static void resume() {
        System.out.println("Press enter to continue...");
        String selection = scanner.nextLine();
        if (selection != null) {
            playTheGame();
        }
    }
    
    public static void levelUp(PrimaryAttributes attributes, String heroClass) {
        PrimaryAttributes newBaseAttributes;
        switch (heroClass) {
            case "Mage" -> newBaseAttributes = new PrimaryAttributes(attributes.Strength+1, attributes.Dexterity+1, attributes.Intelligence+5);
            case "Ranger" -> newBaseAttributes = new PrimaryAttributes(attributes.Strength+1, attributes.Dexterity+5, attributes.Intelligence+1);
            case "Rogue" -> newBaseAttributes = new PrimaryAttributes(attributes.Strength+1, attributes.Dexterity+4, attributes.Intelligence+1);
            case "Warrior" -> newBaseAttributes = new PrimaryAttributes(attributes.Strength+3, attributes.Dexterity+2, attributes.Intelligence+5);
            default -> throw new IllegalStateException("Unexpected value: " + heroClass);
        }
        character.setBasePrimaryAttributes(newBaseAttributes);
    }
 
}
