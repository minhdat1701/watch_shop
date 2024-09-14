package org.example.ewatch.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.ewatch.entity.RoleType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleRes {
    
    private Long id;
    
    private RoleType roleName;
    
}
