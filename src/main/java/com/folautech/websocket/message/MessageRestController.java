package com.folautech.websocket.message;

import com.folautech.websocket.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
public class MessageRestController {

    @Autowired
    private MessageService messageService;

    @PostMapping(value = "/messages")
    public ResponseEntity<Message> addMessage(
            @RequestBody MessageCreateDTO msg) {
        log.info("addMessage msg={}", ObjectUtils.toJson(msg));

        Message message = messageService.save(msg);

        return new ResponseEntity<>(message, OK);
    }

}
