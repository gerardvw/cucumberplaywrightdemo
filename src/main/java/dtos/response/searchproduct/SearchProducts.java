package dtos.response.searchproduct;

import java.util.List;

public record SearchProducts(int responseCode, List<Product> products){}

