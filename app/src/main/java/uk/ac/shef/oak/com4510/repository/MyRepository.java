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

    public LiveData<List<Image>> getAllImageLiveByPathId(int pathId){
        return imageDao.findByPathId(pathId);
    }

    public List<Image> getAllImage() {
        List<Image> images = null;
        try {
            images = new getImageAsyncTask(imageDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return images;
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

    public LiveData<List<Path>> getAllPathsLive(){ return pathDao.getAllPaths();}

    public LiveData<List<Image>> getAllImageLive() {
        return imageDao.getLiveImage();
    }

    public LiveData<Image> getImage(int imageId){
        return imageDao.findById(imageId);
    }

    public LiveData<Path> getPath(int pathId){
        Path value = pathDao.findById(pathId).getValue();
        return pathDao.findById(pathId);
    }

    public LiveData<List<Image>> getImageLiveByPathId(int pathId){return imageDao.findByPathId(pathId);}




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








    /**
     *
     *
     *
     *
     *
     *
     */
    public void updatePath(Path path){
        new pathAsyncTask(pathDao).execute(path);
    }
    //updatePath AsyncTask
    private static class pathAsyncTask extends AsyncTask<Path,Void,Void> {

        private final PathDao pathDao;
        public pathAsyncTask( PathDao pathDao) {
            this.pathDao = pathDao;
        }

        @Override
        protected Void doInBackground(Path... paths) {
            pathDao.updatePath(paths[0].getId(), paths[0].getLocation(), paths[0].getStopTimestamp());
            return null;
        }
    }

    //call by UI
    public void insertOneImage(Image image){
        new imageAsyncTask(imageDao).execute(image);
    }

    //AsyncTask
    private static class imageAsyncTask extends AsyncTask<Image,Void,Void> {

        private final ImageDao imageDao;

        public imageAsyncTask(ImageDao imageDao) {
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

    private class getImageAsyncTask extends AsyncTask<Void,Void,List<Image>>{
        private final ImageDao imageDao;
        public getImageAsyncTask(ImageDao imageDao) {
            this.imageDao = imageDao;
        }

        @Override
        protected List<Image> doInBackground(Void... voids) {
            return imageDao.findAll();
        }
    }
}





