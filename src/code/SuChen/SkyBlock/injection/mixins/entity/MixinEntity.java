//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Administrator\Documents\mcp918\conf"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockFence
 *  net.minecraft.block.BlockFenceGate
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockWall
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.crash.CrashReport
 *  net.minecraft.crash.CrashReportCategory
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ReportedException
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package code.SuChen.SkyBlock.injection.mixins.entity;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={Entity.class})
public abstract class MixinEntity {
    @Shadow
    public double posX;
    @Shadow
    public double posY;
    @Shadow
    public double posZ;
    @Shadow
    public double motionX;
    @Shadow
    public double motionY;
    @Shadow
    public double motionZ;
    @Shadow
    public Entity ridingEntity;
    @Shadow
    public float rotationYaw;
    @Shadow
    public float rotationPitch;
    @Shadow
    public float prevRotationYaw;
    @Shadow
    public float prevRotationPitch;
    @Shadow
    protected int portalCounter;
    @Shadow
    public boolean onGround;
    @Shadow
    protected boolean isImmuneToFire;
    @Shadow
    public boolean isCollidedHorizontally;
    @Shadow
    public boolean isCollidedVertically;
    @Shadow
    protected boolean isInWeb;
    @Shadow
    public boolean noClip;
    @Shadow
    public World worldObj;
    @Shadow
    public float stepHeight;
    @Shadow
    public boolean isCollided;
    @Shadow
    public float distanceWalkedModified;
    @Shadow
    public float distanceWalkedOnStepModified;
    @Shadow
    private int nextStepDistance;
    @Shadow
    protected Random rand;
    @Shadow
    private int fire;
    @Shadow
    public int fireResistance;
    @Shadow
    public float width;
    @Shadow
    protected boolean inPortal;
    @Shadow
    public int timeUntilPortal;
    @Shadow
    public AxisAlignedBB boundingBox;
    @Shadow
    public float prevDistanceWalkedModified;
    @Shadow
    public double prevPosX;
    @Shadow
    public double prevPosY;
    @Shadow
    public double prevPosZ;
    @Shadow
    public float fallDistance;
    @Shadow
    public boolean firstUpdate;
    @Shadow
    protected UUID entityUniqueID;
    Minecraft mc = Minecraft.getMinecraft();

    @Shadow
    public abstract boolean isSprinting();

    @Shadow
    public abstract boolean isRiding();

    public int getFire() {
        return this.fire;
    }

    public int getNextStepDistance() {
        return this.nextStepDistance;
    }

    public void setNextStepDistance(int nextStepDistance) {
        this.nextStepDistance = nextStepDistance;
    }

    public AxisAlignedBB getEntityBoundingBox() {
        return this.boundingBox;
    }

    @Shadow
    public abstract UUID getUniqueID();

    @Shadow
    protected abstract float getEyeHeight();

