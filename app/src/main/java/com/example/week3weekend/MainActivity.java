package com.example.week3weekend;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week3weekend.fragments.InsertEmployeeFragment;
import com.example.week3weekend.fragments.ShowAllUsersFragment;
import com.example.week3weekend.fragments.UpdateEmployeeFragment;
import com.example.week3weekend.fragments.ViewPagerAdapter;
import com.example.week3weekend.model.DBHelper;
import com.example.week3weekend.model.Employee;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        InsertEmployeeFragment.OnFragmentInteractionListener,
        UpdateEmployeeFragment.OnFragmentInteractionListener,
        ViewPager.OnPageChangeListener {

    // Navigation
    private ViewPager viewPager;
    private BottomNavigationView navigation;
    private ViewPagerAdapter pagerAdapter;

    // Fragments
    private ShowAllUsersFragment fragment1;
    private InsertEmployeeFragment fragment2;
    private UpdateEmployeeFragment fragment3;

    // Storage
    private DBHelper database;
    private SharedPreferences preferences;
    private static final String PREFERENCES = "PREFERENCES";
    private static final String DB_EMPTY = "DB_EMPTY";
    private boolean isDBEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new DBHelper(this);

        // Populate Recycler View on first run
        preferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        isDBEmpty = preferences.getBoolean(DB_EMPTY, true);
        if (isDBEmpty) {
            populateDB();
        }

        initViews();
    }

    private void populateDB() {
        String url = "https://cdn.pixabay.com/photo/2016/08/20/05/38/avatar-1606916_960_720.png";
        database.insertEmployeeIntoDB(new Employee("Marvin", "10-31-1995", 500, "02-18-2016", url));
        database.insertEmployeeIntoDB(new Employee("Marco", "11-30-1995", 500, "02-18-2016", url));
        database.insertEmployeeIntoDB(new Employee("Mario", "09-29-1995", 500, "02-18-2016", url));
        database.insertEmployeeIntoDB(new Employee("Marcelo", "08-28-1995", 500, "02-18-2016", url));
        database.insertEmployeeIntoDB(new Employee("Marvin", "10-31-1995", 500, "02-18-2016", url));
        database.insertEmployeeIntoDB(new Employee("Marco", "11-30-1995", 500, "02-18-2016", url));
        database.insertEmployeeIntoDB(new Employee("Mario", "09-29-1995", 500, "02-18-2016", url));
        database.insertEmployeeIntoDB(new Employee("Marcelo", "08-28-1995", 500, "02-18-2016", url));
        preferences.edit().putBoolean(DB_EMPTY, false).commit();
    }

    private void initViews() {
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        viewPager = findViewById(R.id.viewpager);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // init fragments
        fragment1 = ShowAllUsersFragment.newInstance();
        fragment1.attachListOfEmployees(database.getAllEmployeesFromDB());
        fragment2 = InsertEmployeeFragment.newInstance();
        fragment2.attachDatabase(database);
        fragment3 = UpdateEmployeeFragment.newInstance();
        fragment3.attachDatabase(database);

        // add fragments
        pagerAdapter.addFragment(fragment1);
        pagerAdapter.addFragment(fragment2);
        pagerAdapter.addFragment(fragment3);

        // set adapter
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this); //this syncs the bottom navbar with the viewpager
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_all:
                viewPager.setCurrentItem(0);
                return true;
            case R.id.navigation_add:
                viewPager.setCurrentItem(1);
                return true;
            case R.id.navigation_edit:
                viewPager.setCurrentItem(2);
                return true;
        }
        return false;
    }

    @Override
    public void onInsertEmployeeFragmentInteraction() {
        Toast.makeText(this, "Employee Created Successfully", Toast.LENGTH_SHORT).show();
        resetIndexAndUpdateList();
    }

    @Override
    public void onUpdateEmployeeFragmentInteraction() {
        Toast.makeText(this, "Employee Updated Successfully", Toast.LENGTH_SHORT).show();
        resetIndexAndUpdateList();
    }

    private void resetIndexAndUpdateList() {
        viewPager.setCurrentItem(0);
        fragment1.attachListOfEmployees(database.getAllEmployeesFromDB());
        fragment1.updateList();
    }

    // Let the user use the back key to navigate through fragments. If in last fragment, use backstack instead
    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    // Update bottom navigation bar when navigation is performed in the viewpager
    @Override
    public void onPageSelected(int i) {
        switch (i) {
            case 0:
                navigation.setSelectedItemId(R.id.navigation_all);
                break;
            case 1:
                navigation.setSelectedItemId(R.id.navigation_add);
                break;
            case 2:
                navigation.setSelectedItemId(R.id.navigation_edit);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
