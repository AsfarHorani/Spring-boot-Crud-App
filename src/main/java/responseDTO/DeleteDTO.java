package responseDTO;

public class DeleteDTO {

	String message;
	String id;
	public DeleteDTO( String id, String entity) {
		super();
		this.message = entity + "with id: " + id+ " has been deleted succesfully";
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
