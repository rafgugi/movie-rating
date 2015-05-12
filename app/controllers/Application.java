package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("it wotks!"));
    }

    public static Result greet(String name) {
    	return ok(greet.render(name)).as("text/html");
    }

    public static Result home() {
    	return ok(app.render("title", home.render()));
    }

}
