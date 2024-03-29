package com.gildedrose.items;

import static com.gildedrose.Constants.SELL_IN_0_DAY;
import static com.gildedrose.GildedRose.decreaseItemSellIn;
import static com.gildedrose.GildedRose.decreaseQuality;

public class ClassicItem extends AbstractItem {
    public ClassicItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        decreaseItemSellIn(this);

        decreaseQuality(this);

        if (this.sellIn < SELL_IN_0_DAY) {
            decreaseQuality(this);
        }
    }
}
