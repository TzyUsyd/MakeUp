package au.edu.sydney.comp5216.makeup;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class RecyclerviewAdapter2 extends RecyclerView.Adapter<RecyclerviewAdapter2.ViewHolder> {

    private Context context;
    private List<String> data;

    public RecyclerviewAdapter2(Context context, List<String> data){
        this.context = context;
        this.data = data;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order2,parent,false);
        return new ViewHolder(view);
    }
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        reference.child("Order").child(data.get(position)).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.getKey().equals("email")){
                        holder.email.setText("Customer email:"+child.getValue()+"");
                    }else if (child.getKey().equals("number")){
                        holder.num.setText("Order number :"+child.getValue()+"");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child("Order").child(data.get(position)).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            reference.child("confirmed").child(data.get(position)).child("email").setValue(dataSnapshot.child("email").getValue());
                            reference.child("confirmed").child(data.get(position)).child("number").setValue(dataSnapshot.child("number").getValue());
                             Toast.makeText(context, "Accept successfully",
                                Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView checkout;
        private TextView num;
        private TextView email;

        public ViewHolder(View itemView) {
            super(itemView);
            checkout = itemView.findViewById(R.id.checkout);
            num = itemView.findViewById(R.id.num);
            email = itemView.findViewById(R.id.email);

        }
    }
}
