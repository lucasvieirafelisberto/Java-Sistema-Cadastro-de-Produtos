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
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JOptionPane; // operador ternário


 
public class CadastrodeProdutos {

// =============================================== Menus - Textos
    
    public static void cabecalho(String titulo){

        System.out.println("\tEMPRESA DE IMPORTAÇÃO DE PRODUTOS LTDA.");
        System.out.println("\tSISTEMA DE CONTROLE DE ESTOQUE");
        System.out.println(" ");
        System.out.println("\t" + titulo);
        System.out.println(" ");        
    }    
    
    
    public static void menuPrincipal(){

        cabecalho("MENU PRINCIPAL");

        System.out.println("1 - CADASTRO DE PRODUTOS");
        System.out.println("2 - MOVIMENTAÇÃO");
        System.out.println("3 - REAJUSTE DE PREÇOS");
        System.out.println("4 - RELATÓRIOS");
        System.out.println("0 - FINALIZAR");
        System.out.println("OPÇÃO: ");
    }
 

    public static void menuCadastros(){
        
        cabecalho("CADASTRO DE PRODUTOS");

        System.out.println("1 - INCLUSÃO");
        System.out.println("2 - ALTERAÇÃO");
        System.out.println("3 - CONSULTA");
        System.out.println("4 - EXCLUSÃO");
        System.out.println("0 - RETORNAR");
        System.out.println("OPÇÃO: ");
    }
  
    public static void menuMovimentacoes(){
        
        cabecalho("MOVIMENTAÇÃO");

        System.out.println("1 - ENTRADA");
        System.out.println("2 - SAÍDA");
        System.out.println("0 - RETORNAR");
        System.out.println("OPÇÃO: ");
    }  
    
    public static void menuReajustes(){
        
        cabecalho("REAJUSTE");

        System.out.println("1 - PRODUTO ESPECÍFICO");
        System.out.println("2 - TODOS OS PRODUTOS");
        System.out.println("0 - RETORNAR");
        System.out.println("OPÇÃO: ");
    }     
	
    public static int retornaPosicaoDoProduto(ArrayList<Produto> estoque , String nomeDoProduto){
        int i;
        int n = estoque.size();
        int p = -1;
        for (i=0; i<n; i++) {
            //System.out.println(estoque.get(i).Nome());
            if (estoque.get(i).Nome().equalsIgnoreCase(nomeDoProduto)) {
                p = i; // Salva a posição do vetor que possui o mesmo Nome
                i=n+1; // Faz parar a procura
            } else {
            }; //if - Else
        } // For
	
        return(p); // retorna -1 quando não localiza o produto
    }
    
 // =============================================== Operações genéricas
    
    public static Produto solicitarDadosdoProduto(boolean getNome, boolean getPreço, boolean getUnidade, boolean getQnt ){
        
        String nome ="mouse";
        float preço =0;
        String unid ="";
        int qnt =-1;
        
        Scanner entrada = new Scanner(System.in);
        System.out.print("INFORME _");
        
		if (getNome) {
			do{
				System.out.print("Nome: ");
				nome = entrada.next();
				String mensagem = (!nome.isEmpty()) ? " OK" :" ERRO: nome não pode ser vazio! ";
                                System.out.println(mensagem);                                
			} while(nome.isEmpty());
		};
		
		if (getPreço) {
			do{
				System.out.print("Preço: ");
				preço = entrada.nextFloat();
				String mensagem = (preço > 0) ? " OK" :" ERRO: O preço deve ser maior que zero! ";
                                System.out.println(mensagem);
			} while(preço <= 0);
		};
		
		if (getUnidade) {
			do{
				System.out.print("Unidade: ");
				unid = entrada.next();
				String mensagem = (!unid.isEmpty()) ? " OK" :" ERRO: Unidade deve conter uma descrição! ";
                                System.out.println(mensagem);                                
			} while(unid.isEmpty());
		};
		
		if (getQnt) {
			do{
				System.out.print("Quantidade: ");
				qnt = entrada.nextInt();				
				String mensagem = (qnt >= 0) ? " OK" :" ERRO: A quantidade deve ser maior ou igual a zero! ";
                                System.out.println(mensagem);                                
			} while(qnt < 0);
		};				
        // agora retorna um produto com os valores solicitados e validados
        return(new Produto(nome,preço,unid,qnt));
    }	
	
