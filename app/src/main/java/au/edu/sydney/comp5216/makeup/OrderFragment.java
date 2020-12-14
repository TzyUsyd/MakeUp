package au.edu.sydney.comp5216.makeup;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment implements ValueEventListener {

    private TabLayout tabLayout;
    View view;
    List<String> list = new ArrayList<>();
    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order, container, false);
        initData();
        initView();
        return view;
    }
    RecyclerviewAdapter adapter;
    RecyclerviewAdapter3 adapter3;
    RecyclerView recyclerView;
    private String key = "Order";
    FirebaseAuth instance = FirebaseAuth.getInstance();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private void initData() {
         recyclerView = view.findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



    }

    private void initView() {
        tabLayout = view.findViewById(R.id.aftserop_tab);

        tabLayout.addTab(tabLayout.newTab().setText("My orders"));
        tabLayout.addTab(tabLayout.newTab().setText("Confirmed"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("My orders")){
                    key = "Order";
                    reference.removeEventListener(OrderFragment.this);
                    reference.child(key).addValueEventListener(OrderFragment.this);
                }else {
                    key = "confirmed";
                    reference.removeEventListener(OrderFragment.this);
                    reference.child(key).addValueEventListener(OrderFragment.this);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
//

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        list.clear();
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            Log.e("tag1",child.getKey());
            Log.e("tag1",child.child("email").getValue()+"");
            Log.e("tag2",instance.getCurrentUser().getEmail()+"");
            if (child.child("email").getValue().toString().equals(instance.getCurrentUser().getEmail()+"")){
                list.add("");
            }
        }
        recyclerView.setAdapter(null);
        if (tabLayout.getSelectedTabPosition() ==0){
            adapter = new RecyclerviewAdapter(getActivity(),list);
            recyclerView.setAdapter(adapter);
        }else {
            adapter3 = new RecyclerviewAdapter3(getActivity(),list);
            recyclerView.setAdapter(adapter3);
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}
