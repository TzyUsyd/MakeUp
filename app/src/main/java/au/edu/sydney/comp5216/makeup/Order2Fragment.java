package au.edu.sydney.comp5216.makeup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Order2Fragment extends Fragment implements ValueEventListener {


    View view;
    List<String> list = new ArrayList<>();
    public static Order2Fragment newInstance() {
        Order2Fragment fragment = new Order2Fragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order2, container, false);
        initData();
        initView();
        return view;
    }
    FirebaseAuth instance = FirebaseAuth.getInstance();
    //email.setText("Customer email:"+instance.getCurrentUser().getEmail());
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    RecyclerviewAdapter2 adapter;
    RecyclerviewAdapter4 adapter3;
    RecyclerView recyclerView;
    private String key = "Order";
    private void initData() {
        recyclerView = view.findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initView() {
       reference.removeEventListener(Order2Fragment.this);
       reference.child(key).addValueEventListener(Order2Fragment.this);
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        list.clear();
        for (DataSnapshot child : dataSnapshot.getChildren()) {
                list.add(child.getKey()+"");
        }
        recyclerView.setAdapter(null);
            adapter = new RecyclerviewAdapter2(getActivity(),list);
            recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}
