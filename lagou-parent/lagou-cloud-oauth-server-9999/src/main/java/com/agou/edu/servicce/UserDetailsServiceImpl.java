package com.agou.edu.servicce;

import com.agou.edu.dao.UsersRepository;
import com.lagou.edu.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    /**
     * 使⽤⾃定义的⽤户详情服务对象
     * 3.3.7 基于Oauth2的 JWT 令牌信息扩展
     * OAuth2帮我们⽣成的JWT令牌载荷部分信息有限，关于⽤户信息只有⼀个user_name，有些场景下我
     * 们希望放⼊⼀些扩展信息项，⽐如，之前我们经常向session中存⼊userId，或者现在我希望在JWT的载
     * 荷部分存⼊当时请求令牌的客户端IP，客户端携带令牌访问资源服务时，可以对⽐当前请求的客户端真
     * 实IP和令牌中存放的客户端IP是否匹配，不匹配拒绝请求，以此进⼀步提⾼安全性。那么如何在OAuth2
     * 环境下向JWT令牌中存如扩展信息？
     * 认证服务器⽣成JWT令牌时存⼊扩展信息（⽐如clientIp）
     * 继承DefaultAccessTokenConverter类，重写convertAccessToken⽅法存⼊扩展信息
     * 根据username查询出该⽤户的所有信息，封装成UserDetails类型的对象返回，⾄于密码，框
     * 架会⾃动匹配
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username);
        return new User(users.getUsername(), users.getPassword(), new ArrayList<>());
    }
}
