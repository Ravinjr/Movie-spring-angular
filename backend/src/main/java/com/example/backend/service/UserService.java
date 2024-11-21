package com.example.backend.service;

import com.example.backend.dto.ExceptionDTO;
import com.example.backend.dto.UserDTO;
import com.example.backend.model.User;
import com.example.backend.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public JWTService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    public UserService(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    public ExceptionDTO signUpUser(UserDTO userDTO) {
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        try {
            if (userRepo.existsById(userDTO.getId())) {
                return new ExceptionDTO("04", "User Already Exists", null);
            }
            userRepo.save(modelMapper.map(userDTO, User.class));
            return new ExceptionDTO("00", "success", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new ExceptionDTO("00", "success", null);
        }

    }

//    public ExceptionDTO loginUser(UserDTO userDTO) {
//        try {
//            Optional<User> optionalUser = userRepo.findByEmail(userDTO.getEmail());
//            if (userRepo.existsByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword())) {
//                return new ExceptionDTO("00", "success", null);
//            }
//            return new ExceptionDTO("02", "No such user exists!", null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ExceptionDTO("02", "No such user exists!", null);
//        }
//
//
//    }


    public ExceptionDTO loginUser(UserDTO userDTO) {
        try {
            Optional<User> optionalUser = userRepo.findByEmail(userDTO.getEmail());

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                // Check if the password matches
                if (encoder.matches(userDTO.getPassword(), user.getPassword())) {

                    return new ExceptionDTO("00", "success", null);
                } else {
                    return new ExceptionDTO("03", "Incorrect password", null);

                }
            } else {
                return new ExceptionDTO("02", "No such user exists!", null);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ExceptionDTO("02", "An error occurred during login", null);

        }
    }


    public String verify(UserDTO userDTO) {


        Authentication authentication=authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(),userDTO.getPassword()));

       if(authentication.isAuthenticated()){
           return jwtService.generateToken();
       }
       return "not success";
    }
}
