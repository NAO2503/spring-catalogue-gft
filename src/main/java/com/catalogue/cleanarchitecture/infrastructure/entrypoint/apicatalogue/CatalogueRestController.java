package com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue;

import com.catalogue.cleanarchitecture.domain.ports.GetPriceUseCase;
import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.dto.PriceRequestDto;
import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.dto.PriceResponseDto;
import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.mapper.PriceMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/catalogue")
public class CatalogueRestController {

    private final GetPriceUseCase getPriceUseCase;

    private final PriceMapper priceMapper;

    @GetMapping("/price/{priceList}")
    public ResponseEntity<PriceResponseDto> getPriceByPriceList(@PathVariable Long priceList) {

        return new ResponseEntity<>(priceMapper.toResponseDto(getPriceUseCase.getPrice(priceList)), HttpStatus.OK);

    }

    @PostMapping("/price/findByBrandProductBetweenDate")
    public ResponseEntity<PriceResponseDto> findByBrandProductBetweenDate(@Valid @RequestBody PriceRequestDto priceRequestDto) {

        return new ResponseEntity<>(priceMapper.toResponseDto(getPriceUseCase.findByBrandProductBetweenDate(priceRequestDto.getBrandId(),
                priceRequestDto.getProductId(), priceRequestDto.getDateQuery())), HttpStatus.OK);

    }

    @PostMapping("/price/listAllByBrandProductBetweenDate")
    public ResponseEntity<List<PriceResponseDto>> listAllByBrandProductBetweenDate(@Valid @RequestBody PriceRequestDto priceRequestDto) {

        return new ResponseEntity<>(priceMapper.listToResponseDtoList(getPriceUseCase.listAllByBrandProductBetweenDate(priceRequestDto.getBrandId(),
                priceRequestDto.getProductId(), priceRequestDto.getDateQuery())), HttpStatus.OK);

    }
}
