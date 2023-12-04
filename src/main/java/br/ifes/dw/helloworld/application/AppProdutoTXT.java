package br.ifes.dw.helloworld.application;

import br.ifes.dw.helloworld.model.Produto;
import br.ifes.dw.helloworld.repository.RepositorioTXT;
import org.springframework.stereotype.Component;
import br.ifes.dw.helloworld.exception.ProdutoNotFoundException;
import br.ifes.dw.helloworld.dto.ProdutoInputDTO;

import java.io.IOException;
import java.util.List;
//import java.util.ArrayList;

@Component
public class AppProdutoTXT {

    private final RepositorioTXT repositorioTXT;
    private int lastId = 0;
    private List<Produto> produtos;

    public AppProdutoTXT() {
        try {
            repositorioTXT = new RepositorioTXT("produtos.txt");
        } catch (IOException e) {
            throw new RuntimeException("Falha ", e);
        }
    }

    public List<Produto> getAll() throws IOException {
        this.produtos = repositorioTXT.lerArquivo();

        if(!this.produtos.isEmpty()){
            lastId = this.produtos.get(this.produtos.size() - 1).getId();
        }
        return this.produtos;
    }

    public Produto create(ProdutoInputDTO ProdutoInputDTO) throws IOException {
        lastId++;
        Produto produto = new Produto();
        produto.setId(lastId);
        produto.setNome(ProdutoInputDTO.getNome());
        produto.setPreco(ProdutoInputDTO.getPreco());
        
        produtos.add(produto);
        repositorioTXT.adicionarNovosDados(produto);

        return produto;
    }

    public Produto delete(int id) throws Exception {
        List<Produto> produtos = repositorioTXT.lerArquivo();

        for (Produto item : produtos) {
            if (item.getId() == id) {
                produtos.remove(item);
                repositorioTXT.atualizarArquivo(this.produtos);
                return item;
            }
        }
        throw new ProdutoNotFoundException();
    }

    public Produto getById(int id) throws Exception {
        List<Produto> produtos = repositorioTXT.lerArquivo();

        for (Produto item : produtos) {
            if (item.getId() == id) {
                return item;
            }
        }
        throw new ProdutoNotFoundException();
    }

    public Produto updateProduto(Produto novoProduto) throws Exception {
        int id = novoProduto.getId();
        List<Produto> produtos = repositorioTXT.lerArquivo();

        for (Produto antigoProduto : produtos) {
            if (antigoProduto.getId() == id) {
                antigoProduto.setNome(novoProduto.getNome());
                antigoProduto.setPreco(novoProduto.getPreco());
                repositorioTXT.atualizarArquivo(this.produtos);
                return antigoProduto;
            }
        }
        throw new ProdutoNotFoundException();
    }
}
