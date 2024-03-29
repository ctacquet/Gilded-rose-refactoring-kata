package com.gildedrose.items;

import static com.gildedrose.GildedRose.decreaseItemSellIn;
import static com.gildedrose.GildedRose.increaseQuality;

public class AgedBrie extends AbstractItem {
    public AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        decreaseItemSellIn(this);

        increaseQuality(this);

        if (this.sellIn < 0) {
            increaseQuality(this);
        }
    }
}
