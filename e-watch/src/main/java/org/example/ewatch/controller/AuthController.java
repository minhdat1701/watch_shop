package org.example.ewatch.controller;

import jakarta.validation.Valid;
import org.example.ewatch.dto.users.*;
import org.example.ewatch.entity.User;
import org.example.ewatch.mapper.UserMapper;
import org.example.ewatch.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserRes register(@RequestBody @Valid RegisterReq dto){
        User newUser = userService.register(dto);
        return Mappers.getMapper(UserMapper.class).toUserRes(newUser);
    }

    @PostMapping("/login")
    public TokenRes login(@RequestBody @Valid LoginReq dto){
        return userService.login(dto);
    }

    @PostMapping("/tokens")
    public TokenRes refreshToken(@RequestBody RefreshTokenReq req){
        return userService.refreshToken(req);
    }

}
