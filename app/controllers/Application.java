package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import play.db.ebean.*;
import java.util.List;

import models.Movie;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("it wotks!"));
    }

    public static Result greet(String name) {
        return ok(greet.render(name)).as("text/html");
    }

}
