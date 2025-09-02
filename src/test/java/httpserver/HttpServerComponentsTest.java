package httpserver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import miniSpringBoot.GetMapping;
import miniSpringBoot.RequestParam;
import miniSpringBoot.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class HttpServerComponentsTest {

    private HttpServer server;
    private HttpRequest request;
    private HttpResponse response;

    @BeforeEach
    void setUp() throws URISyntaxException {
        server = new HttpServer();
        request = new HttpRequest(new URI("/app/hello?name=test"));
        response = new HttpResponse();
    }

    @Test
    void testHttpServerLoadServices() {
        String[] args = new String[]{};
        HttpServer.loadServices(args);
        assertFalse(HttpServer.services.isEmpty(), "Los servicios no deberían estar vacíos después de cargar");
    }


    @Test
    void testHttpServerDefaultResponse() {
        String response = HttpServer.defaultResponse();
        assertTrue(response.contains("HTTP/1.1 200 OK"), "La respuesta por defecto debe contener el encabezado HTTP");
        assertTrue(response.contains("<html>"), "La respuesta por defecto debe contener HTML");
    }

    @Test
    void testHttpRequestGetValue() throws URISyntaxException {
        URI testUri = new URI("/app/hello?name=john");
        HttpRequest request = new HttpRequest(testUri);
        String value = request.getValue("name");
        assertEquals("john", value, "El valor del parámetro debe ser extraído correctamente");
    }

    @Test
    void testHttpRequestConstructor() throws URISyntaxException {
        URI testUri = new URI("/app/hello?name=john");
        HttpRequest request = new HttpRequest(testUri);
        assertNotNull(request.requri, "El URI no debe ser null");
        assertEquals(testUri, request.requri, "El URI debe coincidir con el proporcionado");
    }

    @Test
    void testServiceImplementation() {
        Service testService = new Service() {
            @Override
            public String invoke(HttpRequest req, HttpResponse res) {
                return "test response";
            }
        };

        String result = testService.invoke(request, response);
        assertEquals("test response", result, "El servicio debe retornar la respuesta esperada");
    }


    @Test
    void testHttpServerStaticFiles() {
        assertDoesNotThrow(() -> {
            HttpServer.staticfiles("/public");
        }, "El método staticfiles no debe lanzar excepciones");
    }
}
