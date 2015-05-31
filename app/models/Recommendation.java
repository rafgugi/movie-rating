package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="recommendation")
public class Recommendation extends Model {

    public Long item_id;
    public Long user_id;
    public double rating;
    
    public static Finder<Long,Recommendation> find = new Finder<Long,Recommendation>(
        Long.class, Recommendation.class
    );

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    public Movie movie;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    public User user;

}