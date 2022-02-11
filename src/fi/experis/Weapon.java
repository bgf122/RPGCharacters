package fi.experis;

public class Weapon {
    private String name;
    private double dps;
    private PrimaryAttributes primaryAttributes;

    public Weapon() {
        super();
    }

    public Weapon(String name, double dps, PrimaryAttributes primaryAttributes) {
        this.name = name;
        this.dps = dps;
        this.primaryAttributes = primaryAttributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDps() {
        return dps;
    }

    public void setDps(double dps) {
        this.dps = dps;
    }

    public PrimaryAttributes getPrimaryAttributes() {
        return primaryAttributes;
    }

    public void setPrimaryAttributes(PrimaryAttributes primaryAttributes) {
        this.primaryAttributes = primaryAttributes;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' +
                ", dps=" + dps +
                ", primaryAttributes=" + primaryAttributes +
                '}';
    }
}
