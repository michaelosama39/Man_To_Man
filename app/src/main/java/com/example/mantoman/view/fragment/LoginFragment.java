package com.example.mantoman.view.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mantoman.R;
import com.example.mantoman.view.activity.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginFragment extends Fragment {

    @BindView(R.id.email_ET_login)
    EditText emailETLogin;
    @BindView(R.id.password_ET_login)
    EditText passwordETLogin;

    public LoginFragment() {
        // Required empty public constructor
    }

    Button enterBTN, registerBTN;
    TextView reset;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        enterBTN = view.findViewById(R.id.enter_BTN_login);
        registerBTN = view.findViewById(R.id.createNewAccount_BTN_login);
        reset = view.findViewById(R.id.forgetPassward_TV_login);

        ButterKnife.bind(this, view);
        mAuth = FirebaseAuth.getInstance();

        enterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailETLogin.getText().toString().trim();
                String passward = passwordETLogin.getText().toString().trim();

                if (email.isEmpty()) {
                    emailETLogin.setError("isEmpty");
                    return;
                }
                if (passward.isEmpty()) {
                    passwordETLogin.setError("isEmpty");
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, passward).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "User created", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity() , HomeActivity.class);
                            getActivity().startActivity(intent);
                            getActivity().finish();

                        } else {
                            Toast.makeText(getActivity(), "User not created", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText restEmail = new EditText(v.getContext());
                final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Rest Passward");
                builder.setMessage("Enter your Email To Received Rest Link");
                builder.setView(restEmail);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail = restEmail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getContext(), "Reset link sent to your email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Error!!! Reset link not sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                builder.create().show();

            }
        });

        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment fragment = new RegisterFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.Auth_activity, fragment);
                transaction.commit();
            }
        });

        return view;
    }
}