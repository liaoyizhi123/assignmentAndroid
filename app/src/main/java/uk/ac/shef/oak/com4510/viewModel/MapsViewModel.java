package uk.ac.shef.oak.com4510.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import uk.ac.shef.oak.com4510.entities.Image;
import uk.ac.shef.oak.com4510.entities.Path;
import uk.ac.shef.oak.com4510.repository.MyRepository;

public class MapsViewModel extends AndroidViewModel {

    private final MyRepository myRepository;

    public MapsViewModel(@NonNull Application application) {
        super(application);
        myRepository = new MyRepository(application);


    }

    public void updatePath(int id,Path path){
        path.setId(id);
        myRepository.updatePath(path);
    }

    public void addImage(Image image){

        myRepository.insertOneImage(image);
    }

}
