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
import com.gmash.tapgm.configs.OwnerProductConfig;
import com.gmash.tapgm.configs.ProductConfig;
import com.gmash.tapgm.helperClass.FirebaseDatabaseHelper;
import com.gmash.tapgm.models.OwnerProductModel;
import com.gmash.tapgm.models.ProductModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KioskFragment extends Fragment {

    // variables
    private TextInputEditText search;
    private ImageView searchButton,aboutBalance;
    private TextView balance,status;
    private RecyclerView ownerProductLayout;
    private String statues,UserID,userBalance;
    private FirebaseAuth auth;
    private FirebaseDatabase root;
    private DatabaseReference userReference;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kiosk,container,false);

        // find id
        search = view.findViewById(R.id.owner_search);
        searchButton =  view.findViewById(R.id.owner_search_button);
        aboutBalance = view.findViewById(R.id.owner_about_pay);
        balance = view.findViewById(R.id.owner_balance);
        status = view.findViewById(R.id.kiosk_status);
        ownerProductLayout = view.findViewById(R.id.owner_product);

//        auth = FirebaseAuth.getInstance();
//        FirebaseUser user = auth.getCurrentUser();
//        assert user != null;
//        UserID = user.getUid();
//        root = FirebaseDatabase.getInstance();
//        userReference = root.getReference("users").child(UserID);
//
//        userReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                if (snapshot.exists()){
//                    statues = snapshot.child("kiosk_status").getValue(String.class);
//                    userBalance = snapshot.child("balance").getValue(String.class);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });

//        // set balance
//        balance.setText(userBalance);

//        // check kiosk status
//        if (statues.equals("open")) {
//            status.setText("Produk");
//        } else {
//            status.setText("Anda belum memiliki kios");
//        }

        // read data
        new FirebaseDatabaseHelper().ReadOwnerProductData(new FirebaseDatabaseHelper.OwnerProductDataStatues() {
            @Override
            public void OwnerProductDataIsLoaded(List<OwnerProductModel> ownerProduct, List<String> key) {
                new OwnerProductConfig().setConfig(ownerProductLayout,inflater.getContext(),ownerProduct,key);
            }

            @Override
            public void OwnerProductDataIsInserted() {

            }

            @Override
            public void OwnerProductDataIsUpdated() {

            }

            @Override
            public void OwnerProductDataIsDeleted() {

            }
        });

        return view;
    }
}
