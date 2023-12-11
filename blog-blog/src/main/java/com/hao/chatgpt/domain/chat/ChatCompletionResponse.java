package com.hao.chatgpt.domain.chat;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.hao.chatgpt.domain.other.Usage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class ChatCompletionResponse implements Serializable {

    /** ID */
    private String id;
    /** 对象 */
    private String object;
    /** 模型 */
    private String model;
    /** 对话 */
    private List<ChatChoice> choices;
    /** 创建 */
    private long created;
    /** 耗材 */
    private Usage usage;

    @JsonProperty("system_fingerprint")
    private String systemFingerprint;

}
