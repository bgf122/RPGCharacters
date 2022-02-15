package fi.experis.classes;


import fi.experis.enumerators.ArmorType;
import fi.experis.enumerators.HeroClass;
import fi.experis.enumerators.Slot;
import fi.experis.exceptions.InvalidArmorException;
import fi.experis.exceptions.InvalidWeaponException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTest {
    Armor testArmor;
    Armor testClothHead;
    Hero hero;


    @BeforeEach()
    public void setup() {
        testArmor = new Armor();
        testArmor.setName("Common Plate Body Armor");
        testArmor.setLevel(1);
        testArmor.setSlot(Slot.Body);
        testArmor.setArmorType(ArmorType.Plate);
        testArmor.setPrimaryAttributes(new PrimaryAttributes(1, 0, 0));

        testClothHead = new Armor();
        testClothHead.setName("Common Cloth Head Armor");
        testClothHead.setLevel(1);
        testClothHead.setSlot(Slot.Head);
        testClothHead.setArmorType(ArmorType.Cloth);
        testClothHead.setPrimaryAttributes(new PrimaryAttributes(0,0,5));

        hero = new Hero("Warrior", 1, new PrimaryAttributes(5, 2, 1), HeroClass.Warrior);
    }

    @Test
    public void equip_tooHighArmor_shouldThrowError() throws InvalidArmorException, InvalidWeaponException {
        testArmor.setLevel(2);
        hero.equipItem(testArmor);
    }

    @Test
    public void equip_wrongTypeArmor_shouldThrowError() throws InvalidArmorException, InvalidWeaponException {
        hero.equipItem(testClothHead);
    }

    @Test
    public void equip_validArmor_shouldPass() {
        assertEquals(hero.isValid(testArmor, hero.getHeroClass(), hero.getLevel()), true);
    }

}