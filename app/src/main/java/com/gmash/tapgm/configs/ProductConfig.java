package com.gmash.tapgm.configs;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmash.tapgm.R;
import com.gmash.tapgm.models.ProductModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductConfig {

    public void setConfig(RecyclerView recyclerView, Context context, List<ProductModel> productModels, List<String> key){
        ProductAdapter productAdapter = new ProductAdapter(productModels,key);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(productAdapter);
    }

    static class ProductView extends RecyclerView.ViewHolder {
        private final TextView _visitor,owner;
        private final ImageView status;
        private final RelativeLayout relativeLayout;
        private String key;

        public ProductView(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_layout,parent,false));

            status = itemView.findViewById(R.id.img_status);
            _visitor = itemView.findViewById(R.id.visitor);
            owner = itemView.findViewById(R.id.owner);
            relativeLayout = itemView.findViewById(R.id.product_layout);
        }

        public void bind(ProductModel productModel,String key) {
            String visitors = productModel.getVisitor();
            int visitor = Integer.parseInt(visitors);
            if (visitor >= 10) {
                status.setImageResource(R.drawable.ic_visitor_is_full);
                _visitor.setTextColor(Color.parseColor("#EC7474"));
            } else {
                status.setImageResource(R.drawable.ic_visitor_is_not_full);
            }

            _visitor.setText("Jumlah pengunjung: "+visitors);
            owner.setText("Kios "+productModel.getOwner());
            this.key = key;
        }
    }

    static class ProductAdapter extends RecyclerView.Adapter<ProductView> {
        private final List<ProductModel> productModels;
        private final List<String> keys;

        public ProductAdapter(List<ProductModel> productModels, List<String> keys) {
            this.productModels = productModels;
            this.keys = keys;
        }

        @NonNull
        @NotNull
        @Override
        public ProductView onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            return new  ProductView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull ProductConfig.ProductView holder, int position) {
            holder.bind(productModels.get(position),keys.get(position));
        }

        @Override
        public int getItemCount() {
            return productModels.size();
        }
    }
}
