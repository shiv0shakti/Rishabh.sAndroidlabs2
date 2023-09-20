package algonquin.cst2335.puri0023.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

        model.Compound.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean selected) {

            }
        });

        model.Compound.observe(this, selected -> {
            variableBinding.checkbox.setChecked(selected);
            variableBinding.radiobutton.setChecked(selected);
            variableBinding.switch1.setChecked(selected);
        });
        variableBinding.checkbox.setOnCheckedChangeListener(( btn, isChecked) -> {
            model.Compound.setValue(isChecked);
        });

        variableBinding.switch1.setOnCheckedChangeListener(( btn, isChecked) -> {
            model.Compound.setValue(isChecked);
        });

        variableBinding.radiobutton.setOnCheckedChangeListener(( btn, isChecked) -> {
            model.Compound.setValue(isChecked);
        });


        variableBinding.myimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle ImageView click here
                // You can add your custom logic here
                Toast.makeText(MainActivity.this, "ImageView Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // ImageButton onClickListener
        variableBinding.myimagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle ImageButton click here
                // Get the width and height of the ImageButton
                int width = variableBinding.myimagebutton.getWidth();
                int height = variableBinding.myimagebutton.getHeight();

                // Show a Toast message with the width and height
                Toast.makeText(MainActivity.this, "The width = " + width + " and height = " + height, Toast.LENGTH_SHORT).show();
            }
        });



    }
}