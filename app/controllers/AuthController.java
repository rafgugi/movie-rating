package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.*;

import play.db.ebean.*;
import java.util.List;

public class AuthController extends Controller {

    public static Result login() {
        return ok(app.render("Login", login.render()));
    }

    public static Result attempt() {
        DynamicForm requestData = Form.form().bindFromRequest();
        String id = requestData.get("id");
        String password = requestData.get("password");
        if (password.equals(id)) {
            session("id", id);
        } else {
            return login();
        }
        return ok("attempting to login, with id '"+id+"' and password '" + password + "'");
    }

    public static Result logout() {
        session().remove("id");
        return ok("logout");
    }

}