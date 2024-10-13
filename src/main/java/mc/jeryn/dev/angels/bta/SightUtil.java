package mc.jeryn.dev.angels.bta;

import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.phys.Vec3d;

public class SightUtil {

	public static double calculateDotProduct(Vec3d vec1, Vec3d vec2) {
		return vec1.xCoord * vec2.xCoord + vec1.yCoord * vec2.yCoord + vec1.zCoord * vec2.zCoord;
	}

	public static boolean isOnScreen(EntityLiving entity, EntityPlayer player) {
		if (player == null)
			return false;
		else {
			Vec3d playerLookDirection = player.getViewVector(1.0f).normalize();
			Vec3d entityToPlayerDirection = Vec3d.createVector(entity.x - player.x,
				entity.bb.minY + (double) entity.bbHeight - player.y + (double) player.cameraPitch,
				entity.z - player.z);

			double entityToPlayerDistance = entityToPlayerDirection.lengthVector();

			double angleBetweenDirections = SightUtil.calculateDotProduct(playerLookDirection, entityToPlayerDirection);
			double thresholdAngles = 1.0d - 0.025d / entityToPlayerDistance;

			return angleBetweenDirections > thresholdAngles && player.canEntityBeSeen(entity);
		}
	}
}
