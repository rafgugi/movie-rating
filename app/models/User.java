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
    
    public static Finder<Long,User> find = new Finder<Long,User>(
        Long.class, User.class
    ); 

}