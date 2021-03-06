package net.hycrafthd.teambattle.proxy;

import java.awt.Color;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import net.hycrafthd.teambattle.TBlocks;
import net.hycrafthd.teambattle.TItems;
import net.hycrafthd.teambattle.TeambattleReference;
import net.hycrafthd.teambattle.event.ClientEventHandler;
import net.hycrafthd.teambattle.particle.EntityTeambattleOreFX;
import net.hycrafthd.teambattle.util.ClientRegistryUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	Minecraft mc = Minecraft.getMinecraft();

	public static KeyBinding craftinggui;

	public void registerModels() {

		// Blocks
		ClientRegistryUtil.registerModelRenderer(TBlocks.teambattleore);

		// Items
		ClientRegistryUtil.registerModelRenderer(TItems.teambattleingotraw);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattleingot);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattlesword);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattlepickaxe);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattleshovel);
		ClientRegistryUtil.registerItemVariants(TItems.teambattlebow, "teambattlebow", "teambattlebow_0", "teambattlebow_1", "teambattlebow_2");
		ClientRegistryUtil.registerModelRenderer(TItems.teambattlebow);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattlehelmet);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattlechestplate);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattleleggings);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattleboots);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattlebackpack);

	}

	public void registerEffects() {
		EffectRenderer effect = mc.effectRenderer;
		effect.registerParticle(42, new EntityTeambattleOreFX.Factory());
	}

	public void registerEvents() {
		MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
		super.registerEvents();
	}

	public void registerKeybinding() {
		craftinggui = new KeyBinding("keybinding.keycraftinggui", Keyboard.KEY_K, "category.keybinding.teambattle");
		ClientRegistryUtil.registerKeybinding(craftinggui);
	}

	Color color = null;

	public void registerColorThread() {
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					Random rand = new Random();
					float red = rand.nextFloat();
					float green = rand.nextFloat();
					float blue = rand.nextFloat();
					color = new Color(red, green, blue);
					try {
						Thread.sleep(250L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "color");
		th.start();
	}

	public int getColor() {
		if (color == null)
			return 0;
		return color.getRGB();
	}

}
