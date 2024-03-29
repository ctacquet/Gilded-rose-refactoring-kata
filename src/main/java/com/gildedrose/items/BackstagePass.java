package com.gildedrose.items;

import static com.gildedrose.Constants.MINIMUM_QUALITY_VALUE;
import static com.gildedrose.Constants.SELL_IN_0_DAY;
import static com.gildedrose.Constants.SELL_IN_10_DAY;
import static com.gildedrose.Constants.SELL_IN_5_DAY;
import static com.gildedrose.GildedRose.decreaseItemSellIn;
import static com.gildedrose.GildedRose.increaseQuality;

public class BackstagePass extends AbstractItem {
    public BackstagePass(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

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
