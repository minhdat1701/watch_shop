package org.example.ewatch.seeder;

import org.example.ewatch.entity.Role;
import org.example.ewatch.entity.RoleType;
import org.example.ewatch.entity.User;
import org.example.ewatch.repository.RoleRepository;
import org.example.ewatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DataSeeder implements CommandLineRunner {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepo;


    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = seedRoles(RoleType.ADMIN);
        seedRoles(RoleType.USER);
        seedAdmin(roleAdmin);
    }

    @Transactional
    protected void seedAdmin(Role role){
        Optional<User> admin = userRepo.findByUsername("admin");
        if(admin.isEmpty()){
            User user = new User();
            user.setFirstName("admin");
            user.setLastName("admin");
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.getRoles().add(role);
            userRepo.save(user);
        }
    }

    private Role seedRoles(RoleType type){
        Optional<Role> thisRole = roleRepo.findByRoleName(type);
        if(thisRole.isEmpty()){
            Role role = new Role();
            role.setRoleName(type);
            return roleRepo.save(role);
        }
        return thisRole.get();
    }
}
