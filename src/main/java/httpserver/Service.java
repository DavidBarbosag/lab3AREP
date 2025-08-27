package httpserver;

public interface Service {

    public String invoke(HttpRequest req, HttpResponse res);

}