    public static float solicitarPercentualDeReajuste(){
        float percentual = 0;
        //Solicita o % a ser incrementado
            Scanner entrada = new Scanner(System.in);                    
            do{
                    System.out.print("Porcentagem de reajuste: ");
                    percentual = entrada.nextFloat();				
                    String mensagem = (percentual > 0) ? " OK" :" ERRO: A % deve ser maior que zero! ";
                    System.out.println(mensagem);                                
            } while(percentual <= 0);
        // agora retorna o percentual
        return(percentual);
    }
    
    public static boolean confirmarOperacao(String operacao){
    String opcao;
    Scanner entrada = new Scanner(System.in);		
            do{
                    System.out.print("CONFIRMA " + operacao + " (S/N)? _");
                    opcao= entrada.next();
            } while(  (!opcao.equalsIgnoreCase("S")) && (!opcao.equalsIgnoreCase("N")) );		

            return opcao.equalsIgnoreCase("S") ? true : false;
    }


    public static boolean repetirOperacao(){
    String opcao;
    Scanner entrada = new Scanner(System.in);		
            do{
                    System.out.print("REPETIR OPERAÇÃO (S/N)? _");
                    opcao= entrada.next();
            } while(  (!opcao.equalsIgnoreCase("S")) && (!opcao.equalsIgnoreCase("N")) );		

            return opcao.equalsIgnoreCase("S") ? true : false;
    }
	
// =============================================== Cadastros - SubOpções
    
        public static boolean incluirProduto(ArrayList<Produto> estoque){
        cabecalho("INCLUSÃO DE PRODUTO");
        Produto novoItem = solicitarDadosdoProduto(true,true,true,true);
        int cadastroNumero = retornaPosicaoDoProduto(estoque ,novoItem.Nome());
            if(  cadastroNumero == -1) {
                    System.out.println("Dados do novo Produto:" + novoItem.toString());
                    //solicita confirmação ao usuário
                    boolean resposta = confirmarOperacao("INCLUSÃO");
                    if(resposta) { estoque.add(novoItem); };  // caso SIM, Adiciona o Item ao estoque
                    return(resposta);
            }
            else {
                    System.out.println("!! Já existe um produto com o mesmo NOME !!");
            return(false);
            }
        }

        public static boolean alterarProduto(ArrayList<Produto> estoque){
                cabecalho("ALTERAÇÃO DE PRODUTO");
        Produto novoItem = solicitarDadosdoProduto(true,true,true,true);
        int cadastroNumero = retornaPosicaoDoProduto(estoque ,novoItem.Nome());
            if(  cadastroNumero >= 0 ) {
                    //Exibe dados atuais de cadastro
                    System.out.print("DADOS ATUAIS DO CADASTRO:");
                    System.out.printf("Posição %d- %s\n", cadastroNumero, estoque.get(cadastroNumero).toString());
                    System.out.print("Novos dados informados: Preço: " + novoItem.preço() + " Unidade:" + novoItem.unidade() + " Qnt:" + novoItem.quantidade() + " | " );
                    //solicita confirmação ao usuário
                    boolean resposta = confirmarOperacao("ALTERAÇÃO");
                    if(resposta) { 
                        estoque.get(cadastroNumero).atualizaCadastro(novoItem.preço(), novoItem.unidade(), novoItem.quantidade());
                        };  // caso SIM, Atualiza dados do Item no estoque
                    return(resposta);
            }
            else {
                    System.out.println("!! O nome informado não foi localizado !!");
            return(false);
            }                
        }

        public static int consultarProduto(ArrayList<Produto> estoque){
                cabecalho("CONSULTA DE PRODUTO");
        Produto novoItem = solicitarDadosdoProduto(true,false,false,false);
        int cadastroNumero = retornaPosicaoDoProduto(estoque ,novoItem.Nome());
            return(cadastroNumero);
        }

