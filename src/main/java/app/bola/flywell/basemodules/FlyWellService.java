package app.bola.flywell.basemodules;

public interface FlyWellService<Req, Res> {

    Res createNew(Req request) ;

    Res findByPublicId(String publicId);
    boolean existsByPublicId(String publicId);
}