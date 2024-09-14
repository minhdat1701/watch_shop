package org.example.ewatch.dto.users;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenRes {
    
    private Long userId;
    
    private String username;
    
    private Set<String> roles = new HashSet<>();
    
    private String accessToken;
    
    private String refreshToken;
    
}
