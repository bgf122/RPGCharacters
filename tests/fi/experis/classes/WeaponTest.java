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
    public void equip_tooHighWeapon_shouldThrowError() {
        testWeapon.setLevel(2);
        InvalidWeaponException exception = assertThrows(InvalidWeaponException.class, () -> {
            hero.equipItem(testWeapon);
        });

        String expectedMessage = "Wrong type of equipment or too high level";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void equip_wrongTypeWeapon_shouldThrowError() {
        InvalidWeaponException exception = assertThrows(InvalidWeaponException.class, () -> {
            hero.equipItem(testBow);
        });

        String expectedMessage = "Wrong type of equipment or too high level";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void equip_validWeapon_shouldPass() {
        assertTrue(hero.isValid(testWeapon, hero.getHeroClass(), hero.getLevel()));
    }

}