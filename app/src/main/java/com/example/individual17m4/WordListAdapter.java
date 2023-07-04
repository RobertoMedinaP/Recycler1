package com.example.individual17m4;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.individual17m4.databinding.DataListItemBinding;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private List<String>mWordList;

    public WordListAdapter(List<String>mWordList){
        this.mWordList=mWordList;

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


    public class WordViewHolder extends RecyclerView.ViewHolder {
        public TextView worditemTv;

        public WordViewHolder(@NonNull DataListItemBinding binding) {
            super(binding.getRoot());
            worditemTv = binding.textview;
        }
    }
}
