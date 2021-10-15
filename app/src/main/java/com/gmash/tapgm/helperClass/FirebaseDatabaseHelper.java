package com.gmash.tapgm.helperClass;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.gmash.tapgm.models.OwnerProductModel;
import com.gmash.tapgm.models.ProductModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private final DatabaseReference productReference;
    private final DatabaseReference ownerProductReference;
    private final List<ProductModel> productDesc = new ArrayList<>();
    private final List<OwnerProductModel> ownerProductModels = new ArrayList<>();

    public interface ProductDataStatues{
        void ProductDataIsLoaded(List<ProductModel> product,List<String> key);
        void ProductDataIsInserted();
        void ProductDataIsUpdated();
        void ProductDataIsDeleted();
    }

    public interface OwnerProductDataStatues{
        void OwnerProductDataIsLoaded(List<OwnerProductModel> ownerProduct,List<String> key);
        void OwnerProductDataIsInserted();
        void OwnerProductDataIsUpdated();
        void OwnerProductDataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        assert user != null;
        String UserID = user.getUid();
        FirebaseDatabase root = FirebaseDatabase.getInstance();
        ownerProductReference = root.getReference("users").child(UserID).child("kiosk");
        productReference = root.getReference("product");
    }

    public void ReadProductData(final ProductDataStatues productDataStatues){
        productReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                productDesc.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    ProductModel productModel = keyNode.getValue(ProductModel.class);
                    productDesc.add(productModel);
                }

                productDataStatues.ProductDataIsLoaded(productDesc,keys);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public void ReadOwnerProductData(final OwnerProductDataStatues ownerProductDataStatues) {
        ownerProductReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                ownerProductModels.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    OwnerProductModel ownerProductModel = keyNode.getValue(OwnerProductModel.class);
                    ownerProductModels.add(ownerProductModel);
                }

                ownerProductDataStatues.OwnerProductDataIsLoaded(ownerProductModels,keys);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}
