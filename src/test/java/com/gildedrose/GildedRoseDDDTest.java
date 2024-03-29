package com.gildedrose;

import com.gildedrose.items.AbstractItem;
import com.gildedrose.items.AgedBrie;
import com.gildedrose.items.BackstagePass;
import com.gildedrose.items.ClassicItem;
import com.gildedrose.items.Item;
import com.gildedrose.items.Sulfuras;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseDDDTest {
    private final List<AbstractItem> items = List.of(
        new ClassicItem("+5 Dexterity Vest", 10, 20),
        new ClassicItem("Elixir of the Mongoose", 5, 7),
        new ClassicItem("Conjured Mana Cake", 3, 6),
        new AgedBrie("Aged Brie", 2, 0),
        new Sulfuras("Sulfuras, Hand of Ragnaros", 0, 80),
        new Sulfuras("Sulfuras, Hand of Ragnaros", -1, 80),
        new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 15, 20),
        new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 10, 49),
        new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 5, 49)
    );

    @ParameterizedTest(name = "After {0} days")
    @MethodSource("provideItemsForTesting")
    void itemsRulesShouldAffectItByDay(int daysPassed, List<AbstractItem> expectedItems) {
        //Given
        GildedRoseDDD gildedRose = new GildedRoseDDD(items.toArray(AbstractItem[]::new));

        //When
        IntStream.range(0, daysPassed).forEach(day -> gildedRose.updateQuality());

        //Then
        assertThat(gildedRose.items).containsAll(expectedItems);
    }

    private static Stream<Arguments> provideItemsForTesting() {
        return Stream.of(
            Arguments.of(
                1,
                Arrays.asList(
                    new ClassicItem("+5 Dexterity Vest", 9, 19),
                    new ClassicItem("Elixir of the Mongoose", 4, 6),
                    new ClassicItem("Conjured Mana Cake", 2, 5),
                    new AgedBrie("Aged Brie", 1, 1),
                    new Sulfuras("Sulfuras, Hand of Ragnaros", 0, 80),
                    new Sulfuras("Sulfuras, Hand of Ragnaros", -1, 80),
                    new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 14, 21),
                    new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 9, 50),
                    new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 4, 50)
                )
            ),
            Arguments.of(
                9,
                Arrays.asList(
                    new ClassicItem("+5 Dexterity Vest", 1, 11),
                    new ClassicItem("Elixir of the Mongoose", -4, 0),
                    new ClassicItem("Conjured Mana Cake", -6, 0),
                    new AgedBrie("Aged Brie", -7, 16),
                    new Sulfuras("Sulfuras, Hand of Ragnaros", 0, 80),
                    new Sulfuras("Sulfuras, Hand of Ragnaros", -1, 80),
                    new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 6, 33),
                    new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 1, 50),
                    new BackstagePass("Backstage passes to a TAFKAL80ETC concert", -4, 0)
                )
            )
        );
    }
}
