package example.instagram2.Home;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

import example.instagram2.R;
import example.instagram2.Utils.BottomNavigationViewHelper;
import example.instagram2.Utils.SectionsPagerAdapter;
import example.instagram2.Utils.UniversalImageLoader;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private Context mContext = HomeActivity.this;
    private static final int ACTIVITY_NUM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: starting.");

        initImageLoader();
        setupBottomNavigationView();
        setupViewPager();

    }
    /** BottomNavigationView setup */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavView);
        //helper function cuz we're calling it over and over again
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
    private void initImageLoader() {
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }
    //responsible for adding the three tabs
    private void setupViewPager() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        sectionsPagerAdapter.addFragment(new CameraFragment());
        sectionsPagerAdapter.addFragment(new HomeFragment());
        sectionsPagerAdapter.addFragment(new MessagesFragment());
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_icon);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_arrow);
    }
}
