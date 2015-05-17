package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.*;

import play.db.ebean.*;
import java.util.List;

import models.Movie;

public class RatingController extends Controller {

    private static List<Movie> movies;

    private static final String title = "Movie Rating";

    public static Result page(int page) {
        try {
            movies = Movie.find
                .orderBy("title asc")
                .findPagingList(10)
                .setFetchAhead(false)
                .getPage(page)
                .getList();
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            movies = null;
        }
        return ok(app.render(title, rating.render(movies)));
    }

    public static Result search(int page) {
        DynamicForm requestData = Form.form().bindFromRequest();
        String search = requestData.get("search");
        if (search.equals("")) {
            return page(1);
        }
        try {
            movies = Movie.find
                .where()
                .ilike("title", "%" + search + "%")
                .orderBy("title asc")
                .findPagingList(10)
                .setFetchAhead(false)
                .getPage(page)
                .getList();
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            movies = null;
        }
        return ok(app.render(title, rating.render(movies)));
    }

    public static Result rate() {
        int rating = Integer.parseInt(Form.form().bindFromRequest().get("rating"));
        int movie = Integer.parseInt(Form.form().bindFromRequest().get("movie_id"));
        return ok("rating: " + rating);
    }

}
