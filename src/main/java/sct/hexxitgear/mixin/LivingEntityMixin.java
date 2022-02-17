package sct.hexxitgear.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends LivingEntity {


	protected LivingEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}


	@Accessor("getJumpVelocity")
	public abstract float getJumpVelocity();
	
	/*
	@Redirect(
			at = @At(value="INVOKE", target="Lnet/minecraft/util/math/Vec3d/add(DDD)"), 
			method="Lnet/minecraft/entity/LivingEntity;Lnet/jump()V"
	)
	public void add(Vec3d vector, double x, double y, double z) {
		vector.add(x+5, y, z+5);
	}*/
	
}
