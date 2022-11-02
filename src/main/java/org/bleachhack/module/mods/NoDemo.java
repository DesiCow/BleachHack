package org.bleachhack.module.mods;

import net.minecraft.network.packet.s2c.play.WorldEventS2CPacket;
import org.bleachhack.event.events.EventPacket;
import org.bleachhack.eventbus.BleachSubscribe;
import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;

public class NoDemo extends Module {

    private final static int WIN_GAME = 4;
    private final static int DEMO = 5;

    public NoDemo() {
        super("NoDemo", KEY_UNBOUND, ModuleCategory.MISC, "Cancel Incoming Demo Screen Packets");
    }

    @BleachSubscribe
    public void onGameEvent(EventPacket.Read event) {
        if (event.getPacket() instanceof WorldEventS2CPacket packet) {
            if (packet.getEventId() == WIN_GAME || packet.getEventId() == DEMO) {
                event.setCancelled(true);
            }
        }
    }

}
