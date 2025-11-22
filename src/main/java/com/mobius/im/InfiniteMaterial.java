package com.mobius.im;

import java.rmi.registry.Registry;

import com.mobius.im.item.ItemHandler;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;

@Mod(IM_Reference.MODID)
public class InfiniteMaterial {

    public InfiniteMaterial(IEventBus modEventBus, ModContainer modContainer) {
        IM_Reference.LOGGER.info("成功加载到" + IM_Reference.FORMS.toString());

        ItemHandler.init(modEventBus);
        modEventBus.addListener(InfiniteMaterial::started);
    }

    public static void started(FMLLoadCompleteEvent event) {
        BuiltInRegistries.CREATIVE_MODE_TAB
                .forEach(tab -> IM_Reference.LOGGER.info("检测了一个标签页:{}", tab.getDisplayName().getString()));
        IM_Reference.LOGGER.info("Infinite Material 已启动");
    }

}