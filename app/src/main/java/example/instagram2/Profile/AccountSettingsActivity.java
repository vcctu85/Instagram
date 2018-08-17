package example.instagram2.Profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import example.instagram2.R;
import example.instagram2.Utils.SectionsStatePagerAdapter;

public class AccountSettingsActivity extends AppCompatActivity {
    //shortcut: type logt
    private static final String TAG = "AccountSettingsActivity";
    private Context mContext;
    private SectionsStatePagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private RelativeLayout relativeLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsetting);
        mContext= AccountSettingsActivity.this;
        Log.d(TAG, "onCreate: started.");
        viewPager = findViewById(R.id.container);
        relativeLayout =findViewById(R.id.relLayout1);

        setupSettingsList();
        //make back arrow functional
        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick: navigating back to Profile");
                finish();
            }
        });
    }
    private void setupFragments() {
        pagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new EditProfileFragment(), getString(R.string.edit_profile_fragment)); //fragment 0
        pagerAdapter.addFragment(new SignOutFragment(), getString(R.string.sign_out_fragment)); //fragment 1
    }

    private void setViewPager(int fragmentNumber) {
        relativeLayout.setVisibility(View.GONE);
        Log.d(TAG, "navigating to Fragment #");
        //instantiate the viewpager, navigate to whatever fragment i chose
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(fragmentNumber);
    }

    private void setupSettingsList() {
        Log.d(TAG, "initializing Account Settings list");
        ListView listView = (ListView) findViewById(R.id.listViewAccountSettings);

        ArrayList<String>options = new ArrayList<>();
        //edit your profile, sign out buttons
        options.add(getString(R.string.edit_profile_fragment));
        options.add(getString(R.string.sign_out_fragment));

        //create arrayadapter,but we need context
        ArrayAdapter adapter = new ArrayAdapter(mContext,android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "navigating to fragment");
                setViewPager(i);
            }
        });
    }
}
