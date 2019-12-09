package oak.shef.ac.uk.assignment.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import oak.shef.ac.uk.assignment.entities.Image;
import oak.shef.ac.uk.assignment.repository.MyRepository;

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
        imagesToDisplay = myRepository.getAllImage();

    }

    //getter for LiveData
    public LiveData<List<Image>> getAllImages(){
        if(imagesToDisplay == null){
            imagesToDisplay = new MutableLiveData<List<Image>>();
        }
        return imagesToDisplay;
    }

    public void addImage(Image image){
        Image i = new Image("a","b","c",10.0,10.0,"a",1);
        myRepository.insertOneImage(i);
    }

}




