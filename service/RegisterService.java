package com.laioffer.booking.service;

import com.laioffer.booking.exception.UserAlreadyExistException;
import com.laioffer.booking.model.Authority;
import com.laioffer.booking.model.User;
import com.laioffer.booking.model.UserRole;
import com.laioffer.booking.repository.AuthorityRepository;
import com.laioffer.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RegisterService {

    private UserRepository userRepository;

    private AuthorityRepository authorityRepository;

    private PasswordEncoder passwordEncoder;


    @Autowired
    //注入后就可以对user在数据库上进行增删改查
    public RegisterService(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //@Transactional 保证method对数据库的操作是原子性的，要么成功，要么nothing，相当于hibernate里，如果出错，自动rollback
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void add(User user, UserRole role) throws UserAlreadyExistException { //要添加的user是不是已经存在数据库了
        if (userRepository.existsById(user.getUsername())) {
            throw new UserAlreadyExistException("User already exists"); //already exist, 在CustomExceptionHandler处理，相当于catch exception
        } {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEnabled(true); //check 用户是不是true
            userRepository.save(user);
            authorityRepository.save(new Authority(user.getUsername(), role.name()));


        }

    }
}
