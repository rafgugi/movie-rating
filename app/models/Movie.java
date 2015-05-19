package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="item")
public class Movie extends Model {

    @Id
    public Long id;
    public String title;
    public String release_date;
    public String url;
    public int unknown;
    public int action;
    public int adventure;
    public int animation;
    public int childrens;
    public int comedy;
    public int crime;
    public int documentary;
    public int drama;
    public int fantasy;
    public int film_noir;
    public int horror;
    public int musical;
    public int mistery;
    public int romance;
    public int sci_fi;
    public int thriller;
    public int war;
    public int western;
    
    public static Finder<Long,Movie> find = new Finder<Long,Movie>(
        Long.class, Movie.class
    );

    @OneToMany(mappedBy = "movie")
    @JoinColumn(name = "item_id")
    public List<Rating> ratings;

}