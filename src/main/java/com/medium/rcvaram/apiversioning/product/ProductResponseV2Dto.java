package com.medium.rcvaram.apiversioning.product;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Product response for API version 2, includes price")
public record ProductResponseV2Dto(@Schema(description = "Unique product identifier", example = "1") String id,
                                   @Schema(description = "Name of the product", example = "apple") String name,
                                   @Schema(description = "Price of the product", example = "23.43") BigDecimal price) implements IProductResponseDto {
}

