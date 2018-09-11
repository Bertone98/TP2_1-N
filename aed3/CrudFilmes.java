

import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

public class CrudFilmes {

    private static Scanner console = new Scanner(System.in);
    private static ArquivoIndexado<Filme> arqFilmes;
    private static CrudGeneros crudGen;


    public CrudFilmes(CrudGeneros crudGen) throws Exception{
        this.arqFilmes = new ArquivoIndexado<>(Filme.class.getConstructor(), "filme_dados.db", "filme_dados.idx");
        this.crudGen = crudGen;
    }


    public  void listarFilmes() throws Exception {

        Object[] obj = arqFilmes.listar();

        System.out.println("\nLISTA DE FILMES");
        for(int i=0; i<obj.length; i++) {
            System.out.println((Filme)obj[i]+"\n\n");
        }
        pausa();

   }

    public  void incluirFilme() throws Exception {

        Scanner input = new Scanner(System.in);
        String titulo,tituloOriginal,pais,diretor,sinopse;
        short ano;
        short min;
        int idGenero;
        Filme filme = null;

        System.out.print("Titulo: ");
        titulo = input.nextLine();

        System.out.print("Titulo Original: ");
        tituloOriginal = input.nextLine();

        System.out.print("Pais de origem: ");
        pais = input.nextLine();

        System.out.print("Diretor: ");
        diretor = input.nextLine();

        System.out.print("Sinopse: ");
        sinopse = input.nextLine();

        System.out.print("Ano: ");
        ano = input.nextShort();

        System.out.print("Minutos filme: ");
        min = input.nextShort();

        

        boolean isGenero = false;

        Genero obj =null;

        try{
            crudGen.listarGeneros();
            do{
                System.out.print("Id do Gênero do filme: ");
            
                idGenero = input.nextInt();

                obj = crudGen.buscarGenero(idGenero);

                if(obj == null)
                    System.out.println("Genero inválido!");
                else{
                    System.out.println("Genero : " + obj.getNome());
                    isGenero = true;
                }
                    

            }while(!isGenero);
        }catch(Exception e ){e.printStackTrace();}


        System.out.print("Insira 1 para confirma inclusão ou 0 para cancelar: ");
        if(input.nextByte() == 1)
            filme = new Filme(titulo,tituloOriginal,pais,ano,min,diretor,sinopse,obj.getId());
            int id = arqFilmes.incluir(filme);
            System.out.println("Filme incluído com ID: "+id);

        pausa();
    
    }

   /*
   public  void alterarFilme() throws Exception {

       System.out.println("\nALTERAÇÃO DE FILME");

       int id;
       System.out.print("ID do filme: ");
       id = Integer.valueOf(console.nextLine());
       if(id <=0)
           return;

       Filme obj;
       if( (obj = (Filme)arqFilmes.buscar(id))!=null ) {
            System.out.println(obj);

            System.out.print("Digite os dados do filme a ser inserido. \nTítulo: ");
            String titulo = console.nextLine();

            System.out.print(" \nTítulo_Original: ");
            String titulo_original = console.nextLine();

            System.out.print(" \nPaís: ");
            String pais = console.nextLine();

            System.out.print(" \nAno: ");
            Short ano = console.nextShort();

            System.out.print(" \nDuração: ");
            Short duracao = console.nextShort();

            console.nextLine();

            System.out.print(" \nDiretor: ");
            String diretor = console.nextLine();

            System.out.print(" \nSinopse: ");
            String sinopse = console.nextLine();

            System.out.print("\nTitulo: " + titulo
            + "\nTitulo Original: " + titulo_original
            + "\nPais: " + pais
            + "\nAno: " + ano
            + "\nDuração: " + duracao
            + "\nDiretor: " + diretor
            + "\nSinopse: " + sinopse + "\nEsses dados estão corretos? [s/n]: ");

            if(titulo.length()>0 || duracao>=0 || ano >=0) {
                System.out.print("\nConfirma alteração? ");
                char confirma = console.nextLine().charAt(0);
                if(confirma=='s' || confirma=='S') {

                obj.titulo = (titulo.length()>0 ? titulo : obj.titulo);
                obj.titulo_original = (titulo_original.length()>0 ? titulo_original : obj.titulo_original);
                obj.pais = (pais.length()>0 ? pais : obj.pais);
                obj.diretor = (diretor.length()>0 ? diretor : obj.diretor);
                obj.sinopse = (sinopse.length()>0 ? sinopse : obj.sinopse);
                obj.duracao = (duracao>=0?duracao:obj.duracao);
                obj.ano = (ano>=0?ano:obj.ano);

                if( arqFilmes.alterar(obj) )
                        System.out.println("Filme alterado.");
                    else
                        System.out.println("Filme não pode ser alterado.");
                }
            }
       }
       else
           System.out.println("Filme não encontrado");
       pausa();

   }*/

   /*
   public  void excluirFilme() throws Exception {

       System.out.println("\nEXCLUSÃO DE FILME");

       int id;
       System.out.print("ID do filme: ");
       id = Integer.valueOf(console.nextLine());
       if(id <=0)
           return;

       Filme obj;
       if( (obj = (Filme)arqFilmes.buscar(id))!=null ) {
            System.out.println(obj);

            System.out.print("\nConfirma exclusão? ");
            char confirma = console.nextLine().charAt(0);
            if(confirma=='s' || confirma=='S') {
                if( arqFilmes.excluir(id) ) {
                    System.out.println("Filme excluído.");
                }
            }
       }
       else
           System.out.println("Filme não encontrado");
       pausa();

   }*/

   /*
   public  void buscarFilme() throws Exception {

       System.out.println("\nBUSCA DE FILME POR CÓDIGO");

       int codigo;
       System.out.print("Código: ");
       codigo = Integer.valueOf(console.nextLine());
       if(codigo <=0)
           return;

       Filme obj;
       if( (obj = (Filme)arqFilmes.buscar(codigo))!=null )
           System.out.println(obj);
       else
           System.out.println("Filme não encontrado");
       pausa();

   }*/
   
    public  void pausa() throws Exception {
        System.out.println("\nPressione ENTER para continuar ...");
        console.nextLine();
    }

    /*
    public int  verificarId() throws Exception{
        boolean resp = false;
        int id = 0;
        crudGen.listarGeneros();


        System.out.println("Digite o código do genero: ");
        id = Integer.valueOf(console.nextLine());

        Genero obj;

        if((obj = crudGen.buscarGenero(id) ) != null)
            System.out.println(b);
        else
            id = -1;

        return id;

    }*/


}
