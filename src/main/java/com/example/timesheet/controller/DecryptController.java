package com.example.timesheet.controller;

import com.example.timesheet.security.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DecryptController {

    @Autowired
    private AESUtil aesUtil;

    /**
     * Accepts Base64 AES-encrypted string and returns decrypted JSON string.
     */
    @PostMapping("/decrypt")
    public String decryptPayload(@RequestBody String encrypted) throws Exception {
        return aesUtil.decrypt(encrypted.trim());
    }
}
