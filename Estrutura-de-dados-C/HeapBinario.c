#include <stdio.h>
#include <stdlib.h>

int getLeftIndex(int index){
    return index * 2 + 1;
}

int getRightIndex(int index){
    return index * 2 + 2;
}

int getIndexPai(int index){
    return (index - 1) / 2;
}

void shiftUP(int *heap, int tamanho){
    int indexPai = getIndexPai(tamanho - 1);
    int valor = heap[tamanho - 1];
    
    while(indexPai > 0 && valor < heap[indexPai]){
        int temp = heap[indexPai];
        heap[indexPai] = valor;
        heap[tamanho - 1] = temp;
        
        tamanho--;
        indexPai = getIndexPai(tamanho - 1);
    }
}

void insert(int* tamanho, int numero, int* heap){
    if(heap[0] == 0){
        printf("O heap esta vazio.");
        return;
    }
    
    heap[*tamanho] = numero;
    
    shiftUP(heap, *tamanho);
    *tamanho ++;
}

int* criarHeap(int tamanho, int sizeHeap, int raiz){
    if(tamanho == 0){
        return NULL;
    }
    
    int* heap = (int*)calloc(tamanho, (sizeof(int)));
    
    if(heap == NULL){
        printf("Erro de alocacao de memoria.");
        return NULL;
    }
    
    heap[0] = raiz;
    
    return heap;
}

int main(){
    
    return 0;
}