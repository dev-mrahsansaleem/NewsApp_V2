package com.example.newsapp_v2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp_v2.Model.NewsRes;
import com.example.newsapp_v2.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private NewsRes newsResponses;

    private OnItemClickListener onItemClickListener;

    public NewsAdapter(NewsRes newsResponses,OnItemClickListener onItemClickListener) {
        this.newsResponses = newsResponses;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_article_adapter, parent, false);
        return new NewsAdapter.ViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.title.setText(newsResponses.getArticalList().get(position).getTitle());
        holder.desc.setText(newsResponses.getArticalList().get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return newsResponses.getArticalList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title, desc;
        private OnItemClickListener onItemClickListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            title = itemView.findViewById(R.id.article_adapter_tv_title);
            desc = itemView.findViewById(R.id.article_adapter_tv_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
