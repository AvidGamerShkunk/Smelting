package com.avid.debugbook.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import com.avid.debugbook.DebugBook;

public class Channel {
    public static final ResourceLocation channelName = new ResourceLocation(DebugBook.ID, "network");
    public static final String networkVersion = new ResourceLocation(DebugBook.ID, "1").toString();

    public static SimpleChannel register() {
        final var channel = NetworkRegistry.ChannelBuilder.named(channelName)
            .clientAcceptedVersions(version -> true)
            .serverAcceptedVersions(version -> true)
            .networkProtocolVersion(() -> networkVersion)
            .simpleChannel();

        channel.registerMessage(0, DebugBookMessage.class, DebugBookMessage::encode, DebugBookMessage::decode, DebugBookMessage::handle);
        channel.registerMessage(1, UnDebugBookMessage.class, UnDebugBookMessage::encode, UnDebugBookMessage::decode, UnDebugBookMessage::handle);

        return channel;
    }
}
