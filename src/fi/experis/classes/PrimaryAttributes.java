package fi.experis.classes;

public class PrimaryAttributes {
    private int Strength;
    private int Dexterity;
    private int Intelligence;

    public PrimaryAttributes(int strength, int dexterity, int intelligence) {
        Strength = strength;
        Dexterity = dexterity;
        Intelligence = intelligence;
    }


    public void setStrength(int strength) {
        Strength = strength;
    }

    public void setDexterity(int dexterity) {
        Dexterity = dexterity;
    }

    public void setIntelligence(int intelligence) {
        Intelligence = intelligence;
    }

    public int getStrength() {
        return Strength;
    }

    public int getDexterity() {
        return Dexterity;
    }

    public int getIntelligence() {
        return Intelligence;
    }

    @Override
    public String toString() {
        return "PrimaryAttributes{" +
                "Strength=" + Strength +
                ", Dexterity=" + Dexterity +
                ", Intelligence=" + Intelligence +
                '}';
    }
}
