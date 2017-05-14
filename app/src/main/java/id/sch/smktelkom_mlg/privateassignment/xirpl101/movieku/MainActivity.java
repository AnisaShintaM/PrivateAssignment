package id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.adapter.NPAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.adapter.PAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.adapter.TRAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.adapter.WLAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.fragment.NPFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.fragment.PFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.fragment.TRFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.fragment.WLFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.model.WatchList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, PAdapter.IPopularAdapter, NPAdapter.INowPlayingAdapter, TRAdapter.ITopRatedAdapter, WLAdapter.IWatcListAdapter {

    WatchList watchList;
    PAdapter mAdapterP;
    NPAdapter mAdapterNP;
    TRAdapter mAdapterTR;
    WLAdapter mAdapter;
    ArrayList<WatchList> mList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        changePage(R.id.nav_popular);
        navigationView.setCheckedItem(R.id.nav_popular);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        changePage(id);

        return true;

    }

    private void changePage(int id) {
        Fragment fragment = null;

        if (id == R.id.nav_popular) {
            fragment = new PFragment();
            setTitle("Popular");
        } else if (id == R.id.nav_toprated) {
            fragment = new TRFragment();
            setTitle("Top Rated");
        } else if (id == R.id.nav_nowplaying) {
            fragment = new NPFragment();
            setTitle("Now Playing");
        } else if (id == R.id.nav_watchlist) {
            fragment = new WLFragment();
            setTitle("Watch List");
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment).commitNow();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @Override
    public void doWatchP(String title, String overview) {
        watchList = new WatchList(title, overview, ColorUtil.getRandomColor());
        watchList.save();
    }


    @Override
    public void doWatchNP(String title, String overview) {
        watchList = new WatchList(title, overview, ColorUtil.getRandomColor());
        watchList.save();
    }

    @Override
    public void doWatchTR(String title, String overview) {
        watchList = new WatchList(title, overview, ColorUtil.getRandomColor());
        watchList.save();
    }

    @Override
    public void doDelete(long id_sugar) {
        WatchList watch = WatchList.findById(WatchList.class, id_sugar);
        watch.delete();

        changePage(R.id.nav_watchlist);
    }


}
