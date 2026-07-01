package com.tripnest.gateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        // Public APIs
        if (path.equals("/users/register") || path.equals("/users/login")) {
            return chain.filter(exchange);
        }

        String authHeader =
                exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        try {

            // jwtService.extractUsername(token);
            if (!jwtService.isTokenValid(token)) {

                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

                return exchange.getResponse().setComplete();
            }
            
            String role = jwtService.extractRole(token);
            String method = exchange.getRequest().getMethod().toString();
            // USER Restrictions

            if ("USER".equals(role)) {

                // Hotel
                if (path.startsWith("/hotels")
                        && !method.equals("GET")) {

                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);

                    return exchange.getResponse().setComplete();
                }

                // Flight
                if (path.startsWith("/flights")
                        && !method.equals("GET")) {

                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);

                    return exchange.getResponse().setComplete();
                }

                // Booking
                if (path.equals("/bookings")
                        && method.equals("GET")) {

                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }

            }

        } catch (Exception e) {

            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
