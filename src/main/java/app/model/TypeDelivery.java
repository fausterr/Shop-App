package app.model;

public enum TypeDelivery {
	INPOST("InPost(8,50zł)"),
	POCZTA_POLSKA("Poczta Polska(7,50zł)"),
	KURIER("Kurier(10zł)");
	
	private final String description;
	
	private TypeDelivery(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}