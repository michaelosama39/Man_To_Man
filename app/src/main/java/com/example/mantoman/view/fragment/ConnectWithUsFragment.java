package com.example.mantoman.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.mantoman.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ConnectWithUsFragment extends Fragment {

    @BindView(R.id.phone_TV_connect)
    TextView phoneTVConnect;
    @BindView(R.id.phoneNumber_TV_connect)
    TextView phoneNumberTVConnect;
    @BindView(R.id.email_TV_connect)
    TextView emailTVConnect;
    @BindView(R.id.emailText_TV_connect)
    TextView emailTextTVConnect;
    @BindView(R.id.imageView8)
    ImageView imageView8;
    @BindView(R.id.imageView9)
    ImageView imageView9;
    @BindView(R.id.imageView10)
    ImageView imageView10;
    @BindView(R.id.imageView11)
    ImageView imageView11;
    @BindView(R.id.titleMasg_ET_connect)
    EditText titleMasgETConnect;
    @BindView(R.id.masg_ET_connect)
    EditText masgETConnect;
    @BindView(R.id.send_BTN_connect)
    Button sendBTNConnect;
    private Unbinder unbinder;
    public ConnectWithUsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_connect_with_us, container, false);
        try {
            unbinder = ButterKnife.bind(this , view);
        }catch (Exception e){
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}