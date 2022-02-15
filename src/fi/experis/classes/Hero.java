package fi.experis.classes;

import fi.experis.exceptions.InvalidArmorException;
import fi.experis.exceptions.InvalidWeaponException;
import fi.experis.enumerators.HeroClass;
import fi.experis.enumerators.Slot;

public class Hero extends Character {
    private final HeroClass heroClass;

    public Hero(String name, int level, PrimaryAttributes basePrimaryAttributes, HeroClass heroClass) {
        super(name, level, basePrimaryAttributes);
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
    public void equipItem(Item item) throws InvalidWeaponException, InvalidArmorException {
        Armor newArmor;
        Weapon newWeapon;

        if (item.getSlot() != Slot.Weapon) {
            newArmor = (Armor) item;
            if (newArmor.isValid(newArmor, heroClass, this.getLevel())) {
                setEquipment(newArmor.getSlot(), newArmor);
                calculateTotalAttributes();
            } else {
                throw new InvalidArmorException("Wrong type of equipment or too high level");
            }
        } else {
            newWeapon = (Weapon) item;
            if (newWeapon.isValid(newWeapon, heroClass, this.getLevel())) {
                setEquipment(newWeapon);
            } else {
                throw new InvalidWeaponException("Wrong type of equipment or too high level");

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
}

