package com.xixi.routers;

import com.xixi.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * RouterFunction路由
 */
@Configuration
public class AllRouters {

    /**
     * 注入spring RouterFunction
     *
     * @param handler 用户信息逻辑处理
     * @return RouterFunction ServerResponse
     */
    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler handler) {
        return nest(
                // 相当于类上面的 @RequestMapping("/user")
                path("/user"),
                // 下面的相当于类里面的 @RequestMapping
                // 得到所有用户
                route(GET("/"), handler::getAllUser)
                        // 创建用户
                        .andRoute(POST("/").and(accept(APPLICATION_JSON)),
                                handler::createUser)
                        // 删除用户
                        .andRoute(DELETE("/{id}"), handler::deleteUserById));
    }

}
