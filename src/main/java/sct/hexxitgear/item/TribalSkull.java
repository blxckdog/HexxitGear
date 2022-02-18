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
import sct.hexxitgear.render.HexxitGearHelmetModel;

public class TribalSkull extends ArmorItem implements HexxitGearHelmetModel {

	private final Identifier texture;
	
	private final ModelPart skull;
	/*
	private final ModelPart horn_left1;
	private final ModelPart horn_left2;
	
	private final ModelPart horn_right1;
	private final ModelPart horn_right2;*/
	
	
	public TribalSkull(ArmorMaterial material, EquipmentSlot slot) {
		super(material, slot, new Item.Settings().group(HexxitGear.ITEM_GROUP));
		texture = new Identifier("hexxitgear", "textures/maps/skull_helmet.png");
		
		List<Cuboid> cuboids = new ArrayList<>();
		
		cuboids.add(new Cuboid(0, 0, -4F, -8F, -4F, 8, 8, 8, 0, 0, 0, false, 64, 32));
		
		// Left Horn
		cuboids.add(new Cuboid(25, 0, 4F,  -7F,  0F, 3, 2, 2, 0, 0, 0, false, 64, 32));
		cuboids.add(new Cuboid(25, 5, 6F,-8F, 0F, 1, 1, 1, 0, 0, 0, false, 64, 32));
		
		// Right Horn
		cuboids.add(new Cuboid(25, 0, -7F, -7F, 0F, 3, 2, 2, 0, 0, 0, false, 64, 32));
		cuboids.add(new Cuboid(25, 5, -7F, -8F, 0F, 1, 1, 1, 0, 0, 0, false, 64, 32));
		
		skull = new ModelPart(cuboids, new HashMap<>());
		
		/*
		skull = new ModelPart(64, 32, 0, 0);
		skull.addCuboid(-4F, -8F, -4F, 8, 8, 8);
		
		// Left Horn
		horn_left1 = new ModelPart(64, 32, 25, 0);
		horn_left1.addCuboid(4F,  -7F,  0F, 3, 2, 2);
		
		horn_left2 = new ModelPart(64, 32, 25, 5);
		horn_left2.addCuboid(6F,-8F, 0F, 1, 1, 1);
		
		// Right Horn
		horn_right1 = new ModelPart(64, 32, 25, 0);
		horn_right1.addCuboid(-7F, -7F, 0F, 3, 2, 2);
		
		horn_right2 = new ModelPart(64, 32, 25, 5);
		horn_right2.addCuboid(-7F, -8F, 0F, 1, 1, 1);
		
		skull.addChild(horn_left1);
		skull.addChild(horn_left2);
		skull.addChild(horn_right1);
		skull.addChild(horn_right2);*/
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
		
		if(head == HexxitGear.TRIBAL_HELMET && chest == HexxitGear.TRIBAL_CHEST && legs == HexxitGear.TRIBAL_LEGS && feet == HexxitGear.TRIBAL_BOOTS) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 400, 0, false, false, true));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0, false, false, true));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, 2, false, false, true));
		}
	}
	

	@Override
	public <T extends LivingEntity, A extends BipedEntityModel<T>> void renderHelmet(MatrixStack matrices, VertexConsumerProvider vertexConsumer, T entity, int light, A model) {
		model.getHead().rotate(matrices);
		matrices.scale(1.15F, 1.15F, 1.15F);
		
		VertexConsumer vertex = vertexConsumer.getBuffer(model.getLayer(texture));
		skull.render(matrices, vertex, light, OverlayTexture.DEFAULT_UV, 1F, 1F, 1F, 1F);
		
		matrices.scale(1F, 1F, 1F);
	}
	
}
