package com.hao.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.hao.chatgpt.domain.chat.ChatCompletionRequest;
import com.hao.chatgpt.session.Configuration;
import com.hao.chatgpt.session.OpenAiSession;
import com.hao.chatgpt.session.OpenAiSessionFactory;
import com.hao.chatgpt.session.defaults.DefaultOpenAiSessionFactory;
import com.hao.domain.ResponseResult;
import com.hao.domain.entity.Askprompt;
import com.hao.domain.vo.PageVo;
import com.hao.service.AskpromptService;
import com.hao.utils.PageUtils;
import com.hao.websocket.domain.WsReq;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/gpt")
public class ChatController {
    @Autowired
    private AskpromptService askpromptService;

    @GetMapping("/askprompt/list")
    public ResponseResult listAskprompt() {
        Long current = PageUtils.getCurrent();
        Page<Askprompt> page = new Page<>(current,9);
        Page<Askprompt> askpromptPage = askpromptService.page(page);
        PageVo build = PageVo.builder().rows(askpromptPage.getRecords()).total(askpromptPage.getTotal()).build();
        return ResponseResult.okResult(build);
    }

    @PutMapping("/askprompt")
    public ResponseResult insetAskprompt(@RequestBody Askprompt askprompt) {
        if (Objects.isNull(askprompt.getId())) {
            askpromptService.save(askprompt);
        } else {
            askpromptService.updateById(askprompt);
        }
        return ResponseResult.okResult();
    }

    @DeleteMapping("/askprompt/{id}")
    public ResponseResult deleteAskprompt(@PathVariable Long id) {
        askpromptService.removeById(id);
        return ResponseResult.okResult();
    }


    @PostMapping("/chatCompletions")
    public SseEmitter chatCompletions(@RequestBody WsReq req) throws JsonProcessingException {
        System.out.println(req);
        SseEmitter sseEmitter = new SseEmitter();
        Configuration configuration = new Configuration();
        configuration.setApiHost("https://api.openai-hk.com/");
        configuration.setApiKey("hk-0z2x6v1000003982141ce37857fefa8be947f068eb398d8b");

        // 2. 会话工厂
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        OpenAiSession openAiSession = factory.openSession();


        ChatCompletionRequest chatCompletion = ChatCompletionRequest
                .builder()
                .stream(true)
                .messages(req.getMessages())
                .model(req.getModel())
                .build();

        openAiSession.chatCompletions(chatCompletion, new EventSourceListener() {
            @Override
            public void onEvent(EventSource eventSource, String id, String type, String data) {
                super.onEvent(eventSource, id, type, data);
                try {
                    System.out.println(data);
                    sseEmitter.send(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return sseEmitter;
    }
}
