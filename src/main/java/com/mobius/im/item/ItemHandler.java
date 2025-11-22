package com.mobius.im.item;

import com.mobius.im.IM_Reference;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemHandler {
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(IM_Reference.MODID);
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, IM_Reference.MODID);

    public static void init(IEventBus bus) {

        IM_Reference.FORMS.forEach((name, form) -> {
            var items = IM_Reference.MATERIALS.entrySet().stream().filter(entry -> entry.getValue().hasForm(form))
                    .map(entry -> {
                        String itemName = entry.getKey() + "_" + name;
                        return ITEMS.registerItem(itemName, prop -> form.createItem(entry.getValue(), prop));
                    }).toList();

            var tab = CREATIVE_MODE_TABS.register(name, () -> CreativeModeTab.builder().displayItems(items)
                    .title(Component.translatable("itemGroup." + IM_Reference.MODID + "." + name)).build());

            ITEMS.registerItem(name, MateItem::new);
        });
        CREATIVE_MODE_TABS.register(bus);
        ITEMS.register(bus);
    }

    public static void regTab(BuildCreativeModeTabContentsEvent event) {

    }

}
