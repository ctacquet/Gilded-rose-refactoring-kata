package com.gildedrose;

import com.gildedrose.items.Item;

import static com.gildedrose.Constants.AGED_BRIE;
import static com.gildedrose.Constants.BACKSTAGE;
import static com.gildedrose.Constants.MAXIMUM_QUALITY_VALUE;
import static com.gildedrose.Constants.MINIMUM_QUALITY_VALUE;
import static com.gildedrose.Constants.QUALITY_DECREMENT_VALUE;
import static com.gildedrose.Constants.QUALITY_INCREMENT_VALUE;
import static com.gildedrose.Constants.SELL_IN_0_DAY;
import static com.gildedrose.Constants.SELL_IN_10_DAY;
import static com.gildedrose.Constants.SELL_IN_5_DAY;
import static com.gildedrose.Constants.SULFURAS;

public class GildedRose {
    public Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (SULFURAS.equals(item.name)) {
                continue;
            }

            if (AGED_BRIE.equals(item.name)) {
                updateAgedBrie(item);
            } else if (BACKSTAGE.equals(item.name)) {
                updateBackstagePass(item);
            } else {
                updateClassicItem(item);
            }
        }
    }

    private void updateAgedBrie(Item item) {
        decreaseItemSellIn(item);

        increaseQuality(item);

        if (item.sellIn < 0) {
            increaseQuality(item);
        }
    }

    private void updateBackstagePass(Item item) {
        decreaseItemSellIn(item);

        increaseQuality(item);

        if (item.sellIn < SELL_IN_10_DAY) {
            increaseQuality(item);
        }

        if (item.sellIn < SELL_IN_5_DAY) {
            increaseQuality(item);
        }

        if (item.sellIn < SELL_IN_0_DAY) {
            item.quality = MINIMUM_QUALITY_VALUE;
        }
    }

    private void updateClassicItem(Item item) {
        decreaseItemSellIn(item);

        decreaseQuality(item);

        if (item.sellIn < SELL_IN_0_DAY) {
            decreaseQuality(item);
        }
    }

    public static void decreaseItemSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    public static void increaseQuality(Item item) {
        if (item.quality < MAXIMUM_QUALITY_VALUE) {
            item.quality = item.quality + QUALITY_INCREMENT_VALUE;
        }
    }

    public static void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - QUALITY_DECREMENT_VALUE;
        }
    }
}
