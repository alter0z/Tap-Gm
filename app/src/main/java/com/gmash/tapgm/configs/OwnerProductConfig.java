package com.gmash.tapgm.configs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmash.tapgm.R;
import com.gmash.tapgm.models.OwnerProductModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OwnerProductConfig {

    public void setConfig(RecyclerView recyclerView, Context context, List<OwnerProductModel> ownerProductModels, List<String> key){
        OwnerProductAdapter ownerProductAdapter = new OwnerProductAdapter(ownerProductModels,key);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(ownerProductAdapter);
    }

    static class OwnerProductView extends RecyclerView.ViewHolder {
        private final TextView productName,category,stock,price;
        private final ImageView edit;
        private final LinearLayout linearLayout;
        private String key;

        public OwnerProductView(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_owner_item_layout,parent,false));

            edit = itemView.findViewById(R.id.edit_icon);
            productName = itemView.findViewById(R.id.product_owner_name);
            category = itemView.findViewById(R.id.category);
            stock = itemView.findViewById(R.id.owner_stock);
            price = itemView.findViewById(R.id.owner_price);
            linearLayout = itemView.findViewById(R.id.product_owner_linear_layout);
        }

        public void bind(OwnerProductModel ownerProductModel,String key) {
            productName.setText(ownerProductModel.getProduct());
            category.setText(ownerProductModel.getCategory());
            stock.setText("Stok "+ownerProductModel.getStock());
            price.setText("Rp"+ownerProductModel.getPrice());
            this.key = key;
        }
    }

    static class OwnerProductAdapter extends RecyclerView.Adapter<OwnerProductView> {
        private final List<OwnerProductModel> ownerProductModels;
        private final List<String> keys;

        public OwnerProductAdapter(List<OwnerProductModel> ownerProductModels, List<String> keys) {
            this.ownerProductModels = ownerProductModels;
            this.keys = keys;
        }

        @NonNull
        @NotNull
        @Override
        public OwnerProductView onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            return new  OwnerProductView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull OwnerProductConfig.OwnerProductView holder, int position) {
            holder.bind(ownerProductModels.get(position),keys.get(position));
        }

        @Override
        public int getItemCount() {
            return ownerProductModels.size();
        }
    }
}
