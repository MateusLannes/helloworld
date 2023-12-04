package br.ifes.dw.helloworld.controller;

import br.ifes.dw.helloworld.dto.ProdutoInputDTO;
import org.springframework.web.bind.annotation.*;
//import java.util.ArrayList;
import java.util.List;
import br.ifes.dw.helloworld.model.Produto;
import br.ifes.dw.helloworld.application.AppProduto;
import org.springframework.beans.factory.annotation.*;
//import org.springframework.web.bind.annotation.*;
import br.ifes.dw.helloworld.exception.*;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

  @Autowired
  private AppProduto appProduto;

  @GetMapping("/")
  public List<Produto> getAll() {
    return appProduto.getAll();
  }

  @PostMapping("/")
  public Produto createProduto(@RequestBody ProdutoInputDTO produto) {
    return appProduto.create(produto);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable int id) {
    appProduto.delete(id);
  }

  @GetMapping("/{id}")
  public Produto getById(@PathVariable int id) {
    
    try {
      
      return appProduto.getById(id);
      
     } catch (VaiMeuFilhoException e) {
      // alguma tratativa
      return null;
    
    } catch (NotFoundException e) {
      // alguma tratativa
         return null;
    } catch (Exception e) {

      // erro desconhecido
      return null;
    }

  }

}