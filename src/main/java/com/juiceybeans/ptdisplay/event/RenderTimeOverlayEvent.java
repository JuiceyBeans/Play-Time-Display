package com.juiceybeans.ptdisplay.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.StatType;
import net.minecraft.stats.Stats;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;

@OnlyIn(Dist.CLIENT)
public class RenderTimeOverlayEvent {

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiLayerEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Font font = mc.font;
        StatType<ResourceLocation> dummyStat = Stats.CUSTOM;

        assert mc.player != null;

        Stat<ResourceLocation> playtime = dummyStat.get(Stats.PLAY_TIME, StatFormatter.TIME);
        int time = mc.player.getStats().getValue(playtime);
        String statFormattedTime = playtime.format(time);
        GuiGraphics gui = event.getGuiGraphics();


        gui.drawString(font, Component.literal(soyFormattedTime(time)), 0, 0, 0xFFFFFF, true);
    }

    /**
     * @param time PLAY_TIME stat value
     * @return time in the format yy:dd:hh:mm:ss
     */
    private static String soyFormattedTime(int time) {
        int s = time / 20;
        int m = s / 60;
        int h = m / 60;
        int d = h / 24;
        int y = d / 365;

        // Leading zeroes
        String zS = String.format("%02d", s % 60);
        String zM = String.format("%02d", m % 60);
        String zH = String.format("%02d", h % 24);
        String zD = String.format("%02d", d % 365);

        // No formatted years. If you get more than a decade of playtime, FUCK YOU

        if (y >= 1) {
            return String.format("%d:%s:%s:%s:%s", y, zD, zH, zM, zS);
        } else if (d >= 1) {
            return String.format("%d:%s:%s:%s", d, zH, zM, zS);
        } else if (h >= 1) {
            return String.format("%d:%s:%s", h, zM, zS);
        } else {
            return String.format("%d:%s", m, zS);
        }
    }
}
