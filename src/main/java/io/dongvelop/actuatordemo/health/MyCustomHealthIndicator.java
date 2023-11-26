package io.dongvelop.actuatordemo.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 아래와 같이 작성하면 "myCustom" 이라는 이름으로 health 정보 생성
 * 항상 UP status를 리턴하고, 아래 값들을 가짐
 */
@Component
public class MyCustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {

        final boolean status = getStatus();

        if (status) {
            return Health.up()
                    .withDetail("key1", "value1")
                    .withDetail("key2", "value2")
                    .build();
        }

        return Health.down()
                .withDetail("key3", "value3")
                .withDetail("key4", "value4")
                .build();
    }

    boolean getStatus() {
        return System.currentTimeMillis() % 2 == 0;
    }
}
