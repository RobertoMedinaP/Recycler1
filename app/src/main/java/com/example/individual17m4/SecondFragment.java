package com.example.individual17m4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.individual17m4.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    //implementando factory, argumento enviado desde el FirstFragment
    private static final String ARG_PARAM1 = "clave1";

    //el string recibido
    private String mParam1;

    private FragmentSecondBinding binding;

    //constructor vacio
    public SecondFragment(){}

    //factory para recibir
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        // el edittext recibe la palabra recibida
        binding.textviewSecond.setText(mParam1);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //se envia el elemento modificado en el edittext al FirstFragment segun interfaz
                passElement(binding.textviewSecond.getText().toString());
                //textviewsecond es el edittext, deberia cambiarle el nombre

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void passElement(String element) {

        //en esta interfaz se envia el elemento modificado al primer fragmento

        Bundle bundle=new Bundle();
        bundle.putString("clave2",element);
        NavController navController= Navigation.findNavController(getActivity(),R.id.clayout);
        navController.navigate(R.id.action_SecondFragment_to_FirstFragment,
                bundle);

    }



}