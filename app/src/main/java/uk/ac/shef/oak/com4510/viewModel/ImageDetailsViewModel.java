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

    public ImageDetailsViewModel(@NonNull Application application) {
        super(application);
        myRepository = new MyRepository(application);

    }

    public LiveData<Image> getImage(int imageId){
        return myRepository.getImage(imageId);
    }

    public LiveData<Path> getPath(Integer pathId){
        return myRepository.getPath(pathId);
    }

//    public LiveData<List<Image>> getAllImagesLive(){
//        if(imagesToDisplay == null){
//            imagesToDisplay = new MutableLiveData<List<Image>>();
//        }
//        return imagesToDisplay;
//    }


}
