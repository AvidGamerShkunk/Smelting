package website.avid.avidsmelting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import website.avid.avidsmelting.network.Channel;
import website.avid.avidsmelting.network.AvidSmeltingMessage;
import website.avid.avidsmelting.network.UnAvidSmeltingMessage;
import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod(AvidSmelting.ID)
public class AvidSmelting {
    public static final String ID = "debugbook";
    public static final Logger LOGGER = LogManager.getLogger(ID);

    public static final KeyMapping KEY_DEBUGBOOK = new KeyMapping("key." + ID + ".debugbook.desc",
            KeyConflictContext.IN_GAME,
            InputConstants.UNKNOWN, "key." + ID + ".category");
    public static final KeyMapping KEY_UNDEBUGBOOK = new KeyMapping("key." + ID + ".undebugbook.desc",
            KeyConflictContext.IN_GAME, InputConstants.UNKNOWN, "key." + ID + ".category");

    public static SimpleChannel CHANNEL;

    public AvidSmelting() {
        var modEvent = FMLJavaModLoadingContext.get().getModEventBus();
        modEvent.addListener(this::onCommonSetup);
        modEvent.addListener(this::onRegisterKeyMappings);

        MinecraftForge.EVENT_BUS.addListener(this::onClientTick);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        CHANNEL = Channel.register();
    }

    private void onRegisterKeyMappings(final RegisterKeyMappingsEvent event) {
        event.register(KEY_DEBUGBOOK);
        event.register(KEY_UNDEBUGBOOK);
    }

    private void onClientTick(ClientTickEvent event) {
        if (KEY_DEBUGBOOK.isDown()) {
            CHANNEL.sendToServer(new AvidSmeltingMessage());
        }

        if (KEY_UNDEBUGBOOK.isDown()) {
            CHANNEL.sendToServer(new UnAvidSmeltingMessage());
        }
    }
}
