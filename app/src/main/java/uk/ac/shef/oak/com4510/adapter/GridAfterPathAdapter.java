package uk.ac.shef.oak.com4510.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import uk.ac.shef.oak.com4510.R;

public class GridAfterPathAdapter extends BaseAdapter {
    private Context mContext;
    public int [] imageArray={
            R.drawable.joe1, R.drawable.joe2,
            R.drawable.joe3, R.drawable.view1,
            R.drawable.view2, R.drawable.view3,
            R.drawable.view4
    };



    public GridAfterPathAdapter(Context mContext) {
        this.mContext = mContext;
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
        imageView.setImageResource(imageArray[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(340,350));

        return imageView;
    }
}
