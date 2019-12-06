

package oak.shef.ac.uk.livedata;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import oak.shef.ac.uk.livedata.database.NumberData;

public class MyViewModel extends AndroidViewModel {
    private final MyRepository mRepository;

    LiveData<NumberData> numberDataToDisplay;

    public MyViewModel (Application application) {
        super(application);
        // creation and connection to the Repository
        mRepository = new MyRepository(application);
        numberDataToDisplay = mRepository.getNumberData();
    }


    /**
     * getter for the live data
     * @return
     */
    LiveData<NumberData> getNumberDataToDisplay() {
        if (numberDataToDisplay == null) {
            numberDataToDisplay = new MutableLiveData<NumberData>();
        }
        return numberDataToDisplay;
    }

    /**
     * request by the UI to generate a new random number
     */
    public void generateNewNumber() {
        mRepository.generateNewNumber();
    }
}
