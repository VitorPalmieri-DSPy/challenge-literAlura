package br.com.alura.challengeliteralura.model;

import br.com.alura.challengeliteralura.dto.LivroDTO;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private Double totalDownloads;

    @ManyToOne
    private Autor autor;

    public Livro (LivroDTO livroDTO) {
        this.titulo = livroDTO.titulo();
        this.idioma = livroDTO.idioma().getFirst();
        try {
            this.totalDownloads = Double.parseDouble(livroDTO.totalDownloads());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public String toString() {
        return
                "-------------Livro----------------" + "\n" +
                " titulo: " + titulo + '\n' +
                " Autor: " + autor.getNome() + '\n' +
                " idioma: " + idioma + '\n' +
                " total de Downloads: " + totalDownloads;
    }
}
