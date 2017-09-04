package be.vdab.entities;

public class Herbivore extends Organism
{
    public Herbivore (int life, boolean hasActed)
    {
        super(life, hasActed);
    }

    @Override
    public String toString()   
    {
        return "H";
    }
}
