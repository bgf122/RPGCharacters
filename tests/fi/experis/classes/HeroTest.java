package fi.experis.classes;

import fi.experis.enumerators.ArmorType;
import fi.experis.enumerators.HeroClass;
import fi.experis.enumerators.Slot;
import fi.experis.enumerators.WeaponType;
import fi.experis.exceptions.InvalidArmorException;
import fi.experis.exceptions.InvalidWeaponException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    Hero hero;
    double damage;
    double speed;
    Weapon noWeapon;
    Weapon weapon;
    Armor armor;
    float primaryStat;

    @BeforeEach()
    public void setup() {
        hero = new Hero("Warrior", 1, new PrimaryAttributes(5, 2, 1), HeroClass.Warrior);
        noWeapon = (Weapon) hero.getEquipment().get(Slot.Weapon);
        primaryStat = hero.getTotalPrimaryAttributes().getStrength();
        weapon = new Weapon("Common axe", 1,7, 1.1, WeaponType.Axes);
        armor = new Armor("Common Plate Body Armor", Slot.Body, 1, new PrimaryAttributes(1, 0, 0), ArmorType.Plate);
    }

    @Test
    public void calculate_dpsWhenNoWeapon_shouldPass() {
        damage = noWeapon.getDamage();
        speed = noWeapon.getSpeed();
        double actual = damage * speed * (1 + primaryStat / 100);
        double expected = 1 * ( 1 +((float)5/100));
        assertEquals(actual, expected);
    }

    @Test
    public void calculate_dpsWhenValidWeapon_shouldPass() throws InvalidArmorException, InvalidWeaponException {
        hero.equipItem(weapon);
        Weapon equippedWeapon = (Weapon) hero.getEquipment().get(Slot.Weapon);
        damage = equippedWeapon.getDamage();
        speed = equippedWeapon.getSpeed();
        double actual = damage * speed * (1 + primaryStat / 100);
        double expected = (7 * 1.1) * ( 1 +((float)5/100));
        assertEquals(actual, expected);
    }

    @Test
    public void calculate_dpsWhenWeaponAndArmor_shouldPass() throws InvalidArmorException, InvalidWeaponException {
        hero.equipItem(weapon);
        hero.equipItem(armor);
        Weapon equippedWeapon = (Weapon) hero.getEquipment().get(Slot.Weapon);
        Armor equippedArmor = (Armor) hero.getEquipment().get(Slot.Body);
        PrimaryAttributes primaryAttributes = equippedArmor.getPrimaryAttributes();
        primaryStat += primaryAttributes.getStrength();
        damage = equippedWeapon.getDamage();
        speed = equippedWeapon.getSpeed();

        double actual = damage * speed * (1 + primaryStat / 100);
        double expected = (7 * 1.1) * ( 1 +((float)(5+1)/100));
        assertEquals(actual, expected);
    }

}