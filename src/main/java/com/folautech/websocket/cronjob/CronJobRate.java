package com.folautech.websocket.cronjob;

public interface CronJobRate {

    long   SECOND                    = 1000;

    long   MINUTE                    = SECOND * 60;

    long   HOUR                      = MINUTE * 60;

    // 1:55pm Monday - Friday
    String CLOSE_OPEN_POSITIONS_TIME = "0 55 13 * * 1-5";
    
    String AT_CLOSE = "0 1 14 * * 1-5";

    // 7:31am Monday - Friday
    String OPEN_TIME = "0 32 7 * * 1-5";
    
    // 8am Monday - Friday
    String EIGHT_AM = "0 0 8 * * 1-5";
}
