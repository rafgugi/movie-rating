package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.*;

import java.util.List;
import java.util.Date;
import play.db.ebean.*;
import com.avaje.ebean.*;

import models.Movie;
import models.Rating;
import models.Pagination;

@Security.Authenticated(Secured.class)
public class RatingController extends Controller {

    private static List<Movie> movies;
    private static Pagination pagination;

    private static Result render() {
        pagination.url = request().uri();
        return ok(app.render("Movie Rating", rating.render(movies, pagination)));
    }

    public static Result page(int page) {
        try {
            PagingList<Movie> pg = Movie.find
                .orderBy("title asc")
                .findPagingList(10)
                .setFetchAhead(false);
            movies = pg
                .getPage(page)
                .getList();
            pagination = new Pagination(page, pg.getTotalPageCount() - 1);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            movies = null;
        }
        return render();
    }

    public static Result search(int page, String search) {
        if (search.equals("")) {
            return page(1);
        }
        try {
            PagingList<Movie> pg = Movie.find
                .where()
                    .istartsWith("title", search)
                .orderBy("title asc")
                .findPagingList(10)
                .setFetchAhead(false);
            movies = pg
                .getPage(page)
                .getList();
            pagination = new Pagination(page, pg.getTotalPageCount() - 1);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            movies = null;
        }
        return render();
    }

    public static Result rate() {
        int rating = Integer.parseInt(Form.form().bindFromRequest().get("rating"));
        Long movie = Long.parseLong(Form.form().bindFromRequest().get("item_id"), 10);
        Long user = Long.parseLong(session("id"), 10);

        Rating r = Rating.find
            .where()  
              .eq("item_id", movie)
              .eq("user_id", user)
            .findUnique();
        if (r == null) {
            r = new Rating();
            r.item_id = movie;
            r.user_id = user;
        }

        float prev = r.rating;
        r.rating = rating;
        r.timestamp = new Date().getTime() + "";
        r.save();
        return ok("rating: " + rating + "\npreviously: " + prev);
    }

}
