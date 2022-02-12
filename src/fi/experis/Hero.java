package fi.experis;

public class Hero extends Character {
    private final String heroClass;

    public Hero(String name, int level, PrimaryAttributes basePrimaryAttributes, String heroClass) {
        super(name, level, basePrimaryAttributes);
        this.heroClass = heroClass;
    }

    @Override
    public void getXp() {
        levelUp();
        setLevel(getLevel() + 1);
    }

    @Override
    public void getItems() {
        int random = (int) Math.round(Math.random());
        Armor newArmor;
        Weapon newWeapon;

        if (random == 0) {
            newArmor = Armor.createItem();
            Writer.armorWriter(newArmor);
            if (Writer.promptEquip()) {
                setEquipment(newArmor.getSlot(), newArmor);
            }
        } else {
            newWeapon = Weapon.createItem();
            Writer.weaponWriter(newWeapon);
            if (Writer.promptEquip()) {
                setEquipment(Slot.Weapon, newWeapon);
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
            case "Mage" -> {
                newAttributes = new PrimaryAttributes(strength + 1, dexterity + 1, intelligence + 5);
                setBasePrimaryAttributes(newAttributes);
            }
            case "Ranger" -> {
                newAttributes = new PrimaryAttributes(strength + 1, dexterity + 5, intelligence + 1);
                setBasePrimaryAttributes(newAttributes);
            }
            case "Rogue" -> {
                newAttributes = new PrimaryAttributes(strength + 1, dexterity + 4, intelligence + 1);
                setBasePrimaryAttributes(newAttributes);
            }
            case "Warrior" -> {
                newAttributes = new PrimaryAttributes(strength + 3, dexterity + 2, intelligence + 5);
                setBasePrimaryAttributes(newAttributes);
            }
            default -> throw new IllegalStateException("Unexpected value: " + heroClass);
        }
    }
}

