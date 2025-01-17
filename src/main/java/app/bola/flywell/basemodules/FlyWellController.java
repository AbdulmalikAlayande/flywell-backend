package app.bola.flywell.basemodules;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

public interface FlyWellController<Req, Res extends BaseResponse> {

    @PostMapping("new")
    ResponseEntity<Res> createNew(Req req);

    @GetMapping("{public-id}")
    ResponseEntity<Res> findByPublicId(@PathVariable("public-id") String publicId);

    @GetMapping("all")
    ResponseEntity<Collection<Res>> findAll();
}
