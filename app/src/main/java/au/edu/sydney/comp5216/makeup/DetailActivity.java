package au.edu.sydney.comp5216.makeup;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class DetailActivity extends AppCompatActivity {


    private ImageView like;
    private ImageView save2;
    private boolean likeb = false;
    private boolean saveb = false;
    private ImageView detail_img;
    private ImageView bacMassage;
    private TextView buynow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        like = findViewById(R.id.like);
        save2 = findViewById(R.id.save2);
        detail_img = findViewById(R.id.detail_img);
        bacMassage = findViewById(R.id.bacMassage);
        buynow = findViewById(R.id.buynow);

        bacMassage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        detail_img.setImageDrawable(DetailActivity.this.getResources().getDrawable(getIntent().getIntExtra("id", 0)));
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set Heart image change when click
                likeb = !likeb;
                like.setImageDrawable(likeb ? DetailActivity.this.getResources().getDrawable(R.mipmap.icon_save_true)
                        : DetailActivity.this.getResources().getDrawable(R.mipmap.icon_save));
            }
        });

        save2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveb = !saveb;
                save2.setImageDrawable(saveb ? DetailActivity.this.getResources().getDrawable(R.mipmap.icon_star_true)
                        : DetailActivity.this.getResources().getDrawable(R.mipmap.icon_star));
            }
        });
        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailActivity.this, ComfirmActivity.class));
            }
        });
    }


}


