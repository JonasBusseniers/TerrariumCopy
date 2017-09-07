package be.vdab.entities;

public class Herbivore extends Organism
{
	public String url = "images/herbivore.png";
	
    public Herbivore (int life, boolean hasActed)
    {
        super(life, hasActed);
    }

    @Override
    public String toString()   
    {
        return "Herb";
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
