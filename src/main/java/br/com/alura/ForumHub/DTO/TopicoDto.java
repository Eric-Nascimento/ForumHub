package br.com.alura.ForumHub.DTO;

import br.com.alura.ForumHub.curso.CursoEnum;
import br.com.alura.ForumHub.topico.Topico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TopicoDto{
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String status;
    private UsuarioDto autor;
    private CursoEnum curso;

    public TopicoDto(Topico topico){
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.status = topico.getStatus();
        this.autor = new UsuarioDto(topico.getAutor());
        this.curso = topico.getCurso();
   }
}
