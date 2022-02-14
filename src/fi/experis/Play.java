package fi.experis;

public interface Play {
    void getXp();

    void getItems() throws InvalidWeaponException, InvalidArmorException;

    void inspect();

    void levelUp();
}
