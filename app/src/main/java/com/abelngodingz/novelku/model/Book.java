package com.abelngodingz.novelku.model;

import java.util.List;

public class Book {
    private String title;
    private List<String> author_name;
    private int cover_i;
    private String key;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        if (author_name != null && !author_name.isEmpty()) {
            return author_name.get(0);
        } else {
            return "Unknown";
        }
    }

    public String getCoverUrl() {
        return (cover_i != 0)
                ? "https://covers.openlibrary.org/b/id/" + cover_i + "-L.jpg"
                : null;
    }

    public String getKey() {
        return key;
    }

    public int getCoverId() {
        return cover_i;
    }

}

