package website.avid.avidsmelting.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UnAvidSmeltingMessage {
    public static UnAvidSmeltingMessage decode(final FriendlyByteBuf buffer) {
        buffer.readByte();
        return new UnAvidSmeltingMessage();
    }

    public static void encode(final UnAvidSmeltingMessage message, final FriendlyByteBuf buffer) {
        buffer.writeByte(0);
    }

    public static void handle(final UnAvidSmeltingMessage message, final Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            var player = context.get().getSender();
            var server = player.getServer();
            var source = server.createCommandSourceStack();

            server.getCommands().performPrefixedCommand(source, "scale reset " + player.getGameProfile().getName());
        });

        context.get().setPacketHandled(true);
    }
}