#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX_ESCOLHAS 6


int valido(int num, int min, int max){
    return num >= min && num <= max;
}

int repetido(int num, int* vetor, int qtde){

    for(int i=0;i<qtde;i++){
        if(vetor[i]==num){
            return 1;
        }
    }

    return 0;
}


int* sorteioComputador(int qtde){

    int* numeros = calloc(qtde,sizeof(int));
    int num;

    for(int i=0;i<qtde;i++){
        
        num = (rand()%60) + 1;
        while(!valido(num,1,60) || repetido(num,numeros,qtde)){
            num = rand()%60;    
        }

        numeros[i] = num;
    } 

    return numeros;
}

int* escolhasUsuario(int qtde){

    int* escolhas = calloc(qtde,sizeof(int));
    int escolha;

    for(int i=0;i<qtde;i++){
        printf("Digite um número entre 1 e 60:");
        scanf("%d",&escolha);
        while(!valido(escolha,1,60) || repetido(escolha,escolhas,qtde)){
            printf("Número inválido!\n");
            printf("Digite um número entre 1 e 60:");
            scanf("%d",&escolha);
        }
        escolhas[i] = escolha;
    }
    return escolhas;
}

void imprimeVetor(int* vetor, int qtde){
    printf("[");
    for(int i=0;i<qtde;i++){
        printf("%d ",vetor[i]);
    }
    printf("]\n");
}

int calculaAcertos(int* escolhas, int* sorteio, int qtde){

    int acertos = 0;

    for(int i=0;i<qtde;i++){
        for(int j=0;j<qtde;j++){
            if(escolhas[i] == sorteio[j]){
                acertos += 1;
                break;
            }
        }
    }

    return acertos;
}

int main(){
    srand(time(NULL));

    int* sorteados = sorteioComputador(MAX_ESCOLHAS);
    imprimeVetor(sorteados,MAX_ESCOLHAS);

    int* escolhas = escolhasUsuario(MAX_ESCOLHAS);
    imprimeVetor(escolhas,MAX_ESCOLHAS);

    int acertos = calculaAcertos(escolhas,sorteados,MAX_ESCOLHAS);

    printf("Acertos: %d",acertos);

}