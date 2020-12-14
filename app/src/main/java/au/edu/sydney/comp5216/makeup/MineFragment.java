package au.edu.sydney.comp5216.makeup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;


public class MineFragment extends Fragment {



    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        TextView logout = (TextView) view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        TextView email = (TextView) view.findViewById(R.id.email);
        FirebaseAuth instance = FirebaseAuth.getInstance();
        email.setText(instance.getCurrentUser().getEmail());
        return view;
    }



}
