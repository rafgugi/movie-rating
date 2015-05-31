package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="data")
public class Rating extends Model {

    // @Id
    // public Long id;

    public Long item_id;
    public Long user_id;
    public String timestamp;
    public int rating;
    
    public static Finder<Long,Rating> find = new Finder<Long,Rating>(
        Long.class, Rating.class
    );

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    public Movie movie;

}