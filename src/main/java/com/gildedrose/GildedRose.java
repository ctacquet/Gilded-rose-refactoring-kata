package com.gildedrose;

import com.gildedrose.items.Item;

public class GildedRose {
    private String AGED_BRIE = "Aged Brie";
    private String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    private String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public static final int MAXIMUM_QUALITY_VALUE = 50;
    public static final int MINIMUM_QUALITY_VALUE = 0;
    public static final int QUALITY_DECREMENT_VALUE = 1;
    public static final int QUALITY_INCREMENT_VALUE = 1;

    public static final int SELL_IN_0_DAY = 0;
    public static final int SELL_IN_5_DAY = 5;
    public static final int SELL_IN_10_DAY = 10;

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

/**   DDD programming logic :
    public void updateQuality2() {
        for (AbstractItem item : items) {
            item.updateQuality();
        }
    }
 */

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
