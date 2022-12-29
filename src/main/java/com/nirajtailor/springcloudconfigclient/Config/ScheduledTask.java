package com.nirajtailor.springcloudconfigclient.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
@ConditionalOnProperty(name="scheduler.enabled", matchIfMissing = true)
public class ScheduledTask {

    @Autowired
    private RefreshEndpoint refreshEndpoint;

    @Scheduled(fixedDelayString = "${scheduler.interval}")
    public void scheduleFixedDelayTask() {
        refreshEndpoint.refresh();
        System.out.println(
                "Fixed delay task - " + System.currentTimeMillis() / 1000);
    }
}