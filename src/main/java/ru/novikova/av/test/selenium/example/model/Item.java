package ru.novikova.av.test.selenium.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Item {
    private String name;
    private String price;
    private String image;
}
