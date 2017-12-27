import db.DBAlbum;
import db.DBArtist;
import models.Album;
import models.Artist;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Artist artist = new Artist("AC/DC");
        DBAlbum dbAlbum = new DBAlbum();

        DBArtist.saveArtist(artist);

        artist.setName("Iron Maiden");
        DBArtist.updateArtist(artist);

        Album album = new Album("Number of the Beast", artist);
        dbAlbum.saveAlbum(album);

        Artist artist2 = DBArtist.getArtistById(2);
        System.out.println(artist2.getId() + " : " + artist2.getName());

        Album album2 = new Album("Back in Black", artist2);
        dbAlbum.saveAlbum(album2);

        //dbArtist.deleteArtist(artist2.getId());

        List<Artist> allArtists = DBArtist.getArtists();
        for (Artist a : allArtists) {
            System.out.println(a.getId() + " : " + a.getName());
        }

        List<Album> allAlbums = dbAlbum.getAlbums();
        for (Album a : allAlbums) {
            System.out.println(a.getId() + " : " + a.getTitle() + " - " + a.getArtist().getName());
        }
     }
}
