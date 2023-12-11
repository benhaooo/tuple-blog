package com.hao;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.hao.chatgpt.common.Constants;
import com.hao.chatgpt.domain.chat.ChatCompletionRequest;
import com.hao.chatgpt.domain.chat.ChatCompletionResponse;
import com.hao.chatgpt.domain.chat.Message;
import com.hao.chatgpt.session.Configuration;
import com.hao.chatgpt.session.OpenAiSession;
import com.hao.chatgpt.session.OpenAiSessionFactory;
import com.hao.chatgpt.session.defaults.DefaultOpenAiSessionFactory;
import com.hao.domain.ResponseResult;

import com.hao.domain.entity.Comment;
import com.hao.mapper.ArticleMapper;
import com.hao.mapper.RoleMapper;
import com.hao.mapper.TagMapper;
import com.hao.service.*;
import com.hao.service.impl.UploadServiceImpl;

import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

@SpringBootTest
public class test {
    @Test
    public void uploadDemo() {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-guangzhou.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5t9qt7kuAfVeLgE3p64u";
        String accessKeySecret = "TH22KG6pGRNQDDlSpeRqsL0Zg6OkEw";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "benhao-test";
        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
        String objectName = "exampleobject2.jpg";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            InputStream in = new FileInputStream(new File("C:\\Users\\小道名也\\Desktop\\图片\\然2.jpg"));
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, in);
            System.out.println(putObjectResult.getETag() + "/n" + putObjectResult.getVersionId());

            System.out.println("成功上传");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }


    @Autowired
    private ArticleService articleService;

    @Test
    public void testListArticle() {

    }


    //    @Autowired
//    CommentMapper commentMapper;
    @Autowired
    CommentService commentService;

    @Test
    public void test1() {
        ResponseResult responseResult = commentService.selectList(732l);
        System.out.println(responseResult);
    }

    @Test
    public void commentAddTest() {
        Comment comment = new Comment();
        comment.setContent("测试测试测试");
        comment.setArticleId(732L);

        commentService.publish(comment);
    }

    @Autowired
    CategoryService categoryService;

    @Test
    public void categoryTest() {
        System.out.println(categoryService.listAllCategoryDTO());
    }


    @Autowired
    RoleMapper roleMapper;

    @Test
    public void roleTest() {
        System.out.println(roleMapper.selectRoleKeyByUserId(1l));
    }

    @Autowired
    TagMapper tagMapper;

    @Test
    public void tagTest() {
        System.out.println(tagMapper.selectListTagDTO());
    }

    @Test
    public void articleTest() {
//        System.out.println(articleService.listArticle(new ConditionVO()));
    }

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void viewCountTest() {
//        Map<String, Object> map = articleMapper.listViewCount();

//        System.out.println(map.getClass().getSimpleName());
    }


    @Autowired
    private UserService userService;

    @Test
    public void userInfoTest() {
//        System.out.println(userService.userInfoById);

    }


    @Autowired
    private UploadServiceImpl uploadService;

    @Test
    public void urltest() {
//        ResponseResult responseResult = uploadService.uploadRandomImg();
//        System.out.println(responseResult.getData());
//        RestTemplate restTemplate = new RestTemplate();
//        byte[] imageBytes = restTemplate.getForObject("https://imgapi.xl0408.top/index.php", byte[].class);
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
//        System.out.println(contentType);
//        uploadService.uploadOss(inputStream,"teseetttt.jpg");

//        File tempFile = File.createTempFile("temp", null);
//        Files.write(tempFile.toPath(), imageBytes);
//
//        MultipartFile multipartFile = new MockMultipartFile(
//                tempFile.getName(),
//                tempFile.getName(),
//                null,
//                new FileInputStream(tempFile)
//        );
//
//    // 可选择：将临时文件删除
//        tempFile.delete();
//        System.out.println(multipartFile);

    }

    @Test
    public void chatTest() throws JsonProcessingException {
        // 1. 配置文件
        Configuration configuration = new Configuration();
        configuration.setApiHost("https://api.openai-hk.com/");
        configuration.setApiKey("hk-0z2x6v1000003982141ce37857fefa8be947f068eb398d8b");

        // 2. 会话工厂
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        OpenAiSession openAiSession = factory.openSession();

        System.out.println("我是 OpenAI ChatGPT，请输入你的问题：");

        ChatCompletionRequest chatCompletion = ChatCompletionRequest
                .builder()
//                .stream(false)
                .messages(new ArrayList<>())
                .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
                .user("testUser02")
                .build();

        // 3. 等待输入
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextLine()) {
//            String text = scanner.nextLine();
        String text = "你好";
        chatCompletion.getMessages().add(Message.builder().role(Constants.Role.USER).content(text).build());
        ChatCompletionResponse chatCompletionResponse = openAiSession.completions(chatCompletion);
//        chatCompletion.getMessages().add(Message.builder().role(Constants.Role.USER).content(chatCompletionResponse.getChoices().get(0).getMessage().getContent()).build());
        // 输出结果
        System.out.println(chatCompletionResponse.getChoices().get(0).getMessage().getContent());
//        openAiSession.chatCompletions(chatCompletion, new EventSourceListener() {
//            @Override
//            public void onEvent(EventSource eventSource, String id, String type, String data) {
//                System.out.println(data);
//                super.onEvent(eventSource, id, type, data);
//
//            }
//        });
//        System.out.println("请输入你的问题：");

//        }
    }
}
