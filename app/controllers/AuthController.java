package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.*;

import play.db.ebean.*;
import java.util.List;

import models.User;

public class AuthController extends Controller {

    public static Result login(String flash) {
        if (session("id") == null) {
            return ok(app.render("Login", login.render(flash)));
        }
        return redirect(routes.Application.home());
    }

    public static Result login() {
        return login(null);
    }

    public static Result attempt() {
        DynamicForm requestData = Form.form().bindFromRequest();
        Long id;
        try {
            id = Long.parseLong(requestData.get("id"), 10);
        } catch (NumberFormatException e) {
            return login("Bad user ID.");
        }
        String password = requestData.get("password");

        User user = User.find.byId(id);
        if (user == null || !user.password.equals(password)) {
            return login("Invalid user or password.");
        }

        session("id", id + "");
        return login();
    }

    public static Result logout() {
        session().remove("id");
        return login();
    }

}