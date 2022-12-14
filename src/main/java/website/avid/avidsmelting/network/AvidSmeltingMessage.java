package website.avid.avidsmelting.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AvidSmeltingMessage {
    public static AvidSmeltingMessage decode(final FriendlyByteBuf buffer) {
        buffer.readByte();
        return new AvidSmeltingMessage();
    }

    public static void encode(final AvidSmeltingMessage message, final FriendlyByteBuf buffer) {
        buffer.writeByte(0);
    }

    public static void handle(final AvidSmeltingMessage message, final Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            var player = context.get().getSender();
            var server = player.getServer();
            var source = server.createCommandSourceStack();

            server.getCommands().performPrefixedCommand(source, "scale set 0.2 " + player.getGameProfile().getName());
        });

        context.get().setPacketHandled(true);
    }
}