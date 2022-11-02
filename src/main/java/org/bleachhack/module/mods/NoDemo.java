package org.bleachhack.module.mods;

import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import org.bleachhack.event.events.EventPacket;
import org.bleachhack.eventbus.BleachSubscribe;
import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;

public class NoDemo extends Module {

    public NoDemo() {
        super("NoDemo", KEY_UNBOUND, ModuleCategory.MISC, "Cancel Incoming Demo Screen and Game Won Packets");
    }

    @BleachSubscribe
    public void onGameEvent(EventPacket.Read event) {
        if (event.getPacket() instanceof GameStateChangeS2CPacket packet) {
            if (packet.getReason() == GameStateChangeS2CPacket.GAME_WON || packet.getReason() == GameStateChangeS2CPacket.DEMO_MESSAGE_SHOWN) {
                event.setCancelled(true);
            }
        }
    }

}
