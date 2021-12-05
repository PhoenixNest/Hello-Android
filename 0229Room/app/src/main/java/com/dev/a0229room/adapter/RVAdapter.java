package com.dev.a0229room.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.a0229room.R;
import com.dev.a0229room.db.Word;
import com.dev.a0229room.viewmodel.WordViewModel;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {

    private List<Word> wordList = new ArrayList<>();
    private boolean isUseCard;
    private WordViewModel wordViewModel;

    public RVAdapter(boolean isUseCard, WordViewModel wordViewModel) {
        this.isUseCard = isUseCard;
        this.wordViewModel = wordViewModel;
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (isUseCard) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_card_new, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_new, parent, false);
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Word word = wordList.get(position);
        holder.tv_id.setText(String.valueOf(position + 1));
        holder.tv_en.setText(word.getWord());
        holder.tv_zh.setText(word.getChineseMeaning());
        holder.switch_zh_enable.setOnCheckedChangeListener(null);

        if (word.isChineseInvisible()) {
            holder.tv_zh.setVisibility(View.GONE);
            holder.switch_zh_enable.setChecked(true);
        } else {
            holder.tv_zh.setVisibility(View.VISIBLE);
            holder.switch_zh_enable.setChecked(false);
        }

        holder.switch_zh_enable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    holder.tv_zh.setVisibility(View.GONE);
                    word.setChineseInvisible(true);
                    wordViewModel.updateWords(word);
                } else {
                    holder.tv_zh.setVisibility(View.VISIBLE);
                    word.setChineseInvisible(false);
                    wordViewModel.updateWords(word);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://m.youdao.com/dict?le=eng&q=" + holder.tv_en.getText());
                holder.itemView.getContext().startActivity(new Intent(Intent.ACTION_VIEW).setData(uri));
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_id, tv_en, tv_zh;
        Switch switch_zh_enable;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_en = itemView.findViewById(R.id.tv_en);
            tv_zh = itemView.findViewById(R.id.tv_zh);
            switch_zh_enable = itemView.findViewById(R.id.switch_zh_enable);
        }
    }
}
