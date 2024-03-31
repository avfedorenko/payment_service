package faang.school.paymentservice.client;


import faang.school.paymentservice.model.ExchangeRatesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "openExchangeRatesClient", url = "${feign.openExchangeRatesClient.url}")
public interface OpenExchangeRatesClient {

    @GetMapping("/latest.json?app_id=${feign.openExchangeRatesClient.appId}")
    ExchangeRatesResponse getRates();

}
