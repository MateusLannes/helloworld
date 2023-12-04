package br.ifes.dw.helloworld.controller;



import br.ifes.dw.helloworld.application.AppProdutoTXT;
import br.ifes.dw.helloworld.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.ifes.dw.helloworld.dto.ProdutoInputDTO;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoControllerTXT {

    private final AppProdutoTXT appProdutoTXT;

    @Autowired
    public ProdutoControllerTXT(AppProdutoTXT appProdutoTXT) {
        this.appProdutoTXT = appProdutoTXT;
    }

    @GetMapping("/")
    public List<Produto>listarProdutos(){
        try{
          return appProdutoTXT.getAll();
        }catch(IOException e){
          System.out.println(e.getMessage());
        }
          return null;
      }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> obterProduto(@PathVariable int id) {
        try {
            Produto produto = appProdutoTXT.getById(id);
            return ResponseEntity.ok(produto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Produto> criarProduto(@RequestBody ProdutoInputDTO produto) {
        try {
            Produto novoProduto = appProdutoTXT.create(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable int id, @RequestBody Produto novoProduto) {
        try {
            novoProduto.setId(id);
            Produto produtoAtualizado = appProdutoTXT.updateProduto(novoProduto);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deletarProduto(@PathVariable int id) {
        try {
            Produto produtoDeletado = appProdutoTXT.delete(id);
            return ResponseEntity.ok(produtoDeletado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
