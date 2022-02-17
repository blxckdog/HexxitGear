/*
 * HexxitGear
 * Copyright (C) 2013  Ryan Cohen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package sct.hexxitgear.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPart.Cuboid;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import sct.hexxitgear.HexxitGear;
import sct.hexxitgear.render.HexxitHelmetModel;

public class ScaleHelmet extends ArmorItem implements HexxitHelmetModel {

	private final Identifier texture;
	private final ModelPart helmet;
	
	/*
	private final ModelPart inner;
	private final ModelPart outer;
	
	private final ModelPart spike_left1;
	private final ModelPart spike_left2;
	private final ModelPart spike_left3;
	private final ModelPart spike_left4;
	private final ModelPart spike_left5;
	
	private final ModelPart spike_right1;
	private final ModelPart spike_right2;
	private final ModelPart spike_right3;
	private final ModelPart spike_right4;
	private final ModelPart spike_right5;*/
	
	
	public ScaleHelmet(ArmorMaterial material, EquipmentSlot slot) {
		super(material, slot, new Item.Settings().group(HexxitGear.ITEM_GROUP));
		texture = new Identifier("hexxitgear", "textures/maps/scale_helmet.png");
		
		List<Cuboid> cuboids = new ArrayList<>();
		
		// Inner
		cuboids.add(new Cuboid(0, 0, -4F, -8F, -4F, 8, 8, 8, 0, 0, 0, false, 64, 64));
		// Outer
		cuboids.add(new Cuboid(0, 16, -5F, -9F, -5F, 10, 9, 10, 0, 0, 0, false, 64, 64));
		
		// Left Spike
		cuboids.add(new Cuboid(24, 0, -6F, -7F, -2F, 1, 5, 3, 0, 0, 0, false, 64, 64));
		cuboids.add(new Cuboid(4, 20, -7F, -8F, 0F, 1, 4, 2, 0, 0, 0, true, 64, 64));
		cuboids.add(new Cuboid(2, 2, -9F, -9F, 2F, 2, 1, 1, 0, 0, 0, true, 64, 64));
		cuboids.add(new Cuboid(4, 4, -8F, -8F, 1F, 1, 3, 1, 0, 0, 0, true, 64, 64));
		cuboids.add(new Cuboid(2, 2, -9F, -7F, 2F, 2, 1, 1, 0, 0, 0, true, 64, 64));
		
		// Right Spike
		cuboids.add(new Cuboid(24, 0, 5F, -7F, -2F, 1, 5, 3, 0, 0, 0, false, 64, 64));
		cuboids.add(new Cuboid(4, 20, 6F, -8F, 0F, 1, 4, 2, 0, 0, 0, false, 64, 64));
		cuboids.add(new Cuboid(2, 2, 7F, -9F, 1F, 1, 3, 1, 0, 0, 0, false, 64, 64));
		cuboids.add(new Cuboid(4, 4, 7F, -9F, 2F, 2, 1, 1, 0, 0, 0, false, 64, 64));
		cuboids.add(new Cuboid(2, 2, 7F, -7F, 2F, 2, 1, 1, 0, 0, 0, false, 64, 64));
		
		helmet = new ModelPart(cuboids, new HashMap<>());
		
		/*
		inner = new ModelPart(64, 64, 0, 0);
		inner.addCuboid(-4F, -8F, -4F, 8, 8, 8);
		
		outer = new ModelPart(64, 64, 0, 16);
		outer.addCuboid(-5F, -9F, -5F, 10, 9, 10);
		
		// Left Spike
		spike_left1 = new ModelPart(64, 64, 24, 0);
		spike_left1.addCuboid(-6F, -7F, -2F, 1, 5, 3);
		
		spike_left2 = new ModelPart(64, 64, 4, 20);
		spike_left2.addCuboid(-7F, -8F, 0F, 1, 4, 2);
		
		spike_left3 = new ModelPart(64, 64, 2, 2);
		spike_left3.addCuboid(-9F, -9F, 2F, 2, 1, 1);
		
		spike_left4 = new ModelPart(64, 64, 4, 4);
		spike_left4.addCuboid(-8F, -8F, 1F, 1, 3, 1);
		
		spike_left5 = new ModelPart(64, 64, 2, 2);
		spike_left5.addCuboid(-9F, -7F, 2F, 2, 1, 1);
		
		// Right Spike
		spike_right1 = new ModelPart(64, 64, 24, 0);
		spike_right1.addCuboid(5F, -7F, -2F, 1, 5, 3);
		
		spike_right2 = new ModelPart(64, 64, 4, 20);
		spike_right2.addCuboid(6F, -8F, 0F, 1, 4, 2);
		
		spike_right3 = new ModelPart(64, 64, 4, 4);
		spike_right3.addCuboid(7F, -9F, 1F, 1, 3, 1);
		
		spike_right4 = new ModelPart(64, 64, 2, 2);
		spike_right4.addCuboid(7F, -9F, 2F, 2, 1, 1);
		
		spike_right5 = new ModelPart(64, 64, 2, 2);
		spike_right5.addCuboid(7F, -7F, 2F, 2, 1, 1);
		
		inner.addChild(outer);
		inner.addChild(spike_left1);
		inner.addChild(spike_left2);
		inner.addChild(spike_left3);
		inner.addChild(spike_left4);
		inner.addChild(spike_left5);
		inner.addChild(spike_right1);
		inner.addChild(spike_right2);
		inner.addChild(spike_right3);
		inner.addChild(spike_right4);
		inner.addChild(spike_right5);*/
	}
	
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if(!(entity instanceof PlayerEntity)) {
			return;
		}
		
		PlayerEntity player = (PlayerEntity) entity;
		
		Item head = player.getEquippedStack(EquipmentSlot.HEAD).getItem();
		Item chest = player.getEquippedStack(EquipmentSlot.CHEST).getItem();
		Item legs = player.getEquippedStack(EquipmentSlot.LEGS).getItem();
		Item feet = player.getEquippedStack(EquipmentSlot.FEET).getItem();
		
		if(head == HexxitGear.SCALE_HELMET && chest == HexxitGear.SCALE_CHEST && legs == HexxitGear.SCALE_LEGS && feet == HexxitGear.SCALE_BOOTS) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 400, 0, false, false, true));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 0, false, false, true));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 1, false, false, true));
		}
	}
	

	@Override
	public <T extends LivingEntity, A extends BipedEntityModel<T>> void renderHelmet(MatrixStack matrices, VertexConsumerProvider vertexConsumer, T entity, int light, A model) {
		model.getHead().rotate(matrices);
		matrices.scale(1.05F, 1.05F, 1.05F);
		
		VertexConsumer vertex = vertexConsumer.getBuffer(model.getLayer(texture));
		helmet.render(matrices, vertex, light, OverlayTexture.DEFAULT_UV, 1F, 1F, 1F, 1F);
		
		matrices.scale(1F, 1F, 1F);
	}

}
