package com.juiceybeans.ptdisplay.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundClientCommandPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;

public class StatRequestEvent {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        final Minecraft mc = Minecraft.getInstance();

        if (mc.getConnection()  == null|| mc.player == null) {
            return;
        }

        if (mc.player.tickCount % 20 == 0) {
            // Main.LOGGER.info("Reached 20 ticks, sending packet request");

            mc.getConnection().send(new ServerboundClientCommandPacket(ServerboundClientCommandPacket.Action.REQUEST_STATS));
        }
    }
}
