package br.ifes.dw.helloworld.model;


import lombok.Data;
//import lombok.NoArgsConstructor;

//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
@Data
public class Produto {
  
  //@Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String nome;

  private double preco;

}
