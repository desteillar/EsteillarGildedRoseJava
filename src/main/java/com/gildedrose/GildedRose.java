package com.gildedrose;

class GildedRose {
    static final String BACKSTAGE_PASS_NAME = "Backstage passes to a " + "TAFKAL80ETC concert";
    static final String AGED_BRIE_NAME = "Aged Brie";
    static final String SULFURAS_NAME = "Sulfuras, Hand of Ragnaros";
    static final String CONJURED_NAME_PART = "Conjured";
    static final int MAX_ITEM_QUALITY = 50;
    final Item[] items;

    public GildedRose(final Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (final Item item : items) {
            updateItemQuality(item);
            updateItemSellIn(item);
        }
    }

    private void updateItemQuality(Item item) {
        if (isConjured(item.name) && item.quality  > 0) {
            item.quality -= 2;
        }
        else if (isSpecialName(item.name) || item.quality <= 0) {
            if (item.quality < MAX_ITEM_QUALITY && !item.name.contains(CONJURED_NAME_PART)) {
                item.quality++;

                if (item.name.equals(BACKSTAGE_PASS_NAME)) {
                    if (item.sellIn < 11 && item.quality < MAX_ITEM_QUALITY) {
                        item.quality++;
                    }

                    if (item.sellIn < 6 && item.quality < MAX_ITEM_QUALITY) {
                        item.quality++;
                    }
                }
            }
        } else {
            item.quality--;
        }
    }


    private void updateItemSellIn(Item item) {
        if (!item.name.equals(SULFURAS_NAME)) {
            item.sellIn--;
        }

        if (item.sellIn < 0) {
            updateExpiredItem(item);
        }
    }

    private void updateExpiredItem(Item item) {
        if (item.name.equals(AGED_BRIE_NAME)) {
            if (item.quality < MAX_ITEM_QUALITY) {
                item.quality++;
            }
        } else if (item.name.equals(BACKSTAGE_PASS_NAME)) {
            item.quality = 0;
        } else if (isConjured(item.name) && item.quality > 0) {
            item.quality -= 2;
        } else if (item.quality > 0 && !item.name.equals(SULFURAS_NAME)) {
            item.quality--;
        }
    }

    private boolean isSpecialName(String name) {
        return name.equals(SULFURAS_NAME) ||
                name.equals(AGED_BRIE_NAME) ||
                name.equals(BACKSTAGE_PASS_NAME) ||
                isConjured(name);
    }

    private boolean isConjured(String name) {
        return name.contains(CONJURED_NAME_PART);
    }
}