package com.gildedrose.items;

import lombok.experimental.SuperBuilder;

import static com.gildedrose.GildedRose.SELL_IN_0_DAY;
import static com.gildedrose.GildedRose.decreaseItemSellIn;
import static com.gildedrose.GildedRose.decreaseQuality;

@SuperBuilder
public class ClassicItem extends AbstractItem {
    @Override
    public void updateQuality() {
        decreaseItemSellIn(this);

        decreaseQuality(this);

        if (this.sellIn < SELL_IN_0_DAY) {
            decreaseQuality(this);
        }
    }
}
