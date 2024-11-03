package com.product.trial.master.controllers;

import com.product.trial.master.constants.ProductConstants;
import com.product.trial.master.dto.ProductDto;
import com.product.trial.master.dto.ResponseDto;
import com.product.trial.master.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "Products Crud Rest Apis",
        description = "Crud Rest Apis for Alten Store to create,delete,update Products"
)
@RestController
@RequestMapping(value = "/products",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping()
    @Operation(summary = "Get all products", description = "Retrieve a list of all products.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of products."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtos = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK)
                .body(productDtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieve a product by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the product."),
            @ApiResponse(responseCode = "404", description = "Product not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    public ResponseEntity<ProductDto> getProductById(@Parameter(description = "ID of the product to be retrieved") @PathVariable Object id) throws Throwable {
        ProductDto productDto = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(productDto);
    }

    @PostMapping()
    @Operation(summary = "Create a new product", description = "Create a new product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the product."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    public ResponseEntity createProduct(@Parameter(description = "Product details to create") @RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ProductConstants.STATUS_201, ProductConstants.MESSAGE_201));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update an existing product", description = "Update an existing product by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the product."),
            @ApiResponse(responseCode = "404", description = "Product not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    public ResponseEntity<ProductDto> updateProduct(@Parameter(description = "ID of the product to be updated") @PathVariable Object id,
                                                    @Parameter(description = "Updated product details") @RequestBody ProductDto productDto) throws Throwable {
        ProductDto updatedProductDto = productService.updateProduct(id, productDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProductDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Delete a product by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the product."),
            @ApiResponse(responseCode = "404", description = "Product not found.")
    })
    public ResponseEntity<Void> deleteProduct(@Parameter(description = "ID of the product to be deleted") @PathVariable Object id) throws Throwable {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
