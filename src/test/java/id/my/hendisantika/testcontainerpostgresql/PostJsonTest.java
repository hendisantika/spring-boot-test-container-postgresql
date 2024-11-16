package id.my.hendisantika.testcontainerpostgresql;

import id.my.hendisantika.testcontainerpostgresql.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

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
}
