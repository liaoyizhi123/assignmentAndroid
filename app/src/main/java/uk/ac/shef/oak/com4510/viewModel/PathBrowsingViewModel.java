package uk.ac.shef.oak.com4510.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import uk.ac.shef.oak.com4510.entities.Path;
import uk.ac.shef.oak.com4510.repository.MyRepository;

public class PathBrowsingViewModel extends AndroidViewModel {

    private final MyRepository myRepository;
    //data to display
    LiveData<List<Path>> pathsToDisplay;

    public PathBrowsingViewModel(@NonNull Application application) {
        super(application);
        myRepository = new MyRepository(application);
        pathsToDisplay = myRepository.getAllPathsLive();
    }

    //get livedata of all the images
    public LiveData<List<Path>> getAllImagesLive(){
        if(pathsToDisplay == null){
            pathsToDisplay = new MutableLiveData<List<Path>>();
        }
        return pathsToDisplay;
    }


}




