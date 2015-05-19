package models;

public class Pagination {
    
    public int current;
    public int last;
    public int treshold;
    public String url;

    public Pagination (int current, int last, int treshold) {
        this.current = current;
        this.last = last;
        this.treshold = treshold;
    }

    public Pagination (int current, int last) {
        this(current, last, 5);
    }

    public int lower() {
        int x = current - treshold;
        return x < 1 ? 1 : x;
    }

    public int upper() {
        int x = current + treshold;
        return x > last ? last : x;
    }

    public String removePage() {
        return url.split("/page/")[0] + "/page/";
    }

}