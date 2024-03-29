package faang.school.paymentservice.service.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class FeeCalculatorServiceTest {
    @InjectMocks
    FeeCalculatorService feeCalculatorService;

    BigDecimal initialAmount;
    double percent;
    BigDecimal resultAmount;

    @BeforeEach
    void setUp() {
        initialAmount = BigDecimal.valueOf(100.50);
        percent = 1.0;
        resultAmount = initialAmount.multiply(BigDecimal.valueOf(percent / 100));
    }

    @Test
    public void testCalculateFee() {
        assertEquals(resultAmount, feeCalculatorService.calculateFee(initialAmount, percent));
    }
}