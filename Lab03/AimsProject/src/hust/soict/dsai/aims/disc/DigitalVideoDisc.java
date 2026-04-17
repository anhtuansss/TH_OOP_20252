package hust.soict.dsai.aims.disc;
public class DigitalVideoDisc {
    private String title;      
    private String category;  
    private String director;   
    private int length;        
    private float cost;

	// Class variable to track the total number of DVDs created
	private static int nbDigitalVideoDiscs = 0; 
	// Instance variable for unique ID
    private int id;

	public DigitalVideoDisc(String title) {
		super();
		this.title = title;
		nbDigitalVideoDiscs++; // Increment class counter
        this.id = nbDigitalVideoDiscs; // Assign unique ID
	}
	
	public DigitalVideoDisc(String title, String category, float cost) {
		super();
		this.title = title;
		this.category = category;
		this.cost = cost;
		nbDigitalVideoDiscs++; // Increment class counter
        this.id = nbDigitalVideoDiscs; // Assign unique ID
	}

	public DigitalVideoDisc(String title, String category, String director, float cost) {
		super();
		this.title = title;
		this.category = category;
		this.director = director;
		this.cost = cost;
		nbDigitalVideoDiscs++; // Increment class counter
        this.id = nbDigitalVideoDiscs; // Assign unique ID
	}

	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
		super();
		this.title = title;
		this.category = category;
		this.director = director;
		this.length = length;
		this.cost = cost;
		nbDigitalVideoDiscs++; // Increment class counter
        this.id = nbDigitalVideoDiscs; // Assign unique ID
	}

	@Override 
	public String toString() {
		return "DVD - " + title + " - " + category + " - " + director + " - " + length + ": " + cost + "$";
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isMatch(String title) {
		return this.getTitle().equalsIgnoreCase(title);
	}

	public String getTitle() {
		return title;
	}
	public String getCategory() {
		return category;
	}
	public String getDirector() {
		return director;
	}
	public int getLength() {
		return length;
	}
	public float getCost() {
		return cost;
	}        

	public int getId() {
		return id;
	}	
}
