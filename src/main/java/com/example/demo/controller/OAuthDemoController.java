package com.example.demo.controller;

import com.example.demo.client.SimonRestTemplate;
import com.example.demo.model.AccessToken;
import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * Oauth 示例
 *
 * @author SimonX
 */
@Slf4j
@Controller
public class OAuthDemoController {

    @Resource
    private SimonRestTemplate restTemplate;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("oauth", String.format("https://github.com/login/oauth/authorize?client_id=%s&redirect_uri=http://localhost:8080/oauth/redirect", clientId));
        return modelAndView;
    }

    @GetMapping("/welcome")
    public ModelAndView welcome(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/oauth/redirect")
    public String githubRedirect(RedirectAttributes redirectAttributes, @RequestParam("code") String code) {
        log.info(">>>>>>>>>>>> GitHub 返回的 Code：  " + code);

        AccessToken accessToken = getAccessToken(code);

        User user = getUserInfo(accessToken.getAccess_token());

        redirectAttributes.addFlashAttribute("user", user);

        return "redirect:/welcome";
    }

    /**
     * 根据返回的 oauth code 获取 access_token
     */
    private AccessToken getAccessToken(String code) {
        String accessTokenUrl = "https://github.com/login/oauth/access_token?client_id=%s&client_secret=%s&code=%s";
        ResponseEntity<AccessToken> responseEntity = restTemplate.postForEntity(String.format(accessTokenUrl, clientId, clientSecret, code), null, AccessToken.class);
        log.info(">>>>>>>>>>>> GitHub 返回的 access_token：  " + responseEntity.getBody());

        return responseEntity.getBody();
    }

    /**
     * 根据 access_token 获取 user info
     */
    private User getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "token " + accessToken);
        HttpEntity<Object> entity = new HttpEntity<>(headers);

        String userInfoUrl = "https://api.github.com/user";
        ResponseEntity<User> responseEntity = restTemplate.getForEntity(userInfoUrl, entity, User.class);
        log.info(">>>>>>>>>>>> GitHub 返回的 user_info：  " + responseEntity.getBody());

        return responseEntity.getBody();
    }

}
