package org.example.ewatch.dto.users;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordReq {
    
    @NotBlank
    private String oldPassword;
    
    @NotBlank
    private String newPassword;
    
}
