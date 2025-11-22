package com.mobius.im;

import java.util.Map;

import org.slf4j.Logger;

import com.mobius.im.entity.Form;
import com.mobius.im.entity.Material;
import com.mobius.im.loader.ConfigHandler;
import com.mojang.logging.LogUtils;

public class IM_Reference {
    public static final String MODID = "infinitematerial";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Map<String, Form> FORMS = ConfigHandler.getForms();
    public static final Map<String, Material> MATERIALS = ConfigHandler.getMaterials();
    
}
