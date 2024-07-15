package br.com.alura.ForumHub.Controller;

import br.com.alura.ForumHub.DTO.UsuarioDto;
import br.com.alura.ForumHub.repository.UsuarioRepository;
import br.com.alura.ForumHub.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<UsuarioDto> listar(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }

   @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody @Validated Usuario usuario){
       boolean exists = usuarioRepository.existsByEmail(usuario.getEmail());
       if (exists){
           return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail já cadastrado!");
       }

       usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        usuarioRepository.save(usuario);
       return ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso!");
   }
}
