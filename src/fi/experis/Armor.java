package fi.experis;

public class Armor {
    private String name;
    private String slot;
    private PrimaryAttributes primaryAttributes;

    public Armor() {
        super();
    }

    public Armor(String name, String slot, PrimaryAttributes primaryAttributes) {
        this.name = name;
        this.slot = slot;
        this.primaryAttributes = primaryAttributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public PrimaryAttributes getPrimaryAttributes() {
        return primaryAttributes;
    }

    public void setPrimaryAttributes(PrimaryAttributes primaryAttributes) {
        this.primaryAttributes = primaryAttributes;
    }

    @Override
    public String toString() {
        return "Armor{" +
                "name='" + name + '\'' +
                ", slot='" + slot + '\'' +
                ", primaryAttributes=" + primaryAttributes +
                '}';
    }
}
