package com.faisal.superduperdrive.services;

import com.faisal.superduperdrive.mappers.CredentialMapper;
import com.faisal.superduperdrive.models.Credential;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final UserService userService;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, UserService userService, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    public void insertCredential(Credential credential, Authentication auth){
        credential.setKey(encryptionService.generateKey());
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
        Integer userId = userService.getUserByUsername(auth.getName()).getId();
        credentialMapper.insertCredential(new Credential(null, credential.getUrl(), credential.getUsername(), credential.getKey(), credential.getPassword(), userId));
    }

    public List<Credential> getAllCredentials(){
        return credentialMapper.getAllCredentials();
    }

    public void deleteCredential(Integer id){
        credentialMapper.deleteCredential(id);
    }
}
