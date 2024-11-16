package id.my.hendisantika.testcontainerpostgresql;

import id.my.hendisantika.testcontainerpostgresql.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-container-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/11/24
 * Time: 05.28
 * To change this template use File | Settings | File Templates.
 */
@JsonTest
public class PostJsonTest {

    @Autowired
    private JacksonTester<Post> jacksonTester;

    @Test
    void shouldSerializePost() throws Exception {
        Post post = new Post(1, 1, "Hello, World!", "This is my first post.", null);
        String expected = STR."""
                {
                    "id":\{post.id()},
                    "userId":\{post.userId()},
                    "title":"\{post.title()}",
                    "body":"\{post.body()}",
                    "version": null
                }
                """;
        assertThat(jacksonTester.write(post)).isEqualToJson(expected);
    }
}
