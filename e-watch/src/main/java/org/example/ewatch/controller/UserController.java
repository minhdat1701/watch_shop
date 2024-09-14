package org.example.ewatch.controller;

import org.example.ewatch.dto.PageRes;
import org.example.ewatch.mapper.UserMapper;
import org.example.ewatch.dto.users.UserRes;
import org.example.ewatch.entity.User;
import org.example.ewatch.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public PageRes<UserRes> getAllUsers(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ){
        Page<User> users= userService.getAll(pageable);
        return new PageRes<>(users.map(Mappers.getMapper(UserMapper.class)::toUserRes));
    }

    @GetMapping("{id}")
    public UserRes getById(@PathVariable Long id){
        return Mappers.getMapper(UserMapper.class).toUserRes(userService.getById(id));
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Long id) {
//        return userService.findUserById(id);
//    }
//    @PostMapping
//    public void createUser(@RequestBody User user) {
//        userService.createUser(user);
//    }
//    @PutMapping("/{id}")
//    public void updateUser(@PathVariable Long id, @RequestBody User user) {
//        userService.updateUser(id, user);
//    }
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//    }
}
