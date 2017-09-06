package be.vdab.entities;

public class Plant extends Organism
{
	public String url = "images/plant.png";
	
    public Plant (int life, boolean hasActed)
    {
        super(life, hasActed);
    }

    @Override
    public String toString()   
    {
        return "P";
    }
}
