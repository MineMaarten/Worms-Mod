package wormsmod.common.world;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import wormsmod.common.entity.projectile.EntityWormProjectile;

public class ExplosionWorms extends Explosion{
    private final int field_77289_h = 16;
    private final World worldObj;
    private final Map field_77288_k = new HashMap();
    private final double entityDamageFactor;
    private final double propelFactor;

    public ExplosionWorms(World par1World, Entity par2Entity, double x, double y, double z, float baseDamage,
            double entityDamageFactor, double propelFactor){
        super(par1World, par2Entity, x, y, z, baseDamage);
        worldObj = par1World;
        this.entityDamageFactor = entityDamageFactor;
        this.propelFactor = propelFactor;
    }

    /**
     * Does the first part of the explosion (destroy blocks)
     * Copied from superclass, as we need to modify the damage done to entities. Not really clean, but it does the job.
     */
    @Override
    public void doExplosionA(){
        float f = explosionSize;
        HashSet hashset = new HashSet();
        int i;
        int j;
        int k;
        double d0;
        double d1;
        double d2;

        for(i = 0; i < field_77289_h; ++i) {
            for(j = 0; j < field_77289_h; ++j) {
                for(k = 0; k < field_77289_h; ++k) {
                    if(i == 0 || i == field_77289_h - 1 || j == 0 || j == field_77289_h - 1 || k == 0 || k == field_77289_h - 1) {
                        double d3 = i / (field_77289_h - 1.0F) * 2.0F - 1.0F;
                        double d4 = j / (field_77289_h - 1.0F) * 2.0F - 1.0F;
                        double d5 = k / (field_77289_h - 1.0F) * 2.0F - 1.0F;
                        double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
                        d3 /= d6;
                        d4 /= d6;
                        d5 /= d6;
                        float f1 = explosionSize * (0.7F + worldObj.rand.nextFloat() * 0.6F);
                        d0 = explosionX;
                        d1 = explosionY;
                        d2 = explosionZ;

                        for(float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F) {
                            int l = MathHelper.floor_double(d0);
                            int i1 = MathHelper.floor_double(d1);
                            int j1 = MathHelper.floor_double(d2);
                            int k1 = worldObj.getBlockId(l, i1, j1);

                            if(k1 > 0) {
                                Block block = Block.blocksList[k1];
                                float f3 = exploder != null ? exploder.getBlockExplosionResistance(this, worldObj, l, i1, j1, block) : block.getExplosionResistance(exploder, worldObj, l, i1, j1, explosionX, explosionY, explosionZ);
                                f1 -= (f3 + 0.3F) * f2;
                            }

                            if(f1 > 0.0F && (exploder == null || exploder.shouldExplodeBlock(this, worldObj, l, i1, j1, k1, f1))) {
                                hashset.add(new ChunkPosition(l, i1, j1));
                            }

                            d0 += d3 * f2;
                            d1 += d4 * f2;
                            d2 += d5 * f2;
                        }
                    }
                }
            }
        }

        affectedBlockPositions.addAll(hashset);
        explosionSize *= 2.0F;
        i = MathHelper.floor_double(explosionX - explosionSize - 1.0D);
        j = MathHelper.floor_double(explosionX + explosionSize + 1.0D);
        k = MathHelper.floor_double(explosionY - explosionSize - 1.0D);
        int l1 = MathHelper.floor_double(explosionY + explosionSize + 1.0D);
        int i2 = MathHelper.floor_double(explosionZ - explosionSize - 1.0D);
        int j2 = MathHelper.floor_double(explosionZ + explosionSize + 1.0D);
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(exploder, AxisAlignedBB.getAABBPool().getAABB(i, k, i2, j, l1, j2));
        Vec3 vec3 = worldObj.getWorldVec3Pool().getVecFromPool(explosionX, explosionY, explosionZ);

        for(int k2 = 0; k2 < list.size(); ++k2) {
            Entity entity = (Entity)list.get(k2);
            double d7 = entity.getDistance(explosionX, explosionY, explosionZ) / explosionSize;

            if(d7 <= 1.0D) {
                d0 = entity.posX - explosionX;
                d1 = entity.posY + entity.getEyeHeight() - explosionY;
                d2 = entity.posZ - explosionZ;
                double d8 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);

                if(d8 != 0.0D) {
                    d0 /= d8;
                    d1 /= d8;
                    d2 /= d8;
                    double d9 = worldObj.getBlockDensity(vec3, entity.boundingBox);
                    double d10 = (1.0D - d7) * d9 * propelFactor;
                    if(!(entity instanceof EntityWormProjectile)) entity.attackEntityFrom(DamageSource.setExplosionSource(this), (int)(((d10 * d10 + d10) / 2.0D * 8.0D * explosionSize + 1.0D) * entityDamageFactor));
                    double d11 = EnchantmentProtection.func_92092_a(entity, d10);
                    entity.motionX += d0 * d11;
                    entity.motionY += d1 * d11;
                    entity.motionZ += d2 * d11;

                    if(entity instanceof EntityPlayer) {
                        field_77288_k.put(entity, worldObj.getWorldVec3Pool().getVecFromPool(d0 * d10, d1 * d10, d2 * d10));
                    }
                }
            }
        }

        explosionSize = f;
    }

    @Override
    public Map func_77277_b(){
        return field_77288_k;
    }

}
