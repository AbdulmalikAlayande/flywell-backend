package app.bola.flywell.basemodules;

import java.util.Collection;

public interface FlyWellService<Req, Res> {

    Res createNew(Req request) ;

    Res findByPublicId(String publicId);
    boolean existsByPublicId(String publicId);

    Collection<Res> findAll();

    void removeAll();
}