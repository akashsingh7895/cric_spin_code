package com.avssolution.fancylivecricketscore.SideDrawer;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avssolution.fancylivecricketscore.R;


public class AdapterNavItems extends RecyclerView.Adapter<AdapterNavItems.myViewholder>{
    Context context;
    String[] arrNavItems;

   // private int[] arrImgs={R.drawable.ic_rate_star,R.drawable.ic__share,R.drawable.ic__contact};
    private int[] arrImgs={R.drawable.ic_baseline_star_24,R.drawable.ic_baseline_share_24,R.drawable.ic_baseline_contacts_24};

    public AdapterNavItems(Context context){
        this.context=context;
        arrNavItems=context.getResources().getStringArray(R.array.arr_nav_items);
    }
    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater l=LayoutInflater.from(context);
        View v=l.inflate(R.layout.adapter_nav_items,parent,false);
        myViewholder myViewholder=new myViewholder(v);
        v.findViewById(R.id.root)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new CoreTask().navTask(context,myViewholder.getAdapterPosition());
                    }
                });
        return myViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewholder holder, int position) {
        holder.tvItem.setText(arrNavItems[position]);
        holder.imgIcon.setImageResource(arrImgs[position]);
    }

    @Override
    public int getItemCount() {
        return arrNavItems.length;
    }

    class myViewholder extends RecyclerView.ViewHolder {
        TextView tvItem;
        ImageView imgIcon;
        public myViewholder(@NonNull View itemView) {
            super(itemView);
            tvItem=itemView.findViewById(R.id.tvItem);
            imgIcon=itemView.findViewById(R.id.imgIcon);
        }
    }
}
