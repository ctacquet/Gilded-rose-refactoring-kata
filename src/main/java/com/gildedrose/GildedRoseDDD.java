package com.gildedrose;

import com.gildedrose.items.AbstractItem;

public class GildedRoseDDD {

    public AbstractItem[] items;

    public GildedRoseDDD(AbstractItem[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (AbstractItem item : items) {
            item.updateQuality();
        }
    }
}
