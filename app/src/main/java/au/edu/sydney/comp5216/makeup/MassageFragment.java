package au.edu.sydney.comp5216.makeup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;


public class MassageFragment extends Fragment {


    View view;
    List<MassageData> list = new ArrayList<>();
    private List<Boolean> booleanArrayList;

    public static MassageFragment newInstance() {
        MassageFragment fragment = new MassageFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_massage, container, false);
        initData();
        initView();
        return view;
    }

    private void initData() {
        booleanArrayList = new ArrayList<>();
        booleanArrayList.add(false);
        booleanArrayList.add(false);
        booleanArrayList.add(false);
        booleanArrayList.add(false);
        booleanArrayList.add(false);
        booleanArrayList.add(false);
        booleanArrayList.add(false);
        booleanArrayList.add(false);
        booleanArrayList.add(false);
        booleanArrayList.add(false);

        List<Integer> integerList = new ArrayList<>();
        integerList.add(R.mipmap.icon_massage_1);
        integerList.add(R.mipmap.icon_massage_2);
        integerList.add(R.mipmap.icon_massage_3);
        integerList.add(R.mipmap.icon_massage_4);
        integerList.add(R.mipmap.icon_massage_5);
        integerList.add(R.mipmap.icon_massage_1);
        integerList.add(R.mipmap.icon_massage_2);
        integerList.add(R.mipmap.icon_massage_3);
        integerList.add(R.mipmap.icon_massage_4);
        integerList.add(R.mipmap.icon_massage_5);
        List<String> stringList = new ArrayList<>();
        stringList.add("Deep Tissue Massage");
        stringList.add("Swedish Massage");
        stringList.add("Aromatherapy Massage");
        stringList.add("Hot Stone Massage");
        stringList.add("Reflexology Foot Massage");
        stringList.add("Deep Tissue Massage");
        stringList.add("Swedish Massage");
        stringList.add("Aromatherapy Massage");
        stringList.add("Hot Stone Massage");
        stringList.add("Reflexology Foot Massage");
        List<String> stringList2 = new ArrayList<>();
        stringList2.add("A strong oil massage to ease tight");
        stringList2.add("A gentle oil massage to relax your body");
        stringList2.add("Aromatherapy oils to tantailse your senses");
        stringList2.add("A deeper massage aided by heat to relax and alleviate ailments");
        stringList2.add("An intensely relaxing experience");
        stringList2.add("A strong oil massage to ease tight");
        stringList2.add("A gentle oil massage to relax your body");
        stringList2.add("Aromatherapy oils to tantailse your senses");
        stringList2.add("A deeper massage aided by heat to relax and alleviate ailments");
        stringList2.add("An intensely relaxing experience");
        for (int i = 0; i < 10; i++) {
            MassageData massageData = new MassageData();
            massageData.setImg(integerList.get(i));
            massageData.setTitle(stringList.get(i));
            massageData.setDetail(stringList2.get(i));
            massageData.setRating(i/2f);
            massageData.setSave(i*22);
            massageData.setMassage(i*33);
            massageData.setShare(i*44);
            list.add(massageData);
        }
    }

    private void initView() {
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        ImageView bacMassage = view.findViewById(R.id.bacMassage);
        bacMassage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity homeActivity=(HomeActivity)getActivity();
                homeActivity.bacCategory();
            }
        });
        MassageRecyclerviewAdapter adapter = new MassageRecyclerviewAdapter(getActivity(),list);
        adapter.setBooleanList(booleanArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }


}
