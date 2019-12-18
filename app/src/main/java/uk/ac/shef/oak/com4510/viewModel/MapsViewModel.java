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

    //update the path when the user exit the map UI
    public void updatePath(int id,Path path){
        path.setId(id);
        myRepository.updatePath(path);
    }

    //add one image
    public void addImage(Image image){

        myRepository.insertOneImage(image);
    }

}
