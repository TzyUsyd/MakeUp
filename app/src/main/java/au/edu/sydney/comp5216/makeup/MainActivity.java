package au.edu.sydney.comp5216.makeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView5;
    FirebaseAuth instance;
    private EditText editTextTextEmailAddress2;
    private EditText editTextTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int hasWriteStoragePermission = ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        instance = FirebaseAuth.getInstance();
        textView5=(TextView)findViewById(R.id.textView5);
        String text1="Don't have an account? Sign up";
        SpannableString spannableString1=new SpannableString(text1);
        spannableString1.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        }, 0, text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView5.setText(spannableString1);
        textView5.setMovementMethod(LinkMovementMethod.getInstance());
         editTextTextEmailAddress2 = (EditText) findViewById(R.id.editTextTextEmailAddress2);
         editTextTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button.setOnClickListener(this);
        if (hasWriteStoragePermission == PackageManager.PERMISSION_GRANTED) {
        }else{
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // Request permission on storage access
        if (requestCode == 1000){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }else{
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("no permission")
                            .setPositiveButton("OK", (dialog1, which) ->
                                    ActivityCompat.requestPermissions(this,
                                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                            1000))
                            .setNegativeButton("Cancel", null)
                            .create()
                            .show();
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button2:
                login(UserInfo.ARTIST);
                break;
            case R.id.button:
                login(UserInfo.CUSTOMER);
                break;
        }
    }

    private void login(final int role) {
        instance.signInWithEmailAndPassword(editTextTextEmailAddress2.getText().toString(), editTextTextPassword.getText().toString())
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //success

                            initUserInfo(role);


                        } else {
                            //error
                            checkError(task.getException());

                        }
                    }
                });
    }

    private void initUserInfo(final int userRole) {
        // Get user profile from firebase
        FirebaseUser user = instance.getCurrentUser();
        DatabaseReference myDf = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid());
        myDf.addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserInfo model = dataSnapshot.getValue(UserInfo.class);
                int role = model.getRole();
                if (userRole==role){
                    Toast.makeText(MainActivity.this, "Login success",
                            Toast.LENGTH_SHORT).show();

                    if (role ==UserInfo.CUSTOMER){
                        startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    }else {
                        startActivity(new Intent(MainActivity.this,Home2Activity.class));
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Role error",
                            Toast.LENGTH_SHORT).show();
                }
            }






            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void checkError(Exception exception) {
        // Verify input with firebase

        if (exception instanceof FirebaseAuthInvalidCredentialsException) {
            Toast.makeText(MainActivity.this, "password is not correct.",
                    Toast.LENGTH_SHORT).show();
        }else if (exception instanceof FirebaseAuthInvalidUserException) {

            Toast.makeText(MainActivity.this, "User is not exist, please sign up",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}


