package au.edu.sydney.comp5216.makeup;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MassageRecyclerviewAdapter extends RecyclerView.Adapter<MassageRecyclerviewAdapter.ViewHolder> {

    private Context context;
    private List<MassageData> data;
    private List<Boolean> booleanList = new ArrayList<>();

    public MassageRecyclerviewAdapter(Context context, List<MassageData> data){
        this.context = context;
        this.data = data;

    }

    public void setBooleanList(List<Boolean> booleanList){
        this.booleanList.clear();
        this.booleanList.addAll(booleanList);
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_massage,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,  int position) {

        holder.title.setText(data.get(position).getTitle());
        holder.detail.setText(data.get(position).getDetail());
        holder.rating.setRating(data.get(position).getRating());
        holder.image.setImageDrawable(context.getResources().getDrawable(data.get(position).getImg()));
        holder.savetext.setText(data.get(position).getSave()+"");
        holder.massage.setText(data.get(position).getMassage()+"");
        holder.share.setText(data.get(position).getShare()+"");
        holder.saveimg.setImageDrawable(booleanList.get(position)?context.getResources().getDrawable(R.mipmap.icon_save_true):
                context.getResources().getDrawable(R.mipmap.icon_save));

        holder.saveimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booleanList.set(position,!booleanList.get(position));
                notifyDataSetChanged();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to buy
                context.startActivity(new Intent(context,DetailActivity.class).putExtra("id",data.get(position).getImg()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView detail;
        private RatingBar rating;
        private ImageView saveimg;
        private ImageView image;
        private TextView savetext;
        private TextView massage;
        private TextView share;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            detail = itemView.findViewById(R.id.detail);
            rating = itemView.findViewById(R.id.rating);
            saveimg = itemView.findViewById(R.id.saveimg);
            image = itemView.findViewById(R.id.image);
            savetext = itemView.findViewById(R.id.savetext);
            massage = itemView.findViewById(R.id.massage);
            share = itemView.findViewById(R.id.share);

        }
    }
}
