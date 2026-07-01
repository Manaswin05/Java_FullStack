package streams;

public class Flight {
	private int code;
	private String Carrier;
	private String Source;
	private String destination;
	public Flight(int code, String carrier, String source, String destination) {
		this.code = code;
		Carrier = carrier;
		Source = source;
		this.destination = destination;
	}
	@Override
	public String toString() {
		return "Flight [code=" + code + ", Carrier=" + Carrier + ", Source=" + Source + ", destination=" + destination
				+ "]";
	}
	public int getCode() {
		return code;
	}
	public String getCarrier() {
		return Carrier;
	}
	public String getSource() {
		return Source;
	}
	public String getDestination() {
		return destination;
	}
	
	//setters
	public void setCode(int code) {
		this.code = code;
	}
	public void setCarrier(String carrier) {
		Carrier = carrier;
	}
	public void setSource(String source) {
		Source = source;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
}
