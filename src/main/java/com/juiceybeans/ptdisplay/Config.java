package com.juiceybeans.ptdisplay;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.awt.Color;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.ConfigValue<Integer> X_POS =
            BUILDER.comment("X position of overlay (default: 4)")
                    .define("x_pos", 4);

    private static final ModConfigSpec.ConfigValue<Integer> Y_POS =
            BUILDER.comment("Y position of overlay (default: 4)")
                    .define("y_pos", 4);

    private static final ModConfigSpec.ConfigValue<String> COLOR =
            BUILDER.comment("Color of overlay text (default: 0xFFFFFF)")
                    .define("color", "0xFFFFFF");

    private static final ModConfigSpec.ConfigValue<Boolean> DROP_SHADOW =
            BUILDER.comment("Drop shadow for overlay text (default: true)")
                    .define("drop_shadow", true);

    private static final ModConfigSpec.ConfigValue<Boolean> USE_STAT_FORMAT =
            BUILDER.comment("Use the default statistic format instead of YYDDHHSS (default: false)")
                    .define("use_stat_format", false);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static int xPos;
    public static int yPos;
    public static int color;
    public static boolean dropShadow;
    public static boolean useStatFormat;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        xPos = X_POS.get();
        yPos = Y_POS.get();
        color = Color.decode(COLOR.get()).getRGB();
        dropShadow = DROP_SHADOW.get();
        useStatFormat = USE_STAT_FORMAT.get();
    }
}