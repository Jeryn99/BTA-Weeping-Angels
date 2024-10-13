package mc.jeryn.dev.angels.bta.client.model;

import com.mojang.nbt.CompoundTag;
import mc.jeryn.dev.angels.bta.SightUtil;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockTorch;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.monster.EntityMonster;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.world.World;

public class AngelEntity extends EntityMonster {

	private static int SEEN_SYNC = 26;
	private static int POSE_SYNC = 27;

	private int currentPose = 0;
	private boolean isSeen = false;

	public AngelEntity(World world) {
		super(world);
		this.moveSpeed = 0.5F;
		this.attackStrength = 5;
		this.scoreValue = 300;
		this.mobDrops.add(new WeightedRandomLootObject(Block.stone.getDefaultStack(), 0, 2));
		getEntityData().define(SEEN_SYNC, isSeen ? 1 : 0);
		getEntityData().define(POSE_SYNC, currentPose);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		tag.putInt("pose", currentPose);
		tag.putBoolean("seen", isSeen);
		super.addAdditionalSaveData(tag);

	}


	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		currentPose = tag.getInteger("pose");
		isSeen = tag.getBoolean("seen");
		super.readAdditionalSaveData(tag);

	}

	public void setCurrentPose(int currentPose) {
		this.currentPose = currentPose;
		getEntityData().set(POSE_SYNC, currentPose);
	}

	public int getCurrentPose() {
		return entityData.getInt(POSE_SYNC);
	}

	@Override
	public int getMaxHealth() {
		return 30;
	}

	@Override
	public String getDefaultEntityTexture() {
		switch (getCurrentPose()) {
			case 0:
				return "/assets/weeping_angels/entity/weeping_angel_angry.png";
			case 1:
				return "/assets/weeping_angels/entity/weeping_angel_crying.png";
			case 2:
				return "/assets/weeping_angels/entity/weeping_angel_frustrated.png";
			case 4:
				return "/assets/weeping_angels/entity/weeping_angel_screaming.png";
			default:
				return "/assets/weeping_angels/entity/weeping_angel_neutral.png";
		}
	}


	@Override
	protected boolean makeStepSound() {
		return super.makeStepSound();
	}

	@Override
	public void knockBack(Entity entity, int i, double d, double d1) {
		// No Operation
	}

	@Override
	protected String getHurtSound() {
		return "weeping_angels.stone_impact";
	}

	@Override
	public boolean hurt(Entity attacker, int i, DamageType type) {

		if (attacker instanceof EntityPlayer) {
			EntityPlayer entityPlayer = (EntityPlayer) attacker;
			if (entityPlayer.getHeldItem() != null && entityPlayer.getHeldItem().canHarvestBlock(entityPlayer, Block.stone)) {
				return super.hurt(attacker, i, type);
			}
		}

		return false;
	}

	@Override
	protected void attackEntity(Entity entity, float distance) {
		if (!isSeen() && this.attackTime <= 0 && distance < 2.0F && entity.bb.maxY > this.bb.minY && entity.bb.minY < this.bb.maxY) {
			this.attackTime = 20;
			entity.hurt(this, this.attackStrength, WADamageTypes.ANGEL_ATTACK);
		}

	}

	@Override
	protected boolean isMovementBlocked() {
		for (EntityPlayer player : world.players) {
			if (SightUtil.isOnScreen(this, player)) {
				setSeen(true);
				return true;
			}
		}
		setSeen(false);
		return false;
	}

	@Override
	public String getEntityTexture() {
		return getDefaultEntityTexture();
	}

	@Override
	public void tick() {

		if(!isSeen() && tickCount % 200 == 0) {
			setCurrentPose(random.nextInt(5));
		}

		if (tickCount % 1200 == 0) {
			if (isSeen()) {
				checkForTorchInRadius(x, y, z, 20);
			}
		}

		if(getTarget() == null){
			setTarget(world.getClosestPlayer(x, y, z, 70));
		}

		super.tick();


	}

	public void setSeen(boolean seen) {
		isSeen = seen;
		getEntityData().set(SEEN_SYNC, isSeen ? 1 : 0);
	}

	private boolean isSeen() {
		return getEntityData().getInt(SEEN_SYNC) == 1;
	}

	private boolean isTorch(double x, double y, double z) {
		Block block = world.getBlock((int) x, (int) y, (int) z);
		return block instanceof BlockTorch;
	}

	// Method to check for a torch within a radius of 20 blocks
	public boolean checkForTorchInRadius(double x, double y, double z, double radius) {
		// Loop through each block in the cube surrounding the (x, y, z) position
		for (double dx = -radius; dx <= radius; dx++) {
			for (double dy = -radius; dy <= radius; dy++) {
				for (double dz = -radius; dz <= radius; dz++) {
					// Calculate the new position to check
					double newX = x + dx;
					double newY = y + dy;
					double newZ = z + dz;

					// Check if this position has a torch
					if (isTorch(newX, newY, newZ)) {
						Block block = world.getBlock((int) newX, (int) newY, (int) newZ);
						block.dropBlockWithCause(world, EnumDropCause.WORLD, (int) newX, (int) newY, (int) newZ, 0, null);
						world.setBlock((int) newX, (int) newY, (int) newZ, 0);
					}
				}
			}
		}
		// No torch found within the given radius
		return false;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

}
