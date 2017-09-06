package be.vdab.entities;


public class Carnivore extends Organism
{
	public String url = "images/carnivore.png";
	
    public Carnivore (int life, boolean hasActed)
    {
        super(life, hasActed);
    }

    @Override
    public String toString()   
    {
        return "C";
    }
}