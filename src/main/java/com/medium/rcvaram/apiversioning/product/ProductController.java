package com.medium.rcvaram.apiversioning.product;

import com.medium.rcvaram.apiversioning.util.Version;
import com.medium.rcvaram.apiversioning.util.VersionProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final VersionProvider versionProvider;
    private final IProductService productService;

    @Operation(
            summary = "Retrieve a list of products",
            description = "Returns products based on the Accept header. Version 1 (vnd.rcvaram.v1+json) includes id and name; Version 2 (vnd.rcvaram.v2+json) adds price. Defaults to v1 if header is omitted."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful response with a list of products",
                    content = {
                            @Content(
                                    mediaType = "application/vnd.rcvaram.v1+json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProductResponseV1Dto.class)),
                                    examples = @ExampleObject(
                                            name = "v1-example",
                                            summary = "Version 1 Response",
                                            value = "[{\"id\": \"1\", \"name\": \"apple\"}, {\"id\": \"2\", \"name\": \"orange\"}, {\"id\": \"3\", \"name\": \"mango\"}]"
                                    )
                            ),
                            @Content(
                                    mediaType = "application/vnd.rcvaram.v2+json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProductResponseV2Dto.class)),
                                    examples = @ExampleObject(
                                            name = "v2-example",
                                            summary = "Version 2 Response",
                                            value = "[{\"id\": \"1\", \"name\": \"apple\", \"price\": 23.43}, {\"id\": \"2\", \"name\": \"orange\", \"price\": 10.00}, {\"id\": \"3\", \"name\": \"mango\", \"price\": 26.00}]"
                                    )
                            )
                    }
            )
    })
    @GetMapping(value = "/products", produces = {
            "application/vnd.rcvaram.v1+json",
            "application/vnd.rcvaram.v2+json"
    })
    public ResponseEntity<List<IProductResponseDto>> getProducts(@Parameter(
            name = "Accept",
            description = "Specifies the response format and version. Defaults to v1 (application/vnd.rcvaram.v1+json) if omitted.",
            required = false,
            schema = @Schema(type = "string",
                    defaultValue = "application/vnd.rcvaram.v1+json")
    ) @RequestHeader(value = "Accept", defaultValue = "application/vnd.rcvaram.v1+json") String acceptHeader) {
        Version version = versionProvider.identifyVersion(acceptHeader);
        List<IProductResponseDto> products = productService.getProducts(version);
        return ResponseEntity.ok(products);
    }
}
