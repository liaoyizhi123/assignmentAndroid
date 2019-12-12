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


//viewModel is used to prepare and pre-process the data
public class MyViewModel extends AndroidViewModel {

    private final MyRepository myRepository;

    //data to display
    LiveData<List<Image>> imagesToDisplay;

    public MyViewModel(@NonNull Application application) {
        super(application);
        //initialize the data to the corresponding view
        //初始化一个页面所需要的所有数据
        myRepository = new MyRepository(application);
        imagesToDisplay = myRepository.getAllImageLive();

    }

    //    //getter for LiveData
    public LiveData<List<Image>> getAllImages(){
        if(imagesToDisplay == null){
            imagesToDisplay = new MutableLiveData<List<Image>>();
        }
        return imagesToDisplay;
    }



    public int addPath(Path path){
        return myRepository.addPath(path);
    }

    public List<Path> getAllPath(){
        return myRepository.getAllPath();
    }

}




