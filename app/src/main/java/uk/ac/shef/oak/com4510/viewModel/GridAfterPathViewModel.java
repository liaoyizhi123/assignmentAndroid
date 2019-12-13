package uk.ac.shef.oak.com4510.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import uk.ac.shef.oak.com4510.entities.Image;
import uk.ac.shef.oak.com4510.entities.Path;
import uk.ac.shef.oak.com4510.repository.MyRepository;

public class GridAfterPathViewModel extends AndroidViewModel {

    private final MyRepository myRepository;
    //data to display
    LiveData<List<Path>> imagesToDisplay;


    public GridAfterPathViewModel(@NonNull Application application) {
        super(application);
        myRepository = new MyRepository(application);

    }

    public LiveData<List<Image>> getImageLiveByPathId(int pathId){
        return myRepository.getImageLiveByPathId(pathId);
    }


}
