package storeApp.models;

public class Track {
    private String name;
    private String artist;
    private String album;
    private String id;
    private String image;
    private int duration;
    private Features features;

    // Constructor
    public Track(String name, String artist, String album, String id, String image, int duration, Features features) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.id = id;
        this.image = image;
        this.duration = duration;
        this.features = features;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features features) {
        this.features = features;
    }

    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", id='" + id + '\'' +
                ", image='" + image + '\'' +
                ", duration=" + duration +
                ", features=" + features +
                '}';
    }
}