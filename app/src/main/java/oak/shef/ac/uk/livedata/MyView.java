

package oak.shef.ac.uk.livedata;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import oak.shef.ac.uk.livedata.database.NumberData;

public class MyView extends AppCompatActivity {
    LiveData<NumberData> stringToDisplay;
    private MyViewModel myViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a new or existing ViewModel from the ViewModelProvider.
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        // Add an observer on the LiveData. The onChanged() method fires
        // when the observed data changes and the activity is
        // in the foreground.
        myViewModel.getNumberDataToDisplay().observe(this, new Observer<NumberData>(){
            @Override
            public void onChanged(@Nullable final NumberData newValue) {
                TextView tv= findViewById(R.id.textView);
                // if database is empty
                if (newValue==null)
                    tv.setText("click button");
                else
                    tv.setText(newValue.getNumber()+"");
            }});


        // it generates a request to generate a new random number
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.generateNewNumber();
            }
        });

    }
}

