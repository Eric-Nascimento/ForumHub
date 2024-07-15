package br.com.alura.ForumHub.Controller;

import br.com.alura.ForumHub.DTO.TopicoDto;
import br.com.alura.ForumHub.repository.TopicoRepository;
import br.com.alura.ForumHub.repository.UsuarioRepository;
import br.com.alura.ForumHub.topico.DadosCadastroTopico;
import br.com.alura.ForumHub.topico.Topico;
import br.com.alura.ForumHub.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody @Validated DadosCadastroTopico dados){
        Optional<Usuario> autorOptional = usuarioRepository.findByNome(dados.getAutor());
        if (!autorOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Autor não encontrado!");
        }

        Usuario autor = autorOptional.get();

        boolean exists = topicoRepository.existsByTituloAndMensagem(dados.getTitulo(), dados.getMensagem());
        if (exists){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tópico duplicado");
        }
        Topico topico = new Topico();
        topico.setTitulo(dados.getTitulo());
        topico.setMensagem(dados.getMensagem());
        topico.setAutor(autor);
        topico.setCurso(dados.getCurso());

        topicoRepository.save(topico);

        return ResponseEntity.status(HttpStatus.CREATED).body("Tópico criado com sucesso");
    }

    @GetMapping
    public List<TopicoDto> listar(){
        List<Topico> topicos = topicoRepository.findAll();
        return topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> datalhar(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (!topicoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID não encontrado!");
        }

        Topico topico = topicoOptional.get();
        TopicoDto topicoDto = new TopicoDto(topico);
        return ResponseEntity.ok(topicoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody @Validated DadosCadastroTopico dados) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (!topicoOptional.isPresent()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID não encontrado!");
        }

        Topico topico = topicoOptional.get();

        Optional<Usuario>  autorOptional = usuarioRepository.findByNome(dados.getAutor());
        if (!autorOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Autor não encontrado!");
        }

        Usuario autor = autorOptional.get();

        boolean exists = topicoRepository.existsByTituloAndMensagemAndIdNot(dados.getTitulo(), dados.getMensagem(), id);
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tópico duplicado!");
        }

        topico.setTitulo(dados.getTitulo());
        topico.setMensagem(dados.getMensagem());
        topico.setAutor(autor);
        topico.setCurso(dados.getCurso());

        topicoRepository.save(topico);

        return ResponseEntity.ok("Tópico atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (!topicoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID não encontrada!");
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.ok("Tópico excluido com sucesso!");
    }

}