    @Overwrite
    public void moveEntity(double p_moveEntity_1_, double p_moveEntity_3_, double p_moveEntity_5_) {
        if (this.noClip) {
            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(p_moveEntity_1_, p_moveEntity_3_, p_moveEntity_5_));
            this.resetPositionToBB();
        } else {
            Block block;
            this.worldObj.theProfiler.startSection("move");
            double d0 = this.posX;
            double d1 = this.posY;
            double d2 = this.posZ;
            if (this.isInWeb) {
                this.isInWeb = false;
                p_moveEntity_1_ *= 0.25;
                p_moveEntity_3_ *= (double)0.05f;
                p_moveEntity_5_ *= 0.25;
                this.motionX = 0.0;
                this.motionY = 0.0;
                this.motionZ = 0.0;
            }
            double d3 = p_moveEntity_1_;
            double d4 = p_moveEntity_3_;
            double d5 = p_moveEntity_5_;
            boolean flag = this.onGround && this.isSneaking() && (Entity)this instanceof EntityPlayer;
            boolean bl = flag;
            if (flag) {
                double d6 = 0.05;
                while (p_moveEntity_1_ != 0.0 && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.getEntityBoundingBox().offset(p_moveEntity_1_, -1.0, 0.0)).isEmpty()) {
                    d3 = p_moveEntity_1_ = p_moveEntity_1_ < d6 && p_moveEntity_1_ >= -d6 ? 0.0 : (p_moveEntity_1_ > 0.0 ? (p_moveEntity_1_ = p_moveEntity_1_ - d6) : (p_moveEntity_1_ = p_moveEntity_1_ + d6));
                }
                while (p_moveEntity_5_ != 0.0 && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.getEntityBoundingBox().offset(0.0, -1.0, p_moveEntity_5_)).isEmpty()) {
                    d5 = p_moveEntity_5_ = p_moveEntity_5_ < d6 && p_moveEntity_5_ >= -d6 ? 0.0 : (p_moveEntity_5_ > 0.0 ? (p_moveEntity_5_ = p_moveEntity_5_ - d6) : (p_moveEntity_5_ = p_moveEntity_5_ + d6));
                }
                while (p_moveEntity_1_ != 0.0 && p_moveEntity_5_ != 0.0 && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.getEntityBoundingBox().offset(p_moveEntity_1_, -1.0, p_moveEntity_5_)).isEmpty()) {
                    d3 = p_moveEntity_1_ = p_moveEntity_1_ < d6 && p_moveEntity_1_ >= -d6 ? 0.0 : (p_moveEntity_1_ > 0.0 ? (p_moveEntity_1_ = p_moveEntity_1_ - d6) : (p_moveEntity_1_ = p_moveEntity_1_ + d6));
                    d5 = p_moveEntity_5_ = p_moveEntity_5_ < d6 && p_moveEntity_5_ >= -d6 ? 0.0 : (p_moveEntity_5_ > 0.0 ? (p_moveEntity_5_ = p_moveEntity_5_ - d6) : (p_moveEntity_5_ = p_moveEntity_5_ + d6));
                }
            }
            List list1 = this.worldObj.getCollidingBoundingBoxes((Entity)this, this.getEntityBoundingBox().addCoord(p_moveEntity_1_, p_moveEntity_3_, p_moveEntity_5_));
            AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
            for (Object axisalignedbb1 : list1) {
                p_moveEntity_3_ = axisalignedbb1.calculateYOffset(this.getEntityBoundingBox(), p_moveEntity_3_);
            }
            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, p_moveEntity_3_, 0.0));
            boolean flag1 = this.onGround || d4 != p_moveEntity_3_ && d4 < 0.0;
            for (AxisAlignedBB axisalignedbb2 : list1) {
                p_moveEntity_1_ = axisalignedbb2.calculateXOffset(this.getEntityBoundingBox(), p_moveEntity_1_);
            }
            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(p_moveEntity_1_, 0.0, 0.0));
            for (AxisAlignedBB axisalignedbb13 : list1) {
                p_moveEntity_5_ = axisalignedbb13.calculateZOffset(this.getEntityBoundingBox(), p_moveEntity_5_);
            }
            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, 0.0, p_moveEntity_5_));
            if (this.stepHeight > 0.0f && flag1 && (d3 != p_moveEntity_1_ || d5 != p_moveEntity_5_)) {
                double d11 = p_moveEntity_1_;
                double d7 = p_moveEntity_3_;
                double d8 = p_moveEntity_5_;
                AxisAlignedBB axisalignedbb3 = this.getEntityBoundingBox();
                this.setEntityBoundingBox(axisalignedbb);
                p_moveEntity_3_ = this.stepHeight;
                List list = this.worldObj.getCollidingBoundingBoxes((Entity)this, this.getEntityBoundingBox().addCoord(d3, p_moveEntity_3_, d5));
                AxisAlignedBB axisalignedbb4 = this.getEntityBoundingBox();
                AxisAlignedBB axisalignedbb5 = axisalignedbb4.addCoord(d3, 0.0, d5);
                double d9 = p_moveEntity_3_;
                for (AxisAlignedBB axisalignedbb6 : list) {
                    d9 = axisalignedbb6.calculateYOffset(axisalignedbb5, d9);
                }
                axisalignedbb4 = axisalignedbb4.offset(0.0, d9, 0.0);
                double d15 = d3;
                for (AxisAlignedBB axisalignedbb7 : list) {
                    d15 = axisalignedbb7.calculateXOffset(axisalignedbb4, d15);
                }
                axisalignedbb4 = axisalignedbb4.offset(d15, 0.0, 0.0);
                double d16 = d5;
                for (AxisAlignedBB axisalignedbb8 : list) {
                    d16 = axisalignedbb8.calculateZOffset(axisalignedbb4, d16);
                }
                axisalignedbb4 = axisalignedbb4.offset(0.0, 0.0, d16);
                AxisAlignedBB axisalignedbb14 = this.getEntityBoundingBox();
                double d17 = p_moveEntity_3_;
                for (AxisAlignedBB axisalignedbb9 : list) {
                    d17 = axisalignedbb9.calculateYOffset(axisalignedbb14, d17);
                }
                axisalignedbb14 = axisalignedbb14.offset(0.0, d17, 0.0);
                double d18 = d3;
                for (AxisAlignedBB axisalignedbb10 : list) {
                    d18 = axisalignedbb10.calculateXOffset(axisalignedbb14, d18);
                }
                axisalignedbb14 = axisalignedbb14.offset(d18, 0.0, 0.0);
                double d19 = d5;
                for (AxisAlignedBB axisalignedbb11 : list) {
                    d19 = axisalignedbb11.calculateZOffset(axisalignedbb14, d19);
                }
                axisalignedbb14 = axisalignedbb14.offset(0.0, 0.0, d19);
                double d20 = d15 * d15 + d16 * d16;
                double d10 = d18 * d18 + d19 * d19;
                if (d20 > d10) {
                    p_moveEntity_1_ = d15;
                    p_moveEntity_5_ = d16;
                    p_moveEntity_3_ = -d9;
                    this.setEntityBoundingBox(axisalignedbb4);
                } else {
                    p_moveEntity_1_ = d18;
                    p_moveEntity_5_ = d19;
                    p_moveEntity_3_ = -d17;
                    this.setEntityBoundingBox(axisalignedbb14);
                }
                for (AxisAlignedBB axisalignedbb12 : list) {
                    p_moveEntity_3_ = axisalignedbb12.calculateYOffset(this.getEntityBoundingBox(), p_moveEntity_3_);
                }
                this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, p_moveEntity_3_, 0.0));
                if (d11 * d11 + d8 * d8 >= p_moveEntity_1_ * p_moveEntity_1_ + p_moveEntity_5_ * p_moveEntity_5_) {
                    p_moveEntity_1_ = d11;
                    p_moveEntity_3_ = d7;
                    p_moveEntity_5_ = d8;
                    this.setEntityBoundingBox(axisalignedbb3);
                }
            }
            this.worldObj.theProfiler.endSection();
            this.worldObj.theProfiler.startSection("rest");
            this.resetPositionToBB();
            this.isCollidedHorizontally = d3 != p_moveEntity_1_ || d5 != p_moveEntity_5_;
            this.isCollidedVertically = d4 != p_moveEntity_3_;
            this.onGround = this.isCollidedVertically && d4 < 0.0;
            this.isCollided = this.isCollidedHorizontally || this.isCollidedVertically;
            int i = MathHelper.floor_double((double)this.posX);
            int j = MathHelper.floor_double((double)(this.posY - (double)0.2f));
            int k = MathHelper.floor_double((double)this.posZ);
            BlockPos blockpos = new BlockPos(i, j, k);
            Block block1 = this.worldObj.getBlockState(blockpos).getBlock();
            if (block1.getMaterial() == Material.air && ((block = this.worldObj.getBlockState(blockpos.down()).getBlock()) instanceof BlockFence || block instanceof BlockWall || block instanceof BlockFenceGate)) {
                block1 = block;
                blockpos = blockpos.down();
            }
            this.updateFallState(p_moveEntity_3_, this.onGround, block1, blockpos);
            if (d3 != p_moveEntity_1_) {
                this.motionX = 0.0;
            }
            if (d5 != p_moveEntity_5_) {
                this.motionZ = 0.0;
            }
            if (d4 != p_moveEntity_3_) {
                block1.onLanded(this.worldObj, (Entity)this);
            }
            if (this.canTriggerWalking() && !flag && this.ridingEntity == null) {
                double d12 = this.posX - d0;
                double d13 = this.posY - d1;
                double d14 = this.posZ - d2;
                if (block1 != Blocks.ladder) {
                    d13 = 0.0;
                }
                if (block1 != null && this.onGround) {
                    block1.onEntityCollidedWithBlock(this.worldObj, blockpos, (Entity)this);
                }
                this.distanceWalkedModified = (float)((double)this.distanceWalkedModified + (double)MathHelper.sqrt_double((double)(d12 * d12 + d14 * d14)) * 0.6);
                this.distanceWalkedOnStepModified = (float)((double)this.distanceWalkedOnStepModified + (double)MathHelper.sqrt_double((double)(d12 * d12 + d13 * d13 + d14 * d14)) * 0.6);
                if (this.distanceWalkedOnStepModified > (float)this.nextStepDistance && block1.getMaterial() != Material.air) {
                    this.nextStepDistance = (int)this.distanceWalkedOnStepModified + 1;
                    if (this.isInWater()) {
                        float f = MathHelper.sqrt_double((double)(this.motionX * this.motionX * (double)0.2f + this.motionY * this.motionY + this.motionZ * this.motionZ * (double)0.2f)) * 0.35f;
                        if (f > 1.0f) {
                            f = 1.0f;
                        }
                        this.playSound(this.getSwimSound(), f, 1.0f + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4f);
                    }
                    this.playStepSound(blockpos, block1);
                }
            }
            try {
                this.doBlockCollisions();
            }
            catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.makeCrashReport((Throwable)throwable, (String)"Checking entity block collision");
                CrashReportCategory crashreportcategory = crashreport.makeCategory("Entity being checked for collision");
                this.addEntityCrashInfo(crashreportcategory);
                throw new ReportedException(crashreport);
            }
            boolean flag2 = this.isWet();
            if (this.worldObj.isFlammableWithin(this.getEntityBoundingBox().contract(0.001, 0.001, 0.001))) {
                this.dealFireDamage(1);
                if (!flag2) {
                    ++this.fire;
                    if (this.fire == 0) {
                        this.setFire(8);
                    }
                }
            } else if (this.fire <= 0) {
                this.fire = -this.fireResistance;
            }
            if (flag2 && this.fire > 0) {
                this.playSound("random.fizz", 0.7f, 1.6f + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4f);
                this.fire = -this.fireResistance;
            }
            this.worldObj.theProfiler.endSection();
        }
    }

    @Overwrite
    public void onEntityUpdate() {
        this.worldObj.theProfiler.startSection("entityBaseTick");
        if (this.ridingEntity != null && this.ridingEntity.isDead) {
            this.ridingEntity = null;
        }
        this.prevDistanceWalkedModified = this.distanceWalkedModified;
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.prevRotationPitch = this.rotationPitch;
        this.prevRotationYaw = this.rotationYaw;
        if (!this.worldObj.isRemote && this.worldObj instanceof WorldServer) {
            this.worldObj.theProfiler.startSection("portal");
            MinecraftServer minecraftserver = ((WorldServer)this.worldObj).getMinecraftServer();
            int i = this.getMaxInPortalTime();
            if (this.inPortal) {
                if (minecraftserver.getAllowNether()) {
                    if (this.ridingEntity == null && this.portalCounter++ >= i) {
                        this.portalCounter = i;
                        this.timeUntilPortal = this.getPortalCooldown();
                        int j = this.worldObj.provider.getDimensionId() == -1 ? 0 : -1;
                        this.travelToDimension(j);
                    }
                    this.inPortal = false;
                }
            } else {
                if (this.portalCounter > 0) {
                    this.portalCounter -= 4;
                }
                if (this.portalCounter < 0) {
                    this.portalCounter = 0;
                }
            }
            if (this.timeUntilPortal > 0) {
                --this.timeUntilPortal;
            }
            this.worldObj.theProfiler.endSection();
        }
        this.spawnRunningParticles();
        this.handleWaterMovement();
        if (this.worldObj.isRemote) {
            this.fire = 0;
        } else if (this.fire > 0) {
            if (this.isImmuneToFire) {
                this.fire -= 4;
                if (this.fire < 0) {
                    this.fire = 0;
                }
            } else {
                if (this.fire % 20 == 0) {
                    this.attackEntityFrom(DamageSource.onFire, 1.0f);
                }
                --this.fire;
            }
        }
        if (this.isInLava()) {
            this.setOnFireFromLava();
            this.fallDistance *= 0.5f;
        }
        if (this.posY < -64.0) {
            this.kill();
        }
        if (!this.worldObj.isRemote) {
            this.setFlag(0, this.fire > 0);
        }
        this.firstUpdate = false;
        this.worldObj.theProfiler.endSection();
    }

    public boolean isInLiquid() {
        if (this.mc.thePlayer == null) {
            return false;
        }
        for (int x = MathHelper.floor_double((double)this.boundingBox.minX); x < MathHelper.floor_double((double)this.boundingBox.maxX) + 1; ++x) {
            for (int z = MathHelper.floor_double((double)this.boundingBox.minZ); z < MathHelper.floor_double((double)this.boundingBox.maxZ) + 1; ++z) {
                BlockPos pos = new BlockPos(x, (int)this.boundingBox.minY, z);
                Block block = this.mc.theWorld.getBlockState(pos).getBlock();
                if (block == null || block instanceof BlockAir) continue;
                return block instanceof BlockLiquid;
            }
        }
        return false;
    }

    public boolean isMoving() {
        if (this.mc.thePlayer.movementInput.moveForward != 0.0f) {
            return true;
        }
        return this.mc.thePlayer.movementInput.moveStrafe != 0.0f;
    }

    @Shadow
    public abstract boolean isInLava();

    @Shadow
    protected abstract void setOnFireFromLava();

    @Shadow
    protected abstract void kill();

    @Shadow
    protected abstract void setFlag(int var1, boolean var2);

    @Shadow
    public abstract boolean attackEntityFrom(DamageSource var1, float var2);

    @Shadow
    public abstract int getPortalCooldown();

    @Shadow
    public abstract int getMaxInPortalTime();

    @Shadow
    public abstract boolean handleWaterMovement();

    @Shadow
    public abstract void travelToDimension(int var1);

    @Shadow
    public abstract void spawnRunningParticles();

    @Shadow
    public abstract void setFire(int var1);

    @Shadow
    protected abstract void dealFireDamage(int var1);

    @Shadow
    public abstract boolean isWet();

    @Shadow
    public abstract void addEntityCrashInfo(CrashReportCategory var1);

    @Shadow
    public abstract void setEntityBoundingBox(AxisAlignedBB var1);

    @Shadow
    protected abstract void resetPositionToBB();

    @Shadow
    public abstract boolean isSneaking();

    @Shadow
    protected abstract boolean canTriggerWalking();

    @Shadow
    public abstract boolean isInWater();

    @Shadow
    protected abstract String getSwimSound();

    @Shadow
    protected abstract void doBlockCollisions();

    @Shadow
    protected abstract void playStepSound(BlockPos var1, Block var2);

    @Shadow
    public abstract void playSound(String var1, float var2, float var3);

    @Shadow
    protected abstract void updateFallState(double var1, boolean var3, Block var4, BlockPos var5);
}

