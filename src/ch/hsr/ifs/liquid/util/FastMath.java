package ch.hsr.ifs.liquid.util;

public class FastMath {

	/**
	 * This is not faster than Math.PI, but it's a float not a double to remove
	 * unnecessary casting operations
	 */
	public static final float PI = 3.14159265358979323846264338327950288f;
	public static final float PISQ = 9.86960440108935861883449099987615114f;
	public static final float HALF_PI = 1.57079632679489661923132169163975144f;
	public static final float TWO_PI = 6.28318530717958647692528676655900577f;

	/*
	 * fast atan2, I think we do not need the precision provided by Math.atan2,
	 * we will break this down to 36 bins anyway
	 */
	static float coeff_1 = PI / 4f;
	static float coeff_2 = 3f * coeff_1;

	static double lookup[] = { 0.0, 0.17632698070846498, 0.36397023426620234,
			0.5773502691896257, 0.8390996311772799, 1.19175359259421,
			1.7320508075688767, 2.7474774194546216, 5.671281819617707,
			1.633123935319537E16 };

	/**
	 * approximative atan2. Error of up to 0.07 radians, but 18x faster that
	 * Math.atan2
	 * 
	 * @param y
	 * @param x
	 * @return
	 */
	public final static float atan2(float y, float x) {
		if (y == 0 && x == 0) {
			return 0;
		}

		float abs_y = Math.abs(y);
		float angle;

		if (x >= 0d) {
			float r = (x - abs_y) / (x + abs_y);
			angle = coeff_1 - coeff_1 * r;
		} else {
			float r = (x + abs_y) / (abs_y - x);
			angle = coeff_2 - coeff_1 * r;
		}

		return y < 0d ? -angle : angle;
	}

	/**
	 * fast single precision sin
	 * 
	 * @param x
	 * @return sin(x)
	 */
	public static float sin(float x) {
		float B = 4 / PI;
		float C = -4 / PISQ;

		float y = B * x + C * x * Math.abs(x);
		float P = 0.225f;
		y = P * (y * Math.abs(y) - y) + y;
		return y;
	}

	/**
	 * fast single precision cos
	 * 
	 * @param x
	 * @return cos(x)
	 */
	public static float cos(float x) {
		x += HALF_PI;
		if (x > PI) {
			x -= TWO_PI;
		}
		return sin(x);
	}

	/*
	 * exp approximation exploiting IEEE 754 Floating Point Number properties
	 */
	public static double exp(double val) {
		final long tmp = (long) (1512775 * val + (1072693248 - 60801));
		return Double.longBitsToDouble(tmp << 32);
	}

	/*
	 * fast approximation of math.pow this does however have an error of about
	 * 10%, which makes it unusable...
	 */
	public static double pow(final double a, final double b) {
		final int x = (int) (Double.doubleToLongBits(a) >> 32);
		final int y = (int) (b * (x - 1072632447) + 1072632447);
		return Double.longBitsToDouble(((long) y) << 32);
	}

	final static int[] table = { 0, 16, 22, 27, 32, 35, 39, 42, 45, 48, 50, 53,
			55, 57, 59, 61, 64, 65, 67, 69, 71, 73, 75, 76, 78, 80, 81, 83, 84,
			86, 87, 89, 90, 91, 93, 94, 96, 97, 98, 99, 101, 102, 103, 104,
			106, 107, 108, 109, 110, 112, 113, 114, 115, 116, 117, 118, 119,
			120, 121, 122, 123, 124, 125, 126, 128, 128, 129, 130, 131, 132,
			133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 144,
			145, 146, 147, 148, 149, 150, 150, 151, 152, 153, 154, 155, 155,
			156, 157, 158, 159, 160, 160, 161, 162, 163, 163, 164, 165, 166,
			167, 167, 168, 169, 170, 170, 171, 172, 173, 173, 174, 175, 176,
			176, 177, 178, 178, 179, 180, 181, 181, 182, 183, 183, 184, 185,
			185, 186, 187, 187, 188, 189, 189, 190, 191, 192, 192, 193, 193,
			194, 195, 195, 196, 197, 197, 198, 199, 199, 200, 201, 201, 202,
			203, 203, 204, 204, 205, 206, 206, 207, 208, 208, 209, 209, 210,
			211, 211, 212, 212, 213, 214, 214, 215, 215, 216, 217, 217, 218,
			218, 219, 219, 220, 221, 221, 222, 222, 223, 224, 224, 225, 225,
			226, 226, 227, 227, 228, 229, 229, 230, 230, 231, 231, 232, 232,
			233, 234, 234, 235, 235, 236, 236, 237, 237, 238, 238, 239, 240,
			240, 241, 241, 242, 242, 243, 243, 244, 244, 245, 245, 246, 246,
			247, 247, 248, 248, 249, 249, 250, 250, 251, 251, 252, 252, 253,
			253, 254, 254, 255 };

	/**
	 * A faster replacement for (int)(java.lang.Math.sqrt(x)). Completely
	 * accurate for x < 2147483648 (i.e. 2^31)...
	 */
	public static int sqrt(int x) {
		int xn;

		if (x >= 0x10000) {
			if (x >= 0x1000000) {
				if (x >= 0x10000000) {
					if (x >= 0x40000000) {
						xn = table[x >> 24] << 8;
					} else {
						xn = table[x >> 22] << 7;
					}
				} else {
					if (x >= 0x4000000) {
						xn = table[x >> 20] << 6;
					} else {
						xn = table[x >> 18] << 5;
					}
				}

				xn = (xn + 1 + (x / xn)) >> 1;
				xn = (xn + 1 + (x / xn)) >> 1;
				return ((xn * xn) > x) ? --xn : xn;
			} else {
				if (x >= 0x100000) {
					if (x >= 0x400000) {
						xn = table[x >> 16] << 4;
					} else {
						xn = table[x >> 14] << 3;
					}
				} else {
					if (x >= 0x40000) {
						xn = table[x >> 12] << 2;
					} else {
						xn = table[x >> 10] << 1;
					}
				}

				xn = (xn + 1 + (x / xn)) >> 1;

				return ((xn * xn) > x) ? --xn : xn;
			}
		} else {
			if (x >= 0x100) {
				if (x >= 0x1000) {
					if (x >= 0x4000) {
						xn = (table[x >> 8]) + 1;
					} else {
						xn = (table[x >> 6] >> 1) + 1;
					}
				} else {
					if (x >= 0x400) {
						xn = (table[x >> 4] >> 2) + 1;
					} else {
						xn = (table[x >> 2] >> 3) + 1;
					}
				}

				return ((xn * xn) > x) ? --xn : xn;
			} else {
				if (x >= 0) {
					return table[x] >> 4;
				}
			}
		}
		return -1;
	}

	public static float invSqrt(float x) {
		float half = 0.5F * x;
		int i = Float.floatToIntBits(x);
		i = 0x5f375a86 - (i >> 1);
		x = Float.intBitsToFloat(i);
		return x * (1.5F - half * x * x);
	}
	
	public static float invInvSqrt(float x){
		return x * invSqrt(x);
	}

	/** sqrt(a^2 + b^2) without under/overflow. * */

	public static double hypot(double a, double b) {
		double r;
		if (Math.abs(a) > Math.abs(b)) {
			r = b / a;
			r = Math.abs(a) * Math.sqrt(1 + r * r);
		} else if (b != 0) {
			r = a / b;
			r = Math.abs(b) * Math.sqrt(1 + r * r);
		} else {
			r = 0.0;
		}
		return r;
	}
}
