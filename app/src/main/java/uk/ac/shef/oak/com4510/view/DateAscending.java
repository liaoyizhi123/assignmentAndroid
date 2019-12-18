package uk.ac.shef.oak.com4510.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.adapter.DateAscendingAdapter;
import uk.ac.shef.oak.com4510.entities.Image;
import uk.ac.shef.oak.com4510.viewModel.DataAscendingViewModel;
import uk.ac.shef.oak.com4510.viewModel.MyViewModel;

public class DateAscending extends AppCompatActivity {
    GridView gridView;

    //multiple
    LiveData<List<Image>> imagesToDisplay;
    //viewmodel to get livedata
    private DataAscendingViewModel dataAscendingViewModel;

    private  Context applicationContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationContext = getApplicationContext();

        setContentView(R.layout.activity_date_ascending);
        gridView=(GridView) findViewById(R.id.GridView1);

        dataAscendingViewModel = ViewModelProviders.of(this).get(DataAscendingViewModel.class);
        //add observation on the data
        dataAscendingViewModel.getAllImagesLive().observe(this, new Observer<List<Image>>() {

            //method invoked automatically on data change
            @Override
            public void onChanged(@Nullable final List<Image> images) {
                gridView.setAdapter(new DateAscendingAdapter(applicationContext,images));

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent =new Intent(getApplicationContext(),ImageDetails.class);

                        intent.putExtra("id", (int)id);
                        intent.putExtra("pathId",images.get(position).getPathId());
                        startActivity(intent);
                    }
                });

            }
        });


    }


}
