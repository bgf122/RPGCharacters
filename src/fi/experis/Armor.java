package fi.experis;

import java.util.List;
import java.util.Random;

public class Armor extends Item {
    private PrimaryAttributes primaryAttributes;
    private ArmorType armorType;

    public Armor(String name, Slot slot, int level, PrimaryAttributes primaryAttributes, ArmorType armorType) {
        super(name, slot, level);
        this.primaryAttributes = primaryAttributes;
        this.armorType = armorType;
    }

    public PrimaryAttributes getPrimaryAttributes() {
        return primaryAttributes;
    }

    public void setPrimaryAttributes(PrimaryAttributes primaryAttributes) {
        this.primaryAttributes = primaryAttributes;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
    }

    @Override
    public String toString() {
        return "Armor{" +
                "primaryAttributes=" + primaryAttributes +
                ", armorType=" + armorType +
                '}';
    }


    public static Armor createItem() {
        List<ArmorType> armorTypes = List.of(ArmorType.values());
        List<Slot> slotTypes = List.of(Slot.values());
        int sizeArmor = armorTypes.size();
        int sizeSlot = slotTypes.size();
        Random randomArmor = new Random();
        Random randomSlot = new Random();
        int level = (int) (Math.random() * 60);
        int strength = (int) (Math.random() * level * 1.5);
        int dexterity = (int) (Math.random() * level * 1.5);
        int intelligence = (int) (Math.random() * level * 1.5);
        PrimaryAttributes primaryAttributes = new PrimaryAttributes(strength, dexterity, intelligence);
        ArmorType armorType = armorTypes.get(randomArmor.nextInt(sizeArmor));
        Slot slot = slotTypes.get(randomSlot.nextInt(sizeSlot - 1));
        String name = generateName(armorType, slot, level);

        return new Armor(name, slot, level, primaryAttributes, armorType);
    }

    public static String generateName(ArmorType type, Slot slot, int level) {
        String armorName = type.name();
        String slotName;
        String quality = Item.getString(level);

        switch (slot) {
            case Body -> {
                List<BodyPrefix> bodyPrefixes = List.of(BodyPrefix.values());
                Random randomBody = new Random();
                int sizeBody = bodyPrefixes.size();
                slotName = String.valueOf(bodyPrefixes.get(randomBody.nextInt(sizeBody)));
            }
            case Head -> {
                List<HeadPrefix> headPrefixes = List.of(HeadPrefix.values());
                Random randomHead = new Random();
                int sizeHead = headPrefixes.size();
                slotName = String.valueOf(headPrefixes.get(randomHead.nextInt(sizeHead)));
            }
            case Legs -> {
                List<LegsPrefix> legsPrefixes = List.of(LegsPrefix.values());
                Random randomLegs = new Random();
                int sizeLegs = legsPrefixes.size();
                slotName = String.valueOf(legsPrefixes.get(randomLegs.nextInt(sizeLegs)));
            }
            default -> slotName = "";
        }


        return quality + " " + armorName + " " + slotName;
    }
}
