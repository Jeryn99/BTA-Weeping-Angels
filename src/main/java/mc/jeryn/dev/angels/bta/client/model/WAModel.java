package mc.jeryn.dev.angels.bta.client.model;

import org.lwjgl.opengl.GL11;
import org.useless.dragonfly.model.entity.BenchEntityModel;
import org.useless.dragonfly.model.entity.processor.BenchEntityBones;

import static java.lang.Math.toRadians;

public class WAModel extends BenchEntityModel {

	private AngelEntity angelEntity;

	public AngelEntity getAngelEntity() {
		return angelEntity;
	}

	public void setAngelEntity(AngelEntity angelEntity) {
		this.angelEntity = angelEntity;
	}

	@Override
	public void render(float limbSwing, float limbYaw, float ticksExisted, float headYaw, float headPitch, float scale) {
		GL11.glPushMatrix();
		GL11.glRotatef(180, 0, 1, 0);
		super.render(limbSwing, limbYaw, ticksExisted, headYaw, headPitch, scale);
		GL11.glPopMatrix();
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbYaw, float limbPitch, float headYaw, float headPitch, float scale) {

		BenchEntityBones head = getIndexBones().get("head");
		BenchEntityBones leftarm = getIndexBones().get("leftarm");
		BenchEntityBones rightarm = getIndexBones().get("rightarm");
		BenchEntityBones torso = getIndexBones().get("torso");
		BenchEntityBones legs = getIndexBones().get("legs");
		BenchEntityBones wings = getIndexBones().get("wings");

		int currentPose = getAngelEntity().getCurrentPose();

		if (currentPose == 0) {

			head.rotateAngleX = (float) toRadians(-45);
			head.rotateAngleY = (float) toRadians(0);
			head.rotateAngleZ = (float) toRadians(0);

			leftarm.rotateAngleX = (float) toRadians(25);
			leftarm.rotateAngleY = (float) toRadians(0);
			leftarm.rotateAngleZ = (float) toRadians(20);

			rightarm.rotateAngleX = (float) toRadians(25);
			rightarm.rotateAngleY = (float) toRadians(10);
			rightarm.rotateAngleZ = (float) toRadians(-20);
			return;
		}

		if (currentPose == 1) {
			leftarm.rotateAngleX = (float) toRadians(100f);
			leftarm.rotateAngleY = (float) toRadians(-20f);
			leftarm.rotateAngleZ = (float) toRadians(0);
			rightarm.rotateAngleX = (float) toRadians(100f);
			rightarm.rotateAngleY = (float) toRadians(20f);
			rightarm.rotateAngleZ = (float) toRadians(0);
			head.rotateAngleX = (float) toRadians(10f);
			return;
		}

		if (currentPose == 2) {

			head.rotateAngleX = (float) Math.toRadians(-17);

			rightarm.rotateAngleX = (float) Math.toRadians(123);
			rightarm.rotateAngleY = (float) Math.toRadians(-7);
			rightarm.rotateAngleZ = (float) Math.toRadians(-28);

			leftarm.rotateAngleX = (float) Math.toRadians(123);
			leftarm.rotateAngleY = (float) Math.toRadians(7);
			leftarm.rotateAngleZ = (float) Math.toRadians(28);
			return;
		} else if (currentPose == 3) {
			// Pose 3 implementation (twisted arms)
			head.rotateAngleX = (float) toRadians(0);
			head.rotateAngleY = (float) toRadians(0);
			head.rotateAngleZ = (float) toRadians(0);

			leftarm.rotateAngleX = (float) toRadians(90);
			leftarm.rotateAngleY = (float) toRadians(45);
			leftarm.rotateAngleZ = (float) toRadians(30);

			rightarm.rotateAngleX = (float) toRadians(90);
			rightarm.rotateAngleY = (float) toRadians(-45);
			rightarm.rotateAngleZ = (float) toRadians(-30);
		} else if (currentPose == 4) {

			head.rotateAngleX = (float) toRadians(0);
			head.rotateAngleY = (float) toRadians(0);
			head.rotateAngleZ = (float) toRadians(0);

			torso.rotateAngleX = (float) toRadians(-10);
			torso.rotateAngleY = (float) toRadians(0);
			torso.rotateAngleZ = (float) toRadians(0);

			leftarm.rotateAngleX = (float) toRadians(105.6853);
			leftarm.rotateAngleY = (float) toRadians(9.1823);
			leftarm.rotateAngleZ = (float) toRadians(-8.5181);

			rightarm.rotateAngleX = (float) toRadians(105.797);
			rightarm.rotateAngleY = (float) toRadians(-5.7362);
			rightarm.rotateAngleZ = (float) toRadians(9.3457);

			legs.rotateAngleX = (float) toRadians(0);
			legs.rotateAngleY = (float) toRadians(0);
			legs.rotateAngleZ = (float) toRadians(0);

		} else if (currentPose == 5) {
			// Pose 5 implementation (back to the viewer)
			head.rotateAngleX = (float) toRadians(0);
			head.rotateAngleY = (float) toRadians(180);
			head.rotateAngleZ = (float) toRadians(0);

			leftarm.rotateAngleX = (float) toRadians(0);
			leftarm.rotateAngleY = (float) toRadians(0);
			leftarm.rotateAngleZ = (float) toRadians(0);

			rightarm.rotateAngleX = (float) toRadians(0);
			rightarm.rotateAngleY = (float) toRadians(0);
			rightarm.rotateAngleZ = (float) toRadians(0);
		} else {
			super.setRotationAngles(limbSwing, limbYaw, limbPitch, headYaw, headPitch, scale);
		}


		super.setRotationAngles(limbSwing, limbYaw, limbPitch, headYaw, headPitch, scale);
	}
}
