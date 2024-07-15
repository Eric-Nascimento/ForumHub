package br.com.alura.ForumHub.DTO;

import br.com.alura.ForumHub.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto{
      private String nome;
      private String email;

    public UsuarioDto(Usuario usuario){
         this.nome = usuario.getNome();
           this.email = usuario.getEmail();
    }
}
