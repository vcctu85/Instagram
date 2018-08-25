package example.instagram2.Profile;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import example.instagram2.R;
import example.instagram2.Utils.UniversalImageLoader;

public class EditProfileFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "EditProfileFragment";
    private ImageView mProfilePhoto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        mProfilePhoto = (ImageView) view.findViewById(R.id.profile_photo);

        setProfileImage();
        //back arrow to go back to ProfileActivity
        ImageView backArrow = (ImageView) view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: navigating back");
                getActivity().finish();
            }
        });
        return view;
    }



    private void setProfileImage() {
        Log.d(TAG, "setProfileImage: setting profile image");
        String imgURL = "www.android.com/static/2016/img/share/n-lg.png";
        UniversalImageLoader.setImage(imgURL, mProfilePhoto, null, "https://");
    }
}
