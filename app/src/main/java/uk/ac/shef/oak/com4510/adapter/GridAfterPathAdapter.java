package uk.ac.shef.oak.com4510.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.entities.Image;

public class GridAfterPathAdapter extends BaseAdapter {
    private Context mContext;
    public Image [] imageArray;



    public GridAfterPathAdapter(Context mContext, Image[] myDataset) {
        this.mContext = mContext;
        this.imageArray = myDataset;
    }

    @Override
    public int getCount() {
        return imageArray.length;
    }

    @Override
    public Object getItem(int position) {
        return imageArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);

        Bitmap bitmap = BitmapFactory.decodeFile(imageArray[position].getUrl());
        imageView.setImageBitmap(bitmap);

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(340,350));

        return imageView;
    }
}
