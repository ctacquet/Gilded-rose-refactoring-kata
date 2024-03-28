package com.gildedrose.items;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class AbstractItem extends Item {
    public abstract void updateQuality();
}
