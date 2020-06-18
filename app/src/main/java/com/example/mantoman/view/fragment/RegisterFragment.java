package com.example.mantoman.view.fragment;


import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mantoman.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RegisterFragment extends Fragment {
    @BindView(R.id.name_ET_register)
    EditText nameETRegister;
    @BindView(R.id.phone_ET_register)
    EditText phoneETRegister;
    @BindView(R.id.email_ET_register)
    EditText emailETRegister;
    @BindView(R.id.age_ET_register)
    EditText ageETRegister;
    @BindView(R.id.date_ET_register)
    EditText dateETRegister;
    @BindView(R.id.governorate_SPN_register)
    Spinner governorateSPNRegister;
    @BindView(R.id.city_SPN_register)
    Spinner citySPNRegister;
    @BindView(R.id.passward_ET_register)
    EditText passwardETRegister;
    @BindView(R.id.checkPassward_ET_register)
    EditText checkPasswardETRegister;
    @BindView(R.id.save_BTN_register)
    Button saveBTNRegister;
    private FirebaseAuth mAuth;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        mAuth = FirebaseAuth.getInstance();

        ArrayAdapter<CharSequence> governorate_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.governorate_spinner , android.R.layout.simple_list_item_1);
        governorate_adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        governorateSPNRegister.setAdapter(governorate_adapter);

        ArrayAdapter<CharSequence> city_adapter = ArrayAdapter.createFromResource(getActivity() , R.array.city_spinner , android.R.layout.simple_list_item_1);
        city_adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        citySPNRegister.setAdapter(city_adapter);

        saveBTNRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameETRegister.getText().toString().trim();
                String phone = phoneETRegister.getText().toString().trim();
                String email = emailETRegister.getText().toString().trim();
                String age = ageETRegister.getText().toString().trim();
                String date = dateETRegister.getText().toString().trim();
                String passward = passwardETRegister.getText().toString().trim();
                String checkPassward = checkPasswardETRegister.getText().toString().trim();
                if (name.isEmpty()){
                    nameETRegister.setError("isEmpty");
                    return;
                }
                if (phone.isEmpty()){
                    phoneETRegister.setError("isEmpty");
                    return;
                }
                if (email.isEmpty()){
                    emailETRegister.setError("isEmpty");
                    return;
                }
                if (age.isEmpty() && passward.length()<6){
                    ageETRegister.setError("isEmpty or size");
                    return;
                }
                if (date.isEmpty()){
                    dateETRegister.setError("isEmpty");
                    return;
                }
                if (passward.isEmpty()){
                    passwardETRegister.setError("isEmpty");
                    return;
                }
                if (checkPassward.isEmpty()){
                    checkPasswardETRegister.setError("isEmpty");
                    return;
                }
                if (passward==checkPassward){
                    checkPasswardETRegister.setError("Error");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email , passward).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getActivity(), "User created", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext() , HomeFragment.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();


                    }else {
                        Toast.makeText(getActivity(), "User not created", Toast.LENGTH_SHORT).show();
                    }
                    }
                });

            }
        });
        return view;
    }
}