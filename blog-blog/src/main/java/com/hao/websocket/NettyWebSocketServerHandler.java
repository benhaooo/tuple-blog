package com.hao.websocket;


import cn.hutool.json.JSONUtil;
import com.hao.chatgpt.domain.chat.ChatCompletionRequest;
import com.hao.chatgpt.domain.chat.ChatCompletionResponse;
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
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;


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

        Configuration configuration = new Configuration();
        configuration.setApiHost("https://api.openai-hk.com/");
        configuration.setApiKey("hk-zubm9g1000003982fe8dfb9c662aa9f8ba75b0f5afefbe1e");

        // 2. 会话工厂
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        OpenAiSession openAiSession = factory.openSession();


        ChatCompletionRequest chatCompletion = ChatCompletionRequest
                .builder()
                .stream(true)
                .messages(wsReq.getMessages())
                .model(wsReq.getModel())
                .maxTokens(wsReq.getMaxTokens())
                .build();
        System.out.println(chatCompletion);
        openAiSession.chatCompletions(chatCompletion, new EventSourceListener() {
            @Override
            public void onEvent(EventSource eventSource, String id, String type, String data) {
                super.onEvent(eventSource, id, type, data);
                WsResp build = WsResp.builder().message_id(wsReq.getMessage_id()).content(JSONUtil.toBean(data, ChatCompletionResponse.class).getChoices().get(0).getDelta().getContent()).build();
                channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame(JSONUtil.toJsonStr(build)));
            }
        });
    }


}
