package fi.experis;

import fi.experis.classes.Hero;
import fi.experis.classes.PrimaryAttributes;
import fi.experis.classes.Writer;
import fi.experis.enumerators.HeroClass;
import fi.experis.exceptions.InvalidCustomException;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Hero hero = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (Writer.promptCharacterCreation()) {
            createCharacter();
        }
        if (hero != null) {
            playTheGame();
        }
    }

    public static void createCharacter() {
        PrimaryAttributes primaryAttributes;
        int level = 1;
        System.out.println("Enter character name: ");
        String name = scanner.nextLine();
        HeroClass heroClass = chooseClass();

        switch (Objects.requireNonNull(heroClass)) {
            case Mage -> primaryAttributes = new PrimaryAttributes(1, 1, 8);
            case Ranger -> primaryAttributes = new PrimaryAttributes(1, 7, 1);
            case Rogue -> primaryAttributes = new PrimaryAttributes(2, 6, 1);
            case Warrior -> primaryAttributes = new PrimaryAttributes(5, 2, 1);
            default -> throw new IllegalStateException("Unexpected value: " + heroClass);
        }
        hero = new Hero(name, level, primaryAttributes, heroClass);
    }

    public static HeroClass chooseClass() {
        int input = 0;

        do {
            try {
                System.out.println("Choose class: ");
                System.out.println("1. Mage");
                System.out.println("2. Ranger");
                System.out.println("3. Rogue");
                System.out.println("4. Warrior");
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry try again");
            }
            scanner.nextLine();
        } while (input != 1 && input != 2 && input != 3 && input != 4);

        switch (input) {
            case 1 -> {
                return HeroClass.Mage;
            }
            case 2 -> {
                return HeroClass.Ranger;
            }
            case 3 -> {
                return HeroClass.Rogue;
            }
            case 4 -> {
                return HeroClass.Warrior;
            }
            default -> throw new IllegalStateException("Unexpected value: " + input);
        }

    }

    // This is the main method that loops while playing the game
    public static void playTheGame() {
        int input = 0;
        do {
            try {
                System.out.println("What would you like to do: ");
                System.out.println("1. Level up your character");
                System.out.println("2. Find new random item");
                System.out.println("3. Inspect character");
                System.out.println("4. Quit the Game");
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry try again");
            }
            scanner.nextLine();
        } while (input != 1 && input != 2 && input != 3 && input != 4);

        switch (input) {
            case 1 -> {
                hero.getXp();
                System.out.println("Congratulations you have reached level " + hero.getLevel() + "!");
                Writer.resume();
                playTheGame();
            }
            case 2 -> {
                try {
                    hero.getItems();
                } catch (InvalidCustomException e) {
                    System.out.println(e.getMessage());
                }
                Writer.resume();
                playTheGame();
            }
            case 3 -> {
                hero.inspect();
                Writer.resume();
                playTheGame();
            }
            case 4 -> {
            }
            default -> playTheGame();
        }
    }

}
