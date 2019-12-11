package uk.ac.shef.oak.com4510.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

import uk.ac.shef.oak.com4510.MyDatabase;
import uk.ac.shef.oak.com4510.dao.ImageDao;
import uk.ac.shef.oak.com4510.dao.PathDao;
import uk.ac.shef.oak.com4510.entities.Image;
import uk.ac.shef.oak.com4510.entities.Path;

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

    public List<Path> getAllPath(){
        List<Path> paths = null;
        try {
            paths = new getAllPathAsyncTask(pathDao).execute().get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return paths;
    }









    public Integer addPath(Path path){
        Integer addId=0;
        try {
            addId = new insertAsyncTask2(pathDao).execute(path).get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return addId;
    }




    //call by UI
    public void insertOneImage(Image image){
        new insertAsyncTask1(imageDao).execute(image);
    }


    //AsyncTask
    private static class insertAsyncTask1 extends AsyncTask<Image,Void,Void> {

        private final ImageDao imageDao;

        public insertAsyncTask1(ImageDao imageDao) {
            this.imageDao = imageDao;
        }

        @Override
        protected Void doInBackground(Image... images) {
            imageDao.insert(images[0]);
            Log.i("MyRepository", "image add: "+images[0]+"");
            return null;
        }
    }


    private class insertAsyncTask2 extends AsyncTask<Path,Void,Integer> {
        private final PathDao pathDao;
        public insertAsyncTask2(PathDao pathDao) {
            this.pathDao = pathDao;
        }
        @Override
        protected Integer doInBackground(Path... paths) {
            System.out.println("------------");
            System.out.println(paths[0]);
            long insertId = pathDao.insert(paths[0]);

            Log.i("MyRepository", "path add: "+paths[0]+"");
            return (int)insertId;
        }
    }

    private class getAllPathAsyncTask extends AsyncTask<Void,Void,List<Path>>{
        private final PathDao pathDao;
        public getAllPathAsyncTask(PathDao pathDao) {
            this.pathDao = pathDao;
        }
        @Override
        protected List<Path> doInBackground(Void... voids) {
            List<Path> allPath = pathDao.findAll();
            return allPath;
        }
    }
}





