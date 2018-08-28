package example.instagram2.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import example.instagram2.R;
import example.instagram2.Utils.BottomNavigationViewHelper;
import example.instagram2.Utils.GridImageAdapter;
import example.instagram2.Utils.UniversalImageLoader;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private Context mContext = ProfileActivity.this;
    private static final int ACTIVITY_NUM = 4;
    private static final int NUM_GRID_COLUMNS = 3;
    private ProgressBar mProgressBar;
    private ImageView profilePhoto;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: started.");

        setupBottomNavigationView();
        setupToolBar();
        setupActivityWidgets();
        setProfileImage();
        tempGridSetup();
    }
    private void tempGridSetup() {
        ArrayList<String> imgURLs = new ArrayList<>();

        imgURLs.add("https://www.gannett-cdn.com/-mm-/a0a28bd666af6d80b33247a358069ae6b7ce0cc4/c=0-108-2121-1306/local/-/media/2018/07/05/NJGroup/AsburyPark/636663840922681675-vacation-1.jpg?width=3200&height=1680&fit=crop");
        imgURLs.add("https://ssl.tzoo-img.com/images/tzoo.1.0.567887.C-vacation-Romantic-shutterstock-paris.jpg?width=315");
        imgURLs.add("https://www.go-today.com/stw/Images/hero-images/other-hero-images/norway-hiking-friends-europe-vacation-deals.jpg");
        imgURLs.add("http://www.fulldose.net/wp-content/uploads/2014/11/Beautiful-Norway.jpg");
        imgURLs.add("https://www.westjet.com/assets/wj-web/images/wvi-offers/180405-ticket-offers-universal-mco-globe-SP18-737x426.jpg");
        imgURLs.add("https://secure.instantsoftwareonline.com/StayUSA/PropertyImages/2397/RM%2069823%20CmoPcifco/L1.jpg?h=aDErcWZvMVphRlV0aXpnUmVxMjZwdz09");
        imgURLs.add("https://www.incimages.com/uploaded_files/image/970x450/getty_177715145_200013332000928094_62938.jpg");
        setupImageGrid(imgURLs);
    }
    private void setupImageGrid(ArrayList<String> imgURLs) {
        GridView gridView = findViewById(R.id.gridView);
        //distribute width, divide
        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth / NUM_GRID_COLUMNS;
        gridView.setColumnWidth(imageWidth);

        GridImageAdapter adapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview, "", imgURLs);
        gridView.setAdapter(adapter);
    }

    private void setProfileImage() {
        Log.d(TAG, "setProfileImage: setting profile photo");
        String imgURL = "www.android.com/static/2016/img/share/n-lg.png";
        UniversalImageLoader.setImage(imgURL, profilePhoto, mProgressBar, "https://");
    }

    private void setupActivityWidgets() {
        mProgressBar = findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);
        profilePhoto = (ImageView) findViewById(R.id.profile_photo);
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
    //this is for top bar
    private void setupToolBar() {
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        ImageView profileImage = (ImageView) findViewById(R.id.profileMenu);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AccountSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

}
