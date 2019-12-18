package uk.ac.shef.oak.com4510.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import uk.ac.shef.oak.com4510.entities.Image;
import uk.ac.shef.oak.com4510.entities.Path;
import uk.ac.shef.oak.com4510.repository.MyRepository;

public class ImageDetailsViewModel extends AndroidViewModel {
    private final MyRepository myRepository;
    LiveData<List<Image>> allImage;

    public ImageDetailsViewModel(@NonNull Application application) {
        super(application);
        myRepository = new MyRepository(application);
    }

    //get image by id
    public LiveData<Image> getImage(int imageId){
        return myRepository.getImage(imageId);
    }

    //get path by id
    public LiveData<Path> getPath(Integer pathId){
        return myRepository.getPath(pathId);
    }

    //get all the image by Path id
    public LiveData<List<Image>> getAllImageLiveByPathId(int pathId){
        return myRepository.getAllImageLiveByPathId(pathId);
    }




}
