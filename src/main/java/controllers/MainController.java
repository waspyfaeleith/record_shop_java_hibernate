package controllers;

import db.DBAlbum;
import models.Album;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class MainController {
    public static void main(String[] args) {
        ArtistsController artistsController = new ArtistsController();
        AlbumsController albumsController = new AlbumsController();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Album> stock = DBAlbum.getAlbums();
            model.put("template", "templates/main.vtl");
            model.put("stock", stock);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }


}
