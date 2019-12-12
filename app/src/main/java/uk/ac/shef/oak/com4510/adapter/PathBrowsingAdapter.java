package uk.ac.shef.oak.com4510.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.Util.Util;
import uk.ac.shef.oak.com4510.entities.Path;
import uk.ac.shef.oak.com4510.view.GridAfterPath;
import uk.ac.shef.oak.com4510.view.PathBrowsing;
import uk.ac.shef.oak.com4510.viewModel.MyViewModel;

public class PathBrowsingAdapter extends RecyclerView.Adapter<PathBrowsingAdapter.ViewHolder> {
    private Context context;
    private Path [] items;


    public PathBrowsingAdapter(Path [] items){
        this.items=items;
    }

    public PathBrowsingAdapter(Context context,Path [] items)
    {
        super();
        this.context = context;
        this.items = items;

    }

    @NonNull
    @Override
    public  ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View textView = LayoutInflater.from(parent.getContext()).inflate(R.layout.paths, parent, false);
        return new ViewHolder(textView);    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.TextViewDate.setText(items[position].getStartTimestamp());
        holder.TextViewTitle.setText(items[position].getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), GridAfterPath.class);

                //Pass Title
                String Title = items[position].getTitle();
                intent.putExtra("Title", Title);

                v.getContext().startActivity(intent);
            }
        });


    }
    @Override
    public int getItemCount() {
        return items.length;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView TextViewDate, TextViewTitle;
        ViewHolder(View itemView) {
            super(itemView);
            TextViewDate=(TextView)itemView.findViewById(R.id.TextViewDate);
            TextViewTitle=(TextView)itemView.findViewById(R.id.TextViewTitle);



        }
    }

}