package example.instagram2.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import example.instagram2.R;

public class GridImageAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private LayoutInflater mInflater;
    private int layoutResource;
    private String mAppend;
    private ArrayList<String> imgURLs;

    public GridImageAdapter(Context context, int layoutResource, String append, ArrayList<String> imgURLs) {
        super(context, layoutResource, imgURLs);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;

        this.layoutResource = layoutResource;
        mAppend = append;
        this.imgURLs = imgURLs;
    }

    private static class ViewHolder {
        ImageView image;
        ProgressBar mProgressBar;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder();
            holder.mProgressBar = convertView.findViewById(R.id.gridImageProgressBar);
            holder.image = convertView.findViewById(R.id.gridImageView);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String imgURL = getItem(position);
        ImageLoader imageLoader = ImageLoader.getInstance();

        imageLoader.displayImage(mAppend + imgURL, holder.image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if (holder.mProgressBar != null) {
                    holder.mProgressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if (holder.mProgressBar != null) {
                    holder.mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (holder.mProgressBar != null) {
                    holder.mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if (holder.mProgressBar != null) {
                    holder.mProgressBar.setVisibility(View.GONE);
                }
            }
        });
        return convertView;
    }
}
