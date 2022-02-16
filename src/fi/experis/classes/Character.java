package fi.experis.classes;

import fi.experis.interfaces.Play;
import fi.experis.enumerators.Slot;

import java.util.HashMap;

public abstract class Character implements Play {
    private String name;
    private int level;
    private PrimaryAttributes basePrimaryAttributes;
    private PrimaryAttributes totalPrimaryAttributes;
    private final HashMap<Slot, Item> equipment = new HashMap<>();

    public Character(String name, int level, PrimaryAttributes basePrimaryAttributes) {
        this.name = name;
        this.level = level;
        this.basePrimaryAttributes = basePrimaryAttributes;
        this.totalPrimaryAttributes = basePrimaryAttributes;
        Weapon weapon = new Weapon("Unarmed", 1, 1, 1);
        equipment.put(Slot.Weapon, weapon);
    }

    public Character() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Slot, Item> getEquipment() {
        return equipment;
    }

    public void setEquipment(Slot slot, Armor armor) {
        equipment.put(slot, armor);
    }

    public void setEquipment(Weapon weapon) {
        equipment.put(Slot.Weapon, weapon);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public PrimaryAttributes getBasePrimaryAttributes() {
        return basePrimaryAttributes;
    }

    public void setBasePrimaryAttributes(PrimaryAttributes basePrimaryAttributes) {
        this.basePrimaryAttributes = basePrimaryAttributes;
    }

    public PrimaryAttributes getTotalPrimaryAttributes() {
        return totalPrimaryAttributes;
    }

    public void setTotalPrimaryAttributes(PrimaryAttributes totalPrimaryAttributes) {
        this.totalPrimaryAttributes = totalPrimaryAttributes;
    }

}