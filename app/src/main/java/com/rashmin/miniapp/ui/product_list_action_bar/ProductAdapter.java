package com.rashmin.miniapp.ui.product_list_action_bar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rashmin.miniapp.R;
import com.rashmin.miniapp.util.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> datalist;
    private Context context;
    private MyAdapterItemClickListener listener;
    public ProductAdapter(Context context, List<Product> datalist) {
        this.datalist = datalist;
    }
    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.product_recylcer, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.product_id.setText(datalist.get(position).getName());
        holder.product_name.setText(""+datalist.get(position).getId());
        holder.product_category.setText(""+datalist.get(position).getCategory());
        holder.product_description.setText(""+datalist.get(position).getDescription());
        holder.product_price.setText(""+datalist.get(position).getPrice());
        holder.cardViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                {
                    listener.onItemClick(datalist.get(position),position);


                }
            }
        });
        holder.cardViewLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onItemLongClick(datalist.get(position),position);
                return false;
            }
        });
    }
    public void setListener(MyAdapterItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        private CardView cardViewLayout;
        private TextView product_id;
        private TextView product_name;
        private TextView product_category;
        private TextView product_description;
        private TextView product_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardViewLayout = itemView.findViewById(R.id.cardView);
            product_id = itemView.findViewById(R.id.p_id);
            product_name = itemView.findViewById(R.id.p_name);
            product_category = itemView.findViewById(R.id.p_category);
            product_description = itemView.findViewById(R.id.p_description);
            product_price = itemView.findViewById(R.id.p_price);

        }
    }
    public interface MyAdapterItemClickListener{
        public void onItemClick(Product item, int position);
        public void onItemLongClick(Product item,int position);
    }
}
