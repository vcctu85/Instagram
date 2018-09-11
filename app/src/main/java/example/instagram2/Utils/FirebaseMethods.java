package example.instagram2.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import example.instagram2.R;

public class FirebaseMethods {
    private static final String TAG = "FirebaseMethods";
    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;

    private Context mContext;


    public FirebaseMethods(Context context){
        mAuth = FirebaseAuth.getInstance();
        mContext= context;

        if (mAuth.getCurrentUser() != null) {
            userID = mAuth.getCurrentUser().getUid();
        }

    }

    /**
     * Register a new email and password to Firebase Authentication
     * @param email
     * @param password
     * @param username
     */
    public void registerNewEmail(final String email, String password, final String username) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(mContext, R.string.auth_failed, Toast.LENGTH_SHORT).show();

                        } else if (task.isSuccessful()) {
                            userID = mAuth.getCurrentUser().getUid();
                            Log.d(TAG, "onComplete: authstate changed: " + userID);
                        }

                        // ...
                    }
                });
    }
}
