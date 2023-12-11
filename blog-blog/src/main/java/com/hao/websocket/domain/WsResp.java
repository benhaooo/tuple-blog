package com.hao.websocket.domain;

import com.hao.chatgpt.domain.chat.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WsResp {
    private String session_id;
    private Message message;

}
