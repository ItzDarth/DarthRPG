package ru.ItzDarth.DarthRPG.api.npc.nms;

import net.minecraft.server.v1_12_R1.*;

public class v1_8Path extends PathfinderGoal {

    private EntityCreature b;

    protected double a;

    private double c;

    private double d;

    private double e;

    public v1_8Path(EntityCreature entitycreature, double d0, double x, double y, double z) {
        this.b = entitycreature;
        this.a = d0;
        this.d = y;
        this.c = x;
        this.e = z;
    }

    @Override
    public boolean a() {
        Vec3D vec3d = RandomPositionGenerator.a(this.b, 5, 4);
        if (vec3d == null) return false;
        return true;
    }

    @Override
    public void c() {
        Vec3D vec3d = RandomPositionGenerator.a(this.b, 5, 4);
        if (vec3d == null) return;
        this.b.getNavigation().a(c, d, e, 2);
    }

    @Override
    public boolean b() {
        if ((this.b.ticksLived - this.b.hurtTimestamp) > 100) {
            this.b.b((EntityLiving) null);
            return false;
        }
        return !this.b.getNavigation().o();
    }
}