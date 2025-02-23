package com.medium.rcvaram.apiversioning.product;

import com.medium.rcvaram.apiversioning.util.Version;

import java.util.List;

public interface IProductService {
    List<IProductResponseDto> getProducts(Version version);

}
