package mc.jeryn.dev.angels.bta.client.model;

import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.client.render.model.ModelBase;

public class WARenderAngel extends LivingRenderer<AngelEntity> {

	public WARenderAngel(ModelBase model) {
		super(model, 0.5F);
	}


	@Override
	public void render(AngelEntity entity, double x, double y, double z, float yaw, float partialTick) {

		if(mainModel instanceof WAModel){
			WAModel waModel = (WAModel) mainModel;
			waModel.setAngelEntity(entity);
		}

		super.render(entity, x, y, z, yaw, partialTick);
	}
}
