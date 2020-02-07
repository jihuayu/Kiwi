package snownee.kiwi.test;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Rectangle2d;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ModelManager;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.DownloadingTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.File;

@Mod.EventBusSubscriber
public class TestIngameGui extends IngameGui {
    private static final Rectangle2d area = new Rectangle2d(0, 0, 16, 16);

    @SubscribeEvent
    public static void omRenderGameOverlayEvent(RenderGameOverlayEvent.Pre event){
        if (event.getType().equals(RenderGameOverlayEvent.ElementType.ALL)){
            TestIngameGui.THIS.renderSth();
        }
    }
    public static final TestIngameGui THIS = new TestIngameGui();
    public TestIngameGui() {
        super(Minecraft.getInstance());
    }

    public void renderSth() {
        IBakedModel bakedModel = Minecraft.getInstance().getModelManager().getModel(new ModelResourceLocation(Items.PUMPKIN.getRegistryName(), "inventory"));
        float padding = 2f;
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.translate(area.getX() + padding + 16, area.getY() + padding, 150);
        matrixStack.scale(16, -16, 16);
        matrixStack.translate(-0.5, -0.5, -0.5);
        Minecraft minecraft = Minecraft.getInstance();
        ItemRenderer itemRenderer = minecraft.getItemRenderer();
        IRenderTypeBuffer.Impl iRenderTypeBuffer = minecraft.getRenderTypeBuffers().getBufferSource();
        itemRenderer.renderItem(Items.PUMPKIN.getDefaultInstance(), ItemCameraTransforms.TransformType.GUI, false, matrixStack, iRenderTypeBuffer, 15728880, OverlayTexture.DEFAULT_LIGHT, bakedModel);
        iRenderTypeBuffer.finish();

    }
}
