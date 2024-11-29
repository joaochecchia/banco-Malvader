#include <stdio.h>
#include <stdlib.h>

typedef struct node{
    int numero;
    struct node* prox;
    struct node* prev;
    }node;

    void push(node** h, node** t, int numero, int* tamanho){
        node* novo = (node*)malloc(sizeof(node));
        
        if(novo == NULL){
          printf("Erro ao alocar memoria\n");
          
          return;
        }
        
        novo -> numero = numero;
        novo -> prox = NULL;
        novo -> prev = NULL;
        
        if(*h == NULL){
            *h = novo;
            *t = novo;
        } else{
            node* aux = *t;
            
            aux -> prox = novo;
            novo -> prev = aux;
            
            *t = novo;
        }
        
        *tamanho += 1;
    }
    
    void pop(node** t, node** h, int* tamanho){
        if(*t == NULL){
            printf("A lista esta vazia");
            
            return;
        }
        
        if (*t == *h) { 
            free(*t);
            *h = NULL;
            *t = NULL;
        }else{
        
            node* aux = *t;
            
            aux = aux -> prev;
            aux -> prox = NULL;
            
            free(*t);
        }
        
        *tamanho -= 1;
    }
    
    int indexOf(node* h, node* t, int num, int* tamanho){
        if(h -> numero == num){
            return 0;
        }else if(t -> numero == num){
            return *tamanho - 1;
        } else {
            int index = 0;
            node* aux = h;
            
            while(aux -> numero != num){
                aux = aux -> prox;
                index++;
            }
            
            return index;
            
        } 
    }
    
    node* getAt(node* h,node* t, int index, int tamanho){
        if(tamanho == 0){
            printf("A lista esta vazia\n");
            
            return NULL;
        }
        
        if(tamanho / 2 > index){
            node* aux = h;
            
            for(int i = 0; i < index; i++){
                aux = aux -> prox;
            }
            
            return aux;
        }else{
            node* aux = t;
            
            for(int i = tamanho - 1; i > index; i--){
                aux = aux -> prev;
            }
            
            return aux;
        }

    }
    
    void putAt(node* h, node* t, int index, int num ,int *tamanho) {
        if(*tamanho == 0 || index > *tamanho - 1 || index < 0){
            return;
        }
        
        node* novo = (node*)malloc(sizeof(node));
        
        novo -> numero = num;
        novo -> prox = NULL;
        novo -> prev = NULL;
        
        if(index == *tamanho - 1){
            push(&h, &t, num, tamanho);
            
        } else if(index == 0){
            
            h-> prev = novo;
            novo -> prox = h;
            h = novo;
        } else{
            node* aux = getAt(h, t, index, *tamanho);
            node* anterior = aux -> prev;
            
            anterior -> prox = novo;
            
            novo -> prev = anterior;
            novo -> prox = aux;
            
            aux -> prev = novo;
        }
    }
    
    void removeAt(node** h, node** t, int index, int* tamanho){
        if(*tamanho == 0 || index > *tamanho - 1 || index < 0){
            
            return;
        }
        
        if(*h == *t){
            free(*h);
            
            *h = NULL;
            *t = NULL;
        }else if(index == 0){
            printf("pasei aqadasdasdsa\n");
            
            node* aux = *h;
            
            (*h) = (*h) -> prox;
            
            free(aux);
        } else if(*tamanho - 1 == index){
            printf("pasei aq2\n");
            
            node* aux = *t;
            
            (*t) = (*t) -> prev;
            (*t) -> prox = NULL;
            
            free(aux);
        } else if(*tamanho  / 2 > index){
            printf("pasei aq3\n");
            
            node* aux = *h;
            node* anterior = NULL;
            node* proximo = NULL;
            
            for(int i = 0; i < index; i++){
                anterior = aux;
                aux = aux -> prox;
            }
            proximo = aux -> prox;
            
            anterior -> prox = proximo;
            proximo -> prev = anterior;
            
            free(aux);
        } else{
            printf("pasei aq4\n");
            
            node* aux = *t;
            node* anterior = NULL;
            node* proximo = NULL;
            
            for(int i = *tamanho - 1; i > index; i--){
                proximo = aux;
                aux = aux -> prev;
            }
            anterior = aux -> prev;
            
            proximo -> prev = anterior;
            anterior -> prox = proximo;
            
            free(aux);
        }
        
        (*tamanho)--;
    }
    
    void clear(node** h, node** t){
        if(*h == NULL){
            printf("A lista está vazia.");
            
            return;
        }
        
        if(*h == *t){
            free(*h);
            *t = NULL;
            *h = NULL;
        } else{
            
            while(*h != NULL){
                node* aux = *h;
                (*h) = (*h)-> prox;
                free(aux);
            }
        }
    }
    
    void percorrer(node* h){
        if(h == NULL){
            printf("A lista está vazia");
            
            return;
        }
        
        while(h != NULL){
            printf("%d\n", h -> numero);
            
            h = h -> prox;
        }
    }
    
int main()
{   
    node* head = NULL;
    node* tail = NULL;
    int tamanho = 0;
    
    push(&head, &tail, 1, &tamanho);
    push(&head, &tail, 2, &tamanho);
    push(&head, &tail, 3, &tamanho);
    push(&head, &tail, 4, &tamanho);
    push(&head, &tail, 5, &tamanho);
    push(&head, &tail, 6, &tamanho);
    push(&head, &tail, 7, &tamanho);
    push(&head, &tail, 8, &tamanho);
    
    percorrer(head);
    
    printf("tamanho: %d\n", tamanho);
    
    printf("---------------------------------------");
    
    removeAt(&head, &tail, 6, &tamanho);
    
    percorrer(head);
    
    printf("tamanho: %d\n", tamanho);
    
    return 0;
}