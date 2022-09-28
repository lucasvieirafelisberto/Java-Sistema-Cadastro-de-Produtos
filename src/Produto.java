/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeprodutos;

/**
 *
 * @author 
 */
public class Produto {
    private String nome ;
    private float preço;
    private String unidade;
    private int quantidade;
   
    public Produto()
    {   
    }   
    
    public void atualizaCadastro(Float preço, String unidade, int quantidade)
    {
        this.preço = preço;
        this.unidade = unidade;
        this.quantidade = quantidade;
    }
    
    public Produto(String nome, Float preço, String unidade, int quantidade)
    {   
        this.nome = nome;
        this.preço = preço;
        this.unidade = unidade;
        this.quantidade = quantidade;
    }
    
    public int Incrementar (int quantidade)
    {
        this.quantidade = this.quantidade + quantidade;
        return this.quantidade;
     } 
    
    public int Decrementar (int quantidade)
    {
        if (this.quantidade < quantidade) {
            return -1; // informa ue deu erro 
        } else {
           this.quantidade = this.quantidade - quantidade; 
           return  this.quantidade;
        }
     } 
    
    public float ReajustarPreço (float porcentagem)
    {
        this.preço = this.preço +  (this.preço * porcentagem / 100);
        return this.preço;
     }
        
    public String Nome(){
      return this.nome;  
    }
    public float preço(){
      return this.preço;  
    }
    public String unidade(){
      return this.unidade;  
    }
    public int quantidade(){
      return this.quantidade;  
    }
    
    @Override
    public String toString() {
        return " nome= " + nome + " preço= " + preço + " UNIT: " + unidade + " QNT= " + quantidade;
    }
    
}
