package fi.experis;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class Weapon extends Item {
    private double damage;
    private double speed;
    private WeaponType weaponType;

    public Weapon(String name, Slot slot, int level, double damage, double speed, WeaponType weaponType) {
        super(name, slot, level);
        this.damage = damage;
        this.speed = speed;
        this.weaponType = weaponType;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "damage=" + damage +
                ", speed=" + speed +
                ", weaponType=" + weaponType +
                '}';
    }

    public static Weapon createItem() {
        List<WeaponType> weaponTypes = List.of(WeaponType.values());
        Slot slot = Slot.Weapon;
        int sizeWeaponTypes = weaponTypes.size();
        Random randomWeapon = new Random();
        int level = (int) (Math.random() * 60);
        double damage = (Math.random() * level * 2.8);
        double speed = 0.1 + (Math.random());
        WeaponType weaponType = weaponTypes.get(randomWeapon.nextInt(sizeWeaponTypes));
        String name = generateName(weaponType, level);

        return new Weapon(name, slot, level, damage, speed, weaponType);
    }

    public static String generateName(WeaponType type, int level) {
        String quality = Item.getString(level);
        String weaponName;

        switch (type) {
            case Axes -> weaponName = "Axe";
            case Bows -> weaponName = "Bow";
            case Wands -> weaponName = "Wand";
            case Staffs -> weaponName = "Staff";
            case Swords -> weaponName = "Sword";
            case Daggers -> weaponName = "Dagger";
            case Hammers -> weaponName = "Hammer";
            default -> weaponName = "";
        }


        return quality + " " + weaponName;
    }

}
