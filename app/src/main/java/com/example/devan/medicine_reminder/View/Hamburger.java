package com.example.devan.medicine_reminder.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.devan.medicine_reminder.R;
import com.example.devan.medicine_reminder.datalayer.AppDatabase;

import java.util.ArrayList;

import static com.example.devan.medicine_reminder.businesslayer.HamburgerBusinessLayer.ShowUserInfo;
import static com.example.devan.medicine_reminder.businesslayer.UserDetailsBusinessLayer.IsUserPresent;

public class Hamburger extends AppCompatActivity {
    private static String TAG = Hamburger.class.getSimpleName();
    TextView userName;
    ListView drawerListView;
    RelativeLayout drawerPane;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    AppDatabase appData;

    ArrayList<NavigationItem> navigationItems = new ArrayList<NavigationItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userName = findViewById(R.id.userName);

        appData = AppDatabase.getInMemoryDatabase(getApplicationContext());

        if (IsUserPresent(appData)) {
            navigationItems.add(new NavigationItem("Edit User", "", R.drawable.user_2));
        } else {
            navigationItems.add(new NavigationItem("Add User", "", R.drawable.new_user));
        }

        navigationItems.add(new NavigationItem("My Medication", "", R.drawable.pill_icon_white_128));
        navigationItems.add(new NavigationItem("Calender", "", R.drawable.calendar_icon_white_128));
        navigationItems.add(new NavigationItem("Settings", "", R.drawable.settings_icon_white_128));
        navigationItems.add(new NavigationItem("Medical report", "", R.drawable.report_icon));
        navigationItems.add(new NavigationItem("Help and Support", "", R.drawable.customer_support));
        navigationItems.add(new NavigationItem("About Us", "", R.drawable.about_us_icon));
        appData = AppDatabase.getInMemoryDatabase(getApplicationContext());
        try {
            userName.setText(ShowUserInfo(appData));
        } catch (Exception e) {
            e.printStackTrace();
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        drawerPane = (RelativeLayout) findViewById(R.id.drawerPane);

        drawerListView = (ListView) findViewById(R.id.navList);
        DrawerListAdapter drawerListAdapter = new DrawerListAdapter(this, navigationItems);
        drawerListView.setAdapter(drawerListAdapter);

        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d(TAG, "onDrawerClosed: " + getTitle());

                invalidateOptionsMenu();
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    private void selectItemFromDrawer(int position) {
        Intent intent;
        switch (position) {

            case 0:

                if (IsUserPresent(appData)) {
                    intent = new Intent(this, EditUserDetails.class);
                    startActivity(intent);
                }
                else{
                    intent = new Intent(this, UserDetails.class);
                    startActivity(intent);
                }
                break;
            case 1:

                intent = new Intent(this, MyMedication.class);
                startActivity(intent);
                break;
            case 2:

                intent = new Intent(this, Calender.class);
                startActivity(intent);
                break;
            case 3:

                intent = new Intent(this, SettingsHome.class);
                startActivity(intent);
                break;

            case 4:
                intent = new Intent(this, MedicalReport.class);
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(this, Help.class);
                startActivity(intent);
                break;
            case 6:
                intent = new Intent(this, About_Us.class);
                startActivity(intent);
                break;

        }

        drawerLayout.closeDrawer(drawerPane);
    }

    class NavigationItem {
        String title;
        String subtitle;
        int icon;

        public NavigationItem(String _title, String _subtitle, int _iconId) {
            title = _title;
            subtitle = _subtitle;
            icon = _iconId;
        }
    }

    class DrawerListAdapter extends BaseAdapter {

        Context context;
        ArrayList<NavigationItem> navigationItems;

        public DrawerListAdapter(Context _context, ArrayList<NavigationItem> _navigationItems) {
            context = _context;
            navigationItems = _navigationItems;
        }

        @Override
        public int getCount() {
            return navigationItems.size();
        }

        @Override
        public Object getItem(int position) {
            return navigationItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.drawer_item, null);
            } else {
                view = convertView;
            }

            TextView titleView = (TextView) view.findViewById(R.id.drawerMenuItemTitle);
            ImageView iconView = (ImageView) view.findViewById(R.id.drawerMenuItemIcon);

            titleView.setText(navigationItems.get(position).title);
            iconView.setImageResource(navigationItems.get(position).icon);

            return view;
        }
    }
}

