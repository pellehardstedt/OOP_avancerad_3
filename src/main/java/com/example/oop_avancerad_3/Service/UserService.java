package com.example.oop_avancerad_3.Service;

import com.example.oop_avancerad_3.Entity.User;
import com.example.oop_avancerad_3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Locale;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void saveUser(User user){
        byte[] salt = generateSalt();
        String saltString = convertByteToString(salt);
        String hashPass = createSecureHash(user.getPassword(), salt);
        if(!hashPass.equals("")){
            user.setSalt(saltString);
            user.setPassword(hashPass);
            userRepository.save(user);
        }

    }

    public Boolean authUser(String username, String plainTextPassword){
        User user = userRepository.findByUsername(username);
        if(user == null ) { return false; }
        String userSalt = user.getSalt();
        String passwordToCompare = createSecureHash(plainTextPassword, convertStringToByte(userSalt));
        return passwordToCompare.equals(user.getPassword());
    }

    private String createSecureHash(String password, byte[] salt) {
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashPass = md.digest(password.getBytes());
            return convertByteToString(hashPass);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private byte[] generateSalt(){
        SecureRandom sr = new SecureRandom();
        byte[] salt = sr.generateSeed(12);
        return salt;
    }
    private byte[] convertStringToByte(String stringToByte){
        return DatatypeConverter.parseHexBinary(stringToByte);
    }

    private String convertByteToString(byte[] byteToString){
        return DatatypeConverter.printHexBinary(byteToString).toLowerCase(Locale.ROOT);
    }

    public Boolean deleteUser(String username){
        User user = userRepository.findByUsername(username);
        if(user == null){
            return false;
        }
        userRepository.delete(user);
        return true;
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    public User getUserById(long id){
        return userRepository.findById(id).orElseThrow();
    }

}
