package project.devices;

public abstract class Device {

		protected String name;
		
		public Device(String name){
			this.name = name;
		}
		
		public String getName(){
			return name;
		}
}
