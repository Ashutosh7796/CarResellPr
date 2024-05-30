package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.UserService;
import com.spring.jwt.dto.*;
import com.spring.jwt.exception.InvalidPasswordException;
import com.spring.jwt.exception.PageNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.utils.BaseResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/index")
    public ResponseEntity<String> index(Principal principal){
        return ResponseEntity.ok("Welcome to user page : " + principal.getName());
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<ResponseAllUsersDto> getAllUsers(@RequestParam int pageNo){

        try {
            List<UserProfileDto> list= userService.getAllUsers(pageNo);
            ResponseAllUsersDto responseAllUsersDto = new ResponseAllUsersDto("success");
            responseAllUsersDto.setList(list);
            return ResponseEntity.status(HttpStatus.OK).body(responseAllUsersDto);
        } catch (UserNotFoundExceptions exception){
            ResponseAllUsersDto responseAllCarDto = new ResponseAllUsersDto("unsuccess");
            responseAllCarDto.setException("car not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseAllCarDto);
        }
        catch (PageNotFoundException exception){
            ResponseAllUsersDto responseAllCarDto = new ResponseAllUsersDto("unsuccess");
            responseAllCarDto.setException("page not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseAllCarDto);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editUser(@RequestBody UserProfileDto userProfileDto, @PathVariable int id){

        try {
            BaseResponseDTO result = userService.editUser(userProfileDto,id);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseDTO("Successful",result.getMessage()));
        }catch (UserNotFoundExceptions exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseDTO("Unsuccessful","user not found"));
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> removeUser(@RequestParam("userId") int id) {
        try {
            BaseResponseDTO result = userService.removeUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseDTO("Successful", result.getMessage()));
        } catch (UserNotFoundExceptions exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseDTO("Unsuccessful", "User not found"));
        }
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id ){

        try {
            UserProfileDto userProfileDto = userService.getUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(userProfileDto);
        }catch (UserNotFoundExceptions exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseDTO("Unsuccessful","user not found"));

        }

    }

    @PutMapping("/changePassword/{id}")
    public ResponseEntity<BaseResponseDTO> changePassword(@PathVariable int id, @RequestBody PasswordChange passwordChange){

        try{

            BaseResponseDTO result =userService.changePassword(id,passwordChange);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseDTO("Successful",result.getMessage()));
        }catch (UserNotFoundExceptions exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseDTO("Unsuccessfully","UserNotFoundException"));
        } catch (InvalidPasswordException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseDTO("Unsuccessfully","InvalidPasswordException"));
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseDto> forgotPass(HttpServletRequest request) throws UserNotFoundExceptions {
        try {

            String email = request.getParameter("email");

            String token = RandomStringUtils.randomAlphabetic(40);

            LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(1);

            userService.updateResetPassword(token, email);

            String resetPasswordLink = "http://localhost:8080/user/reset-password?token=" + token;

            ResponseDto response = userService.forgotPass(email, resetPasswordLink, request.getServerName());

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("Successful", response.getMessage()));
        } catch (UserNotFoundExceptions e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("Unsuccessful", "Invalid email. Please register."));
        }
    }

    @GetMapping("/reset-password")
    public ResponseEntity<String> resetPasswordPage(@RequestParam(name = "token") String token) {
        try {
            ClassPathResource resource = new ClassPathResource("templates/reset-password.html");
            String htmlContent = new String(Files.readAllBytes(Paths.get(resource.getURI())), StandardCharsets.UTF_8);
            return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(htmlContent);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading HTML file");
        }
    }

    @PostMapping("/update-password")
    public ResponseEntity<ResponseDto> resetPassword(@RequestBody ResetPassword resetPassword) throws UserNotFoundExceptions {

        try {
            String token = resetPassword.getToken();
            String newPassword = resetPassword.getPassword();

            ResponseDto response = userService.updatePassword(token, newPassword);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("Successful", response.getMessage()));

        } catch (UserNotFoundExceptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("Unsuccessful", "Something went wrong"));
        }
    }
}
