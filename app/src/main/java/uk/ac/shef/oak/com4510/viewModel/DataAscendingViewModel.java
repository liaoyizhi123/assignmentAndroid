package uk.ac.shef.oak.com4510.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import uk.ac.shef.oak.com4510.entities.Image;
import uk.ac.shef.oak.com4510.repository.MyRepository;

public class DataAscendingViewModel extends AndroidViewModel {
    private final MyRepository myRepository;
    //data to display
    LiveData<List<Image>> imagesToDisplay;

    public DataAscendingViewModel(@NonNull Application application) {
        super(application);
        myRepository = new MyRepository(application);
        imagesToDisplay = myRepository.getAllImageLive();

    }

    //getter for LiveData
    public List<Image> getAllImages(){
        return myRepository.getAllImage();
    }

    public LiveData<List<Image>> getAllImagesLive(){
        if(imagesToDisplay == null){
            imagesToDisplay = new MutableLiveData<List<Image>>();
        }
        return imagesToDisplay;
    }
}
