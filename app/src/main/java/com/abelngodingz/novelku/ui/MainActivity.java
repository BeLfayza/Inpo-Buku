package com.abelngodingz.novelku.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.abelngodingz.novelku.R;
import com.abelngodingz.novelku.adapter.BookAdapter;
import com.abelngodingz.novelku.model.Book;
import com.abelngodingz.novelku.network.ApiClient;
import com.abelngodingz.novelku.response.SearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private List<Book> books = new ArrayList<>();
    private ProgressBar progressBar;
    private SearchView searchView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerBooks);
        progressBar = findViewById(R.id.progressBar);
        searchView = findViewById(R.id.searchView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookAdapter(this, books);
        recyclerView.setAdapter(adapter);

        fetchBooks("fiction"); // default awal

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                if (!query.trim().isEmpty()) {
                    fetchBooks(query.trim());
                }
                return true;
            }

            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void fetchBooks(String query) {
        progressBar.setVisibility(View.VISIBLE);
        books.clear();
        adapter.notifyDataSetChanged();

        ApiClient.getApi().searchBooks(query).enqueue(new Callback<SearchResponse>() {
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    books.addAll(response.body().docs);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Tidak ada hasil.", Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure(Call<SearchResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Gagal koneksi.", Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", t.getMessage());
            }
        });
    }
}
