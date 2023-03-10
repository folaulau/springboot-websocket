package com.folautech.websocket.chat;

import com.folautech.websocket.dto.ChatDTO;
import com.folautech.websocket.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequestMapping("/chats")
@RestController
public class ChatRestController {

    @Autowired
    ChatService chatService;

    @GetMapping
    public ResponseEntity<ChatDTO> getById(@RequestParam Long id) {
        log.info("getById id={}", ObjectUtils.toJson(id));

        ChatDTO chat = chatService.getById(id);

        return new ResponseEntity<>(chat, OK);
    }
}
