package br.com.alura.challengeliteralura.principal;

import br.com.alura.challengeliteralura.dto.ResponseDTO;
import br.com.alura.challengeliteralura.model.Autor;
import br.com.alura.challengeliteralura.model.Livro;
import br.com.alura.challengeliteralura.service.ConsumoApi;
import br.com.alura.challengeliteralura.service.ConverteDados;
import br.com.alura.challengeliteralura.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books?search=";

    @Autowired
    private AutorService service;

    public Principal (AutorService service) {
        this.service = service;
    };

    public void exibeMenu () {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em determinado ano
                5 - Listar livros em um determinado idioma
                
                0 - Sair                                 
                """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    salvar();
                    break;
                case 2:
                    listaLivrosResgistrados();
                    break;
                case 3:
                    listaAutoresResgistrados();
                    break;

                case 4:
                    listaAutoresVivosEmDeterminadoAno();
                    break;
                case 5:
                    listaLivrosEmDeterminadoIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }

    }

    private void listaLivrosEmDeterminadoIdioma() {
        service.ListaLivrosEmDeterminadoIdioma();
    }

    private void listaAutoresVivosEmDeterminadoAno() {
        service.listarAutoresVivosEmDeterminadoAno();
    }


    private void listaAutoresResgistrados() {
        service.listarAutoresCadastrados();
    }

    private void listaLivrosResgistrados() {
        service.listarLivrosCadastrados();
    }

    private Livro getDadosLivroEAutor () {
        var responseDTO = getDados();
        var autor = new Autor(responseDTO.results().getFirst().autorDTOList().getFirst());
        var livro = new Livro(responseDTO.results().getFirst());
        autor.setLivros(livro);
        return livro;
    }


    private ResponseDTO getDados() {
        System.out.println("Digite o titulo do livro que deseja buscar: ");
        var livroBuscado = leitura.nextLine();
        var json = consumoApi.obterDados(ENDERECO + livroBuscado.replace(" ", "%20"));
        var responseDTO = conversor.converterDados(json, ResponseDTO.class);
        return responseDTO;
    }


    private void salvar () {
        var livro = getDadosLivroEAutor();
        service.salvarLivroDeAutorJaCadastrado(livro.getAutor(), livro);
    }


}
