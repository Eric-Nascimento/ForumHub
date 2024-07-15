package br.com.alura.ForumHub.topico;

import br.com.alura.ForumHub.curso.CursoEnum;
import br.com.alura.ForumHub.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    @Lob
    private String mensagem;

    @NotNull
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @NotNull
    private String status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "autor")
    private Usuario autor;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CursoEnum curso;

    @PrePersist
    protected void onCreate(){
        dataCriacao = LocalDateTime.now();
        status = "ATIVO";
    }

}
