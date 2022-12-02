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

    public List<Credential> getAllCredentials(Authentication auth){
        return credentialMapper.getAllCredentials(userService.getUserId(auth));
    }

    public void deleteCredential(Integer id, Authentication auth){
        credentialMapper.deleteCredential(id, userService.getUserId(auth));
    }

    public Credential getCredentialById(Integer id){
        return credentialMapper.getCredentialById(id);
    }

    public void updateCredential(Credential credential, Integer credentialId, Authentication auth){
        String key = getCredentialById(credentialId).getKey();
        credential.setId(credentialId);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), key));
        credentialMapper.updateCredential(credential, userService.getUserId(auth));
    }
}
