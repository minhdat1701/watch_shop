package org.example.ewatch.service.Impl;

import org.example.ewatch.dto.users.*;
import org.example.ewatch.entity.Role;
import org.example.ewatch.entity.RoleType;
import org.example.ewatch.entity.User;
import org.example.ewatch.exception.NotFoundException;
import org.example.ewatch.exception.PasswordNotMatchException;
import org.example.ewatch.mapper.UserMapper;
import org.example.ewatch.repository.RoleRepository;
import org.example.ewatch.repository.UserRepository;
import org.example.ewatch.service.UserService;
import org.example.ewatch.utils.JwtUtil;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User register(RegisterReq dto) {
        Role role = roleRepository.findByRoleName(RoleType.USER).orElseThrow(() -> new NotFoundException("Role not found"));
        User user = Mappers.getMapper(UserMapper.class).fromRegisterDto(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(role);
        return userRepository.save(user);
    }

    @Override
    public TokenRes login(LoginReq dto) {
        User currentUser = getByUsername(dto.getUsername());
        if (!passwordEncoder.matches(CharBuffer.wrap(dto.getPassword()), currentUser.getPassword())) throw new PasswordNotMatchException();
        return TokenRes.builder()
                .userId(currentUser.getId())
                .username(currentUser.getUsername())
                .roles(currentUser.getRoles().stream().map(r -> r.getRoleName().name()).collect(Collectors.toSet()))
                .accessToken(jwtUtil.createAccessToken(currentUser))
                .refreshToken(jwtUtil.createRefreshToken(currentUser))
                .build();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void changePassword(User user, ChangePasswordReq dto) {
        if(!passwordEncoder.matches(CharBuffer.wrap(dto.getOldPassword()), user.getPassword())){
            throw new PasswordNotMatchException();
        }
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public TokenRes refreshToken(RefreshTokenReq req) {
        String username = jwtUtil.extractUsername(req.getRefreshToken());
        User currentUser = getByUsername(username);
        return TokenRes.builder()
                .userId(currentUser.getId())
                .username(currentUser.getUsername())
                .roles(currentUser.getRoles().stream().map(r -> r.getRoleName().name()).collect(Collectors.toSet()))
                .accessToken(jwtUtil.createAccessToken(currentUser))
                .refreshToken(req.getRefreshToken())
                .build();

    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User updateUser(User user, UserUpdateReq req) {
        User updatedUser = Mappers.getMapper(UserMapper.class).updateUser(req, user);
        return userRepository.save(updatedUser);
    }




//
//    @Override
//    public void createUser(User user) {
//        userRepository.save(user);
//    }
//
//    @Override
//    public User findUserById(Long id) {
//        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public void updateUser(Long id, User user) {
//        User existingUser = userRepository.findById(id).orElse(null);
//        if (existingUser != null) {
//            existingUser.setFirstName(user.getFirstName());
//            existingUser.setLastName(user.getLastName());
//            existingUser.setAddress(user.getAddress());
//            existingUser.setPhoneNumber(user.getPhoneNumber());
//            userRepository.save(existingUser);
//        }
//    }
//
//    @Override
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
}
