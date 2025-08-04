package com.abelngodingz.novelku.model;

import com.google.gson.annotations.SerializedName;

public class BookDetail {
    @SerializedName("description")
    private Object description;

    public String getDescriptionText() {
        if (description instanceof String) {
            return (String) description;
        } else if (description instanceof java.util.Map) {
            Object value = ((java.util.Map<?, ?>) description).get("value");
            if (value instanceof String) {
                return (String) value;
            }
        }
        return "Sinopsis tidak tersedia.";
    }
}
