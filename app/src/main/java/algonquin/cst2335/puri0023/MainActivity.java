package algonquin.cst2335.puri0023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    Button btn;
    EditText et;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        et = findViewById(R.id.editText2);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        // Retrieve the stored email address
        String storedEmail = sharedPreferences.getString("EmailAddress", "");
        et.setText(storedEmail);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et.getText().toString();

                // Save the email address in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("EmailAddress", email);
                editor.apply();

                // Start the SecondActivity
                Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);
                nextPage.putExtra("EmailAddress", email);
                startActivity(nextPage);
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.w(TAG, "Any Memory used by the application is freed");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.w(TAG, "The application is no longer responding to user input");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.w(TAG, "The application is no longer visible");
        super.onStop();
    }

    @Override
    protected void onResume() {
        Log.w(TAG, "The application is now responding to user input");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.w(TAG, "The application is now visible");
        super.onStart();
    }
}
