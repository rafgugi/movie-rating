package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.*;

import java.util.List;
import play.db.ebean.*;
import com.avaje.ebean.*;

import models.Recommendation;
import models.Movie;

public class RecommendController extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        List<Recommendation> recs;
        try {
            recs = Recommendation.find
                .where().eq("user_id", session("id"))
                .findList();
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            recs = null;
        }
        return ok(app.render("Recommender", rec.render(recs)));
    }

    public static Result details(Long movie_id) {
        Movie movie = Movie.find.byId(movie_id);
        String sql = "SELECT id, title, release_date, video_release_date, "
                + "url, unknown, action, adventure, animation, childrens, "
                + "comedy, crime, documentary, drama, fantasy, film_noir, "
                + "horror, musical, mistery, romance, sci_fi, thriller, war, western "
                + "FROM `item` as i "
                + "INNER JOIN ( "
                + "SELECT `item_id` "
                + "FROM `recommendation` as r "
                + "INNER JOIN ( "
                    + "SELECT `user_id` "
                    + "FROM `data`as d "
                    + "WHERE d.item_id = 1 AND d.rating > 4 "
                + ") as t "
                + "ON t.user_id = r.user_id "
                + "GROUP BY `item_id` "
                + "ORDER BY count(`item_id`) DESC) as t2 "
                + "ON t2.item_id = i.id "
                + "LIMIT 10";
        RawSql rawSql = RawSqlBuilder.parse(sql).create();
        List<Movie> recs = Movie.find.setRawSql(rawSql).findList();
        return ok(app.render(movie.title, details.render(movie, recs)));
    }

}
