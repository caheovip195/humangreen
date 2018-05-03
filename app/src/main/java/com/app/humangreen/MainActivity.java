package com.app.humangreen;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.app.Fragments.FragmentBuys;
import com.app.Fragments.FragmentCheckConneted;
import com.app.Fragments.FragmentSells;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txtbuys,txtsells;

    FragmentBuys fragmentBuys =new FragmentBuys();
    FragmentSells fragmentSells =new FragmentSells();
    FragmentCheckConneted fragmentCheckConneted =new FragmentCheckConneted();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControlls();
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (checkinternet()==true){
            changefragment(fragmentBuys,"fragmentbuys");
        }
        else {
            changefragment(fragmentCheckConneted,"fragmentcheckconnected");
            txtsells.setEnabled(false);
            txtbuys.setEnabled(false);
        }
        addEvents();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void addControlls() {
        txtbuys=findViewById(R.id.txt_buys);
        txtsells=findViewById(R.id.txtsells);
    }

    private void addEvents() {
      txtbuys.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              changecolor(txtbuys,txtsells);
              changefragment(fragmentBuys,"fragmentbuys");
          }
      });
      txtsells.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              changecolor(txtsells,txtbuys);
              changefragment(fragmentSells,"fragmentsells");
          }
      });
    }

    private void changecolor(TextView txta,TextView txtb){
        txta.setBackgroundColor(Color.parseColor("#b2fa053a"));
        txtb.setBackgroundColor(Color.argb(100,0,0,0));
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
    private void changefragment(Fragment fragment,String name){
        final FragmentManager manager =getFragmentManager();
        manager.popBackStack(name,MODE_PRIVATE);
        FragmentTransaction transaction =manager.beginTransaction();
        transaction.replace(R.id.fragment,fragment);
        transaction.addToBackStack(name);
        transaction.commit();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.ic_meat) {
            // Handle the camera action
        } else if (id == R.id.ic_home) {
            if(checkinternet()){
                changecolor(txtbuys,txtsells);
                changefragment(fragmentBuys,"fragmentbuys");
            }
        } else if (id == R.id.ic_fruit) {

        } else if (id == R.id.ic_vegetable) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean checkinternet(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null
                && manager.getActiveNetworkInfo().isAvailable()
                && manager.getActiveNetworkInfo().isConnected()){
            return true;
        }
        return false;
    }
}
