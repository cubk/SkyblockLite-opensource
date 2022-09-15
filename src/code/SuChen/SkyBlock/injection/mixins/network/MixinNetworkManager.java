/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 */
package code.SuChen.SkyBlock.injection.mixins.network;

import code.SuChen.SkyBlock.events.PacketEvent;
import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.types.EventType;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={NetworkManager.class})
public class MixinNetworkManager {
    @Inject(method="channelRead0", at={@At(value="INVOKE", target="Lnet/minecraft/network/Packet;processPacket(Lnet/minecraft/network/INetHandler;)V", shift=At.Shift.BEFORE)}, cancellable=true)
    private void packetReceived(ChannelHandlerContext p_channelRead0_1_, Packet packet, CallbackInfo ci) {
        PacketEvent event = new PacketEvent(EventType.RECIEVE, packet);
        EventManager.call(event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method="sendPacket(Lnet/minecraft/network/Packet;)V", at={@At(value="HEAD")}, cancellable=true)
    private void sendPacket(Packet packetIn, CallbackInfo ci) {
        PacketEvent event = new PacketEvent(EventType.RECIEVE, packetIn);
        EventManager.call(event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }
}

