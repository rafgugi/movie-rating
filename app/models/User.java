package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="user")
public class User extends Model {

    @Id
    public Long id;

    public int age;
    public char gender;
    public String occupation;
    public String zip_code;
    public String password;
    
    public static Finder<Long,User> find = new Finder<Long,User>(
        Long.class, User.class
    );

    @Override
    public String toString() {
    	return id + " " + age + " " + gender + " " + occupation;
    }

}