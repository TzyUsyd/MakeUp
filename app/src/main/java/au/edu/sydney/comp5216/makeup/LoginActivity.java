package au.edu.sydney.comp5216.makeup;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView8;
    FirebaseAuth mAuth;
    private EditText editTextTextPassword3;
    private EditText editTextTextEmailAddress;
    private EditText editTextTextPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);
        mAuth = FirebaseAuth.getInstance();

        textView8=(TextView)findViewById(R.id.textView8);
        String text1="Already have an account? Login in";
        SpannableString spannableString1=new SpannableString(text1);
        spannableString1.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }, 0, text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView8.setText(spannableString1);
        textView8.setMovementMethod(LinkMovementMethod.getInstance());

         editTextTextPassword3 = (EditText) findViewById(R.id.editTextTextPassword3);
         editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
         editTextTextPassword2 = (EditText) findViewById(R.id.editTextTextPassword2);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);


    }
    public static Boolean isEmail(String str) {
        // Set email format
        Boolean isEmail = false;
        String expr = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if (str.matches(expr)) {
            isEmail = true;
        }
        return isEmail;
    }

    @Override
    public void onClick(View view) {
        // Set onclick change login to user or artist
        switch (view.getId()){
            case R.id.button3:
                login(UserInfo.ARTIST);
                break;
            case R.id.button4:
                login(UserInfo.CUSTOMER);
                break;
        }
    }

    private void login(int userRole) {
        // Verify email and password
        if (!isEmail(editTextTextEmailAddress.getText().toString())){
            Toast.makeText(LoginActivity.this, " email account error!",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (!editTextTextPassword2.getText().toString().equals(editTextTextPassword3.getText().toString())){
            Toast.makeText(LoginActivity.this, "Entered passwords differ!",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(editTextTextEmailAddress.getText().toString(), editTextTextPassword3.getText().toString())
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information


                            String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
                            UserInfo userInfo = new UserInfo();
                            userInfo.setEmail(editTextTextEmailAddress.getText().toString());
                            userInfo.setUid(uid);
                            userInfo.setRole(userRole);
                            FirebaseDatabase.getInstance().getReference().child("User").child(uid).setValue(userInfo);
                            Toast.makeText(LoginActivity.this, "Register successfully",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Register error",
                                    Toast.LENGTH_SHORT).show();
                            Log.w("createUserWithEmail", "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }
}
