package com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceDto {

    private Long brandId;

    private Date startDate;

    private Date endDate;

    private Long priceList;

    private Long productId;

    private Integer priority;

    private Double price;

    private String curr;

}
