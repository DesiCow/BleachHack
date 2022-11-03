package org.bleachhack.module.mods;

import net.minecraft.network.packet.s2c.play.*;
import org.bleachhack.event.events.EventPacket;
import org.bleachhack.eventbus.BleachSubscribe;
import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;

public class NoBorder extends Module {

    public NoBorder() {
        super("NoBorder", KEY_UNBOUND, ModuleCategory.LIVEOVERFLOW, "Cancel incoming Worldborder Packets");
    }

    @BleachSubscribe
    public void onBorder(EventPacket.Read event) {
        if (event.getPacket() instanceof WorldBorderInitializeS2CPacket ||
                event.getPacket() instanceof WorldBorderCenterChangedS2CPacket ||
                event.getPacket() instanceof WorldBorderSizeChangedS2CPacket ||
                event.getPacket() instanceof WorldBorderInterpolateSizeS2CPacket ||
                event.getPacket() instanceof WorldBorderWarningBlocksChangedS2CPacket ||
                event.getPacket() instanceof WorldBorderWarningTimeChangedS2CPacket) {
            event.setCancelled(true);
        }
    }

}
