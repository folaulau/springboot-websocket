package com.folautech.websocket.cronjob;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.folautech.websocket.dto.MessageDTO;

import jakarta.annotation.PostConstruct;

@Slf4j
@Component
public class Second5CronJob {
    
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @Scheduled(timeUnit = TimeUnit.SECONDS, fixedDelay = 5)
    public void runSecond5Job() {
        log.info("runSecond5Job...");
        
        String uuid = UUID.randomUUID().toString();
        simpMessagingTemplate.convertAndSend("/topic/messages", MessageDTO.builder().message("random uuid "+uuid).build());

        log.info("runSecond5Job done!");
    }

    
}
