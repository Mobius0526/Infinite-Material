package com.mobius.im.entity;

import java.util.Map;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;

public record Form(String id, Integer size, Map<String, String> translations, Map<String, String> features) {

    public Item createItem(Material value, Properties prop) {
        var item = new Item(prop);

        return item;
    }

}
