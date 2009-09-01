package ch.hsr.ifs.liquids.controller.devices;

import java.util.Iterator;

import ch.hsr.ifs.liquids.common.Audible;
import ch.hsr.ifs.liquids.controller.EventListener;
import ch.hsr.ifs.liquids.util.list.List;

public abstract class Device implements Audible {
	
	private static List<Device> devices = new List<Device>();
	
	protected List<EventListener> listeners = new List<EventListener>();

	public static void plugDevice(Class<? extends Device> deviceClass) {
		try {
			devices.add(deviceClass.newInstance());
		} catch (Exception e) {
			throw new RuntimeException("unable to instantiate '"
					+ deviceClass.getSimpleName() + "'", e);
		}
	}
	
	public static void removeDevices(Class<? extends Device> deviceClass){
		Iterator<Device> iterator = devices.iterator();
		while(iterator.hasNext()){
			Device device = iterator.next();
			if(deviceClass.isInstance(device)){
				device.removeDevice();
				iterator.remove();
			}
		}
	}
	
	public static void removeAllDevices(){
		for(Device device : devices){
			device.removeDevice();
		}
		
		devices = new List<Device>();
	}

	public <T extends EventListener> void addEventListener(T listener) {
		listeners.add(listener);
	}

	public abstract void removeDevice();
	
}
