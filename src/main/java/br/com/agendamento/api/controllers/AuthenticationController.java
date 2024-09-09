package br.com.agendamento.api.controllers;

import br.com.agendamento.api.dtos.AuthenticationDTO;
import br.com.agendamento.api.dtos.LoginResponseDTO;
import br.com.agendamento.api.dtos.RegisterDTO;
import br.com.agendamento.api.entities.User;
import br.com.agendamento.api.infra.security.TokenService;
import br.com.agendamento.api.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
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

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @Operation(summary = "Autenticação por login e retorna um token.")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.genarateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token)); //Tratar para retornar em caso de erro.
    }

    @Operation(summary = "Registra a criação de um novo usuário.")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO data){
        //Retirando o @Valid para a requisição conseguir chegar ao endpoint e ser tratada.
        String errorMessage = "Login ja existe.";
        if(this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().body(errorMessage);

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }


}
