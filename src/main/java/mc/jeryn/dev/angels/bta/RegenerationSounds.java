package mc.jeryn.dev.angels.bta;

import net.minecraft.core.sound.SoundTypes;
import turniplabs.halplibe.helper.SoundHelper;

public class RegenerationSounds {

	public static void init(){
		SoundTypes.register("weeping_angels.stone_impact");
		SoundHelper.addSound(WeepingAngels.MOD_ID, "stone_impact.ogg");
	}

}
