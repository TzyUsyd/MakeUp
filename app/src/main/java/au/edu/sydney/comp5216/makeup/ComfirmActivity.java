package au.edu.sydney.comp5216.makeup;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;


public class ComfirmActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    TextView ordernumber;
    RadioGroup rg1;
    RadioGroup rg2;
    int s;
    TextView adress;

    // Disable lint to check
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirm);
        TextView email = findViewById(R.id.email);
        TextView checkout = findViewById(R.id.checkout);
        adress = findViewById(R.id.adress);
        ImageView bacMassage = findViewById(R.id.bacMassage);
        RelativeLayout tomap = findViewById(R.id.tomap);

        tomap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(ComfirmActivity.this, MapActivity.class), 100);
            }
        });
        // Time selection
        rg1 = findViewById(R.id.rg1);
        rg2 = findViewById(R.id.rg2);
        RadioButton rd1 = findViewById(R.id.rd1);
        RadioButton rd2 = findViewById(R.id.rd2);
        RadioButton rd3 = findViewById(R.id.rd3);
        RadioButton rd4 = findViewById(R.id.rd4);


        rd1.setOnCheckedChangeListener(this);
        rd2.setOnCheckedChangeListener(this);
        rd3.setOnCheckedChangeListener(this);
        rd4.setOnCheckedChangeListener(this);
        // Display random order number
        ordernumber = findViewById(R.id.ordernumber);
        Random random = new Random();
        s = random.nextInt(10) % (10 - 1 + 1) + 1;
        ordernumber.setText("Order number: " + s);
        FirebaseAuth instance = FirebaseAuth.getInstance();
        // Get user info from firebase
        email.setText("Customer email:" + instance.getCurrentUser().getEmail());
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


        bacMassage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long l = System.currentTimeMillis();

                reference.child("Order").child(l + "").child("email").setValue(instance.getCurrentUser().getEmail());
                reference.child("Order").child(l + "").child("number").setValue(s + "");
                Toast.makeText(ComfirmActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            adress.setText(data.getStringExtra("address"));
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.rd1:
                rg2.clearCheck();
                break;
            case R.id.rd2:
                rg2.clearCheck();
                break;
            case R.id.rd3:
                rg1.clearCheck();
                break;
            case R.id.rd4:
                rg1.clearCheck();
                break;
            default:
                break;
        }
    }
}


