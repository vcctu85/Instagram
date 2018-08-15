package example.instagram2.Profile;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import example.instagram2.R;

public class AccountSettingsActivity extends AppCompatActivity {
    //shortcut: type logt
    private static final String TAG = "AccountSettingsActivity";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsetting);
        Log.d(TAG, "onCreate: started.");
    }
}
