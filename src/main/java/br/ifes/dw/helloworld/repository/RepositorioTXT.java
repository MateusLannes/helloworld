package br.ifes.dw.helloworld.repository;

import br.ifes.dw.helloworld.model.Produto;

//import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class RepositorioTXT {

    private final Path caminhoArquivo;
    private final String nomeArquivo;

    public RepositorioTXT(String nomeArquivo) throws IOException {
        this.caminhoArquivo = Path.of(nomeArquivo);
        this.nomeArquivo = nomeArquivo;
        criarArquivo();
    }

    public List<Produto> lerArquivo() throws IOException {
        FileReader fileReader = new FileReader(nomeArquivo);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<Produto> produtos = new ArrayList<Produto>();

        for (String s : separaResultado(bufferedReader)) {

            String[] prod = s.split(";");


            Produto produto = new Produto();
            produto.setId(Integer.valueOf(prod[0]));
            produto.setNome(prod[1]);
            produto.setPreco(Double.parseDouble(prod[2]));


            produtos.add(produto);
        }

        return produtos;
    }

    private List<String> separaResultado(BufferedReader bufferedReader) throws IOException{
        String linha = "";
        List<String> resultado = new ArrayList<String>();

        while ((linha = bufferedReader.readLine()) != null) {
                resultado.add(linha);
        }
        return resultado;
    }



    public void criarArquivo() throws IOException {
        if (Files.notExists(caminhoArquivo)) {
            Files.createFile(caminhoArquivo);
        }
    }
    
    public void atualizarArquivo(List<Produto> produtos) throws IOException {
        FileWriter fileWriter = new FileWriter(nomeArquivo, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for(Produto produto: produtos){
            printWriter.println(String.format("%d;%s;%.2f", produto.getId(), produto.getNome(), produto.getPreco()));
        }
        
        printWriter.flush();
        printWriter.close();
    }

    public void adicionarNovosDados(Produto prod) throws IOException {       
        String novoRegistro = String.format("%d;%s;%.2f", prod.getId(), prod.getNome(), prod.getPreco());
        Files.writeString(caminhoArquivo, novoRegistro, StandardOpenOption.APPEND);
    }


}
