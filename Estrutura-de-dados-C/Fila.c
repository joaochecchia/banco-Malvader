#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct fila{
    char nome[30];
    struct fila* prox;
    
}fila;

void enqueue(fila** p, char entrada[30]){
    fila* novo = (fila*)malloc(sizeof(fila));
    
    if (novo == NULL) {
        printf("Erro ao alocar memória.\n");
        
        return;
    }
    
    strcpy(novo-> nome, entrada);
    novo-> prox = NULL;
    
    if(*p == NULL){
        *p = novo;
        
        return;
    } else{
        fila* temp = *p;
        
        while(temp-> prox != NULL){
            temp = temp-> prox;
        }
        
        temp-> prox = novo;
    }
    
}

void dequeue(fila** p){
    if(*p == NULL){
        printf("A fila esta vazia\n");
        
        return;
    }
    
    fila* aux = *p;
    
    *p = (*p)-> prox;
    free(aux);
}

char* peek(fila* p){
    if (p == NULL) {
        printf("A fila está vazia.\n");
        return NULL;
    }
    
    return p->nome;
}


void imprimir(fila* p){
    if(p == NULL){
        printf("Fila está vazia.");
        
        return;
    }
    
    fila* aux = p;
    
    while(aux != NULL){
        printf("%s\n", aux-> nome);
        aux = aux->prox;
    }
}

void batataQuente(fila** p, int num) {
    if (*p == NULL) {
        printf("A fila está vazia.\n");
        return;
    }

    while ((*p)->prox != NULL) {
        
        for (int i = 0; i < num - 1; i++) {
            fila* temp = *p;
            while (temp->prox != NULL) {
                temp = temp->prox;
            }
            temp->prox = *p; 
            *p = (*p)->prox;
            temp->prox->prox = NULL;
        }

        printf("Eliminado: %s\n", peek(*p));
        dequeue(p);
    }

    printf("Vencedor: %s\n", peek(*p));
}

int main(){
    fila* inicio = NULL;
    
    char nome1[30] = "joao";
    char nome2[30] = "julia";
    char nome3[30] = "ana";
    char nome4[30] = "maria";
    char nome5[30] = "leo";
    char nome6[30] = "rebeca";
    
    enqueue(&inicio, nome1);
    enqueue(&inicio, nome2);
    enqueue(&inicio, nome3);
    enqueue(&inicio, nome4);
    enqueue(&inicio, nome5);
    enqueue(&inicio, nome6);
    
    batataQuente(&inicio, 10);

    return 0;
}
