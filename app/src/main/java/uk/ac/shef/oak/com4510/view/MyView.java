package uk.ac.shef.oak.com4510.view;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;


import pl.aprilapps.easyphotopicker.EasyImage;
import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.Util.Util;
import uk.ac.shef.oak.com4510.entities.Image;
import uk.ac.shef.oak.com4510.entities.Path;
import uk.ac.shef.oak.com4510.viewModel.MyViewModel;

public class MyView extends AppCompatActivity {

    //a list of the Image
    private List<Image> liImages;
    private EditText et;

    //multiple
    LiveData<List<Image>> imagesToDisplay;
    //one
    private MyViewModel myViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et = (EditText) findViewById(R.id.et);

        //initialize the viewModel from ViewModelProviders
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);



    }





    public void dateAscending(View view){
        Intent intent = new Intent();
        intent.setClass(MyView.this,DateAscending.class);
        startActivity(intent);
    }

    public void pathBrowsing(View view){
        Intent intent = new Intent();
        intent.setClass(MyView.this,PathBrowsing.class);
        startActivity(intent);
    }



    public void clickToStart(View view){
        Intent intent = new Intent();
        intent.setClass(MyView.this, MapsActivity.class);

        String title = et.getText().toString().trim();

        if(title==null || title.equals("")) {

            Util.makeNote(MyView.this, this.getApplicationContext(),"Please enter a valid title");

        }else{

            //add data
            long starttime = System.currentTimeMillis();
            String startTimeString = String.valueOf(starttime);
            int addId = myViewModel.addPath(new Path(title, null, null, startTimeString, null));

            //put pathId
            intent.putExtra("pathId",addId);
            //put title
            intent.putExtra("title",title);

            startActivity(intent);

        }



    }
}





