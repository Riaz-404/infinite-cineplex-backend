package me.riazulislam.infinitecineplexbackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import me.riazulislam.infinitecineplexbackend.configurations.InfoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    private static final String APP_VERSION = "v1.0.0";

    @Autowired
    private InfoConfig infoConfig;

    @GetMapping("/api/info")
    public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest request) {
        String baseUrl = extractBaseUrl(request);

        Map<String, Object> response = new HashMap<>();
        response.put("version", APP_VERSION);
        response.put("baseUrl", baseUrl);
        response.put("githubUrl", infoConfig.getGithubLink());
        response.put("apiDocsUrl", infoConfig.getApiDocsLink());
        response.put("timestamp", Instant.now().toString());

        Map<String, String> routes = new HashMap<>();
        routes.put("health", baseUrl + "/api/health");
        routes.put("movies", baseUrl + "/api/movies");
//        routes.put("show-times", baseUrl + "/api/show-times");
//        routes.put("time-slots", baseUrl + "/api/time-slots");

        response.put("routes", routes);

        return ResponseEntity.ok(response);
    }

    private String extractBaseUrl(HttpServletRequest request) {
        // Check for X-Forwarded-Proto and X-Forwarded-Host headers (Render, Railway, Nginx)
        String proto = request.getHeader("X-Forwarded-Proto");
        String host = request.getHeader("X-Forwarded-Host");

        if (proto != null && host != null) {
            int port = 80;
            if (host.contains(":")) {
                String[] parts = host.split(":");
                host = parts[0];
                port = Integer.parseInt(parts[1]);
            } else {
                port = "https".equals(proto) ? 443 : 80;
            }
            return buildUrl(proto, host, port);
        }

        // Fall back to request scheme and server name
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();

        return buildUrl(scheme, serverName, port);
    }

    private String buildUrl(String scheme, String host, int port) {
        if ((scheme.equals("http") && port == 80) || (scheme.equals("https") && port == 443)) {
            return scheme + "://" + host;
        }
        return scheme + "://" + host + ":" + port;
    }
}
