package com.mobius.im.entity;

import java.util.Map;

/**
 * 材料
 */
public record Material(String id, Long color, Map<String, String> translations, Map<String, String> features) {

    public boolean hasForm(Form form) {
        return true;
    }

}
