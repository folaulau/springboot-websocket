package com.folautech.websocket.chat;

import com.folautech.websocket.dto.ChatDTO;

public interface ChatService {
    ChatDTO getById(Long id);
}
