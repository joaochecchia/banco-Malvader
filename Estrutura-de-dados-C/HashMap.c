#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define TAM_MAX 20

typedef struct NO {
    char nome[30];
    int telefone;
    struct NO* prox;
} NO;

int hashLoseLose(char nome[30]) {
    int hash = 0;

    for (int i = 0; i < 30 && nome[i] != '\0'; i++) {
        hash += nome[i];
    }

    return hash % 20;
}

int hasKey(NO* HASHMAP[TAM_MAX], char chave[30]) {
    int hash = hashLoseLose(chave);
    NO* ponteiro = HASHMAP[hash];
    
    while (ponteiro != NULL) {
        if (strcmp(chave, ponteiro->nome) == 0) {
            return 1;
        }
        ponteiro = ponteiro->prox;
    }
    
    return 0;
}

int set(NO* HASHMAP[TAM_MAX], char chave[30], int valor) {
    NO* ponteiro = (NO*)malloc(sizeof(NO));
    
    if (ponteiro == NULL) {
        printf("Erro ao alocar memória\n");
        return 0;
    }
    
    strcpy(ponteiro->nome, chave);
    ponteiro->telefone = valor;
    ponteiro->prox = NULL;
    
    int hash = hashLoseLose(chave);
    
    if (HASHMAP[hash] == NULL) {
        HASHMAP[hash] = ponteiro;
    } else {
        NO* percorrer = HASHMAP[hash];
        
        while (percorrer->prox != NULL) {
            percorrer = percorrer->prox;
        }
        
        percorrer->prox = ponteiro;
    }
    
    return 1;
}

NO* get(NO* HASHMAP[TAM_MAX], char chave[30]) {
    int hash = hashLoseLose(chave);
    NO* ponteiro = HASHMAP[hash];
    
    while (ponteiro != NULL) {
        if (strcmp(chave, ponteiro->nome) == 0) {
            return ponteiro;
        }
        ponteiro = ponteiro->prox;
    }
    
    return NULL;
}

int remove_(NO* HASHMAP[TAM_MAX], char chave[30]) {
    int hash = hashLoseLose(chave);
    NO* atual = HASHMAP[hash];
    NO* anterior = NULL;

    while (atual != NULL) {
        if (strcmp(chave, atual->nome) == 0) {
            if (anterior == NULL) {

                HASHMAP[hash] = atual->prox;
            } else {

                anterior->prox = atual->prox;
            }

            free(atual);
            return 1; 
        }

        anterior = atual;
        atual = atual->prox;
    }

    return 0;
}


int main() {
    NO* HASHMAP[TAM_MAX] = {NULL};
    
    char a[30] = "joao";
    char b[30] = "jooa";
    char c[30] = "ooja";
    char d[30] = "jaoo";
    
    set(HASHMAP, a, 3123123);
    set(HASHMAP, b, 3123123);
    set(HASHMAP, c, 3123123);
    set(HASHMAP, d, 3123123);
    
    char e[30] = "oo";
    

    NO* resultado2 = get(HASHMAP, a);
    NO* resultado3 = get(HASHMAP, b);
    NO* resultado4 = get(HASHMAP, c);
    NO* resultado5 = get(HASHMAP, d);
    
    remove_(HASHMAP, a);
    
    //if (resultado2) printf("%s\n", resultado2->nome);
    //if (resultado3) printf("%s\n", resultado3->nome);
    //if (resultado4) printf("%s\n", resultado4->nome);
    //if (resultado5) printf("%s\n", resultado5->nome);
    //else printf("Chave 'oo' não encontrada\n");
    
    return 0;
}
