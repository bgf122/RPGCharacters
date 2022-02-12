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

        switch (random) {
            case 0 -> {
                newArmor = Armor.createItem();
                System.out.println(newArmor.getName());
                System.out.println(newArmor.getLevel());
                System.out.println("Strength: " + newArmor.getPrimaryAttributes().getStrength());
                System.out.println("Dexterity: " + newArmor.getPrimaryAttributes().getDexterity());
                System.out.println("Intelligence: " + newArmor.getPrimaryAttributes().getIntelligence());
            }
            default -> {
                newWeapon = Weapon.createItem();
                System.out.println(newWeapon.getName());
                System.out.println(newWeapon.getLevel());
                System.out.println("Damage: " + newWeapon.getDamage());
                System.out.println("Speed: " + newWeapon.getSpeed());
                System.out.println("DPS: " + newWeapon.getDamage()*newWeapon.getSpeed());
            }
        }

    }

    @Override
    public void inspect() {
        System.out.println(this);
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

