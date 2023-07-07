package com.example.individual17m4;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.individual17m4.databinding.DataListItemBinding;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    //lista de palabras
    private List<String>mWordList;
    //objeto para guardar la posicion, es final porque no debe ser modificado
    private final IRecyclerItemClick irecyclerItemClick;

    //el constructor lleva los elementos de las dos interfaces
    public WordListAdapter(Context context,List<String>mWordList,PassElementSelected listener, IRecyclerItemClick irecyclerItemClick){
        this.mWordList=mWordList;
        this.listener=listener;
        this.irecyclerItemClick = irecyclerItemClick;


    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DataListItemBinding binding= DataListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        //devuelve posicion y lista de palabras
        return new WordViewHolder(binding, irecyclerItemClick, mWordList);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        //cada palabra de la lista tiene una posicion y un string(word)
        String word= mWordList.get(position);
        holder.worditemTv.setText(word);
    }

    @Override
    public int getItemCount() {
        //el conteo de item retorna el tamaño de la lista de palabras
        return mWordList.size();
    }


    public static class WordViewHolder extends RecyclerView.ViewHolder{

        //el textview donde iran las palabras
        public TextView worditemTv;

        public WordViewHolder(DataListItemBinding binding, IRecyclerItemClick irecyclerItemClick, List<String>mWordList) {
            super(binding.getRoot());
            worditemTv = binding.textview;
            //se escucha si se hace un click en algun lugar del listado dentro del textview, por eso llamamos a
            //la vista de items o itemview
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //si se hace un click se guarda la posicion
                    if (irecyclerItemClick!=null){
                        int pos = getAdapterPosition();
                        //si hay una posicion, se guarda la posicion
                        if (pos!= RecyclerView.NO_POSITION){
                            irecyclerItemClick.OnItemClick(pos);
                            //aca se guarda la posicion
                            String element=mWordList.get(pos);
                            //se pone el texto seleccionado a la palabra seleccionada
                            mWordList.set(pos,"Seleccionado "+ element);
                            //esto lo escucha la interfaz
                            listener.passElement(element);
                        }
                    }
                }
            });
        }
/*
        @Override
        public void onClick(View view) {
            int position= getLayoutPosition();
            String element=mWordList.get(position);
            mWordList.set(position,"Seleccionado "+ element);
            notifyDataSetChanged();
            listener.passElement(element);
        }
        */
    }

    public interface PassElementSelected{
        //esta interfaz guardara el string seleccionado
        void passElement(String element);

    }
    //el escuchador para la interfaz, estatico para que no modifique el dato
    private static PassElementSelected listener;

}