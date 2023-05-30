package org.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml2.provider.service.authentication.DefaultSaml2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "Hello, "+getPrincipalName();
    }

    @GetMapping(value = "/index/login")
    public String handleSamlAuth(HttpServletRequest request) {
        return "Hello, "+ getPrincipalName();
    }

    private String getPrincipalName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //SAMLCredential credential = (SAMLCredential) auth.getCredentials();
        DefaultSaml2AuthenticatedPrincipal principal = (DefaultSaml2AuthenticatedPrincipal) auth.getPrincipal();
        System.err.println(principal.getAttributes());
        return principal.getName();
    }
}