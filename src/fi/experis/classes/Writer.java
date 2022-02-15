package fi.experis.classes;

import fi.experis.enumerators.HeroClass;
import fi.experis.enumerators.Slot;

import java.text.DecimalFormat;
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
        StringBuilder str = new StringBuilder();
        DecimalFormat f = new DecimalFormat(".00");

        str.append("Name: ")
            .append(hero.getName())
                .append("\nLevel: ")
                    .append(hero.getLevel())
                        .append("\nAttributes")
                            .append("\nStrength: ")
                                .append(hero.getTotalPrimaryAttributes().getStrength())
                                    .append("\nDexterity: ")
                                        .append(hero.getTotalPrimaryAttributes().getDexterity())
                                            .append("\nIntelligence: ")
                                                .append(hero.getTotalPrimaryAttributes().getIntelligence())
                                                    .append("\nEquipment");
        hero.getEquipment().forEach((key, value) -> {
            if (key == Slot.Weapon) {
                str.append("\nWeapon: ")
                    .append(value.getName());
            } else {
                str.append("\n")
                    .append(key.name())
                        .append(": ")
                            .append(value.getName());
            }

        });
        str.append("\nDamage");
        hero.getEquipment().forEach((key, value) -> {
            if (key == Slot.Weapon) {
                Weapon currentWeapon = (Weapon) value;
                HeroClass heroClass = hero.getHeroClass();
                double modifier = 0;
                switch (heroClass) {
                    case Warrior -> modifier = hero.getTotalPrimaryAttributes().getStrength();
                    case Rogue, Ranger -> modifier = hero.getTotalPrimaryAttributes().getDexterity();
                    case Mage -> modifier = hero.getTotalPrimaryAttributes().getIntelligence();
                }
                double dps = currentWeapon.getDamage() * currentWeapon.getSpeed() * (1 + modifier / 100);
                str.append("\nDPS: ")
                    .append(f.format(dps));
            }
        });

        System.out.println(str);
    }

}
