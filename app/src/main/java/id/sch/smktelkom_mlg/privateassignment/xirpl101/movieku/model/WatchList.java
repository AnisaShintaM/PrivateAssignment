package id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by user on 14/5/2017.
 */

public class WatchList extends SugarRecord implements Serializable {
    public String title;
    public String overview;
    public int color;

    public WatchList() {
    }

    public WatchList(String title, String overview, int color) {
        this.title = title;
        this.overview = overview;
        this.color = color;
    }
}
