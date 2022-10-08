package com.avid.debugbook.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UnDebugBookMessage {
    public static UnDebugBookMessage decode(final FriendlyByteBuf buffer) {
        buffer.readByte();
        return new UnDebugBookMessage();
    }

    public static void encode(final UnDebugBookMessage message, final FriendlyByteBuf buffer) {
        buffer.writeByte(0);
    }

    public static void handle(final UnDebugBookMessage message, final Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            var player = context.get().getSender();
            var server = player.getServer();
            var source = server.createCommandSourceStack();

            server.getCommands().performPrefixedCommand(source, "scale reset " + player.getGameProfile().getName());
        });

        context.get().setPacketHandled(true);
    }
}