package storeApp.models;

public class Features {
    private double energy;
    private double danceability;
    private double valence;
    private double acousticness;

    // Constructor
    public Features(double energy, double danceability, double valence, double acousticness) {
        this.energy = energy;
        this.danceability = danceability;
        this.valence = valence;
        this.acousticness = acousticness;
    }

    // Getters y Setters
    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getDanceability() {
        return danceability;
    }

    public void setDanceability(double danceability) {
        this.danceability = danceability;
    }

    public double getValence() {
        return valence;
    }

    public void setValence(double valence) {
        this.valence = valence;
    }

    public double getAcousticness() {
        return acousticness;
    }

    public void setAcousticness(double acousticness) {
        this.acousticness = acousticness;
    }

    public String toString() {
        return "Features{" +
                "energy=" + energy +
                ", danceability=" + danceability +
                ", valence=" + valence +
                ", acousticness=" + acousticness +
                '}';
    }
}