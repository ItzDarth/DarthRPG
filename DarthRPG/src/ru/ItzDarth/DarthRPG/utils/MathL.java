package ru.ItzDarth.DarthRPG.utils;

import org.bukkit.util.Vector;

public class MathL {
	
	private static final int SIN_BITS, SIN_MASK, SIN_COUNT;
    private static final double radFull, radToIndex;
    private static final double degFull, degToIndex;
    private static final double[] sin, cos;
    
    //
    // Математическая утилита с вычислением синуса и косинуса уже из готовых резервов, то есть мы при запуске
    // сервера уже рассчитали все возможные градусы и просто при синусе и косинусе получаем их
    //
    
    static {
        SIN_BITS = 12;
        SIN_MASK = ~(-1 << SIN_BITS);
        SIN_COUNT = SIN_MASK + 1;

        radFull = Math.PI * 2.0;
        degFull = 360.0;
        radToIndex = SIN_COUNT / radFull;
        degToIndex = SIN_COUNT / degFull;

        sin = new double[SIN_COUNT];
        cos = new double[SIN_COUNT];

        for (int i = 0; i < SIN_COUNT; i++) {
            sin[i] = Math.sin((i + 0.5f) / SIN_COUNT * radFull);
            cos[i] = Math.cos((i + 0.5f) / SIN_COUNT * radFull);
        }

        for (int i = 0; i < 360; i += 90) {
            sin[(int) (i * degToIndex) & SIN_MASK] = Math.sin(i * Math.PI / 180.0);
            cos[(int) (i * degToIndex) & SIN_MASK] = Math.cos(i * Math.PI / 180.0);
        }
    }

    public static double sin(double rad) {
        return sin[(int) (rad * radToIndex) & SIN_MASK];
    }

    public static double cos(double rad) {
        return cos[(int) (rad * radToIndex) & SIN_MASK];
    }
    
    public static Vector calculateVelocity(Vector from, Vector to, int heightGain) {
	    int endGain = to.getBlockY() - from.getBlockY();
	    double horizDist = Math.sqrt(distanceSquared(from, to));
	    int gain = heightGain;
	    double maxGain = gain > (endGain + gain) ? gain : (endGain + gain);
	    double a = -horizDist * horizDist / (4 * maxGain);
	    double b = horizDist;
	    double c = -endGain;
	    double slope = -b / (2 * a) - Math.sqrt(b * b - 4 * a * c) / (2 * a);
	    double vy = Math.sqrt(maxGain * 0.115);
	    double vh = vy / slope;
	    int dx = to.getBlockX() - from.getBlockX();
	    int dz = to.getBlockZ() - from.getBlockZ();
	    double mag = Math.sqrt(dx * dx + dz * dz);
	    double dirx = dx / mag;
    	double dirz = dz / mag;
    	double vx = vh * dirx;
    	double vz = vh * dirz;
    	
    	return new Vector(vx, vy, vz);
    }

    private static double distanceSquared(Vector from, Vector to) {
    	double dx = to.getBlockX() - from.getBlockX();
    	double dz = to.getBlockZ() - from.getBlockZ();
    	
    	return dx * dx + dz * dz;
    }

	
}
