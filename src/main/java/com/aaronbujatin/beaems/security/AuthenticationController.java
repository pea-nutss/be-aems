package com.aaronbujatin.beaems.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {


    @GetMapping
    public String home(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return "Username " + username + " authenticated";
    }

    @PostMapping("/veira")
    public String veira(){
        return "This is sample response";
    }


}
