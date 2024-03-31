package faang.school.paymentservice.service.payment;

import faang.school.paymentservice.client.OpenExchangeRatesClient;
import faang.school.paymentservice.dto.Currency;
import faang.school.paymentservice.dto.PaymentRequest;
import faang.school.paymentservice.model.ExchangeRatesResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class CurrencyConverterServiceTest {
    @Mock
    OpenExchangeRatesClient openExchangeRatesClient;
    @Mock
    FeeCalculatorService feeCalculatorService;
    @InjectMocks
    CurrencyConverterService converterService;
    private final static double FEE_PERCENT = 1.0;
    PaymentRequest firstPaymentRequest;
    PaymentRequest secondPaymentRequest;
    ExchangeRatesResponse exchangeRatesResponse = new ExchangeRatesResponse();
    BigDecimal fee;
    BigDecimal convertedAmount;

    @BeforeEach
    void setUp() {
        firstPaymentRequest = PaymentRequest.builder()
                .currency(Currency.USD)
                .amount(BigDecimal.valueOf(1000))
                .paymentNumber(UUID.randomUUID())
                .build();

        secondPaymentRequest = PaymentRequest.builder()
                .currency(Currency.EUR)
                .amount(BigDecimal.valueOf(1000))
                .paymentNumber(UUID.randomUUID())
                .build();


        Map<String, BigDecimal> rates = Map.of(
                "EUR", BigDecimal.valueOf(0.927713)
        );

//        exchangeRatesResponse = ExchangeRatesResponse.builder()
//                .timestamp(1711681200L)
//                .base(Currency.USD)
//                .rates(rates)
//                .build();

        exchangeRatesResponse.setTimestamp(1711681200L);
        exchangeRatesResponse.setBase(Currency.USD);
        exchangeRatesResponse.setRates(rates);

        convertedAmount = rates.get("EUR").multiply(firstPaymentRequest.amount());
        BigDecimal decimalPercent = BigDecimal.valueOf(FEE_PERCENT / 100);
        fee = convertedAmount.multiply(decimalPercent);

    }

    @Test
    @Operation(description = "Check if currency is USD")
    public void testConvertCurrencyToUSD_IfUSD() {
        assertEquals(converterService.convertCurrencyToUSD(firstPaymentRequest), firstPaymentRequest.amount());
    }

    @Test
    @Operation(description = "Testing the converting method to USD")
    public void testConvertCurrencyToUSD() {
        Mockito.when(openExchangeRatesClient.getRates()).thenReturn(exchangeRatesResponse);
        Mockito.when(feeCalculatorService.calculateFee(Mockito.any(BigDecimal.class), eq(FEE_PERCENT))).thenReturn(fee);

        assertEquals(converterService.convertCurrencyToUSD(secondPaymentRequest), convertedAmount.add(fee));
    }

}