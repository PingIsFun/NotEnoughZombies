package si.pingisfun.nez.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import si.pingisfun.nez.NotEnoughZombies;
import si.pingisfun.nez.events.title.TitleEvent;
import si.pingisfun.nez.utils.ZombiesUtils;

import java.util.Arrays;
import java.util.List;

@Mixin(value = GuiIngameForge.class, remap = false)
public class GuiIngameForgeMixin extends GuiIngame {
    @Unique
    private static final String[] IGNORED_TITLES = {"§r", ""};
    @Shadow
    private RenderGameOverlayEvent eventParent;
    @Unique
    private TitleEvent notEnoughZombies$oldevent = new TitleEvent("", "");

    public GuiIngameForgeMixin(Minecraft minecraft) {
        super(minecraft);
    }

    @Inject(method = "renderTitle", at = @At("HEAD"))
    private void onRenderTitle(int l, int age, float opacity, CallbackInfo ci) {
        if (!NotEnoughZombies.config.enabled || !ZombiesUtils.isZombiesGame()) {
            return;
        }
        String title = this.displayedTitle;
        String subtitle = this.displayedSubTitle;

        List<String> ignoredTitlesList = Arrays.asList(IGNORED_TITLES);
        if (ignoredTitlesList.contains(title) && ignoredTitlesList.contains(subtitle)) {
            return;
        }
        TitleEvent event = new TitleEvent(title, subtitle);

        // Only alert when title is first rendered
        if ((event).equals(notEnoughZombies$oldevent)) {
            return;
        }
        notEnoughZombies$oldevent = event;

        MinecraftForge.EVENT_BUS.post(event);


    }
}
