#include <stdio.h>
#include <stdlib.h>

typedef struct pilha{
    int numero;
    struct pilha* prox;

}pilha;

void push(pilha** p, int num){
    pilha* novo = (pilha*)malloc(sizeof(pilha));
    
    if(novo == NULL){
        printf("Erro ao alocar memoria.");
        
        return;
    }
    
    novo->numero = num;
    novo->prox = NULL;
    
    if(*p == NULL){
        *p = novo;
    } else{
        pilha* aux = *p;
        
        while(aux-> prox != NULL){
            aux = aux-> prox;
        }
        
        aux-> prox = novo;
    }
    
}

void pop(pilha* p){
    if(p == NULL){
        printf("A pilha esta vazia.");
        
        return;
    }
    
    pilha* aux;
    
    while(p-> prox != NULL){
        aux = p;
        p = p-> prox;
    }
    
    if(aux-> prox == NULL){
        free(aux);
    } else{
        aux-> prox = NULL;
        free(p);
    }
    
    
}

int peek(pilha* p){
    if(p == NULL){
        printf("A pilha esta vazia.\n");
        
        return -1;
    }
    
    while(p-> prox != NULL){
        p = p-> prox;
    }
    
    return p-> numero;
}

int size(pilha* p){
    int tamanho = 0;
    
    if(p == NULL){
        
        return 0;
    }
    
    while(p != NULL){
        p = p-> prox;
        tamanho++;
    }
    
    return tamanho;
}

void clear(pilha** p) {
    if (*p == NULL) {
        return;
    }
    
    pilha* aux;
    
    while (*p != NULL) {
        aux = *p;
        *p = (*p)->prox;
        free(aux);
    }
    
    *p = NULL;
}

void imprimir(pilha* p) {
    if(p == NULL){
        printf("A pilha está vazia.\n");
        
        return;
    }
    
    while(p != NULL){
        printf("%d ", p->numero);
        p = p->prox;
    }
    printf("\n");
}

int main(){
    
    pilha* inicio = NULL;
    
    int x = 10;
    int resto;
    
    while(x > 0){
        resto = x % 2;
        x /= 2;
        
        push(&inicio, resto);
    }
    
    int tamanho = size(inicio);
    
    for(int i = 0; i < tamanho; i++){
        printf("%d ", peek(inicio));
        pop(inicio);
        
    }
}
