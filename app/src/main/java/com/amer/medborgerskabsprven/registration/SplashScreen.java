package com.amer.medborgerskabsprven.registration;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amer.medborgerskabsprven.R;
import com.amer.medborgerskabsprven.databinding.RegistrationSplashScreenBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends Fragment {

    private @NonNull
    RegistrationSplashScreenBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private NavController controller;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = RegistrationSplashScreenBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        controller = Navigation.findNavController(view);
        binding.status.setText("Checking account...");
    }

    @Override
    public void onStart() {
        super.onStart();
        checkUser();


    }

    public void checkUser() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (firebaseUser == null) {
                    binding.status.setText("No account founded...");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            controller.navigate(R.id.action_splashScreen_to_registration);
                        }
                    }, 1500);
                }
                else {
                    binding.status.setText("Logging in...");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            controller.navigate(R.id.action_splashScreen_to_questionsList);
                        }
                    }, 1500);
                }
            }
        }, 2000);
    }


}