package fi.experis.classes;

import fi.experis.enumerators.ItemPrefix;
import fi.experis.enumerators.Slot;
import org.jetbrains.annotations.NotNull;

public abstract class Item{
    private String name;
    private Slot slot;
    private int level;

    public Item() {
        super();
    }

    public Item(String name, Slot slot, int level) {
        this.name = name;
        this.slot = slot;
        this.level = level;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", slot=" + slot +
                ", level=" + level +
                '}';
    }

    @NotNull
    static String getQuality(int level) {
        String quality;
        if (level <= 10) {
            quality = String.valueOf(ItemPrefix.Poor);
        } else if (level <= 20) {
            quality = String.valueOf(ItemPrefix.Common);
        } else if (level <= 30) {
            quality = String.valueOf(ItemPrefix.Uncommon);
        } else if (level <= 40) {
            quality = String.valueOf(ItemPrefix.Rare);
        } else if (level <= 50) {
            quality = String.valueOf(ItemPrefix.Epic);
        } else {
            quality = String.valueOf(ItemPrefix.Legendary);
        }
        return quality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}