package controllers;

import db.DBArtist;
import models.Album;
import models.Artist;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static spark.Spark.*;
import static spark.SparkBase.staticFileLocation;


public class ArtistsController {

    public ArtistsController(){
        this.setupEndpoints();
    }

    private void setupEndpoints() {
        staticFileLocation("/public");

        get("/artists/:id/edit", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Artist artist = DBArtist.getArtistById(intId);
            //Artist artist = DBArtist.getArtistById(Integer.parseInt(req.params(":id")));
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/artists/edit.vtl");
            model.put("artist", artist);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/artists/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/artists/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get("/artists/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Artist artist = DBArtist.getArtistById(intId);
            //Artist artist = DBArtist.getArtistById(Integer.parseInt(req.params(":id")));
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/artists/show.vtl");
            model.put("artist", artist);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/artists", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Artist> artists = DBArtist.getArtists();
            model.put("template", "templates/artists/index.vtl");
            model.put("artists", artists);

//            for (Artist a : artists) {
//                System.out.println(a.getId() + " : " + a.getName());
//            }

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/artists/:id/delete", (req, res) -> {
            DBArtist.deleteArtist(Integer.parseInt(req.params(":id")));
            res.redirect("/artists");
            return null;
//            Map<String, Object> model = new HashMap<>();
//            List<Artist> artists = DBArtist.getArtists();
//            model.put("template", "templates/artists/index.vtl");
//            model.put("artists", artists);
//            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/artists", (req, res) -> {
            System.out.println(req);
            Artist artist = new Artist(req.queryParams("name"));
            DBArtist.saveArtist(artist);
            res.redirect("/artists");
            return null;
//            Map<String, Object> model = new HashMap<>();
//            List<Artist> artists = DBArtist.getArtists();
//            model.put("template", "templates/artists/index.vtl");
//            model.put("artists", artists);
//            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/artists/:id", (req, res) -> {
            Artist artist = DBArtist.getArtistById(Integer.parseInt(req.params(":id")));
            artist.setName(req.queryParams("name"));
            DBArtist.updateArtist(artist);
            res.redirect("/artists");
            return null;
//            Map<String, Object> model = new HashMap<>();
//            List<Artist> artists = DBArtist.getArtists();
//            model.put("template", "templates/artists/index.vtl");
//            model.put("artists", artists);
//            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


    }
}
