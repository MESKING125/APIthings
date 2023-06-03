package com.example.apithings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{
    private Context context;
    private List<NewsModel> NewsList;
    private NewsAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView viewSource, viewAuthor, viewTitle, viewDesc, viewPublish;
        public ImageView viewImage;

        public MyViewHolder(View view) {
            super(view);
            viewSource = view.findViewById(R.id.monSource);
            viewAuthor = view.findViewById(R.id.monAuthor);
            viewTitle = view.findViewById(R.id.monTitle);
            viewDesc = view.findViewById(R.id.monDesc);
            viewPublish = view.findViewById(R.id.monPublish);
            viewImage = view.findViewById(R.id.monImage);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(NewsList.get(getAdapterPosition()));
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            listener.onItemLongClick(pos);
                        }
                    }
                    return true;
                }
            });
        }
    }

    public NewsAdapter(Context context, List<NewsModel> NewsList, NewsAdapterListener listener) {
        this.context = context;
        this.NewsList = NewsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.MyViewHolder holder, int position) {
        final NewsModel contact = this.NewsList.get(position);
        holder.viewSource.setText(contact.getSource());
        holder.viewAuthor.setText(contact.getAuthor());
        holder.viewTitle.setText(contact.getTitle());
        holder.viewDesc.setText(contact.getDesc());
        holder.viewPublish.setText(contact.getPublish());
        Glide.with(holder.itemView.getContext()).load(contact.getImage()).into(holder.viewImage);
    }

    @Override
    public int getItemCount() {
        return this.NewsList.size();
    }

    public interface NewsAdapterListener {
        void onContactSelected(NewsModel contact);
        void onItemLongClick(int pos);
    }
}
