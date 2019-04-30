package slorah.com.homework4;

import android.media.MediaPlayer;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import android.support.v4.widget.DrawerLayout;

public class HostActivity extends AppCompatActivity
        implements HomeFragment.OnNextClickListener,
        NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment fragment = new HomeFragment();
        fragmentTransaction.add(R.id.fragment_host, fragment);
        fragmentTransaction.commit();

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.nav_open_drawer, R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView)findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
    }



    public void swapFragments(Fragment fragment) {
        Fragment newFragment;

        if(fragment instanceof HomeFragment) {
            newFragment = new AboutFragment();
        } else {
            newFragment = new HomeFragment();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_host, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void OnHomeFragmentNextClick(HomeFragment fragment) {
        swapFragments(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_settings:
                Toast toast = Toast.makeText(this, "Toast! Toast! Toast!", Toast.LENGTH_SHORT);
                toast.show();
                return true;

            case R.id.action_sound:
                // from inspiration from
                // https://stackoverflow.com/questions/26538421/how-do-i-play-sound-on-button-click-in-android-studio
                final MediaPlayer mp = MediaPlayer.create(this, R.raw.magic_wand);
                mp.start();
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        Fragment newFragment;

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                newFragment = new HomeFragment();
                break;
            case R.id.nav_android:
                newFragment = new AboutFragment();
                break;
            default:
                newFragment = new HomeFragment();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_host, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        return false;
    }
}
