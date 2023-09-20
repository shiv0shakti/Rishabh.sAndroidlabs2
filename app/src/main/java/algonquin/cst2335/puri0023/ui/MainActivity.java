package algonquin.cst2335.puri0023.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import algonquin.cst2335.puri0023.data.MainViewModel;
import algonquin.cst2335.puri0023.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel model;
    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        model.editString.observe(this,s -> {
        variableBinding.textview.setText("Your edit text has  " + s);
        });


        variableBinding.mybutton.setOnClickListener( click -> {

            model.editString.postValue(variableBinding.myEditText.getText().toString());

        });

        model.editString.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });


    }
}