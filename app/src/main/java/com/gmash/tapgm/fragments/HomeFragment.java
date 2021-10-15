package com.gmash.tapgm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.gmash.tapgm.R;
import com.gmash.tapgm.configs.ProductConfig;
import com.gmash.tapgm.helperClass.FirebaseDatabaseHelper;
import com.gmash.tapgm.models.ProductModel;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeFragment extends Fragment {

    // variables
    private TextInputEditText search;
    private ImageView searchButton,aboutBalance;
    private TextView balance;
    private RecyclerView productLayout;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        // find id
        search = view.findViewById(R.id.user_search);
        searchButton =  view.findViewById(R.id.user_search_button);
        aboutBalance = view.findViewById(R.id.user_about_pay);
        balance = view.findViewById(R.id.user_balance);
        productLayout = view.findViewById(R.id.user_product);

        // read data
        new FirebaseDatabaseHelper().ReadProductData(new FirebaseDatabaseHelper.ProductDataStatues() {
            @Override
            public void ProductDataIsLoaded(List<ProductModel> product, List<String> key) {
                new ProductConfig().setConfig(productLayout,inflater.getContext(),product,key);
            }

            @Override
            public void ProductDataIsInserted() {

            }

            @Override
            public void ProductDataIsUpdated() {

            }

            @Override
            public void ProductDataIsDeleted() {

            }
        });

        return view;
    }
}
