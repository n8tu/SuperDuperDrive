package com.faisal.superduperdrive.services;

import com.faisal.superduperdrive.mappers.UserMapper;
import com.faisal.superduperdrive.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final EncryptionService encryptionService;

    public UserService(UserMapper userMapper, EncryptionService encryptionService) {
        this.userMapper = userMapper;
        this.encryptionService = encryptionService;
    }

    public void createUser(User user){
        user.setSalt(encryptionService.generateSalt());
        user.setPassword(encryptionService.getHashedValue(user.getPassword(), user.getSalt()));
        userMapper.insertUser(new User(null, user.getUsername(), user.getPassword(), user.getSalt(), user.getFirstName(), user.getLastName()));
    }

    public boolean isAvailableUsername(String username){
        return userMapper.getUserByUsername(username) == null;
    }
    public User getUserByUsername(String username){
        return userMapper.getUserByUsername(username);
    }

    public Integer getUserId(Authentication authentication){
        return getUserByUsername(authentication.getName()).getId();
    }
}
