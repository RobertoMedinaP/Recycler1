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

//se implementan las dos interfaces, una se hizo dentro del adapter y otra afuera
public class FirstFragment extends Fragment implements WordListAdapter.PassElementSelected, IRecyclerItemClick {

    private FragmentFirstBinding binding;

    //El arraylist donde se guardan las palabras, static para que permanezca
    private static List<String>dataList=new ArrayList<>();

    private static final String ARG_PARAM1 = "clave2";

    //mparam inicializada vacia, ese valor cambia si se modificó en el edittext del segundo fragmento
    private String mParam= "";

    //adaptador estatico para que no cambie
    static WordListAdapter adapter;

    //variable estatica para guardar la posicion del item en la lista
    static int itemPosition;
    //otro estatico para ver la creacion de la lista
    static boolean yacreada = false;


    public FirstFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //si hay argumentos recibo a mparam(palabra modificada)
            mParam = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        //inflado de vista
        binding = FragmentFirstBinding.inflate(inflater, container, false);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //en este boton se agrega una palabra en cada click
                dataList.add("Palabra "+ dataList.size());
                binding.recyclerView.getAdapter().notifyItemInserted(dataList.size());
                //aca se manda al final de la lista
                binding.recyclerView.smoothScrollToPosition(dataList.size());
                //este es un aviso
                Snackbar.make(view, "Agregada una palabra", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
        //retorna la vista
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //constructor con los listener de las interfaces implementadas en el adapter
        adapter=new WordListAdapter(getContext(),setData(), this, this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);




        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamo al metodo para enviar el string seleccionado
                passElement(dataList.toString());

            }
        });


    }

    private List<String>setData() {

        //sino se ha creado la lista se crea

        if (!yacreada) {

            for (int i = 0; i < 10; i++) {
                dataList.add("Palabra " + i);

            }
            yacreada=true;
        }
        //si la palabra recibida no esta vacia se reemplazara en su posicion

        if (!mParam.equals("")) {
            dataList.set(itemPosition, mParam);
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
        //el toast me sirve para saber la palabra seleccionada
        Toast.makeText(getContext(),element,Toast.LENGTH_SHORT).show();

        //hice un bundle para enviar la palabra seleccionada
        Bundle bundle=new Bundle();
        bundle.putString("clave1",element);
        NavController navController= Navigation.findNavController(getActivity(),R.id.recyclerView);
        navController.navigate(R.id.action_FirstFragment_to_SecondFragment,
                bundle);

    }

    @Override
    public void OnItemClick(int position) {
        //esta interfaz guarda la posicion de la palabra modificada
        itemPosition = position;

    }
}

