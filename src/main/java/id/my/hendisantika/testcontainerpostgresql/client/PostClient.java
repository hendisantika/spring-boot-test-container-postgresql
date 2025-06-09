package id.my.hendisantika.testcontainerpostgresql.client;

import id.my.hendisantika.testcontainerpostgresql.model.Post;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-container-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/11/24
 * Time: 08.30
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PostClient {

    private final WebClient webClient;

    public PostClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Flux<Post> getAllPosts() {
        return webClient.get()
                .uri("/api/posts")
                .retrieve()
                .bodyToFlux(Post.class);
    }

    public Mono<Post> getPostById(Integer id) {
        return webClient.get()
                .uri("/api/posts/{id}", id)
                .retrieve()
                .bodyToMono(Post.class);
    }

    public Mono<Post> createPost(Post post) {
        return webClient.post()
                .uri("/api/posts")
                .bodyValue(post)
                .retrieve()
                .bodyToMono(Post.class);
    }

    public Mono<Post> updatePost(Integer id, Post post) {
        return webClient.put()
                .uri("/api/posts/{id}", id)
                .bodyValue(post)
                .retrieve()
                .bodyToMono(Post.class);
    }

    public Mono<Void> deletePost(Integer id) {
        return webClient.delete()
                .uri("/api/posts/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}