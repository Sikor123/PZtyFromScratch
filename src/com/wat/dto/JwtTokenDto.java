package com.wat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class JwtTokenDto {
    private String token;
    private String username;
    private String type;
    private Boolean isAdmin;
}
