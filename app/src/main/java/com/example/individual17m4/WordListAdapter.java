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
    private List<String>mWordList;

    public WordListAdapter(Context context,List<String>mWordList,PassElementSelected listener){
        this.mWordList=mWordList;
        this.listener=listener;

    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DataListItemBinding binding= DataListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new WordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        String word= mWordList.get(position);
        holder.worditemTv.setText(word);

    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }


    public class WordViewHolder extends RecyclerView.ViewHolder implements View

            .OnClickListener {
        public TextView worditemTv;

        public WordViewHolder(DataListItemBinding binding) {
            super(binding.getRoot());
            worditemTv = binding.textview;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position= getLayoutPosition();
            String element=mWordList.get(position);
            mWordList.set(position,"Seleccionado "+ element);
            notifyDataSetChanged();
            listener.passElement(element);
            //esto es otro intento
            //NavHostFragment.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment);

        }
    }

    public interface PassElementSelected{
        void passElement(String element);
        //estoy pasando solo la posicion??
    }
    private  PassElementSelected listener;


}
