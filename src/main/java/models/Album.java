package models;

public class Album {
    private int id;
    private String title;
    private Artist artist;
    private int quantity;

    public Album() {

    }

    public Album(String title, Artist artist, int quantity) {
        this.title = title;
        this.artist = artist;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String stockLevel() {
        if (this.quantity >= 10) {
            return "High";
        } else if (this.quantity >= 5) {
            return "Medium";
        } else {
            return "Low";
        }
    }
}
