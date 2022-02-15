package fi.experis.classes;

import fi.experis.enumerators.ArmorType;
import fi.experis.enumerators.WeaponType;
import fi.experis.exceptions.InvalidArmorException;
import fi.experis.exceptions.InvalidWeaponException;
import fi.experis.enumerators.HeroClass;
import fi.experis.enumerators.Slot;

public class Hero extends Character {
    private HeroClass heroClass;

    public Hero(String name, int level, PrimaryAttributes basePrimaryAttributes, HeroClass heroClass) {
        super(name, level, basePrimaryAttributes);
        this.heroClass = heroClass;
    }

    public Hero() {
        super();
    }

    public void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    @Override
    public void getXp() {
        levelUp();
        setLevel(getLevel() + 1);
    }

    @Override
    public void getItems() throws InvalidArmorException, InvalidWeaponException {
        int random = (int) Math.round(Math.random());
        Armor newArmor;
        Weapon newWeapon;

        if (random == 0) {
            newArmor = Armor.createItem();
            Writer.armorWriter(newArmor);
            if (Writer.promptEquip()) {
                equipItem(newArmor);
            }
        } else {
            newWeapon = Weapon.createItem();
            Writer.weaponWriter(newWeapon);
            if (Writer.promptEquip()) {
                equipItem(newWeapon);
            }
        }

    }

    @Override
    public void inspect() {
        Writer.inspect(this);
    }

    @Override
    public void levelUp() {
        PrimaryAttributes newAttributes;
        int strength = getBasePrimaryAttributes().getStrength();
        int dexterity = getBasePrimaryAttributes().getDexterity();
        int intelligence = getBasePrimaryAttributes().getIntelligence();

        switch (this.heroClass) {
            case Mage -> {
                newAttributes = new PrimaryAttributes(strength + 1, dexterity + 1, intelligence + 5);
                setBasePrimaryAttributes(newAttributes);
            }
            case Ranger -> {
                newAttributes = new PrimaryAttributes(strength + 1, dexterity + 5, intelligence + 1);
                setBasePrimaryAttributes(newAttributes);
            }
            case Rogue -> {
                newAttributes = new PrimaryAttributes(strength + 1, dexterity + 4, intelligence + 1);
                setBasePrimaryAttributes(newAttributes);
            }
            case Warrior -> {
                newAttributes = new PrimaryAttributes(strength + 3, dexterity + 2, intelligence + 5);
                setBasePrimaryAttributes(newAttributes);
            }
            default -> throw new IllegalStateException("Unexpected value: " + heroClass);
        }
        calculateTotalAttributes();
    }

    public void calculateTotalAttributes() {
        PrimaryAttributes baseAttributes = getBasePrimaryAttributes();
        setTotalPrimaryAttributes(baseAttributes);

        getEquipment().forEach((key,value) -> {
            if (key != Slot.Weapon) {
                Armor armor = (Armor) value;
                final PrimaryAttributes attributes = getTotalPrimaryAttributes();
                final PrimaryAttributes armorAttributes = armor.getPrimaryAttributes();
                final int totalStr = attributes.getStrength() + armorAttributes.getStrength();
                final int totalDex = attributes.getDexterity() + armorAttributes.getDexterity();
                final int totalInt = attributes.getIntelligence() + armorAttributes.getIntelligence();
                PrimaryAttributes newAttributes = new PrimaryAttributes(totalStr, totalDex, totalInt);
                setTotalPrimaryAttributes(newAttributes);
            }
        });


    }

    public void equipItem(Item item) throws InvalidArmorException, InvalidWeaponException{
        Armor armor;
        Weapon weapon;

        if (item.getSlot() != Slot.Weapon) {
            armor = (Armor) item;
            if (isValid(armor, this.heroClass, this.getLevel())) {
                setEquipment(armor.getSlot(), armor);
                calculateTotalAttributes();
            } else {
                throw new InvalidArmorException("Wrong type of equipment or too high level");
            }
        } else {
            weapon = (Weapon) item;
            if (isValid(weapon, this.heroClass, this.getLevel())) {
                setEquipment(weapon);
            } else {
                throw new InvalidWeaponException("Wrong type of equipment or too high level");
            }
        }
    }

    public boolean isValid(Item item, HeroClass heroClass, int level) {
        Armor armor;
        Weapon weapon;

        if (level < item.getLevel()) {
            return false;
        }

        if (item.getSlot() != Slot.Weapon) {
            armor = (Armor) item;
            switch (heroClass) {
                case Mage -> {
                    if (armor.getArmorType() != ArmorType.Cloth) return false;
                } case Ranger, Rogue -> {
                    if (armor.getArmorType() != ArmorType.Mail && armor.getArmorType() != ArmorType.Leather) return false;
                } case Warrior -> {
                    if (armor.getArmorType() != ArmorType.Mail && armor.getArmorType() != ArmorType.Plate) return false;
                }
            }
        } else {
            weapon = (Weapon) item;
            switch (heroClass) {
                case Mage -> {
                    if (weapon.getWeaponType() != WeaponType.Staffs && weapon.getWeaponType() != WeaponType.Wands) return false;
                } case Ranger -> {
                    if (weapon.getWeaponType() != WeaponType.Bows) return false;
                } case Rogue -> {
                    if (weapon.getWeaponType() != WeaponType.Daggers && weapon.getWeaponType() != WeaponType.Swords) return false;
                } case Warrior -> {
                    if (weapon.getWeaponType() != WeaponType.Axes && weapon.getWeaponType() != WeaponType.Hammers && weapon.getWeaponType() != WeaponType.Swords) return false;
                }
            }
        }
        return true;
    }
}

