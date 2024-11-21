package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    public Long id;
    public String imdb;
    public String title;
    public String description;
    public float rating;
    public String category;
    public int year;
    public String imageUrl;

}
