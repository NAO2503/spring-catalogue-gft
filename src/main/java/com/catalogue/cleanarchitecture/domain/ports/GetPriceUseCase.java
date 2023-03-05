package com.catalogue.cleanarchitecture.domain.ports;

import com.catalogue.cleanarchitecture.domain.model.Price;

import java.util.List;

public interface GetPriceUseCase {

   Price getPrice(Long priceList);

   List<Price> listAllByBrandProductBetweenDate(Long brandId, Long productId, String dateBetween);

   Price findByBrandProductBetweenDate(Long brandId, Long productId, String dateBetween);

}
