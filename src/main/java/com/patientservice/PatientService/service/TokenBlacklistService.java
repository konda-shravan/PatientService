package com.patientservice.PatientService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenBlacklistService {

    private final RedisTemplate<String,String> redisTemplate;

    public void blackListToken(String token,long expiryMs){
        redisTemplate.opsForValue()
                .set(token,"BLACKLISTED",expiryMs, TimeUnit.MICROSECONDS);
    }

    public boolean isBlackListed(String token){
        return Boolean.TRUE.equals(redisTemplate.hasKey(token));
    }

}
