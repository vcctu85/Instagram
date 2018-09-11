package example.instagram2.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import example.instagram2.R;
import example.instagram2.Utils.FirebaseMethods;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private Context mContext;
    private String email, username, password;
    private EditText mEmail, mPassword, mUsername;
    private TextView loadingPleaseWait;
    private Button btnRegister;
    private ProgressBar mProgressBar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseMethods firebaseMethods;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext= RegisterActivity.this;
        setContentView(R.layout.activity_register);
        firebaseMethods = new FirebaseMethods(mContext);
        Log.d(TAG, "onCreate: ");
        initWidgets();
        setupFirebaseAuth();
        init();
    }
    private void init() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEmail.getText().toString();
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();

                if (checkInputs(email, username, password)) {
                    mProgressBar.setVisibility(View.VISIBLE);
                    loadingPleaseWait.setVisibility(View.VISIBLE);
                    firebaseMethods.registerNewEmail(email, password, email);
                }
            }
        });
    }

    private boolean checkInputs(String email, String username, String password) {
        Log.d(TAG, "checkInputs: checking inputs for null values");
        if (email.equals("") || username.equals("") || password.equals("")) {
            Toast.makeText(mContext, "All fields must be filled out.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    /**
     * initialize the activity widgets
     */
    private void initWidgets() {
        mEmail = findViewById(R.id.input_email);
        mPassword = findViewById(R.id.input_password);
        mUsername = findViewById(R.id.input_username);
        btnRegister = findViewById(R.id.button_register);
        mProgressBar = findViewById(R.id.progressbar);
        mProgressBar.setVisibility(View.GONE);
        loadingPleaseWait =findViewById(R.id.loadingPleaseWait);
        loadingPleaseWait.setVisibility(View.GONE);
        mContext = RegisterActivity.this;

    }

    private boolean isStringNull(String string) {
        Log.d(TAG, "isStringNull: checking string if null");
        if (string.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private void setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth");
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                //check if user is logged in
                if (user != null) {
                    //user is already signed up
                    Log.d(TAG, "onAuthStateChanged: signed_in:" + user.getUid());

                } else {
                    Log.d(TAG, "onAuthStateChanged: signed_out");
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(mAuthListener);

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}