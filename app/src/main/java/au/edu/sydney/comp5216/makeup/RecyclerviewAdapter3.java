package au.edu.sydney.comp5216.makeup;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerviewAdapter3 extends RecyclerView.Adapter<RecyclerviewAdapter3.ViewHolder> {

    private Context context;
    private List<String> data;

    public RecyclerviewAdapter3(Context context, List<String> data){
        this.context = context;
        this.data = data;

    }
    public void addAll(List<String> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order3,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

        }
    }
}
