package algonquin.cst2335.puri0023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Rishabh Puri
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity {
    boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
    /**
     * This holds the text at the center of screen
     */
    TextView tv = null;
    /**
     * this holds the edit text where we have to write password
     */
    EditText et = null;
    /**
     * this hold the button to login
     */
    Button btn=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        et = findViewById(R.id.Edittext);
        btn = findViewById(R.id.Button);


        btn.setOnClickListener(clk -> {
            String password = et.getText().toString();


            checkPasswordComplexity(password);
        });

    }

    /**
     * This function takes a password as a String and checks its validity. The password (pw)
     * is evaluated for compliance with specified criteria, such as length, complexity, and
     * any additional rules defined by the application.
     *
     * @param pw the strig object that we are checking
     * @return Returns true if  if the password is complex enough,
     * and false if it is not complex enough.
     */
    boolean checkPasswordComplexity(String pw) {
        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;
        for (char c : pw.toCharArray()) {
            if (Character.isUpperCase(c)) {
                foundUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else if (isSpecialCharacter(c)) {
                foundSpecial = true;
            }
        }
        if (!foundUpperCase) {
            tv.setText("You shall not pass!");
            Toast.makeText(getApplicationContext(), "Password is missing an uppercase letter", Toast.LENGTH_SHORT).show();
            // Say that they are missing an upper case letter;
            return false;
        } else if (!foundLowerCase) {
            tv.setText("You shall not pass!");
            Toast.makeText(getApplicationContext(), "Password is missing a lowercase letter", Toast.LENGTH_SHORT).show();
            // Say that they are missing a lower case letter;
            return false;
        } else if (!foundNumber) {
            tv.setText("You shall not pass!");
            Toast.makeText(getApplicationContext(), "Password is missing a number", Toast.LENGTH_SHORT).show();
            // Say that they are missing a Number;
            return false;
        } else if (!foundSpecial) {
            tv.setText("You shall not pass!");
            Toast.makeText(getApplicationContext(), "Password is missing a special character", Toast.LENGTH_SHORT).show();
            // Say that they are missing a Special character;
            return false;
        } else {
            tv.setText("You shall  pass!");
            Toast.makeText(getApplicationContext(), "Password meets all the requirements", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
    /**
     * Checks if a character is a special character.
     *
     * @param c The character to be checked.
     * @return True if the character is a special character, false otherwise.
     */
    boolean isSpecialCharacter(char c) {
        {
            switch (c)
            {
                case '#':
                case '$':
                case '%':
                case '^':
                case '&':
                case '*':
                case '!':
                case '@':
                    return true;
                default:
                    return false;
            }
        }
    }
}
