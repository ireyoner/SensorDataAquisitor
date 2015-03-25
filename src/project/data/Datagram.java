package project.data;


public class Datagram {

	private final Long id;
	private final String data;
	private boolean dataSend;
	
	public Datagram(long id, String data) {
		this.id = id;
		this.data = data;
		this.dataSend = false;
	}
	
	public Datagram(String data){
		this.id = null;
		this.data = data;
		this.dataSend = false;
	}
	
	public boolean isDataSend() {
		return dataSend;
	}

	public void setDataSend(boolean dataSend) {
		this.dataSend = dataSend;
	}

	public Long getId() {
		return id;
	}

	public String getData() {
		return data;
	}

}
