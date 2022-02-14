package fi.experis;

public class Hero extends Character {
    private final HeroClass heroClass;

    public Hero(String name, int level, PrimaryAttributes basePrimaryAttributes, HeroClass heroClass) {
        super(name, level, basePrimaryAttributes);
        this.heroClass = heroClass;
    }

    @Override
    public void getXp() {
        levelUp();
        setLevel(getLevel() + 1);
    }

    @Override
    public void getItems() throws InvalidWeaponException, InvalidArmorException {
        int random = (int) Math.round(Math.random());
        Armor newArmor;
        Weapon newWeapon;

        if (random == 0) {
            newArmor = Armor.createItem();
            Writer.armorWriter(newArmor);
            if (Writer.promptEquip()) {
                if (newArmor.isValid(newArmor, heroClass, this.getLevel())) {
                    setEquipment(newArmor.getSlot(), newArmor);
                }  else {
                    throw new InvalidArmorException("Wrong type of equipment or too high level");
                }
            }
        } else {
            newWeapon = Weapon.createItem();
            Writer.weaponWriter(newWeapon);
            if (Writer.promptEquip()) {
                if (newWeapon.isValid(newWeapon, heroClass, this.getLevel())) {
                    setEquipment(Slot.Weapon, newWeapon);
                } else {
                    throw new InvalidWeaponException("Wrong type of equipment or too high level");
                }

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
    }
}

