package com.gildedrose.items;

import lombok.experimental.SuperBuilder;

import static com.gildedrose.GildedRose.MINIMUM_QUALITY_VALUE;
import static com.gildedrose.GildedRose.SELL_IN_0_DAY;
import static com.gildedrose.GildedRose.SELL_IN_10_DAY;
import static com.gildedrose.GildedRose.SELL_IN_5_DAY;
import static com.gildedrose.GildedRose.decreaseItemSellIn;
import static com.gildedrose.GildedRose.increaseQuality;

@SuperBuilder
public class BackstagePass extends AbstractItem {
    @Override
    public void updateQuality() {
        decreaseItemSellIn(this);

        increaseQuality(this);

        if (this.sellIn < SELL_IN_10_DAY) {
            increaseQuality(this);
        }

        if (this.sellIn < SELL_IN_5_DAY) {
            increaseQuality(this);
        }

        if (this.sellIn < SELL_IN_0_DAY) {
            this.quality = MINIMUM_QUALITY_VALUE;
        }
    }
}
