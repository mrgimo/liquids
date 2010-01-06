package ch.hsr.ifs.liquids.devices;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import ch.hsr.ifs.liquids.util.Vector;

import static gnu.io.CommPortIdentifier.*;

public class Accelerometer extends Device {

	private static final float STEP = 2;
	private static final float PRECISION = 10;

	private static final int BLOCK_LENGTH = 12;

	private static final int TENTH_BIT = 0x00000200;
	private static final int INVERSION_MASK = 0xfffffc00;

	private static final byte SYN_BYTE = (byte) 0xaa;

	private static Map<Byte, Accelerometer> devices = new HashMap<Byte, Accelerometer>();
	private static Queue<Accelerometer> queue = new LinkedList<Accelerometer>();

	static {
		try {
			open();
		} catch (UnsatisfiedLinkError e) {
			System.err.println(e.getMessage());
		}
	}

	private static void open() {
		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> identifiers = getPortIdentifiers();

		while (identifiers.hasMoreElements()) {
			CommPortIdentifier identifier = identifiers.nextElement();
			if (identifier.isCurrentlyOwned())
				continue;

			try {
				CommPort port = identifier.open("Accelerometer", 300);
				if (!(port instanceof SerialPort))
					continue;

				((SerialPort) port).setSerialPortParams(115200,
						SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);

				new Thread(updater(port.getInputStream())).start();
			} catch (Exception e) {
				continue;
			}
		}
	}

	private static Runnable updater(final InputStream input) {
		return new Runnable() {

			public void run() {
				try {
					update();
				} catch (IOException e) {
					return;
				} finally {
					try {
						input.close();
					} catch (IOException e) { }
				}
			}

			private void update() throws IOException {
				byte[] data = new byte[BLOCK_LENGTH];

				int read, off = 0;
				while ((read = input.read(data, off, BLOCK_LENGTH - off)) != -1) {
					if ((off += read) != BLOCK_LENGTH)
						continue;

					if (isValid(data))
						process(data);
					else
						input.skip(1);

					off = 0;
				}
			}

		};
	}

	private static boolean isValid(byte[] data) {
		if (data[0] != SYN_BYTE)
			return false;

		byte pA = 0;
		byte pB = 0;

		for (int i = 0; i < BLOCK_LENGTH - 2; ++i) {
			pA += data[i];
			pB += pA;
		}

		return (data[BLOCK_LENGTH - 2] == pA && data[BLOCK_LENGTH - 1] == pB) || true; // TODO: fix it!
	}

	private static void process(byte[] data) {
		Accelerometer accelerometer;
		if ((accelerometer = fetch(data[1])) != null)
			accelerometer.updatePosition(data);
	}

	private static int toInt(byte b) {
		return b & 0xff;
	}

	private static Accelerometer fetch(byte id) {
		Accelerometer accelerometer;
		if ((accelerometer = devices.get(id)) != null)
			return accelerometer;

		if ((accelerometer = queue.poll()) == null)
			return null;

		accelerometer.id = id;
		devices.put(id, accelerometer);

		return accelerometer;
	}

	private byte id;

	public Accelerometer(Vector position) {
		super(position);
	}

	private void updatePosition(byte[] data) {
		int x = compose(data[2], data[3]);
		int y = compose(data[4], data[5]);
		int z = compose(data[6], data[7]);

		setX(getX() + calcX(x, z));
		setY(getY() + calcY(y, z));
	}

	private int compose(byte l, byte h) {
		int value = (toInt(h) << 8) | toInt(l);
		return (value & TENTH_BIT) == 0 ? value : value | INVERSION_MASK;
	}

	private float calcX(int x, int z) {
		return STEP * Math.round(PRECISION * Math.atan2(x, z));
	}

	private float calcY(int y, int z) {
		return STEP * Math.round(PRECISION * Math.atan2(y, z));
	}

	@Override
	public void plug() {
		queue.offer(this);
	}

	@Override
	public void unplug() {
		devices.remove(id);
	}

}
