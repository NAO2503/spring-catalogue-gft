package com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PriceRequestDto {

    @NotEmpty(message = "Debe diligenciar una fecha")
    private String dateQuery;

    @NotNull(message = "Debe diligenciar un id de producto")
    private Long productId;

    @NotNull(message = "Debe diligenciar un identificador de cadena")
    private Long brandId;

}
