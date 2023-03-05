package com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.mapper.PriceMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.catalogue.cleanarchitecture.domain.model.Price;
import com.catalogue.cleanarchitecture.application.service.GetPriceService;
import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.dto.PriceRequestDto;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class CatalogueRestControllerTest {

    private MockMvc mvc;

    @Mock
    private GetPriceService getPriceUseCase;

    @InjectMocks
    private CatalogueRestController catalogueRestController;

    @BeforeEach
    void setUp() {
        PriceMapper priceMapper = Mappers.getMapper(PriceMapper.class);
        ReflectionTestUtils.setField(catalogueRestController, "priceMapper", priceMapper);
        mvc = MockMvcBuilders.standaloneSetup(catalogueRestController)
                .build();
    }

    @Test
    void returnGetPriceDataOK() throws Exception {
        // given
        Price price = Price.builder()
                .brandId(1L)
                .startDate(LocalDateTime.parse("2022-01-01T00:00:00"))
                .endDate(LocalDateTime.parse("2022-01-01T00:00:00"))
                .priceList(1L)
                .productId(1L)
                .price(12.12)
                .curr("EUR")
                .priority(1)
                .build();
        // when
        when(getPriceUseCase.getPrice(1L))
                .thenReturn(price);
        // then
        MvcResult mvcResult = mvc
                .perform(get("/api/catalogue/price/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mockito.anyString()))
                .andExpect(status().isOk())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(response);
    }

    @Test
    void returnGetPriceDataEmpty() throws Exception {
        // given
        // when
        // then
        MvcResult mvcResult = mvc
                .perform(get("/api/catalogue/price/8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mockito.anyString()))
                .andExpect(status().isOk())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(response);
    }

    @Test
    void returnGetPriceDataNotFound() throws Exception {
        // then
        MvcResult mvcResult = mvc
                .perform(get("/api/catalogue/price/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isNotFound())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(response);
    }

    @Test
    void returnPriceDataNull() throws Exception {
        // given
        PriceRequestDto requestDto = PriceRequestDto.builder()
                .dateQuery("2020-06-14 10:00:00")
                .brandId(2L)
                .productId(2L)
                .build();
        // when
        String requestJson = requestToJson(requestDto);
        // then
        MvcResult mvcResult = mvc
                .perform(post("/api/catalogue/price/findByBrandProductBetweenDate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(response);
    }

    @Test
    void returnListPricesDataNull() throws Exception {
        // given
        PriceRequestDto requestDto = PriceRequestDto.builder()
                .dateQuery("2020-06-14 10:00:00")
                .brandId(2L)
                .productId(2L)
                .build();
        // when
        String requestJson = requestToJson(requestDto);
        // then
        MvcResult mvcResult = mvc
                .perform(post("/api/catalogue/price/listAllByBrandProductBetweenDate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(response);
    }

    @Test
    void returnEmptyBadRequest() throws Exception {
        // then
        MvcResult mvcResult = mvc
                .perform(post("/api/catalogue/price/findByBrandProductBetweenDate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals("", response);
    }

    @Test
    void returnMessageBadRequest() throws Exception {
        // given
        PriceRequestDto requestDto = PriceRequestDto.builder()
                .dateQuery("")
                .brandId(1L)
                .productId(1L)
                .build();
        // when
        String requestJson = requestToJson(requestDto);
        // then
        MvcResult mvcResult = mvc
                .perform(post("/api/catalogue/price/findByBrandProductBetweenDate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(response);
    }

    @Test
    void returnPriceDataAsJsonObject() throws Exception {
        // given
        PriceRequestDto requestDto = PriceRequestDto.builder()
                .dateQuery("2020-06-14 10:00:00")
                .brandId(1L)
                .productId(1L)
                .build();
        // when
        String requestJson = requestToJson(requestDto);
        // then
        MvcResult mvcResult = mvc
                .perform(post("/api/catalogue/price/findByBrandProductBetweenDate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(response);
    }

    @Test
    void returnListPricesDataAsJsonObject() throws Exception {
        // given
        PriceRequestDto requestDto = PriceRequestDto.builder()
                .dateQuery("2020-06-14 10:00:00")
                .brandId(1L)
                .productId(1L)
                .build();
        // when
        String requestJson = requestToJson(requestDto);
        // then
        MvcResult mvcResult = mvc
                .perform(post("/api/catalogue/price/listAllByBrandProductBetweenDate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        mvcResult.getResponse();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(response);
    }

    private String requestToJson(PriceRequestDto requestDto) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(requestDto);
    }
}
