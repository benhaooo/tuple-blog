package com.hao.websocket.domain;

import com.hao.chatgpt.domain.chat.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WsReq {
    private String session_id;
    private List<Message> messages;
}
