package mc.jeryn.dev.angels.bta;

import mc.jeryn.dev.angels.bta.client.model.AngelEntity;
import mc.jeryn.dev.angels.bta.client.model.WAModel;
import mc.jeryn.dev.angels.bta.client.model.WARenderAngel;
import mc.jeryn.dev.angels.bta.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.useless.dragonfly.helper.ModelHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;
import java.util.Random;

import static turniplabs.halplibe.helper.EntityHelper.createEntity;

public class WeepingAngels implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "weeping_angels";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Random MASTER_RANDOM = new Random();

	public static RegenConfig regenConfig = new RegenConfig();

    @Override
    public void onInitialize() {
		LOGGER.info("Weeping Angels initialized.");

	}
	@Override
	public void beforeGameStart() {
		ModItems.init();
		RegenerationSounds.init();
		createEntity(AngelEntity.class, 2000, "weeping_angel", () -> new WARenderAngel(ModelHelper.getOrCreateEntityModel(MOD_ID, "entity/angel.json", WAModel.class)));

	}



	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {

	}
}
