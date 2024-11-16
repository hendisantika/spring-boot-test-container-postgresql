package id.my.hendisantika.testcontainerpostgresql.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-container-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/11/24
 * Time: 05.20
 * To change this template use File | Settings | File Templates.
 */
public record Posts(List<Post> posts) {
}
