package com.example.simpleapi.services.userService;

import lombok.Builder;

@Builder
public record LoginResponse(String accessToken, String refreshToken,  String error) {
}
