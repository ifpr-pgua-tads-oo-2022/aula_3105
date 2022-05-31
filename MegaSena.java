
import java.util.Random;
import java.util.Scanner;

public class MegaSena {
    
    //declaração de uma constante especificando o máximo de escolhas
    public static final int MAX_ESCOLHAS=6;

    //método para verificar se um valor está entre o mínimo e o máximo
    public static boolean valido(int valor, int min, int max){
        return valor >= min && valor <= max;
        
        /*código alternativo
        if(valor >= min && valor <= max){
            return true;
        }else{
            return false;
        }
        */
    }

    //método para verificar se um valor está dentro de um vetor
    public static boolean repetido(int valor, int[] vetor){

        for(int i=0;i<vetor.length;i++){
            if(vetor[i] == valor){
                System.out.println("Repetido!!");
                return true;
            }
        }

        return false;
    }

    //método para ler as escolhas do usuário
    public static int[] escolhasUsuario(int qtde){
        int escolha;
        int[] escolhas;
        escolhas = new int[qtde];

        Scanner scan = new Scanner(System.in);

        for(int i=0;i<qtde;i++){
            System.out.println("Digite um número entre 1 e 60:");
            escolha = scan.nextInt();
            while(!valido(escolha,1,60) || 
                   repetido(escolha, escolhas)){
                if(escolha < 1 || escolha > 60){
                    System.out.printf("O %d é inválido!\n",escolha);
                }
                System.out.println("Digite um número entre 1 e 60:");
                escolha = scan.nextInt();
            }  
            escolhas[i] = escolha;
        }

        return escolhas;

    }

    //método para sortear os números pelo computador
    public static int[] sorteioComputador(int qtde){
        int valor;
        int[] sorteados = new int[qtde];
        Random rnd = new Random();

        for(int i=0;i<qtde;i++){
            valor = rnd.nextInt(60)+1;
            while(repetido(valor, sorteados)){
                valor = rnd.nextInt(60)+1;
            }
            sorteados[i] = valor;
        }

        return sorteados;
    }

    //método para calcular a quantidade de acertos do usuário
    public static int calculaAcertos(int[] escolhas, 
                                 int[] sorteados){
        int total = 0;
        
        for(int i=0;i<escolhas.length;i++){
            for(int j=0;j<sorteados.length;j++){
                if(escolhas[i] == sorteados[j]){
                    total += 1;
                }
            }
        }

        return total;
    }

    //método auxiliar para imprimir o conteúdo de um vetor na tela
    public static void imprimeVetor(int[] vetor){
        for(int i=0;i<vetor.length;i++){
            System.out.print(vetor[i]+" ");
        }
    }

    public static void main(String[] args){
        int[] escolhas = escolhasUsuario(MAX_ESCOLHAS);
        int[] sorteados = sorteioComputador(MAX_ESCOLHAS);
        int acertos = 0;

        System.out.println("Escolhas do usuário:");
        imprimeVetor(escolhas);
        System.out.println("Números sorteados:");
        imprimeVetor(sorteados);

        acertos = calculaAcertos(escolhas, sorteados);

        switch(acertos){
            case 4:
                System.out.println("Acertou a quadra!");
            break;
            case 5:
                System.out.println("Acertou a quina!");
            break;
            case 6:
                System.out.println("Acertou a sena!");
            break;
            default:
                System.out.println("Não foi dessa vez!");
            break;
        }

        /*
        código alternativo
        if(acertos == 4){
            System.out.println("Acertou a quadra!");
        }else if(acertos == 5){
            System.out.println("Acertou a quina!");
        }else if(acertos == 6){
            System.out.println("Acertou a sena!!!");
        }else{
            System.out.println("Não foi dessa vez!");
        }*/

        



    }
}
