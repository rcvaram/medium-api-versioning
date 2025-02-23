package com.medium.rcvaram.apiversioning.product;

import com.medium.rcvaram.apiversioning.util.Version;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService implements IProductService {


    @Override
    public List<IProductResponseDto> getProducts(Version version) {
        return switch (version) {
            case V1 -> getV1Products();
            case V2 -> getV2Products();
        };
    }


    List<IProductResponseDto> getV1Products() {
        //Actual Business logic &  db calls
        return List.of(new ProductResponseV1Dto("1", "apple"),
                new ProductResponseV1Dto("2", "orange"),
                new ProductResponseV1Dto("3", "mango"));
    }

    List<IProductResponseDto> getV2Products() {
        //Actual Business logic &  db calls
        return List.of(new ProductResponseV2Dto("1", "apple", BigDecimal.valueOf(23.43)),
                new ProductResponseV2Dto("2", "orange", BigDecimal.valueOf(10.00)),
                new ProductResponseV2Dto("3", "mango", BigDecimal.valueOf(26.00)));
    }

}
