package com.gildedrose;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {
    Item[] items = new Item[]{
        new Item("+5 Dexterity Vest", 10, 20),
        new Item("Aged Brie", 2, 0),
        new Item("Elixir of the Mongoose", 5, 7),
        new Item("Sulfuras, Hand of Ragnaros", 0, 80),
        new Item("Sulfuras, Hand of Ragnaros", -1, 80),
        new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
        new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
        new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
        new Item("Conjured Mana Cake", 3, 6)
    };

    @ParameterizedTest(name = "After {0} days")
    @MethodSource("provideItemsForTesting")
    void itemsRulesShouldAffectItByDay(int daysPassed, List<Item> expectedItems) {
        //Given
        GildedRose gildedRose = new GildedRose(items);

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
                    new Item("+5 Dexterity Vest", 9, 19),
                    new Item("Aged Brie", 1, 1),
                    new Item("Elixir of the Mongoose", 4, 6),
                    new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                    new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                    new Item("Backstage passes to a TAFKAL80ETC concert", 14, 21),
                    new Item("Backstage passes to a TAFKAL80ETC concert", 9, 50),
                    new Item("Backstage passes to a TAFKAL80ETC concert", 4, 50),
                    new Item("Conjured Mana Cake", 2, 5)
                )
            ),
            Arguments.of(
                9,
                Arrays.asList(
                    new Item("+5 Dexterity Vest", 1, 11),
                    new Item("Aged Brie", -7, 16),
                    new Item("Elixir of the Mongoose", -4, 0),
                    new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                    new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                    new Item("Backstage passes to a TAFKAL80ETC concert", 6, 33),
                    new Item("Backstage passes to a TAFKAL80ETC concert", 1, 50),
                    new Item("Backstage passes to a TAFKAL80ETC concert", -4, 0),
                    new Item("Conjured Mana Cake", -6, 0)
                )
            )
        );
    }
}
