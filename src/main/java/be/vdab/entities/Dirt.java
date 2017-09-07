package be.vdab.entities;

public class Dirt extends Organism {
	
	public String url = "images/dirt.png";
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Dirt(int life, boolean hasActed) {
		super(life, hasActed);
		// TODO Auto-generated constructor stub
	}
    @Override
    public String toString()   
    {
        return "Dirt";
    }

}
