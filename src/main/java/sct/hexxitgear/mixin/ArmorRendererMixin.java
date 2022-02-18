package sct.hexxitgear.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

import sct.hexxitgear.render.HexxitGearHelmetModel;

@Environment(EnvType.CLIENT)
@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorRendererMixin<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {
	
	
	public ArmorRendererMixin(FeatureRendererContext<T, M> context) {
		super(context);
	}
	

	@Inject(at = @At("TAIL"), method="render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V")
	public void renderHelmet(MatrixStack matrices, VertexConsumerProvider vertexConsumer, int i, T entity, float f, float g, float h, float j, float k, float l, CallbackInfo info) {
		ItemStack itemStack = entity.getEquippedStack(EquipmentSlot.HEAD);
		
		if(!(itemStack.getItem() instanceof HexxitGearHelmetModel)) {
			return;
		}
		
		matrices.push();
		
		((HexxitGearHelmetModel) itemStack.getItem()).renderHelmet(matrices, vertexConsumer, entity, i, getModel());
		
		matrices.pop();
	}
	
	
	@Accessor("bodyModel")
	public abstract A getModel();
	
}
