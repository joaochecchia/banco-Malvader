#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int key;
    struct Node* direita;
    struct Node* esquerda;
}Node;

Node* min(Node* node) {
    if (node == NULL) {
        printf("A arvore esta vazia.\n");
        return NULL;
    }

    while (node->esquerda != NULL) {
        node = node->esquerda;
    }
    
    return node;
}

Node* max(Node* node){
    
    if(node == NULL){
        printf("A arvores esta vazia.\n");
        return NULL;
    }
    
    while(node -> direita != NULL){
        node = node -> direita;
    }
    
    return node;
}

void insertRecursivo(Node** node, int key){
    if(key < (*node) -> key){
        if((*node) -> esquerda == NULL){
        Node* novo = (Node*)malloc(sizeof(Node));
            
        if(novo == NULL){
            return;
        }
            
        novo -> key = key;
        novo -> direita = NULL;
        novo -> esquerda = NULL;
            
        (*node) -> esquerda = novo;
        } else{
            insertRecursivo(&(*node) -> esquerda, key);
        }
    }else{
        if((*node) -> direita == NULL){
            Node* novo = (Node*)malloc(sizeof(Node));
            
        if(novo == NULL){
            return;
        }
            
        novo -> key = key;
        novo -> direita = NULL;
        novo -> esquerda = NULL;
            
        (*node) -> direita = novo;
        } else{
            insertRecursivo(&(*node) -> direita, key);
        }
    }
}

int insert(Node** root, int key){
    if(*root != NULL){
        insertRecursivo(&(*root), key);
    } else{
        Node* novo = (Node*)malloc(sizeof(Node));
        
        if(novo == NULL){
            return -1;
        }
        
        novo -> key = key;
        novo -> direita = NULL;
        novo -> esquerda = NULL;
        
        *root = novo;
    }
    
    return 1;
}

void inOrder(Node* node){
    if(node != NULL){
        inOrder(node -> esquerda);
        printf("%d\n", node -> key);
        inOrder(node -> direita);
    }
}

void preOrder(Node* node){
    if(node != NULL){
        printf("%d\n", node -> key);
        preOrder(node -> esquerda);
        preOrder(node -> direita);
    }
}

void postOrder(Node* node){
    if(node != NULL){
        postOrder(node -> esquerda);
        postOrder(node -> direita);
        printf("%d\n", node -> key);
    }
}

Node* getRecursivo(Node* node, int key){
    if(node == NULL){
        printf("A arvore nao contem o valor.\n");
        return NULL;
    }
    
    if(key > node -> key){
        getRecursivo(node -> direita, key);
        
    } else if(key < node -> key){
        getRecursivo(node -> esquerda, key);
        
    } else{
        return node;
    }
}

Node* get(Node* root, int key){
    if(root == NULL){
        printf("A arvores esta vazia.\n");
        return 0;
    }
    
    return getRecursivo(root, key);
}

int removerRecusivo(Node** node, int key){
    if(*node == NULL){
        printf("Arvore nao contem a chave\n");
        return 0;
    }
    
    if(key > (*node) -> key){
        removerRecusivo(&(*node) -> direita, key);
    } else if(key < (*node) -> key){
        removerRecusivo(&(*node) -> esquerda, key);
    } else{
        if((*node) -> esquerda == NULL && (*node) -> direita == NULL){
            free(*node);
            *node = NULL;
            
            return 1;
        } else if((*node) -> direita == NULL){
            Node* aux = (*node);
            *node = aux -> esquerda;
            free(aux);
            
            return 1;
        } else if((*node) -> esquerda == NULL){
            Node* aux = (*node);
            *node = aux -> direita;
            free(aux);
            
            return 1;
        } else{
            Node* aux = min((*node)->direita);
            (*node)->key = aux->key;
            return removerRecusivo(&(*node)->direita, aux->key);
        }
    }
}

int remover(Node** root, int key){
    if(*root == NULL){
        printf("A Arvore esta vazia.\n");
        return 0;
    }
    
    return removerRecusivo(&(*root), key);
}



int main()
{
    Node* root = NULL;
    
    insert(&root, 5);
    insert(&root, 3);
    insert(&root, 7);
    insert(&root, 4);
    insert(&root, 8);
    insert(&root, 2);
    insert(&root, 1);

    remover(&root, 3);
    
    preOrder(root);
    
    return 0;
}