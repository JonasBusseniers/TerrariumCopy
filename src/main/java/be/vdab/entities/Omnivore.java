package be.vdab.entities;

public class Omnivore extends Organism {
	
	public String url = "images/carnivore.png";
	
	public Omnivore (int life, boolean hasActed){
        super(life, hasActed);
	}
	
	@Override
    public String toString() {
        return "Omni";
        
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
