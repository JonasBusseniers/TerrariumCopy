package be.vdab.entities;

public abstract class Organism {
    
    int life;
    int lifespan = 0;
    boolean hasActed;
    String url;
    
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
    
    public int getLifespan() {
		return lifespan;
	}

	public void setLifespan(int lifespan) {
		this.lifespan = lifespan;
	}

    public boolean isHasActed() {
        return hasActed;
    }

    public void setHasActed(boolean hasActed) {
        this.hasActed = hasActed;
    }    
}
