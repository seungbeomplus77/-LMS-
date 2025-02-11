package com.sp.app.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String originalwPassword = "admin9999";  // 예시 평문 비밀번호
        String encryptedPassword = encoder.encode(originalwPassword);
        System.out.println("암호화된 비밀번호: " + encryptedPassword);
    }
}
