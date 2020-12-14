package au.edu.sydney.comp5216.makeup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
// (Reference from github library) Copyright (C) 2017 zhouwei
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class HomeFragment extends Fragment {

    HomeActivity activity ;
    private TabLayout tabLayout;
    //(Reference from github library) Copyright (C) 2017 zhouwei
    private MZBannerView mMZBanner;
    List<Integer> integerList = new ArrayList<>();
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    TextView address;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        activity = (HomeActivity) getActivity();
        tabLayout = view.findViewById(R.id.aftserop_tab);
         address = view.findViewById(R.id.adress);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(),MapActivity.class), Activity.RESULT_FIRST_USER);
            }
        });
        LinearLayout selectMap = view.findViewById(R.id.selectMap);
        selectMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MapActivity.class));
            }
        });
        ImageView homesearch = view.findViewById(R.id.homesearch);
        homesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                activity.toMassage();
            }
        });

       tabLayout.addTab(tabLayout.newTab().setText("Nails"));
       tabLayout.addTab(tabLayout.newTab().setText("Beauty"));
       tabLayout.addTab(tabLayout.newTab().setText("Brows"));
       tabLayout.addTab(tabLayout.newTab().setText("Facial"));
       tabLayout.addTab(tabLayout.newTab().setText("Mas"));
        integerList.add(R.mipmap.banner_1);
        integerList.add(R.mipmap.banner_2);
        integerList.add(R.mipmap.banner_3);
        integerList.add(R.mipmap.banner_4);
        mMZBanner = (MZBannerView) view.findViewById(R.id.banner);

        // Set data (Reference from github library) Copyright (C) 2017 zhouwei
        mMZBanner.setPages(integerList, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode ==RESULT_OK){
                address.setText(data.getStringExtra("address"));
            }

    }
    public static class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {

            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {

            mImageView.setImageResource(data);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
