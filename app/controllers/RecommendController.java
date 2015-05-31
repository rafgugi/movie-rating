package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.*;

import java.util.List;
import play.db.ebean.*;
import com.avaje.ebean.*;

import models.Recommendation;

// @Security.Authenticated(Secured.class)
public class RecommendController extends Controller {

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

}
