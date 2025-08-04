package com.abelngodingz.novelku.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.abelngodingz.novelku.R;
import com.abelngodingz.novelku.model.Book;
import com.abelngodingz.novelku.ui.DetailActivity;
import com.bumptech.glide.Glide;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Book> books;
    private Context context;

    public BookAdapter(Context ctx, List<Book> books) {
        this.context = ctx;
        this.books = books;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        Glide.with(context).load(book.getCoverUrl()).into(holder.cover);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("title", book.getTitle());
            intent.putExtra("author", book.getAuthor());
            intent.putExtra("cover", book.getCoverUrl());
            intent.putExtra("key", book.getKey());
            context.startActivity(intent);
        });
    }

    public int getItemCount() {
        return books.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, author;
        ImageView cover;

        ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.bookTitle);
            author = view.findViewById(R.id.bookAuthor);
            cover = view.findViewById(R.id.bookCover);
        }
    }
}
