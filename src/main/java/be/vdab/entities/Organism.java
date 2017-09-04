package be.vdab.entities;

public abstract class Organism {
    
    int life;
    boolean hasActed;
    
    public Organism (int life, boolean hasActed)
    {
        this.life = life;
        this.hasActed = hasActed;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isHasActed() {
        return hasActed;
    }

    public void setHasActed(boolean hasActed) {
        this.hasActed = hasActed;
    }
    
}
