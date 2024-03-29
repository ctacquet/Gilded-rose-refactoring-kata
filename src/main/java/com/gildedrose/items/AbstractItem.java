package com.gildedrose.items;

public abstract class AbstractItem extends Item {
    protected AbstractItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public abstract void updateQuality();
}
