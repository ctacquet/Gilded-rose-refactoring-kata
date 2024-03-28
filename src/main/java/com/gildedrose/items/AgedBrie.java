package com.gildedrose.items;

import lombok.experimental.SuperBuilder;

import static com.gildedrose.GildedRose.decreaseItemSellIn;
import static com.gildedrose.GildedRose.increaseQuality;

@SuperBuilder
public class AgedBrie extends AbstractItem {
    @Override
    public void updateQuality() {
        decreaseItemSellIn(this);

        increaseQuality(this);

        if (this.sellIn < 0) {
            increaseQuality(this);
        }
    }
}
