package org.example.ewatch.service;

import org.example.ewatch.dto.users.*;
import org.example.ewatch.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    Page<User> getAll(Pageable pageable);
    User register(RegisterReq dto);
    TokenRes login(LoginReq dto);
    User getById(Long id);
    User getByUsername(String username);
    void createUser(User user);
    void changePassword(User user, ChangePasswordReq dto);
    TokenRes refreshToken(RefreshTokenReq req);
    User updateUser(User user, UserUpdateReq req);

//    UserDetailsService userDetailService();
//    public List<User> getAllUsers();
//    public User findUserById(Long id);
//    public void updateUser(Long id, User user);
//    public void deleteUser(Long id);


}
