package fi.experis;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Writer {
    static Scanner scanner = new Scanner(System.in);

    public static boolean promptCharacterCreation() {
        int input = 0;
        do {
            try {
                System.out.println("No character found, would you like to create a new one?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry try again");
            }
            scanner.nextLine();
        } while (input != 1 && input != 2);

        switch (input) {
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
    public static void resume() {
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }
    public static void armorWriter(Armor armor) {
        System.out.println(armor.getName());
        System.out.println(armor.getLevel());
        System.out.println("Strength: " + armor.getPrimaryAttributes().getStrength());
        System.out.println("Dexterity: " + armor.getPrimaryAttributes().getDexterity());
        System.out.println("Intelligence: " + armor.getPrimaryAttributes().getIntelligence());
        System.out.println();




    }
    public static void weaponWriter(Weapon weapon) {
        System.out.println(weapon.getName());
        System.out.println(weapon.getLevel());
        System.out.println("Damage: " + weapon.getDamage());
        System.out.println("Speed: " + weapon.getSpeed());
        System.out.println("DPS: " + weapon.getDamage() * weapon.getSpeed());

    }
    public static boolean promptEquip() {
        int input = 0;
        do {
            try {
                System.out.println("Do you want to equip the item?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry try again");
            }
            scanner.nextLine();
        } while (input != 1 && input != 2);

        switch (input) {
            case 1 -> {
                return true;
            }
            case 2 -> {
                return false;
            } default -> promptEquip();
        }
        return false;
    }
    public static void inspect(Hero hero) {
        System.out.println("Name: " + hero.getName());
        System.out.println("Level: " + hero.getLevel());
        System.out.println("Attributes");
        System.out.println("Strength: " + hero.getBasePrimaryAttributes().getStrength());
        System.out.println("Dexterity: " + hero.getBasePrimaryAttributes().getDexterity());
        System.out.println("Intelligence: " + hero.getBasePrimaryAttributes().getIntelligence());
        System.out.println("Equipment: " + hero.getEquipment());
    }



}
