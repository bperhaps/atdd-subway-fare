package wooteco.subway.path;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import wooteco.subway.path.domain.farepolicy.DefaultFarePolicy;
import wooteco.subway.path.domain.farepolicy.FiftyOverPolicy;
import wooteco.subway.path.domain.farepolicy.TenFiftyPolicy;

@DisplayName("요금 정책 테스트")
public class FarePolicyTest {

    @DisplayName("기본 요금 정책")
    @ParameterizedTest
    @CsvSource(value = {"1, 1250", "0, 0"})
    void defaultFarePolicy(int distance, int expectedFare) {
        DefaultFarePolicy defaultFarePolicy = new DefaultFarePolicy();

        assertThat(defaultFarePolicy.calculate(distance))
            .isEqualTo(BigDecimal.valueOf(expectedFare));
    }

    @DisplayName("10~50km 요금 정책")
    @ParameterizedTest
    @CsvSource(value = {"9, 0", "10, 0", "11, 100", "16, 200", "15, 100", "50, 800"})
    void tenFiftyPolicy(int distance, int expectedFare) {
        TenFiftyPolicy tenFiftyPolicy = new TenFiftyPolicy();

        assertThat(tenFiftyPolicy.calculate(distance))
            .isEqualTo(BigDecimal.valueOf(expectedFare));
    }

    @DisplayName("50km~ 요금 정책")
    @ParameterizedTest
    @CsvSource(value = {"50, 0", "51, 100", "58, 100", "59, 200"})
    void fiftyOverPolicy(int distance, int expectedFare) {
        FiftyOverPolicy fiftyOverPolicy = new FiftyOverPolicy();

        assertThat(fiftyOverPolicy.calculate(distance))
            .isEqualTo(BigDecimal.valueOf(expectedFare));
    }
}
