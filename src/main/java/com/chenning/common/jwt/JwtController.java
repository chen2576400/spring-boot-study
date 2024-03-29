package com.chenning.common.jwt;

import com.chenning.common.jwt.config.LoginConstant;
import com.chenning.common.crud.model.User;
import com.chenning.common.crud.service.UserService;
import com.chenning.common.redis.RedisService;
import com.chenning.common.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author nchen
 * @Date 2021/9/29 16:57
 * @Version 1.0
 * @Description
 */
@RestController
@RequestMapping("/jwt")
public class JwtController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @RequestMapping("login")
    public Result login(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = userService.findUserByNameAndPassword(userName, password);
        if (user == null) {
            return Result.error("未查到该用户");
        }


        Map<String, String> map = new HashMap();
        map.put("id", user.getUserId().toString());
        map.put("userName", user.getUserName());
        String token = JwtUtils.creatToken(map);
        redisService.set(session.getId(),token);
        redisService.set(LoginConstant.ACCESS_TOKEN + user.getUserId(), token);

        {//这里将cookie存放到浏览器  是为了方便后面页面直接调用 免得每次都要放一个jwt-token  看业务需要(也可以前端存入)
            //因为拦截器还有session Id的判断 所以切换浏览器后就需要重新登录
            Cookie c = new Cookie(LoginConstant.HEADER_TOKEN, token);
            c.setHttpOnly(true);
            c.setPath("/");
            c.setMaxAge((int) (60*3));//三分钟
            response.addCookie(c);
        }

        return Result.ok(token);
    }




}
