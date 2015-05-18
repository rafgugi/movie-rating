package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.*;

import play.db.ebean.*;
import java.util.List;

import models.Movie;
import models.Rating;
import models.Pagination;

public class RatingController extends Controller {

    private static List<Movie> movies;
    private static Pagination pagination;

    private static Result render() {
        return ok(app.render("Movie Rating", rating.render(movies)));
    }

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
        return render();
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
                    .istartsWith("title", search)
                .orderBy("title asc")
                .findPagingList(10)
                .setFetchAhead(false)
                .getPage(page)
                .getList();
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            movies = null;
        }
        return render();
    }

    public static Result rate() {
        Long rating = Long.parseLong(Form.form().bindFromRequest().get("rating"), 10);
        Long movie = Long.parseLong(Form.form().bindFromRequest().get("movie_id"), 10);

        List<Rating> list = Rating.find
            .where()  
              .eq("movie_id", movie)
            .findList();
        return ok("rating: " + rating);
    }

}
