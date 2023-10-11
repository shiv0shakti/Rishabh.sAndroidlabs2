


package algonquin.cst2335.puri0023;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

public class SecondActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText editTextPhone;
    private ImageView img;
    private TextView emailTextView;
    private Bitmap capturedImage; // To store the captured image
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btn1 = findViewById(R.id.button2);
        editTextPhone = findViewById(R.id.editTextPhone);
        Button btn2 = findViewById(R.id.button3);
        img = findViewById(R.id.imageView);
        emailTextView = findViewById(R.id.textView3);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);

        // Retrieve the stored email address from SharedPreferences
        String storedEmail = getStoredEmail();
        emailTextView.setText(storedEmail);

        // Retrieve the stored phone number from SharedPreferences
        String storedPhoneNumber = getStoredPhoneNumber();
        editTextPhone.setText(storedPhoneNumber);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = editTextPhone.getText().toString();
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:" + phone));
                Toast.makeText(SecondActivity.this, "11233356565", Toast.LENGTH_SHORT).show();
                startActivity(call);
            }
        });

        // Register for the result callback in the onCreate method
        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Bundle extras = data.getExtras();
                            if (extras != null) {
                                // Capture the image
                                capturedImage = (Bitmap) extras.get("data");
                                if (capturedImage != null) {
                                    img.setImageBitmap(capturedImage);
                                }
                            }
                        }
                    }
                });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent(cameraResult);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_CANCELED) {
            // User canceled taking the photo, don't replace the picture
            if (capturedImage != null) {
                img.setImageBitmap(capturedImage);
            }
        }
    }

    private void dispatchTakePictureIntent(ActivityResultLauncher<Intent> cameraResult) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraResult.launch(cameraIntent);
    }

    private String getStoredEmail() {
        return sharedPreferences.getString("EmailAddress", "");
    }

    private String getStoredPhoneNumber() {
        return sharedPreferences.getString("PhoneNumber", "");
    }
}
