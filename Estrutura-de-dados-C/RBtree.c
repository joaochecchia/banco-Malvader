#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Node{
    int key;
    char cor[6];
    struct Node* pai;
    struct Node* esquerda;
    struct Node* direita;
    
}Node;

void inOrderTraverse(Node* node){
    if(node != NULL){
        inOrderTraverse(node -> esquerda);
        printf("%d e %s\n", node -> key, node -> cor);
        inOrderTraverse(node -> direita);
    }
}

void preOrderTraverse(Node* node){
    if(node != NULL){
        printf("%d e %s\n", node -> key, node -> cor);
        preOrderTraverse(node -> esquerda);
        preOrderTraverse(node -> direita);
    }
}

void posOrderTraverse(Node* node){
    if(node != NULL){
        posOrderTraverse(node -> esquerda);
        posOrderTraverse(node -> direita);
        printf("%d e %s\n", node -> key, node -> cor);
    }
}

Node* min(Node* node){
    if(node != NULL && node -> esquerda != NULL){
        return min(node -> esquerda);
    }
    
    return node;
}

Node* createNode(int key, char cor[5], Node* pai){
    Node* novo = (Node*)malloc(sizeof(Node));
    
    novo -> key = key;
    strcpy(novo -> cor, cor);
    novo -> pai = pai;
    novo -> esquerda = NULL;
    novo -> direita = NULL;

    return novo;
}

void rotacaoLL(Node** node){
    if((*node) != NULL){
       Node* novoPai = (*node) -> esquerda;
       Node* paiDoOriginal = (*node) -> pai;
       
       (*node) -> esquerda = novoPai -> direita;
       
       if(novoPai -> direita != NULL){
           novoPai -> direita -> pai = (*node);
       }
       
       novoPai -> direita = (*node);
       novoPai -> pai = paiDoOriginal;
       (*node) -> pai = novoPai;
       
       if(novoPai -> pai != NULL){
           if (paiDoOriginal->esquerda == *node) {
                paiDoOriginal->esquerda = novoPai;
            } else {
                paiDoOriginal->direita = novoPai;
            }
       }
       
       *node = novoPai;
    }
}

void rotacaoRR(Node** node){
    Node* novoPai = (*node) -> direita;
    Node* paiDoOriginal = (*node) -> pai;
    
    (*node) -> direita = novoPai -> esquerda;
    
    if(novoPai -> esquerda != NULL){
        novoPai -> esquerda -> pai = (*node);
    }
    
    novoPai -> esquerda = (*node);
    novoPai -> pai = paiDoOriginal;
    (*node) -> pai = novoPai;
    
    if(novoPai -> pai != NULL){
        if(paiDoOriginal -> esquerda == (*node)){
            paiDoOriginal -> esquerda = novoPai;
        } else{
            paiDoOriginal -> direita = novoPai;
        }
    }
    
    (*node) = novoPai;
}

void rotacaoLR(Node* node){
    rotacaoLL(&node -> direita);
    rotacaoRR(&node);
}

void rotacaoRl(Node* node){
    rotacaoRR(&node -> esquerda);
    rotacaoLL(&node);
}

void recoloringRecursivo(Node* node){
    while(node != NULL && node -> pai != NULL&& strcmp(node -> cor, "RED") == 0 
    && strcmp(node -> pai -> cor, "RED") == 0){
        Node* pai = node -> pai;
        Node* avo = pai -> pai;
        
        if(pai == avo -> esquerda){
            Node* tio = avo -> direita;
            
            if(strcmp(tio -> cor, pai -> cor) == 0){
                strcpy(pai->cor, "BLACK");
                strcpy(tio->cor, "BLACK");
                strcpy(avo->cor, "RED");
                
            } else{
                if(node == pai->direita){
                    rotacaoLR((node));
                    node = node->esquerda;
                } else{
                    rotacaoLL(&node);
                }
            }
        } else{
            Node* tio = avo -> esquerda;
            
            if(strcmp(tio -> cor, pai -> cor) == 0){
                strcpy(avo -> cor, "RED");
                strcpy(tio -> cor, "BLACK");
                strcpy(pai -> cor, "BLACK");
                
            } else{
                if(node == pai->esquerda){
                    rotacaoRl((node));
                    node = node->direita;
                } else{
                    rotacaoRR(&node);
                }
            }
        }
    }
    
    while(node -> pai != NULL){
        node = node -> pai;
    }
    
    strcpy(node -> cor, "BLACK");
}

void removeRecursivo(Node** node ,int key){
    if(*node == NULL){
        printf("A arvore esta vazia.\n");
        
        return;
    }
    
    if((*node) -> key > key){
        removeRecursivo(&(*node) -> esquerda, key);
    } else if((*node) -> key < key){
        removeRecursivo(&(*node) -> direita, key);
    }else{
        
        if((*node) -> esquerda == NULL && (*node) -> direita == NULL){
            free(*node);
            *node = NULL;
        } else if((*node) -> esquerda == NULL){
            Node* aux = (*node);
            (*node) = (*node) -> esquerda;
            (*node) -> pai = aux -> pai;
            
            free(aux);
        } else if((*node) -> direita == NULL){
            Node* aux = (*node);
            (*node) = (*node) -> esquerda;
            (*node) -> pai = aux -> pai;
            
            free(aux);
        } else{
            Node* aux = min((*node)->direita); 
            (*node)->key = aux->key; 
            strcpy((*node)->cor, aux->cor); 
            removeRecursivo(&(*node)->direita, aux->key);
        }
    }
}

int insertRecursivo(Node** node, int key){
    if((*node) -> key > key){
        if((*node) -> esquerda == NULL){
            char cor[6] = "RED";
            
            (*node) -> esquerda = createNode(key, cor, (*node));
        } else{
            insertRecursivo(&(*node) -> esquerda, key);
        }
    } else{
        if((*node) -> direita == NULL){
            char cor[6] = "RED";
            
            (*node) -> direita = createNode(key, cor, (*node));
        } else{
            insertRecursivo(&(*node) -> direita, key);
        }
    }
    
    recoloringRecursivo((*node));
    
    return 1;
}

int insert(Node** root, int key){
    if(*root == NULL){
        char cor [6] = "BLACK";
        
        (*root) = createNode(key, cor, NULL);
        
        return 1;
    }
    
    insertRecursivo(&(*root), key);
    
    return 1;
}

int main(){
    Node* root = NULL;
    
    insert(&root, 5);
    
    insert(&root, 3);
    insert(&root, 8);
    insert(&root, 6);
    insert(&root, 7);
    insert(&root, 2);
    
    inOrderTraverse(root);
    
    return 0;
}