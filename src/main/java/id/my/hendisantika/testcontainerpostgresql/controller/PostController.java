package id.my.hendisantika.testcontainerpostgresql.controller;

import id.my.hendisantika.testcontainerpostgresql.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-container-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/11/24
 * Time: 05.22
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
class PostController {

    private final PostRepository repository;

}
