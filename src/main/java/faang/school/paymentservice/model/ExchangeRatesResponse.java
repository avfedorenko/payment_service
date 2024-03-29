package faang.school.paymentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import faang.school.paymentservice.dto.Currency;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

//@Builder - why cant I use this annotation in this class, every time stumble over the exception: "message": "Type definition error: [simple type, class java.lang.String]"
@Data
public class ExchangeRatesResponse {
    @JsonIgnore
    private String disclaimer;
    @JsonIgnore
    private String license;

    private long timestamp;

    private Currency base;

    private Map<String, BigDecimal> rates;
}