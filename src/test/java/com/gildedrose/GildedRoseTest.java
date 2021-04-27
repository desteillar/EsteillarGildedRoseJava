package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    @Test
    public void standardItemDecreasesSellByDayNumberEachTime() {
        GildedRose app = newGildedRose("standard item", 0, 0);
        app.updateQuality();
        assertEquals(-1, itemSellByDayNumber(app));
    }

    @Test
    public void agedbrieDecreasesSellByDayNumberEachTime() {
        GildedRose app = newGildedRose("Aged Brie", 0, 0);
        app.updateQuality();
        assertEquals(-1, itemSellByDayNumber(app));
    }

    @Test
    public void backstagePassesItemDecreasesSellByDayNumberEachTime() {
        GildedRose app = newGildedRose("Backstage passes to a", 0, 0);
        app.updateQuality();
        assertEquals(-1, itemSellByDayNumber(app));
    }

    @Test
    public void conjuredItemDecreasesSellByDayNumberEachTime() {
        GildedRose app = newGildedRose("Conjured", 0, 0);
        app.updateQuality();
        assertEquals(-1, itemSellByDayNumber(app));
    }

    @Test
    public void agedbrieIncreasesInQualityEachTime() {
        GildedRose app = newGildedRose("Aged Brie", 1, 1);
        app.updateQuality();
        assertEquals(2, itemQualityValue(app));
    }

    @Test
    public void brieQualityCannotGoAboveFiftyWhenIncreasing() {
        GildedRose app = newGildedRose("Aged Brie", 1, 49);
        app.updateQuality();
        app.updateQuality();
        assertEquals(50, itemQualityValue(app));
    }

    @Test
    public void backstagePassesItemDecreasesQualityByOneIfSellByDayMoreThanEleven() {
        GildedRose app = newGildedRose(GildedRose.BACKSTAGE_PASS_NAME, 12, 1);
        app.updateQuality();
        assertEquals(2, itemQualityValue(app));
    }

    @Test
    public void backstagePassesItemDecreasesQualityByTwoIfSellByDayIsMoreThanSix() {
        GildedRose app = newGildedRose(GildedRose.BACKSTAGE_PASS_NAME, 10, 1);
        app.updateQuality();
        assertEquals(3, itemQualityValue(app));
    }

    @Test
    public void backstagePassesItemDecreasesQualityByThreeIfSellByDayIsMoreThanZero() {
        GildedRose app = newGildedRose(GildedRose.BACKSTAGE_PASS_NAME, 5, 1);
        app.updateQuality();
        assertEquals(4, itemQualityValue(app));
    }

    @Test
    public void backstagePassesItemQualityDropsToZeroIfSellByDayIsZeroOrLess() {
        GildedRose app = newGildedRose(GildedRose.BACKSTAGE_PASS_NAME, 0,50);
        app.updateQuality();
        assertEquals(0, itemQualityValue(app));
    }

    @Test
    public void backstagePassesItemQualityCannotGoAboveFiftyWhenIncreasing() {
        GildedRose app = newGildedRose(GildedRose.BACKSTAGE_PASS_NAME, 5, 50);
        app.updateQuality();
        assertEquals(50, itemQualityValue(app));
    }

    @Test
    public void standardItemDecreasesQualityByOneIfSellByDayIsAboveZero() {
        GildedRose app = newGildedRose("foo", 2, 1);
        app.updateQuality();
        assertEquals(0, itemQualityValue(app));
    }

    @Test
    public void standardItemDecreasesQualityByTwoOnceSellByDayIsZeroOrLess() {
        GildedRose app = newGildedRose("foo",0, 5);
        app.updateQuality();
        assertEquals(3, itemQualityValue(app));
    }

    @Test
    public void standardItemCannotHaveQualityBelowZero() {
        GildedRose app = newGildedRose("foo", 0, 0);
        app.updateQuality();
        assertEquals(0, itemQualityValue(app));
    }

    @Test
    public void sulfurasHasQualityEighty() {
        GildedRose app = newGildedRose("Sulfuras, Hand of Ragnaros", 1, 80);
        assertEquals(80, itemQualityValue(app));
    }

    @Test
    public void sulfurasItemDoesNotAlterValues() {
        GildedRose app = newGildedRose("Sulfuras, Hand of Ragnaros", 1, 80);
        app.updateQuality();
        assertEquals(80, itemQualityValue(app));
        assertEquals(1, itemSellByDayNumber(app));
    }

    @Test
    public void conjuredItemDecreasesQualityByTwoIfSellByDayIsAboveZero() {
        GildedRose app = newGildedRose("Conjured", 2, 5);
        app.updateQuality();
        assertEquals(3, itemQualityValue(app));
    }

    @Test
    public void conjuredItemDecreasesQualityByFourOnceSellByDayIsZeroOrLess() {
        GildedRose app = newGildedRose("Conjured",0, 5);

        app.updateQuality();

        assertEquals(1, itemQualityValue(app));
    }

    @Test
    public void conjuredItemCannotHaveQualityBelowZero() {
        GildedRose app = newGildedRose("Conjured", 0, 0);

        app.updateQuality();

        assertEquals(0, itemQualityValue(app));
    }

    private GildedRose newGildedRose(String itemName, int itemSellIn, int itemQuality) {
        Item[] items = new Item[] { new Item(itemName, itemSellIn, itemQuality)};
        return new GildedRose(items);
    }

    private int itemSellByDayNumber(GildedRose app) {
        return app.items[0].sellIn;
    }

    private int itemQualityValue(GildedRose app) {
        return app.items[0].quality;
    }

}
