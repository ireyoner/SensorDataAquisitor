package project.devices;

import java.util.HashSet;
import java.util.Set;

public class HUB extends Device {

	private Set<CHIP> registredCHIPs;
	
	public HUB(String name) {
		super(name);
		registredCHIPs = new HashSet<CHIP>();
	}

	public void registerCHIP(CHIP chip) {
		registredCHIPs.add(chip);
	}

	public void unregisterCHIP(CHIP chip) {
		registredCHIPs.remove(chip);
	}

	public Set<CHIP> getRegistredCHIPs() {
		return registredCHIPs;
	}

	
}
