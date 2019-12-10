package uk.ac.shef.oak.com4510.view;

import android.content.Intent;
import android.os.Bundle;

import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.io.File;
import java.util.List;


import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import uk.ac.shef.oak.com4510.MapsActivity;
import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.Util.Util;
import uk.ac.shef.oak.com4510.entities.Image;
import uk.ac.shef.oak.com4510.entities.Path;
import uk.ac.shef.oak.com4510.viewModel.MyViewModel;

public class MyView extends AppCompatActivity {

    //multiple
    LiveData<List<Image>> imagesToDisplay;

    //one
    private MyViewModel myViewModel;

    private List<Image> liImages;
    private EditText et;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et = (EditText) findViewById(R.id.et);

        //initialize
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
//        myViewModel.getAllImages().observe(this, new Observer<List<Image>>() {
//            @Override
//            public void onChanged(@Nullable List<Image> images) {
//                //
//                TextView tv = (TextView) findViewById(R.id.textView);
//                // if database is empty
//                if (images == null){
//                    tv.setText("click button");
//                }else {
//                    int size = images.size();
//                    tv.setText(size+" add");
//                }
//            }
//        });
//
//
//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myViewModel.addImage(null);
//            }
//        });
//
//        Button btn_getImage = findViewById(R.id.btn_getImage);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myViewModel.addImage(null);
//            }
//        });
//
//        //initialise EasyImage
//        initEasyImage();
//        //camera
//        Button cameraBtn = (Button) findViewById(R.id.camera);
//        cameraBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EasyImage.openCamera(MyView.this, 0);
//            }
//        });
//

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        System.out.println("--------------");
//        System.out.println("-------"+requestCode);
//        System.out.println("-------"+resultCode);
//
//        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
//            @Override
//            public void onImagesPicked(@NonNull List<File> imageFiles, EasyImage.ImageSource source, int type) {
//
//                System.out.println("source-------"+source);
//                System.out.println("-------"+imageFiles);
//            }
//        });
//
//    }


    private void initEasyImage() {
        EasyImage.configuration(this)
                .setImagesFolderName("EasyImage sample")
                .setCopyTakenPhotosToPublicGalleryAppFolder(true)
                .setCopyPickedImagesToPublicGalleryAppFolder(false)
                .setAllowMultiplePickInGallery(true);
    }
    public void dateAscending(View view){
        Intent intent = new Intent();
        intent.setClass(MyView.this,DateAscending.class);
//        String account =getIntent().getStringExtra("username");
////        intent.putExtra("username",account);
        startActivity(intent);
    }

    public void pathBrowsing(View view){
        Intent intent = new Intent();
        intent.setClass(MyView.this,GridAfterPath.class);
//        String account =getIntent().getStringExtra("username");
////        intent.putExtra("username",account);
        startActivity(intent);
    }



    public void clickToStart(View view){
        Intent intent = new Intent();
        intent.setClass(MyView.this, MapsActivity.class);
//        String account =getIntent().getStringExtra("username");
////        intent.putExtra("username",account);

        String title = et.getText().toString().trim();

        if(title==null || title.equals("")) {

            Util.makeNote(MyView.this, this.getApplicationContext(),"Please enter a valid title");

        }else{

            //add data
//            long starttime = System.currentTimeMillis();
//            String startTimeString = String.valueOf(starttime);
//            int addId = myViewModel.addPath(new Path(title, null, null, startTimeString, null));


//            List<Path> allPath = myViewModel.getAllPath();
//            Util.makeNote(MyView.this, this.getApplicationContext(),String.valueOf(allPath.get(0).getId()));

            //intent.putExtra("pathId",addId);
            //intent.putExtra("title",title);

            startActivity(intent);

        }



    }
}





