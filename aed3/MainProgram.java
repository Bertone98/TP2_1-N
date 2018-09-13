/*
 * Autores: Tulio N. Polido Lopes, Joao Victor da Silva, Gustavo Lescowicz Kotarsky, Temistocles Altivo Schwartz
 * Data: 21/08/2018
 * */


import java.io.*;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

//import aed3.ArquivoIndexado;
//import aed3.CrudGeneros;

public class MainProgram {
	
	private static Scanner console;
	private static CrudFilmes crudF;
	private static CrudGeneros crudG;

	public static void main(String[] args) {
		console = new Scanner(System.in);	
		int opcao = -1;

		RandomAccessFile arq;

		try{
			crudG = new CrudGeneros();
			crudF = new CrudFilmes(crudG);
		}catch(Exception e){e.printStackTrace();}

		System.out.println("Bem-vindo ao CRUD de filmes!");

			int id;

			while(opcao != 0) {
				System.out.print("\n\n\n-----GESTOR DE FILMES-----\n"+
				"0 - Finalizar programa\n"+
				"1 - Gerenciar filmes\n"+
				"2 - Gerenciar Generos\n"+
				"Inserir : ");
				opcao = Integer.valueOf(console.nextLine());
				if( opcao == 1 )
					MenuFilme();
				else if(opcao == 2){
					ViewGenero viewGen = new ViewGenero();
					viewGen.menu();
				}
					
				//TRATAR EXCEÇÕES
			}		
				
	}//end main()

	private static void MenuFilme(){
		byte choice = -1;

		try{
			while(choice != 0 ){
				System.out.println("Menu:\n"+
				"0 - Sair;\n"+
				"1 - Lista Filmes\n"+
				"2- Incluir filme\n"+
				"3 - Alterar filme;\n"+
				"4 - Excluir filme;\n"+
				"5 - Consultar filme;");
				
				choice = console.nextByte();
				int id;
	
				switch(choice) {
						case 0:
							System.out.println("Obrigado por utilizar o CRUD de filmes!");
							break;
						case 1:
							System.out.println("\t\t---Lista de filmes--\n");
							Filme[] listaF = crudF.listarFilmes();

							for(int i = 0; i < listaF.length;i++)
								System.out.println(listaF[i]+"\n\n");

							break;
						case 2:	
							Filme fil = criarObjetoFilme();
							if(fil!=null)
								crudF.incluirFilme(fil);
							break;
						case 3:
							System.out.println("Insira o ID do filme a ser alterado: ");
							break;
						case 4:
							crudF.excluirFilme();
							break;
						case 5 :
							crudF.buscarFilme();
						break;
						default:
							System.out.println("Opção inválida!");
							break;
						}
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/*
	private static void MenuGenero(){
		console = new Scanner(System.in);
		try {
            // menu
           int opcao;
           do {
               System.out.println("\n\nGESTÃO DE GENEROS");
               System.out.println("-----------------------------\n");
               System.out.println("1 - Listar");
               System.out.println("2 - Incluir");
               System.out.println("3 - Alterar");
               System.out.println("4 - Excluir");
               System.out.println("5 - Buscar");
               System.out.println("0 - Sair");
               System.out.print("\nOpcao: ");
               opcao = console.nextInt();
			  
               switch(opcao) {
					case 1:
						
						Genero[] listaGen = crudG.listarGeneros();
						for(int i = 0; i < listaGen.length; i++)
							System.out.println(listaGen[i]+"\n\n");
				   	break;
					case 2:
						objGen = criaObjetoGenero();
						if(objGen!=null) 
							crudG.incluirGenero(objGen);  
					break;
                   case 3:crudG.alterarGenero() ; break;
				   case 4:						
			
				  		System.out.println("\nEXCLUSÃO DE GENERO");
						System.out.print("ID do Genero: ");

						id = console.nextInt();
						if(id > 0){
							objGen = crudG.buscarGenero(id);
							if(objGen != null){
								System.out.print("\nConfirma exclusão? 1 - sim ou 0 - não ");
								int confirma = console.nextInt();
								if(confirma=='s' || confirma=='S'){
									if(crudG.excluirGenero(id));
										System.out.println("\nGenero excluído : Nome = "+objGen.getNome());	
								}
								
							}
							else
								System.out.println("\nGenero não encontrado!");

						}
						else
							System.out.println("ID inválido!\n"); 
					break;
				   case 5:
					
						System.out.print("ID do genero a ser busca :");
						id = console.nextInt();
							
						if(id > 0){
							objGen = crudG.buscarGenero(id);
							if(objGen!=null)
								System.out.println(objGen);
							else
								System.out.println("Genero não encontrado!");	
						}
						else
							System.out.println("ID inválido!");
					
					break;		

				   case 0: 
				   break;
                   default: System.out.println("Opção inválida");
               }

           } while(opcao!=0);
       } catch(Exception e) {
           e.printStackTrace();
       }
	}*/

	public static Filme criarObjetoFilme(){

		Scanner console = new Scanner(System.in);
        String titulo,tituloOriginal,pais,diretor,sinopse;
        short ano;
        short min;
        int idGenero;
        Filme filme = null;

        System.out.print("Titulo: ");
        titulo = console.nextLine();

        System.out.print("Titulo Original: ");
        tituloOriginal = console.nextLine();

        System.out.print("Pais de origem: ");
        pais = console.nextLine();

        System.out.print("Diretor: ");
        diretor = console.nextLine();

        System.out.print("Sinopse: ");
        sinopse = console.nextLine();

        System.out.print("Ano: ");
        ano = console.nextShort();

        System.out.print("Minutos filme: ");
        min = console.nextShort();

		boolean isGenero = false;

        Genero obj =null;

        try{
            crudG.listarGeneros();
            do{
                System.out.print("Id do Gênero do filme: ");
            
                idGenero = console.nextInt();

                obj = crudG.buscarGenero(idGenero);

                if(obj == null)
                    System.out.println("Genero inválido!");
                else{
                    System.out.println("Genero : " + obj.getNome());
                    isGenero = true;
                }
                    

            }while(!isGenero);
        }catch(Exception e ){e.printStackTrace();}
		
		System.out.print("Insira 1 para confirma inclusão ou qualquer coisa para cancelar: ");
        if(console.nextByte() == 1)
			filme = new Filme(titulo,tituloOriginal,pais,ano,min,diretor,sinopse,obj.getId());
		
		return filme;
	}


}
