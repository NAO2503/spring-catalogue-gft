package com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.mapper;

import com.catalogue.cleanarchitecture.domain.model.Price;
import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.dto.PriceResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  PriceResponseDto toResponseDto(Price price);

  List<PriceResponseDto> listToResponseDtoList(List<Price> prices);

}
