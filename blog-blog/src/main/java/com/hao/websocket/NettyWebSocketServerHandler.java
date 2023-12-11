package com.hao.websocket;


import cn.hutool.json.JSONUtil;
import com.hao.chatgpt.domain.chat.ChatCompletionRequest;
import com.hao.chatgpt.domain.chat.ChatCompletionResponse;
import com.hao.chatgpt.domain.chat.Message;
import com.hao.chatgpt.session.Configuration;
import com.hao.chatgpt.session.OpenAiSession;
import com.hao.chatgpt.session.OpenAiSessionFactory;
import com.hao.chatgpt.session.defaults.DefaultOpenAiSessionFactory;
import com.hao.websocket.domain.WsReq;
import com.hao.websocket.domain.WsResp;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.ArrayList;
import java.util.List;


@ChannelHandler.Sharable
public class NettyWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    /**
     * 建立链接
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    /**
     * 断开链接
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        userOffLine(ctx);
        super.channelInactive(ctx);
    }


    private void userOffLine(ChannelHandlerContext ctx) {
        ctx.channel().close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        String text = textWebSocketFrame.text();
        WsReq wsReq = JSONUtil.toBean(text, WsReq.class);
//        System.out.println(wsReq);

        Configuration configuration = new Configuration();
        configuration.setApiHost("https://api.openai-hk.com/");
        configuration.setApiKey("hk-0z2x6v1000003982141ce37857fefa8be947f068eb398d8b");

        // 2. 会话工厂
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        OpenAiSession openAiSession = factory.openSession();


        ChatCompletionRequest chatCompletion = ChatCompletionRequest
                .builder()
                .messages(wsReq.getMessages())
                .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
                .build();
        System.out.println(chatCompletion);
        ChatCompletionResponse chatCompletionResponse = openAiSession.completions(chatCompletion);
        WsResp build = WsResp.builder().session_id(wsReq.getSession_id()).message(chatCompletionResponse.getChoices().get(0).getMessage()).build();
        channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame(JSONUtil.toJsonStr(build)));

    }


}
