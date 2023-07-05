package com.example.individual17m4;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.individual17m4.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements WordListAdapter.PassElementSelected {

    private FragmentFirstBinding binding;
    private List<String>dataList=new ArrayList<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataList.add("Palabra "+ dataList.size());
                binding.recyclerView.getAdapter().notifyItemInserted(dataList.size());
                binding.recyclerView.smoothScrollToPosition(dataList.size());
                Snackbar.make(view, "Agregada una palabra", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WordListAdapter adapter=new WordListAdapter(getContext(),setData(), this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);



        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //esto tambien es nuevo
                passElement(dataList.toString());
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });


    }

    private List<String>setData() {
        for (int i= 0;i<99;i++){
            dataList.add("Palabra "+i);
        }
        return dataList;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void passElement(String element) {
        Toast.makeText(getContext(),element,Toast.LENGTH_SHORT).show();
        //navegamos?

        //hice un bundle
        Bundle bundle=new Bundle();
        bundle.putString("clave1",element);
        NavController navController= Navigation.findNavController(getActivity(),R.id.recyclerView);
        navController.navigate(R.id.action_FirstFragment_to_SecondFragment,
                bundle);

    }
}