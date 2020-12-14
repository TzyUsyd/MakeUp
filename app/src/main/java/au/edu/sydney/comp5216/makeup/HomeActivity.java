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

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {


    private RadioButton home;
    private RadioButton category;
    private RadioButton card;
    private RadioButton cart;
    private RadioButton mine;
    private List<Fragment> mfrgList = new ArrayList<>();
    private MyFragmentPagerAdapter fragmentPagerAdapter;
    NoScrollViewPager container;
    private RadioGroup radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home = (RadioButton) findViewById(R.id.home);
        category = (RadioButton) findViewById(R.id.category);
        card = (RadioButton) findViewById(R.id.card);
        cart = (RadioButton) findViewById(R.id.cart);
        mine = (RadioButton) findViewById(R.id.mine);
        container = (NoScrollViewPager) findViewById(R.id.container);
        radio = (RadioGroup) findViewById(R.id.radio);
        changeImageSize();
        mfrgList.add(HomeFragment.newInstance());
        mfrgList.add(CategoryFragment.newInstance());
        mfrgList.add(CartFragment.newInstance());
        mfrgList.add(OrderFragment.newInstance());
        mfrgList.add(MineFragment.newInstance());
        mfrgList.add(MassageFragment.newInstance());
        fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mfrgList);
        container.setAdapter(fragmentPagerAdapter);
        container.setOffscreenPageLimit(mfrgList.size());
        radio.check(R.id.home);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Set menu onclick performance
                if (checkedId==R.id.home){
                    container.setCurrentItem(0, false);
                }else if (checkedId==R.id.category){
                    container.setCurrentItem(1, false);
                }else if (checkedId==R.id.cart){
                    container.setCurrentItem(2, false);
                }else if (checkedId==R.id.card){
                    container.setCurrentItem(3, false);
                }else if (checkedId==R.id.mine){
                    container.setCurrentItem(4, false);
                }
            }
        });
    }

    public void toMassage(){
        container.setCurrentItem(5, false);
    }
    public void bacCategory(){
        container.setCurrentItem(1, false);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
    private void changeImageSize() {
        //Define the bottom label image size
        Drawable drawable1 = getResources().getDrawable(R.mipmap.icon_home);
        drawable1.setBounds(0, 0, 69, 69);
        home.setCompoundDrawables(null, drawable1, null, null);

        Drawable drawable2 = getResources().getDrawable(R.mipmap.icon_columns);
        drawable2.setBounds(0, 0, 69, 69);
        category.setCompoundDrawables(null, drawable2, null, null);

        Drawable drawable3 = getResources().getDrawable(R.mipmap.icon_card);
        drawable3.setBounds(0, 0, 69, 69);
        card.setCompoundDrawables(null, drawable3, null, null);

        Drawable drawable4 = getResources().getDrawable(R.mipmap.icon_cart);
        drawable4.setBounds(0, 0, 69, 69);
        cart.setCompoundDrawables(null, drawable4, null, null);

        Drawable drawable5 = getResources().getDrawable(R.mipmap.icon_user);
        drawable5.setBounds(0, 0, 69, 69);
        mine.setCompoundDrawables(null, drawable5, null, null);
    }

}


