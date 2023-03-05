package com.catalogue.cleanarchitecture.domain.usecase;

import static org.mockito.Mockito.when;

import com.catalogue.cleanarchitecture.application.service.GetPriceService;
import com.catalogue.cleanarchitecture.domain.model.Price;
import com.catalogue.cleanarchitecture.domain.ports.GetPriceUseCase;
import com.catalogue.cleanarchitecture.domain.ports.PriceRepository;
import com.catalogue.cleanarchitecture.application.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class GetPriceUseCaseTest {

    private GetPriceUseCase getPriceUseCase;

    @Mock
    private PriceRepository priceRepository;

    @BeforeEach
    void setUp() {
        getPriceUseCase = new GetPriceService(priceRepository);
    }

    @Test
    void getPriceByPriceListTest() {
        // given
        Price price = new Price();
        // when
        when(priceRepository.findByPriceList(1L))
                .thenReturn(price);
        //then
        Price result = getPriceUseCase.getPrice(1L);
        Assertions.assertNotNull(result);
    }

    @Test
    void findAllByBrandIdAndProductIdBetweenDatesTest() {
        // given
        String dateTest = "2020-06-14 10:00:00";
        // when
        when(priceRepository.findAllByBrandIdAndProductIdBetweenDates(1L, 35455L, DateUtil.getDateFromString(dateTest)))
                .thenReturn(mockListTest1());
        //then
        List<Price> results = getPriceUseCase.listAllByBrandProductBetweenDate(1L, 35455L, null);
        List<Price> results1 = getPriceUseCase.listAllByBrandProductBetweenDate(1L, 35455L, "Mal formato");
        List<Price> results2 = getPriceUseCase.listAllByBrandProductBetweenDate(1L, 35455L, dateTest);
        Price price = results2.get(0);
        Assertions.assertNotNull(results);
        Assertions.assertEquals(0, results1.size());
        Assertions.assertNotNull(results2);
        Assertions.assertEquals(1L, price.getBrandId());
        Assertions.assertEquals(35455L, price.getProductId());
        Assertions.assertEquals(35.5, price.getPrice());
    }

    @Test
    void findByBrandProductBetweenDateTest1() {
        // given
        String dateTest = "2020-06-14 10:00:00";

        // when
        when(priceRepository.findAllByBrandIdAndProductIdBetweenDates(1L, 35455L, DateUtil.getDateFromString(dateTest)))
                .thenReturn(mockListTest1());

        //then
        Price result = getPriceUseCase.findByBrandProductBetweenDate(1L, 35455L, dateTest);
        Price result2 = getPriceUseCase.findByBrandProductBetweenDate(1L, 35455L, "Mal formato");
        Assertions.assertEquals(1L, result.getBrandId());
        Assertions.assertEquals(35455L, result.getProductId());
        Assertions.assertEquals(35.5, result.getPrice());
        Assertions.assertNull(result2);
    }

    @Test
    void findByBrandProductBetweenDateTest2() {
        // given
        String dateTest = "2020-06-14 16:00:00";

        // when
        when(priceRepository.findAllByBrandIdAndProductIdBetweenDates(1L, 35455L, DateUtil.getDateFromString(dateTest)))
                .thenReturn(mockListTest2());

        //then
        Price result = getPriceUseCase.findByBrandProductBetweenDate(1L, 35455L, dateTest);
        Assertions.assertEquals(1L, result.getBrandId());
        Assertions.assertEquals(35455L, result.getProductId());
        Assertions.assertEquals(25.45, result.getPrice());
    }

    @Test
    void findByBrandProductBetweenDateTest3() {
        // given
        String dateTest = "2020-06-14 21:00:00";

        // when
        when(priceRepository.findAllByBrandIdAndProductIdBetweenDates(1L, 35455L, DateUtil.getDateFromString(dateTest)))
                .thenReturn(mockListTest3());

        //then
        Price result = getPriceUseCase.findByBrandProductBetweenDate(1L, 35455L, dateTest);
        Assertions.assertEquals(1L, result.getBrandId());
        Assertions.assertEquals(35455L, result.getProductId());
        Assertions.assertEquals(35.5, result.getPrice());
    }

    @Test
    void findByBrandProductBetweenDateTest4() {
        // given
        String dateTest = "2020-06-15 10:00:00";

        // when
        when(priceRepository.findAllByBrandIdAndProductIdBetweenDates(1L, 3595L, DateUtil.getDateFromString(dateTest)))
                .thenReturn(mockListTest4());

        //then
        Price result = getPriceUseCase.findByBrandProductBetweenDate(1L, 3595L, dateTest);
        Assertions.assertEquals(1L, result.getBrandId());
        Assertions.assertEquals(35455L, result.getProductId());
        Assertions.assertEquals(30.5, result.getPrice());
    }

    @Test
    void findByBrandProductBetweenDateTest5() {
        // given
        String dateTest = "2020-06-15 21:00:00";

        // when
        when(priceRepository.findAllByBrandIdAndProductIdBetweenDates(1L, 3595L, DateUtil.getDateFromString(dateTest)))
                .thenReturn(mockListTest5());

        //then
        Price result = getPriceUseCase.findByBrandProductBetweenDate(1L, 3595L, dateTest);
        Assertions.assertEquals(1L, result.getBrandId());
        Assertions.assertEquals(35455L, result.getProductId());
        Assertions.assertEquals(38.95, result.getPrice());
    }

    private List<Price> mockListTest1() {
        List<Price> prices = new ArrayList<>();
        Price p1 = new Price(1L, DateUtil.getDateFromString("2020-06-14 00:00:00"), DateUtil.getDateFromString("2020-12-31 23:59:59"), 1L, 35455L, 0, 35.5, "EUR");
        prices.add(p1);
        return prices;
    }

    private List<Price> mockListTest2() {
        List<Price> prices = new ArrayList<>();
        Price p1 = new Price(1L, DateUtil.getDateFromString("2020-06-14 15:00:00"), DateUtil.getDateFromString("2020-06-14 18:30:00"), 2L, 35455L, 1, 25.45, "EUR");
        Price p2 = new Price(1L, DateUtil.getDateFromString("2020-06-14 00:00:00"), DateUtil.getDateFromString("2020-12-31 23:59:59"), 1L, 35455L, 0, 35.5, "EUR");
        prices.add(p1);
        prices.add(p2);
        return prices;
    }

    private List<Price> mockListTest3() {
        List<Price> prices = new ArrayList<>();
        Price p1 = new Price(1L, DateUtil.getDateFromString("2020-06-14 00:00:00"), DateUtil.getDateFromString("2020-12-31 23:59:59"), 1L, 35455L, 0, 35.5, "EUR");
        prices.add(p1);
        return prices;
    }

    private List<Price> mockListTest4() {
        List<Price> prices = new ArrayList<>();
        Price p1 = new Price(1L, DateUtil.getDateFromString("2020-06-15 00:00:00"), DateUtil.getDateFromString("2020-06-15 11:00:00"), 3L, 35455L, 1, 30.5, "EUR");
        Price p2 = new Price(1L, DateUtil.getDateFromString("2020-06-14 00:00:00"), DateUtil.getDateFromString("2020-12-31 23:59:59"), 1L, 35455L, 0, 35.5, "EUR");
        prices.add(p1);
        prices.add(p2);
        return prices;
    }

    private List<Price> mockListTest5() {
        List<Price> prices = new ArrayList<>();
        Price p1 = new Price(1L, DateUtil.getDateFromString("2020-06-15 16:00:00"), DateUtil.getDateFromString("2020-12-31 23:59:59"), 4L, 35455L, 1, 38.95, "EUR");
        Price p2 = new Price(1L, DateUtil.getDateFromString("2020-06-14 00:00:00"), DateUtil.getDateFromString("2020-12-31 23:59:59"), 1L, 35455L, 0, 35.5, "EUR");
        prices.add(p1);
        prices.add(p2);
        return prices;
    }
}
