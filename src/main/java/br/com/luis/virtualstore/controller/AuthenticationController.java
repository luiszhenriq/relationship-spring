package br.com.luis.virtualstore.controller;

import br.com.luis.virtualstore.domain.user.LoginAuthenticationDTO;
import br.com.luis.virtualstore.domain.user.RegisterAuthenticationDTO;
import br.com.luis.virtualstore.domain.user.UserRole;
import br.com.luis.virtualstore.infra.security.TokenService;
import br.com.luis.virtualstore.infra.security.token.TokenJWTData;
import br.com.luis.virtualstore.models.User;
import br.com.luis.virtualstore.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/authentication")
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    private UserRole role;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginAuthenticationDTO data) {
      var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
      var authentication =  manager.authenticate(token);

      var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
      return ResponseEntity.ok(new TokenJWTData(tokenJWT));
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterAuthenticationDTO data) {
        if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User newUser = new User(data.login(), encryptedPassword, data.role());

        if (data.role() != UserRole.USER) {
            newUser.setRole(UserRole.USER);
        }

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
