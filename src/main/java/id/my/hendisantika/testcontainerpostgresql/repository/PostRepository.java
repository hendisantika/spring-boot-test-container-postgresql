package id.my.hendisantika.testcontainerpostgresql.repository;

import id.my.hendisantika.testcontainerpostgresql.model.Post;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-container-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/11/24
 * Time: 05.19
 * To change this template use File | Settings | File Templates.
 */
public interface PostRepository extends ListCrudRepository<Post, Integer> {

    Optional<Post> findByTitle(String title);
}
