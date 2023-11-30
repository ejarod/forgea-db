package com.example.prodoreviewer;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.prodoreviewer.databinding.ActivityTimeoRegisterLoginBinding;
import com.example.prodoreviewer.databinding.FragmentLoginTabBinding;
import com.example.prodoreviewer.databinding.FragmentSignupTabBinding;

public class SignupTabFragment extends Fragment {

    FragmentSignupTabBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignupTabBinding.inflate(getLayoutInflater());
        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirmpassword = binding.signupConfirm.getText().toString();

                if(email.equals("")||password.equals("")||confirmpassword.equals("")){
                    Toast.makeText(getContext(), "Fill up all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if(password.equals(confirmpassword)){
                        Boolean checkUserEmail = databaseHelper.checkEmail(email);
                        if(checkUserEmail == false){
                            Boolean insert = databaseHelper.insertData(email,password);
                            if(insert == true) {
                                Toast.makeText(getContext(),"Signup successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getContext(),LoginTabFragment.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(getContext(),"Signup failed",Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(),"User already exists, Login instead",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(),"Password mismatch",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return inflater.inflate(R.layout.fragment_signup_tab, container, false);
    }


}