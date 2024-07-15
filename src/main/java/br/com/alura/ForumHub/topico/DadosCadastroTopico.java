package br.com.alura.ForumHub.topico;


import br.com.alura.ForumHub.curso.CursoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DadosCadastroTopico{
                                 @NotBlank
                                 private String titulo;
                                 @NotBlank
                                 private String mensagem;
                                 @NotBlank
                                 private String autor;
                                 @NotNull
                                 private CursoEnum curso;
}


