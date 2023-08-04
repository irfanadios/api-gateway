package project.irfanadios.apigateway.config;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {
    public static final List<String> whiteListedEndpoint = List.of(
        "/api/auth-service/v1/user-management"
    );

    private Predicate<ServerHttpRequest> isSecured = request -> whiteListedEndpoint.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));

    public Predicate<ServerHttpRequest> getIsSecured() {
        return isSecured;
    }

    public void setIsSecured(Predicate<ServerHttpRequest> isSecured) {
        this.isSecured = isSecured;
    }

    
}
