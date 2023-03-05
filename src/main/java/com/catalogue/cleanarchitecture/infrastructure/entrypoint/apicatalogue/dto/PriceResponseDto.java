package com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceResponseDto {

    private Long priceList;

    private Long brandId;

    private Double price;

    private String startDate;

    private String endDate;
}
