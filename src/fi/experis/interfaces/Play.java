package fi.experis.interfaces;

import fi.experis.classes.Item;
import fi.experis.exceptions.InvalidArmorException;
import fi.experis.exceptions.InvalidWeaponException;

public interface Play {
    void getXp();

    void getItems() throws InvalidArmorException, InvalidWeaponException;

    void inspect();

    void levelUp();

}
