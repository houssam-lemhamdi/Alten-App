package com.product.trial.master.dto;

import com.product.trial.master.constants.InventoryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object for Product")
public class ProductDto {
    @Schema(description = "Code of the product", example = "P001")
    private String code;
    @Schema(description = "Name of the product", example = "Product 1")
    private String name;
    @Schema(description = "Detailed description of the product", example = "Description of product 1")
    private String description;
    @Schema(description = "Image URL of the product", example = "http://example.com/image1.jpg")
    private String image;
    @Schema(description = "Category of the product", example = "Category 1")
    private String category;
    @Schema(description = "Price of the product", example = "19.99")
    private double price;
    @Schema(description = "Available quantity of the product", example = "50")
    private int quantity;
    @Schema(description = "Internal reference of the product", example = "REF001")
    private String internalReference;
    @Schema(description = "Shell ID associated with the product", example = "101")
    private Long shellId;
    @Schema(description = "Inventory status of the product", example = "INSTOCK")
    private InventoryStatus inventoryStatus;
    @Schema(description = "Rating of the product from 1 to 5", example = "4")
    private int rating;
}
