package com.example.android.popmovies;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 10/3/2015 0003.
 */
public class ImageAdapter extends ArrayAdapter<String> {
    private Context context;

    public ImageAdapter(Context context, int resource, int imageViewResourceId, List<String> objects){
        super(context, resource, imageViewResourceId, objects);
        this.context = context;
    }

    @Override
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater =
                    (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.grid_item_movie, null);
        }
        String url = getItem(position);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.grid_item_movie_imageview);
        Picasso.with(context)
                .load(url)
                .into(imageView);
        return convertView;
    }

    /*
    private Context mContext;
    private final LayoutInflater mInflater;
    private List<String> mObjects;
    private int mFieldId = 0;
    private int mResource;
    private int mDropDownResource;

    public ImageAdapter(Context context, int resource, int imageViewResourceId, List<String> objects) {
        super(context, resource, imageViewResourceId, objects);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = mDropDownResource = resource;
        mObjects = objects;
        mFieldId = imageViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return createViewFromResource(mInflater, position, convertView, parent, mResource);
    }

    private View createViewFromResource(LayoutInflater inflater, int position, View convertView,
                                        ViewGroup parent, int resource) {

        View view;
        ImageView image;

        if (convertView == null) {
            view = inflater.inflate(resource, parent, false);
        } else {
            view = convertView;
        }

        try {
            if (mFieldId == 0) {
                //  If no custom field is assigned, assume the whole resource is a TextView
                image = (ImageView) view;
            } else {
                //  Otherwise, find the TextView field within the layout
                image = (ImageView) view.findViewById(mFieldId);
            }
        } catch (ClassCastException e) {
            Log.e("ArrayAdapter", "You must supply a resource ID for a ImageView");
            throw new IllegalStateException(
                    "ArrayAdapter requires the resource ID to be a ImageView", e);
        }

        String item = getItem(position);
        if (item instanceof CharSequence) {
            Picasso.with(mContext).load(item).into(image);
            Log.v("Poster Loading", "Position" + position + "load successfully!");
        } else {
            Picasso.with(mContext).load(item.toString()).into(image);
            Log.v("Poster Loading", "Position" + position + "load successfully!");
        }

        return view;
    }
*/
/*
    public int getCount() {
        return mThumburl.length;
    }

    public String getItem(int position) {
        return mThumburl[position];
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mContext).load(mThumburl[position]).into(imageView);
        Log.v("Poster Loading","Position"+position+"load successfully!");
        return imageView;
    }

    // references to our images
    private String[] mThumburl = {
            "http://image.tmdb.org/t/p/w185//hTKME3PUzdS3ezqK5BZcytXLCUl.jpg",
            "http://image.tmdb.org/t/p/w185//5JU9ytZJyR3zmClGmVm9q4Geqbd.jpg",
            "http://image.tmdb.org/t/p/w185//kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
            "http://image.tmdb.org/t/p/w185//69Cz9VNQZy39fUE2g0Ggth6SBTM.jpg",
            "http://image.tmdb.org/t/p/w185//qey0tdcOp9kCDdEZuJ87yE3crSe.jpg",
            "http://image.tmdb.org/t/p/w185//t90Y3G8UGQp0f0DrP60wRu9gfrH.jpg",
            "http://image.tmdb.org/t/p/w185//aAmfIX3TT40zUHGcCKrlOZRKC7u.jpg",
            "http://image.tmdb.org/t/p/w185//7SGGUiTE6oc2fh9MjIk5M00dsQd.jpg",
            "http://image.tmdb.org/t/p/w185//vlTPQANjLYTebzFJM1G4KeON0cb.jpg",
            "http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg"
    };
    */
}
