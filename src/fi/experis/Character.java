package fi.experis;

public class Character {
    private String name;
    private int level;
    private String heroClass;
    private PrimaryAttributes basePrimaryAttributes;
    private PrimaryAttributes totalPrimaryAttributes;

    public Character() {
        super();
    }

    public Character(String name, int level, String heroClass, PrimaryAttributes basePrimaryAttributes) {
        this.name = name;
        this.level = level;
        this.heroClass = heroClass;
        this.basePrimaryAttributes = basePrimaryAttributes;
        this.totalPrimaryAttributes = basePrimaryAttributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
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

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", heroClass='" + heroClass + '\'' +
                ", basePrimaryAttributes=" + basePrimaryAttributes +
                ", totalPrimaryAttributes=" + totalPrimaryAttributes +
                '}';
    }


}