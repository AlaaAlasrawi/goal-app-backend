package com.goalproject.backend.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JobService {

    @Scheduled(cron = "0 * * * * *")
    public void runMe() {
        log.info("job is running");
    }
}
