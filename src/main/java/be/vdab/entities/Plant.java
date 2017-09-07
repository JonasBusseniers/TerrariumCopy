package be.vdab.entities;

public class Plant extends Organism
{
	public String url = "images/plant.png";
	
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Plant (int life, boolean hasActed)
    {
        super(life, hasActed);
    }

    @Override
    public String toString()   
    {
        return "Plant";
    }
}
