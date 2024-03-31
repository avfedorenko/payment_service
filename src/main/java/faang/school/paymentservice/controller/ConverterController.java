package faang.school.paymentservice.controller;

import faang.school.paymentservice.dto.PaymentRequest;
import faang.school.paymentservice.service.payment.CurrencyConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping ("/converter")
public class ConverterController {
    private final CurrencyConverterService currencyConverterService;
    @PostMapping
    public BigDecimal convertCurrencyToUSD(@RequestBody PaymentRequest paymentRequest){
        return currencyConverterService.convertCurrencyToUSD(paymentRequest);
    }
}
