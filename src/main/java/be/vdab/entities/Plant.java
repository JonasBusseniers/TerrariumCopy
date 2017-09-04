package be.vdab.entities;

public class Plant extends Organism
{
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
