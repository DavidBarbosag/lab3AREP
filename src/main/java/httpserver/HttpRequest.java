package httpserver;

import java.net.URI;

/**
 *
 * @author luisdanielbenavidesnavarro
 */
public class HttpRequest {

    URI requri = null;

    HttpRequest(URI requri) {
        this.requri = requri;
    }

    public String getValue(String paramName) {
        String query = requri.getQuery();
        if (query == null) {
            return null; // no hay parámetros en la URL
        }

        // soporta varios parámetros ?name=jhon&age=20
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2);
            if (keyValue[0].equals(paramName)) {
                return keyValue.length > 1 ? keyValue[1] : "";
            }
        }

        return null; // no encontró el parámetro
    }

}
