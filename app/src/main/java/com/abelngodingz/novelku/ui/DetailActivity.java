package com.abelngodingz.novelku.ui;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.abelngodingz.novelku.R;
import com.abelngodingz.novelku.model.BookDetail;
import com.abelngodingz.novelku.network.ApiClient;
import com.bumptech.glide.Glide;
import retrofit2.*;

public class DetailActivity extends AppCompatActivity {

    private ImageView coverView;
    private TextView titleView, authorView, synopsisView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        coverView = findViewById(R.id.detailCover);
        titleView = findViewById(R.id.detailTitle);
        authorView = findViewById(R.id.detailAuthor);
        synopsisView = findViewById(R.id.detailSynopsis);

        String title = getIntent().getStringExtra("title");
        String author = getIntent().getStringExtra("author");
        String coverUrl = getIntent().getStringExtra("cover");
        String key = getIntent().getStringExtra("key");

        titleView.setText(title);
        authorView.setText(author);

        if (coverUrl != null) {
            Glide.with(this)
                    .load(coverUrl)
                    .placeholder(R.drawable.ic_book)
                    .into(coverView);
        }

        fetchBookDetail(key);
    }

    private void fetchBookDetail(String key) {
        String cleanKey = key.startsWith("/") ? key.substring(1) : key;

        ApiClient.getApi().getBookDetail(cleanKey).enqueue(new Callback<BookDetail>() {
            @Override
            public void onResponse(Call<BookDetail> call, Response<BookDetail> response) {
                if (response.isSuccessful() && response.body() != null) {
                    synopsisView.setText(response.body().getDescriptionText());
                } else {
                    synopsisView.setText("Sinopsis tidak tersedia.");
                }
            }

            @Override
            public void onFailure(Call<BookDetail> call, Throwable t) {
                synopsisView.setText("Gagal memuat sinopsis.");
            }
        });
    }
}
