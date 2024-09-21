package EAM.Blogging.Controller;

import EAM.Blogging.Model.Post;
import EAM.Blogging.Dto.DtoPost;
import EAM.Blogging.Service.ServicePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/post")
public class ControllerPost {

    @Autowired
    private ServicePost servicePost;

    @GetMapping
    public List<Post> getAllPosts() {
        return servicePost.findAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = servicePost.findPostById(id);
        return post != null ? ResponseEntity.ok(post) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody DtoPost dtoPost) {
        Post createdPost = servicePost.createPost(dtoPost);
        return ResponseEntity.ok(createdPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody DtoPost dtoPost) {
        boolean updated = servicePost.updatePost(id, dtoPost);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        boolean deleted = servicePost.deletePost(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
