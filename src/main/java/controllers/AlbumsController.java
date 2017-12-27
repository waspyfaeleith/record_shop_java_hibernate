package controllers;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import db.DBAlbum;
import db.DBArtist;
import models.Album;
import models.Artist;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.SparkBase.staticFileLocation;


public class AlbumsController {
    public AlbumsController(){
        this.setupEndpoints();
    }

    private void setupEndpoints() {
        //staticFileLocation("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/main.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/albums/:id/edit", (req, res) -> {
            List<Artist> artists = DBArtist.getArtists();
            Album album = DBAlbum.getAlbumById(Integer.parseInt(req.params(":id")));
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/albums/edit.vtl");
            model.put("album", album);
            model.put("artists", artists);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/albums/new", (req, res) -> {
            List<Artist> artists = DBArtist.getArtists();
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/albums/create.vtl");
            model.put("artists", artists);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/albums/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Album album = DBAlbum.getAlbumById(Integer.parseInt(req.params(":id")));
            model.put("template", "templates/albums/show.vtl");
            model.put("album", album);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/albums", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Album> albums = DBAlbum.getAlbums();
            model.put("template", "templates/albums/index.vtl");
            model.put("albums", albums);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/artists/:id/delete", (req, res) -> {
            DBAlbum.deleteAlbum(Integer.parseInt(req.params(":id")));
            res.redirect("/albums");
            return null;
        }, new VelocityTemplateEngine());

        post ("/albums", (req, res) -> {
            System.out.println(req);
            Artist artist = DBArtist.getArtistById(Integer.parseInt(req.queryParams("artist")));
            Album album = new Album(req.queryParams("title"),artist );
            DBAlbum.saveAlbum(album);
            res.redirect("/albums");
            return null;
        }, new VelocityTemplateEngine());

        post ("/albums/:id", (req, res) -> {
            Album album = DBAlbum.getAlbumById(Integer.parseInt(req.params(":id")));
            Artist artist = DBArtist.getArtistById(Integer.parseInt(req.queryParams("artist")));
            album.setTitle(req.queryParams("title"));
            album.setArtist(artist);
            DBAlbum.updateAlbum(album);
            res.redirect("/albums");
            return null;
        }, new VelocityTemplateEngine());


    }
}