        public static boolean excluirProduto(ArrayList<Produto> estoque){
                cabecalho("EXCLUSÃO DE PRODUTO");
        Produto novoItem = solicitarDadosdoProduto(true,false,false,false);
        int cadastroNumero = retornaPosicaoDoProduto(estoque ,novoItem.Nome());
            if(  cadastroNumero >= 0 ) {
                    //Exibe dados atuais de cadastro
                    System.out.print("DADOS ATUAIS DO CADASTRO:");
                    System.out.printf("Posição %d- %s\n", cadastroNumero, estoque.get(cadastroNumero).toString());
                    //solicita confirmação ao usuário
                    boolean resposta = confirmarOperacao("EXCLUSÃO");
                    if(resposta) { estoque.remove(cadastroNumero); };  // caso SIM, remove o Item no estoque
                    return(resposta);
            }
            else {
                    System.out.println("!! O nome de produto informado não foi localizado !!");
            return(false);
            }  
        }
// =============================================== CADASTROS    
    public static void cadastros(ArrayList<Produto> estoque) {   
    int opcao;
    Scanner entrada = new Scanner(System.in);

        do{
            menuCadastros();
            opcao = entrada.nextInt();

            switch(opcao){
            case 1: // CADASTRAR NOVO PRODUTO
                    do{    // Chama procedimento para realizar cadastro, que retorna TRUE/FALSE
                            if( incluirProduto(estoque) )
                                    System.out.println("Ok - Produto cadastrado no estoque");
                            else
                                    System.out.println("Cadastro nao realizado!");
                    } while(repetirOperacao());
                    break;
            case 2: // EDITAR DADOS CADASTRAIS DE UM PRODUTO                
                    do{    // Chama procedimento para realizar EDIÇÂO de um cadastro, que retorna TRUE/FALSE
                            if( alterarProduto(estoque) )
                                    System.out.println("Ok - Cadastro Atualizado");
                            else
                                    System.out.println("Nenhuma alteração foi realizada!");
                    } while(repetirOperacao());
                    break;				
            case 3: // CONSULTAR DADOS DE UM PRODUTO                  
                    do{    // Chama procedimento para consultar um cadastro, que retorna um INT com a posição do vetor onde localizou o produto
                            int cadastroNumero = consultarProduto(estoque);
                            if( cadastroNumero >= 0 )
                                    System.out.printf("Posição %d- %s\n", cadastroNumero, estoque.get(cadastroNumero).toString());
                            else
                                    System.out.println("Nome de produto não está cadastrado!");
                    } while(repetirOperacao());                
                    break;
            case 4: // EXCLUIR O CADASTRO DE UM PRODUTO
                    do{    // Chama procedimento para realizar EXCLUSÂO de um cadastro, que retorna TRUE/FALSE
                            if( excluirProduto(estoque) )
                                    System.out.println("Ok - Cadastro Removido");
                            else
                                    System.out.println("Nenhuma alteração foi realizada!");
                    } while(repetirOperacao());
                    break;                
            default:
                System.out.println("Opção inválida.");
            }
        } while(opcao != 0);
    }// Cadastros
    
// =============================================== movimentações - SubOpções
        public static boolean movimentarProduto(ArrayList<Produto> estoque ,String operação){
        int qnt = 0;
        switch(operação){
        case "+": // Incrementar quantidade
                operação="ENTRADA";
                break;
        case "-": // DECREMENTAR quantidade
                operação="SAÍDA";
                break;              
        default:
            System.out.println("Operação inválida.");
            return(false);
        }// case      
                cabecalho("MOVIMENTAÇÃO - "+operação+" DE PRODUTO");        
                
        Produto novoItem = solicitarDadosdoProduto(true,false,false,false);
        int cadastroNumero = retornaPosicaoDoProduto(estoque ,novoItem.Nome());
            if(  cadastroNumero >= 0 ) {
                    // Copia dados do Vetor para o novoItem
                    novoItem.atualizaCadastro(estoque.get(cadastroNumero).preço(), estoque.get(cadastroNumero).unidade(), estoque.get(cadastroNumero).quantidade()); // pega a quantidade, Unidade e Preço atuais
                    //Exibe dados atuais de cadastro
                    System.out.print("DADOS ATUAIS DO CADASTRO:");
                    System.out.println( estoque.get(cadastroNumero).toString());
                    System.out.print(" " );
                    //Solicita quantidade a ser alterada
                        Scanner entrada = new Scanner(System.in);                    
                   	do{
				System.out.print("Quantidade de "+ operação + " " + novoItem.unidade() +" : ");
				qnt = entrada.nextInt();				
				String mensagem = (qnt > 0) ? " OK" :" ERRO: A quantidade deve ser maior que zero! ";
                                System.out.println(mensagem);                                
			} while(qnt < 1);
                    // Aplica a quantidade informada de acordo com a operação solicitada
                    int novosaldo = 0;
                    if( operação == "ENTRADA" )
                             novosaldo = novoItem.Incrementar(qnt);
                    else
                            novosaldo = novoItem.Decrementar(qnt);
                    // Verifica saldo final antes de Solicitar confirmação
                    if( novosaldo < 0) {
                        System.out.println("Saldo insuficiente");                        
                        return(false); // retorna ao menu de movimentações
                    };
                    //Saldo não é negativo, prossegue exibindo novos valores e solicitando confirmação da operação
                        cabecalho("MOVIMENTAÇÃO - "+operação+" DE PRODUTO");                     
                    System.out.println("PRODUTO:" + novoItem.Nome());                    
                    System.out.println("QTDE ATUAL::" + estoque.get(cadastroNumero).quantidade());                    
                    System.out.println("QTDE " + operação +": "+ qnt);                    
                    System.out.println("QTDE FINAL:" + novoItem.quantidade() + " (" + novoItem.unidade() + ")");                    
                    //solicita confirmação ao usuário
                    boolean resposta = confirmarOperacao("ENTRADA");
                    if(resposta) { 
                        estoque.get(cadastroNumero).atualizaCadastro(novoItem.preço(), novoItem.unidade(), novoItem.quantidade());
                        };  // caso SIM, Atualiza dados do Item no estoque
                    return(resposta);
            }
            else {
                    System.out.println("!! O nome informado não foi localizado !!");
            return(false);
            }                
        }
    
// =============================================== movimentações
    public static void movimentações(ArrayList<Produto> estoque){
    int opcao;
    Scanner entrada = new Scanner(System.in);
        do{
            menuMovimentacoes();
            opcao = entrada.nextInt();

            switch(opcao){
            case 1: // Incremento de ENTRADA
                    do{    // Chama procedimento INCREMENTAR quantidade, que retorna TRUE/FALSE
                            if( movimentarProduto(estoque ,"+") )
                                    System.out.println("Ok - Quantidade atualizada no cadastro!");
                            else
                                    System.out.println("Nenhuma alteração foi realizada!");
                    } while(repetirOperacao());
                    break;
            case 2: // Decremento de Saída               
                    do{    // Chama procedimento DECREMENTAR quantidade, que retorna TRUE/FALSE
                            if( movimentarProduto(estoque ,"-") )
                                    System.out.println("Ok - Quantidade atualizada no cadastro!");
                            else
                                    System.out.println("Nenhuma alteração foi realizada!");
                    } while(repetirOperacao());
                    break;             
            default:
                System.out.println("Opção inválida.");
            }
        } while(opcao != 0);
    }//Movimentações
// =============================================== reajustes - SubOpções
        public static boolean reajustarProduto(ArrayList<Produto> estoque){
    
                cabecalho("REAJUSTE");        
                
            Produto novoItem = solicitarDadosdoProduto(true,false,false,false);
            int cadastroNumero = retornaPosicaoDoProduto(estoque ,novoItem.Nome());
            if(  cadastroNumero >= 0 ) {
                    // Copia dados do Vetor para o novoItem
                    novoItem.atualizaCadastro(estoque.get(cadastroNumero).preço(), estoque.get(cadastroNumero).unidade(), estoque.get(cadastroNumero).quantidade()); // pega a quantidade, Unidade e Preço atuais
                    //Exibe dados atuais de cadastro
                    System.out.print("DADOS ATUAIS DO CADASTRO:");
                    System.out.println( estoque.get(cadastroNumero).toString());
                    System.out.print(" " );
                    //Solicita o % a ser incrementado
                   float percentual = solicitarPercentualDeReajuste();
                    // Aplica a porcentagem informada no preço
                    novoItem.ReajustarPreço(percentual);
                    //Prossegue exibindo novos valores e solicitando confirmação da operação
                        cabecalho("REAJUSTE DE PREÇO DO PRODUTO");                     
                    System.out.println("PRODUTO:" + novoItem.Nome());                    
                    System.out.println("PREÇO ATUAL::" + estoque.get(cadastroNumero).preço());                    
                    System.out.println("REAJUSTE: " + percentual +"%");                    
                    System.out.println("NOVO PREÇO:" + novoItem.preço() );                    
                    //solicita confirmação ao usuário
                    boolean resposta = confirmarOperacao("REAJUSTE");
                    if(resposta) { 
                        estoque.get(cadastroNumero).atualizaCadastro(novoItem.preço(), novoItem.unidade(), novoItem.quantidade());
                        };  // caso SIM, Atualiza dados do Item no estoque
                    return(resposta);
            }
            else {
                    System.out.println("!! O nome informado não foi localizado !!");
            return(false);
            }                
        }
        public static boolean reajustarEstoque(ArrayList<Produto> estoque){
    
                cabecalho("REAJUSTE DE PREÇOS DO ESTOQUE");        
                
            if(  estoque.size() > 0 ) {
                // Estoque possui produtos cadastrados
                //Solicita o % a ser incrementado
                float percentual = solicitarPercentualDeReajuste();
                //solicita confirmação ao usuário                        
                boolean resposta = confirmarOperacao("REAJUSTE DE " + percentual +"% EM TODO O ESTOQUE");
                    if(resposta) { 
                             // Aplica a porcentagem informada nos preços
                            int i;
                            int n = estoque.size();
                                for (i=0; i<n; i++) {
                                    //Exibe dados atuais de cadastro
                                    System.out.print("Produto " + estoque.get(i).Nome() + " - Preço Atual: " + estoque.get(i).preço() );
                                    estoque.get(i).ReajustarPreço(percentual);
                                    System.out.println(" NOVO PREÇO: " + estoque.get(i).preço());
                                }                                         
                        };  // if caso SIM, Atualiza dados do Item no estoque
                    return(resposta);     // if caso NÂO, Abandona sem alterar dados
            }
            else {
                    System.out.println("!! O estoque está vazio - Sem produtos Cadastrados !!");
            return(false);
            }                
        }        
// =============================================== Reajuste
    public static void reajustes(ArrayList<Produto> estoque){
    int opcao;
    Scanner entrada = new Scanner(System.in);
        do{
            menuReajustes();
            opcao = entrada.nextInt();

            switch(opcao){
            case 1: // Reajuste de UM PRODUTO
                    do{    // Chama procedimento Reajustar UM PRODUTO, que retorna TRUE/FALSE
                            if( reajustarProduto(estoque) )
                                    System.out.println("Ok - O preço do produto foi reajustado!");
                            else
                                    System.out.println("Nenhuma alteração foi realizada!");
                    } while(repetirOperacao());
                    break;
            case 2: // Reajustar TODOS OS PRODUTOS               
                    do{    // Chama procedimento Reajustar TODOS PRODUTO, que retorna TRUE/FALSE
                            if( reajustarEstoque(estoque) )
                                    System.out.println("Ok - Os preços do estoque foram reajustados!");
                            else
                                    System.out.println("Nenhuma alteração foi realizada!");
                    } while(repetirOperacao());
                    break;             
            default:
                System.out.println("Opção inválida.");
            }
        } while(opcao != 0);
    }//Reajustes   
// =============================================== relatórios 
    public static void relatorio(ArrayList<Produto> estoque){
            cabecalho("RELATÓRIO DE ESTOQUE");
    int i;
    int n = estoque.size();
        for (i=0; i<n; i++) {
            //Exibe dados atuais de cadastro
             System.out.println("Posição " + i + ": " + estoque.get(i).toString());
        } 
    System.out.println(" ");
    }    

// =============================================== MENU PRINCIPAL      
    public static void main(String[] args) {
    // Criar o vetor de ESTOQUE par acadastro de produtos
    ArrayList<Produto> estoque = new ArrayList();
    
    int opcao;
    Scanner entrada = new Scanner(System.in);
        
        do{
            menuPrincipal();
            opcao = entrada.nextInt();
            
            switch(opcao){
            case 1:
                    cadastros(estoque);
                    break;
            case 2:
                    movimentações(estoque);
                    break;
            case 3:
                    reajustes(estoque);
                    break;
            case 4:
                    relatorio(estoque);
                    break;
            default:
                System.out.println("Opção inválida.");
            }
        } while(opcao != 0);
        
    } // main
    
}
