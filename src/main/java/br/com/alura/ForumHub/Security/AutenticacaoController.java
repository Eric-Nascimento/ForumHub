package br.com.alura.ForumHub.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticacaoController {

    private static final Logger logger = LoggerFactory.getLogger(AutenticacaoController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsuario(), loginDto.getSenha());

        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            logger.info("Usuário autenticado com sucesso: {}", loginDto.getUsuario());
            return ResponseEntity.ok("Usuário autenticado com sucesso");
        } catch (AuthenticationException e) {
            logger.error("Erro de autenticação: {}", e.getMessage());
            return ResponseEntity.status(401).body("Erro de autenticação: " + e.getMessage());
        }
    }
}
