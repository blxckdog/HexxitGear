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

package sct.hexxitgear.material;

public class TribalArmorMaterial extends HexxitArmorMaterial {

	@Override
	public String getName() {
		return "tribal_armor";
	}

	
	
	/*
	public TribalArmorMaterial(String regname, EntityEquipmentSlot slot) {
		super(regname, ArmorMaterial.DIAMOND, 0, slot);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		if (slot == EntityEquipmentSlot.HEAD) return "hexxitgear:textures/maps/tribal_skull.png";

		if (stack.getItem() == HexRegistry.TRIBAL_LEGS) return "hexxitgear:textures/armor/tribal2.png";

		return "hexxitgear:textures/armor/tribal.png";
	}

	@SideOnly(Side.CLIENT)
	private static ModelSkullHelmet skullHelmet;

	@SideOnly(Side.CLIENT)
	private ModelSkullHelmet getHelmet() {
		if (skullHelmet == null) skullHelmet = new ModelSkullHelmet();
		return skullHelmet;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		if (armorSlot == EntityEquipmentSlot.HEAD) {
			ModelBiped skull = getHelmet();
			skull.isSneak = entityLiving.isSneaking();
			return skull;
		}
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag flag) {
		list.add(TextFormatting.DARK_PURPLE + I18n.format("gui.hexxitgear.set.tribal"));
	}*/
	
}
