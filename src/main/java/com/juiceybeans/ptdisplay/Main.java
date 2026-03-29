package com.juiceybeans.ptdisplay;

import com.juiceybeans.ptdisplay.event.RenderTimeOverlayEvent;
import com.juiceybeans.ptdisplay.event.StatRequestEvent;
import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(Main.MODID)
public class Main {
    public static final String MODID = "ptdisplay";
    public static final Logger LOGGER = LogUtils.getLogger();


    public Main(IEventBus bus, ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
    }

    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            NeoForge.EVENT_BUS.register(RenderTimeOverlayEvent.class);
            NeoForge.EVENT_BUS.register(StatRequestEvent.class);
        }
    }
}
