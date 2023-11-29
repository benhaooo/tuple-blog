package com.hao;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.hao.domain.ResponseResult;
import com.hao.domain.entity.Comment;
import com.hao.domain.vo.ConditionVO;
import com.hao.mapper.ArticleMapper;
import com.hao.mapper.RoleMapper;
import com.hao.mapper.TagMapper;
import com.hao.mapper.UserInfoMapper;
import com.hao.service.ArticleService;
import com.hao.service.CategoryService;
import com.hao.service.CommentService;
import com.hao.service.UserService;
import com.hao.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.List;
import java.util.Map;

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
        int pageNum = 1, pageSize = 10;
        Long category = null;
        ResponseResult responseResult = articleService.articleList(pageNum, pageSize, category);
        System.out.println(responseResult.getData());

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
        System.out.println(articleService.listArticle(new ConditionVO()));
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
}
