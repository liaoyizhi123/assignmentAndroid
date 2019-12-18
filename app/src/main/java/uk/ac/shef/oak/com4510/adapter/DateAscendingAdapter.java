package uk.ac.shef.oak.com4510.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

import uk.ac.shef.oak.com4510.entities.Image;

public class DateAscendingAdapter extends BaseAdapter {
    private Context mContext;
    private List<Image> imagesList;

    public DateAscendingAdapter(Context mContext, List<Image> imagesList) {
        this.mContext = mContext;
        this.imagesList = imagesList;
    }

    @Override
    public int getCount() {
        return imagesList.size();
    }

    @Override
    public Object getItem(int position) {
        return imagesList.get(position);
    }


    @Override
    public long getItemId(int position) {
        //get id
        int id = imagesList.get(position).getId();
        return id;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);

        Bitmap bitmap = BitmapFactory.decodeFile(imagesList.get(position).getUrl());
        imageView.setImageBitmap(bitmap);

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(340,350));

        return imageView;
    }
}