package fi.experis.classes;

import fi.experis.enumerators.HeroClass;
import fi.experis.enumerators.Slot;
import fi.experis.enumerators.WeaponType;
import fi.experis.exceptions.InvalidArmorException;
import fi.experis.exceptions.InvalidWeaponException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {
    Weapon testWeapon;
    Weapon testBow;
    Hero hero;

    @BeforeEach
    public void setup() {
        testWeapon = new Weapon();
        testWeapon.setName("Common axe");
        testWeapon.setLevel(1);
        testWeapon.setSlot(Slot.Weapon);
        testWeapon.setWeaponType(WeaponType.Axes);
        testWeapon.setDamage(7);
        testWeapon.setSpeed(1.1);

        testBow = new Weapon();
        testBow.setName("Common Bow");
        testBow.setLevel(1);
        testBow.setSlot(Slot.Weapon);
        testBow.setWeaponType(WeaponType.Bows);
        testBow.setDamage(12);
        testBow.setSpeed(0.8);

        hero = new Hero("Warrior", 1, new PrimaryAttributes(5, 2, 1), HeroClass.Warrior);
    }

    @Test
    public void equip_tooHighWeapon_shouldThrowError() throws InvalidWeaponException, InvalidArmorException {
        testWeapon.setLevel(2);
        hero.equipItem(testWeapon);
    }

    @Test
    public void equip_wrongTypeWeapon_shouldThrowError() throws InvalidWeaponException, InvalidArmorException {
        hero.equipItem(testBow);
    }

    @Test
    public void equip_validWeapon_shouldPass() {
        assertEquals(hero.isValid(testWeapon, hero.getHeroClass(), hero.getLevel()), true);
    }

}