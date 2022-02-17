package sct.hexxitgear.render;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public interface HexxitHelmetModel  {

	/**
	 * This method is used to render custom helmets.
	 * 
	 * @param <T> The entity (e.g. Player, ArmorStand)
	 * @param <A> The entity's model
	 * @param matrices A matrix stack used for rendering
	 * @param vertexConsumer A vertex consumer provider
	 * @param entity The entity which wears the helmet
	 * @param light The light level to adjust brightness
	 * @param model The entity's model to render the helmet on
	 */
	public <T extends LivingEntity, A extends BipedEntityModel<T>> void renderHelmet(
			MatrixStack matrices, 
			VertexConsumerProvider vertexConsumer, 
			T entity, 
			int light, 
			A model
	);
	
}
