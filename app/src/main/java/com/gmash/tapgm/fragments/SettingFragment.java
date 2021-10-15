package com.gmash.tapgm.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gmash.tapgm.R;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class SettingFragment extends Fragment {

    private ImageView logout;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    SettingFragmentListener listener;

    public interface SettingFragmentListener {
        void onButtonClick(String status);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings,container,false);

        logout = view.findViewById(R.id.logout);

        logout.setOnClickListener(v -> signOut());

        return view;
    }

    public void signOut(){
        auth.signOut();
        listener.onButtonClick("signed_out");
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        if(context instanceof SettingFragmentListener){
            listener = (SettingFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()+"Task fragment not implemented");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
