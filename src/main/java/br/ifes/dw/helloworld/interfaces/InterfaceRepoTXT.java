package br.ifes.dw.helloworld.interfaces;

import br.ifes.dw.helloworld.model.Produto;
import java.io.IOException;
import java.util.List;



public interface InterfaceRepoTXT<T> {
    public List<T> lerArquivo() throws IOException;
    public void criarArquivo() throws IOException;
    public void atualizarArquivo(List<T> lista) throws IOException;
    public void adicionarNovosDados(Produto produto) throws IOException;
}
