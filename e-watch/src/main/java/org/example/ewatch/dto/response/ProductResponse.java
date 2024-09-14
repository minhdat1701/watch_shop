package org.example.ewatch.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private int quantity;
    private String image;
    private String description;
    private CategoryResponse categoryResponse;
}
