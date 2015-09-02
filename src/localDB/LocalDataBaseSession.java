package localDB;

import java.math.BigDecimal;

public class LocalDataBaseSession {
	
	private BigDecimal id;
	private int datagramsEnqueued;
	private int datagramsReceived;
	private int datagramsSend_OK;
	private int datagramsSend_Failures;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) throws Exception {
		if (id == null) {
			this.id = id;
		} else {
			throw new Exception("Id is already set [current id: ("
					+ this.id
					+ ") new id: ("
					+ id
					+ ")]");
		}
	}

	public int getDatagramsEnqueued() {
		return datagramsEnqueued;
	}

	private void setDatagramsEnqueued(int datagramsEnqueued) {
		this.datagramsEnqueued = datagramsEnqueued;
	}
	private void addDatagramsEnqueued(int datagramsEnqueued) {
		setDatagramsEnqueued(getDatagramsEnqueued() +datagramsEnqueued);
	}
	private void addDatagramsEnqueued() {
		this.addDatagramsEnqueued(1);
	}

	public int getDatagramsReceived() {
		return datagramsReceived;
	}

	private void setDatagramsReceived(int datagramsReceived) {
		this.datagramsReceived = datagramsReceived;
	}
	private void addDatagramsReceived(int datagramsReceived) {
		setDatagramsReceived(getDatagramsReceived() +datagramsReceived);
	}
	private void addDatagramsReceived() {
		this.addDatagramsReceived(1);
	}

	public int getDatagramsSend_OK() {
		return datagramsSend_OK;
	}

	private void setDatagramsSend_OK(int datagramsSend_OK) {
		this.datagramsSend_OK = datagramsSend_OK;
	}
	private void addDatagramsSend_OK(int datagramsSend_OK) {
		setDatagramsSend_OK(getDatagramsSend_OK() +datagramsSend_OK);
	}
	private void addDatagramsSend_OK() {
		this.addDatagramsSend_OK(1);
	}

	public int getDatagramsSend_Failures() {
		return datagramsSend_Failures;
	}

	private void setDatagramsSend_Failures(int datagramsSend_Failures) {
		this.datagramsSend_Failures = datagramsSend_Failures;
	}
	private void addDatagramsSend_Failures(int datagramsSend_Failures) {
		setDatagramsSend_Failures(getDatagramsSend_Failures() +datagramsSend_Failures);
	}
	private void addDatagramsSend_Failures() {
		this.addDatagramsSend_Failures(1);
	}

	private void SetUpStartValues(){
		setDatagramsEnqueued(0);
		setDatagramsReceived(0);
		setDatagramsSend_OK(0);
		setDatagramsSend_Failures(0);
	}
	
	public LocalDataBaseSession() {
		SetUpStartValues();
	}

	public LocalDataBaseSession(BigDecimal id) throws Exception {
		this.setId(id);
		SetUpStartValues();
	}

}
