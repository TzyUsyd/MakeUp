package au.edu.sydney.comp5216.makeup;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class Home2Activity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton card;

    private RadioButton mine;
    private List<Fragment> mfrgList = new ArrayList<>();
    private MyFragmentPagerAdapter fragmentPagerAdapter;
    NoScrollViewPager container;
    private RadioGroup radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        card = (RadioButton) findViewById(R.id.card);
        mine = (RadioButton) findViewById(R.id.mine);
        container = (NoScrollViewPager) findViewById(R.id.container);
        radio = (RadioGroup) findViewById(R.id.radio);
        changeImageSize();
        mfrgList.add(Order2Fragment.newInstance());
        mfrgList.add(Mine2Fragment.newInstance());
        fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mfrgList);
        container.setAdapter(fragmentPagerAdapter);
        container.setOffscreenPageLimit(mfrgList.size());
        radio.check(R.id.card);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.card){
                    container.setCurrentItem(0, false);
                }else if (checkedId==R.id.mine){
                    container.setCurrentItem(1, false);
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
    private void changeImageSize() {
        Drawable drawable3 = getResources().getDrawable(R.mipmap.icon_card);
        drawable3.setBounds(0, 0, 69, 69);
        card.setCompoundDrawables(null, drawable3, null, null);

        Drawable drawable5 = getResources().getDrawable(R.mipmap.icon_user);
        drawable5.setBounds(0, 0, 69, 69);
        mine.setCompoundDrawables(null, drawable5, null, null);
    }

}


