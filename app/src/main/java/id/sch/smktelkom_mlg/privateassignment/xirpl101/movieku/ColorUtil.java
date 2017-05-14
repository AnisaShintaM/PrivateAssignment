package id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by user on 14/5/2017.
 */

public class ColorUtil {
    public static int getRandomColor() {
        Random rnd = new Random();
        //return Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return Color.rgb(rnd.nextInt(192), rnd.nextInt(192), rnd.nextInt(192));
    }
}

