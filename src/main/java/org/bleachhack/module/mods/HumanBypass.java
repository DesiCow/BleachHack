package org.bleachhack.module.mods;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.VehicleMoveC2SPacket;
import org.bleachhack.event.events.EventPacket;
import org.bleachhack.eventbus.BleachSubscribe;
import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;

public class HumanBypass extends Module {
    public HumanBypass() {
        super("Human Bypass", KEY_UNBOUND, ModuleCategory.MOVEMENT, "Mimics Bot Movement to bypass Anti Human Plugins");
    }

    @BleachSubscribe
    public void sendPacket(EventPacket.Send event) {
        if (event.getPacket() instanceof PlayerMoveC2SPacket.PositionAndOnGround packet) {
            event.setPacket(
                    new PlayerMoveC2SPacket.PositionAndOnGround(
                            round(packet.getX(0), 1),
                            packet.getY(0),
                            round(packet.getZ(0), 1),
                            packet.onGround
                    )
            );
        } else if (event.getPacket() instanceof PlayerMoveC2SPacket.Full packet) {
            event.setPacket(
                    new PlayerMoveC2SPacket.Full(
                            round(packet.getX(0), 1),
                            packet.getY(0),
                            round(packet.getZ(0), 1),
                            packet.yaw,
                            packet.pitch,
                            packet.onGround
                    )
            );
        } else if (event.getPacket() instanceof VehicleMoveC2SPacket packet) {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
            buf.writeDouble(round(packet.getX(), 1));
            buf.writeDouble(packet.getY());
            buf.writeDouble(round(packet.getZ(), 1));
            buf.writeFloat(packet.getPitch());
            buf.writeFloat(packet.getYaw());
            event.setPacket(
                    new VehicleMoveC2SPacket(buf)
            );
        }
    }

    private double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}