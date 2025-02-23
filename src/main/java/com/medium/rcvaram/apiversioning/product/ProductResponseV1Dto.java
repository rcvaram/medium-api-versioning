package com.medium.rcvaram.apiversioning.product;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Product response for API version 1, includes basic fields")
public record ProductResponseV1Dto(@Schema(description = "Unique product identifier", example = "1") String id,
                                   @Schema(description = "Name of the product", example = "apple") String name) implements IProductResponseDto {
}

