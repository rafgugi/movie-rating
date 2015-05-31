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
    public String releaseDate;
    public String videoReleaseDate;
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
    public int filmNoir;
    public int horror;
    public int musical;
    public int mistery;
    public int romance;
    public int sciFi;
    public int thriller;
    public int war;
    public int western;
    
    public static Finder<Long,Movie> find = new Finder<Long,Movie>(
        Long.class, Movie.class
    );

    // @OneToMany(mappedBy = "movie")
    // @JoinColumn(name = "item_id", insertable = false, updatable = false)
    // public List<Rating> ratings;

    public int ratingByUser(Long user_id) {
        List<Rating> rs = Rating.find.where()
                .eq("item_id", this.id + "")
                .eq("user_id", user_id + "")
                .findList();
        for (Rating r : rs) {
            return r.rating;
        }
        return 0;
    }

    public String ratingMatch(Long user_id, int rating, String ans) {
        if (ratingByUser(user_id) == rating) {
            return ans;
        }
        return "";
    }

    public List<String> genre() {
        List<String> genre = new ArrayList<>();
        if (unknown == 1) genre.add("Unknown");
        if (action == 1) genre.add("Action");
        if (adventure == 1) genre.add("Adventure");
        if (animation == 1) genre.add("Animation");
        if (childrens == 1) genre.add("Childrens");
        if (comedy == 1) genre.add("Comedy");
        if (crime == 1) genre.add("Crime");
        if (documentary == 1) genre.add("Documentary");
        if (drama == 1) genre.add("Drama");
        if (fantasy == 1) genre.add("Fantasy");
        if (filmNoir == 1) genre.add("Film noir");
        if (horror == 1) genre.add("Horror");
        if (musical == 1) genre.add("Musical");
        if (mistery == 1) genre.add("Mistery");
        if (romance == 1) genre.add("Romance");
        if (sciFi == 1) genre.add("SciFi");
        if (thriller == 1) genre.add("Thriller");
        if (war == 1) genre.add("War");
        if (western == 1) genre.add("Western");
        return genre;
    }

}