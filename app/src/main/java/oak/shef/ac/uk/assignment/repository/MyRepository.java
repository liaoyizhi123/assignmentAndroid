package oak.shef.ac.uk.assignment.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import oak.shef.ac.uk.assignment.MyDatabase;
import oak.shef.ac.uk.assignment.dao.ImageDao;
import oak.shef.ac.uk.assignment.dao.PathDao;
import oak.shef.ac.uk.assignment.entities.Image;

public class MyRepository extends ViewModel {

    private final ImageDao imageDao;
    private final PathDao pathDao;

    public MyRepository(Application application) {
        MyDatabase db = MyDatabase.getDatabase(application);
        imageDao = db.imageDao();
        pathDao = db.pathDao();
    }





    public LiveData<List<Image>> getAllImage(){
        return imageDao.findAll();
    }

    //call by UI
    public void insertOneImage(Image image){
        new insertAsyncTask(imageDao).execute(image);
    }


    //AsyncTask
    private static class insertAsyncTask extends AsyncTask<Image,Void,Void>{

        private final ImageDao imageDao;

        public insertAsyncTask(ImageDao imageDao) {
            this.imageDao = imageDao;
        }

        @Override
        protected Void doInBackground(Image... images) {
            imageDao.insert(images[0]);
            Log.i("MyRepository", "image add: "+images[0]+"");
            return null;
        }
    }

}





