package org.example.ewatch.dto.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.ewatch.entity.RoleType;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UserRes {
    
    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    private String username;
    
    private Set<String> roles = new HashSet<>();
    
    public UserRes(Long id, String firstName, String lastName, String username, Set<RoleType> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.roles = roles.stream().map(Enum::name).collect(Collectors.toSet());
    }
    
}
