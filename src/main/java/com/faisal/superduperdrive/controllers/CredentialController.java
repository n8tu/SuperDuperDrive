package com.faisal.superduperdrive.controllers;

import com.faisal.superduperdrive.models.Credential;
import com.faisal.superduperdrive.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CredentialController {

    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/credential/create")
    public String createCredential(Credential credential, Authentication auth){
        credentialService.insertCredential(credential,auth);
        return "redirect:/";
    }

    @RequestMapping(value = "/credential/delete/{credentialId}", method = RequestMethod.DELETE)
    public String deleteCredential(@PathVariable Integer credentialId){
        credentialService.deleteCredential(credentialId);
        return "redirect:/";
    }
}
