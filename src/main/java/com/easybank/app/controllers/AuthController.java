package com.easybank.app.controllers;

import com.easybank.app.dto.LoginDTO;
import com.easybank.app.dto.RegisterRequestDTO;
import com.easybank.app.dto.ResponseDTO;
import com.easybank.app.infra.security.TokenService;
import com.easybank.app.model.Account;
import com.easybank.app.model.User;
import com.easybank.app.repositories.AccountRepository;
import com.easybank.app.repositories.UserRepository;
import com.easybank.app.services.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDTO dto) {
        var user = userRepository.findByEmail(dto.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(dto.password(), user.getPassword())) { // compare the password
            var token = tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().body("Invalid credentials");
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequestDTO dto) {

        Optional<User> user = userRepository.findByEmail(dto.email());
        if (user.isEmpty()) {
            var newUser = new User();
            newUser.setPassword(passwordEncoder.encode(dto.password()));
            newUser.setEmail(dto.email());
            newUser.setName(dto.name());
            newUser.setCpf(dto.cpf());
            newUser.setAddress(dto.address());

            // Cria uma nova conta e associa ao usuário
            Account newAccount = new Account();
            newAccount.setBalance(0.0); // ou qualquer valor inicial

            // Gera um número de conta
            BigInteger accountNumber = accountService.generateAccountNumber();
            newAccount.setAccountNumber(accountNumber);

            newAccount.setUser(newUser);
            newUser.setAccount(newAccount); // associa a conta ao usuário

            this.userRepository.save(newUser);
            String token = tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }

        return ResponseEntity.badRequest().body("Invalid credentials");
    }

}
