package com.amalitech.fileserver.Controllers;

import com.amalitech.fileserver.Services.Interfaces.FirebaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final FirebaseService firebaseService;

    @PostMapping("/signup")
    public void signUp(){
    }

    @PostMapping("/signin")
    public void signIn(){
    }
}
