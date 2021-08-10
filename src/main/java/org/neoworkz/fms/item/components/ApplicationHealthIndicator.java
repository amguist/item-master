package org.neoworkz.fms.item.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class ApplicationHealthIndicator implements HealthIndicator {

    @Value("${info.app.version}")
    private String version;

    @Override
    public Health health() {

        return Health.up()
                .withDetail("time", LocalDateTime.now())
                .withDetail("application", "Sample Application")
                .withDetail("description", "Test Application")
                .withDetail("version", getVersion())
                .withDetail("image", getImageVersion())
                .withDetail("region", getRegion())
                .build();
    }

    private String getVersion() {
        return version;
    }

    private String getImageVersion() {
        return System.getProperty("imageVersion") != null ? System.getProperty("imageVersion") : "";
    }

    private String getRegion() {
        return System.getProperty("region") != null ? System.getProperty("region") : "";
    }
}
