package com.gmash.tapgm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.gmash.tapgm.fragments.HomeFragment;
import com.gmash.tapgm.fragments.KioskFragment;
import com.gmash.tapgm.fragments.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserDashboard extends AppCompatActivity implements SettingFragment.SettingFragmentListener {

    // variable
    FirebaseAuth auth = FirebaseAuth.getInstance();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        // find id
        bottomNavigationView = findViewById(R.id.bottom_nav);

        // bottom nav on action
//        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        bottomNavigationView.setOnItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // if user isn't present (null) direct to login
        FirebaseUser user = auth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(UserDashboard.this,Login.class));
            finish();
        }
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = item -> {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.home_nav:
                selectedFragment = new HomeFragment(); break;
            case R.id.kios_nav:
                selectedFragment = new KioskFragment(); break;
            case R.id.setting_nav:
                selectedFragment = new SettingFragment(); break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
        return true;
    };

    @Override
    public void onButtonClick(String status) {
        if(status.equals("signed_out")){
            startActivity(new Intent(UserDashboard.this,Login.class));
            finish();
        }
    }
}