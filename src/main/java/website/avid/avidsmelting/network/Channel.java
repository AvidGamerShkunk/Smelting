package website.avid.avidsmelting.network;

import website.avid.avidsmelting.AvidSmelting;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class Channel {
        public static final ResourceLocation channelName = new ResourceLocation(AvidSmelting.ID, "network");
        public static final String networkVersion = new ResourceLocation(AvidSmelting.ID, "1").toString();

        public static SimpleChannel register() {
                final var channel = NetworkRegistry.ChannelBuilder.named(channelName)
                                .clientAcceptedVersions(version -> true)
                                .serverAcceptedVersions(version -> true)
                                .networkProtocolVersion(() -> networkVersion)
                                .simpleChannel();

                channel.registerMessage(0, AvidSmeltingMessage.class, AvidSmeltingMessage::encode,
                                AvidSmeltingMessage::decode,
                                AvidSmeltingMessage::handle);
                channel.registerMessage(1, UnAvidSmeltingMessage.class, UnAvidSmeltingMessage::encode,
                                UnAvidSmeltingMessage::decode,
                                UnAvidSmeltingMessage::handle);

                return channel;
        }
}
