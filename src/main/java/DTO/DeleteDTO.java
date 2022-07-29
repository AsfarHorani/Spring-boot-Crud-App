package DTO;

public class DeleteDTO {

	String message;
	String hospitalId;
	public DeleteDTO(String message, String hospitalId) {
		super();
		this.message = message;
		this.hospitalId = hospitalId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	
}
