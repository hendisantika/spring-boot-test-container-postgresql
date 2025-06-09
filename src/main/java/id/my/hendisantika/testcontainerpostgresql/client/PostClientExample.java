package id.my.hendisantika.testcontainerpostgresql.client;

import id.my.hendisantika.testcontainerpostgresql.model.Post;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-test-container-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/11/24
 * Time: 08.40
 * To change this template use File | Settings | File Templates.
 * <p>
 * This class demonstrates how to use the PostClient.
 * It's disabled by default (only runs with "client-example" profile).
 * To run it, use: ./mvnw spring-boot:run -Dspring-boot.run.profiles=client-example
 */
@Component
@Profile("client-example")
public class PostClientExample implements CommandLineRunner {

    private final PostClient postClient;

    public PostClientExample(PostClient postClient) {
        this.postClient = postClient;
    }

    @Override
    public void run(String... args) {
        System.out.println("Running PostClient example...");

        // Get all posts
        postClient.getAllPosts()
                .collectList()
                .doOnNext(posts -> System.out.println("Found " + posts.size() + " posts"))
                .block();

        // Get post by ID
        Post post = postClient.getPostById(1)
                .doOnNext(p -> System.out.println("Found post: " + p))
                .block();

        // Create a new post
        if (post != null) {
            Post newPost = new Post(null, post.userId(), "New Post Title", "New Post Body", null);
            postClient.createPost(newPost)
                    .doOnNext(p -> System.out.println("Created post: " + p))
                    .block();
        }

        // Example of how to update a post
        if (post != null) {
            Post updatedPost = new Post(post.id(), post.userId(), "Updated Title", "Updated Body", post.version());
            postClient.updatePost(post.id(), updatedPost)
                    .doOnNext(p -> System.out.println("Updated post: " + p))
                    .block();
        }

        // Example of how to delete a post (commented out to prevent actual deletion)
        // postClient.deletePost(101).block();

        System.out.println("PostClient example completed.");
    }
}