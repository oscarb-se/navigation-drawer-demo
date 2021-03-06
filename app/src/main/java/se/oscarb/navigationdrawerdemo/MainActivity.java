package se.oscarb.navigationdrawerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Knappen (hamburger-ikonen) som öppnar/stänger Navigation Drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // Koppla samman NavigationView (vår Navigation Drawer)
        // med vad som ska hända när man klickar på alternativen
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        // Metoden som triggas när ett menyval klickas på finns i den här klassen (this)
        navigationView.setNavigationItemSelectedListener(this);

        /*
            Skapa och placera ut ett första fragment
         */
        // 1. Skapa ett SlideshowFragment
        HomeFragment slideshow = new HomeFragment();

        // 2. Hämta FragmentManager
        FragmentManager manager = getSupportFragmentManager();

        // 3. Påbörja en FragmentTransaction
        FragmentTransaction transaction = manager.beginTransaction();

        // 4. Vad vill vi göra? Lägg till/byt ut/ta bort fragment
        transaction.add(R.id.content_container, slideshow);

        // 5. Kör transaktionen (gör ändringen)
        transaction.commit();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Starta en annan app (kamera-appen)
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);

        } else if (id == R.id.nav_gallery) {
            // Starta en activity i vår egen app
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            // Hur byter jag ut till ett fragment?
            // 1. Skapa ett SlideshowFragment
            SlideshowFragment slideshow = new SlideshowFragment();

            // 2. Hämta FragmentManager
            FragmentManager manager = getSupportFragmentManager();

            // 3. Påbörja en FragmentTransaction
            FragmentTransaction transaction = manager.beginTransaction();

            // 4. Vad vill vi göra? Lägg till/byt ut/ta bort fragment
            transaction.replace(R.id.content_container, slideshow);

            transaction.addToBackStack(null);

            // 5. Kör transaktionen (gör ändringen)
            transaction.commit();

            // Sätt titel i App Bar till titel på menyvalet
            getSupportActionBar().setTitle(item.getTitle());

        } else if (id == R.id.nav_tools) {
            // 1. Skapa ett ToolsFragment
            ToolsFragment tools = new ToolsFragment();

            // 2. Hämta FragmentManager
            FragmentManager manager = getSupportFragmentManager();

            // 3. Påbörja en FragmentTransaction
            FragmentTransaction transaction = manager.beginTransaction();

            // 4. Vad vill vi göra? Lägg till/byt ut/ta bort fragment
            transaction.replace(R.id.content_container, tools);

            transaction.addToBackStack(null);

            // 5. Kör transaktionen (gör ändringen)
            transaction.commit();

            // Sätt titel i App Bar till titel på menyvalet
            getSupportActionBar().setTitle(item.getTitle());

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
