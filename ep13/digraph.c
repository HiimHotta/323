/*
 * MAC0323 Algoritmos e Estruturas de Dados II
 * 
 * ADT Digraph implementada atrevés de vetor de listas de adjacência.
 * As listas de adjacência são bag de ints que são mais restritos 
 * que as bags genéricas do EP12. Veja a api bag.h e simplifique 
 * o EP12 de acordo. 
 *  
 * Busque inspiração em: 
 *
 *    https://algs4.cs.princeton.edu/42digraph/ (Graph representation)
 *    https://algs4.cs.princeton.edu/42digraph/Digraph.java.html
 * 
 * DIGRAPH
 *
 * Digraph representa um grafo orientado de vértices inteiros de 0 a V-1. 
 * 
 * As principais operações são: add() que insere um arco no digrafo, e
 * adj() que itera sobre todos os vértices adjacentes a um dado vértice.
 * 
 * Arcos paralelos e laços são permitidos.
 * 
 * Esta implementação usa uma representação de _vetor de listas de adjacência_,
 * que  é uma vetor de objetos Bag indexado por vértices. 

 * ATENÇÃO: Por simplicidade esses Bag podem ser int's e não de Integer's.
 *
 * Todas as operações consomen no pior caso tempo constante, exceto
 * iterar sobre os vértices adjacentes a um determinado vértice, cujo 
 * consumo de tempo é proporcional ao número de tais vértices.
 * 
 * Para documentação adicional, ver 
 * https://algs4.cs.princeton.edu/42digraph, Seção 4.2 de
 * Algorithms, 4th Edition por Robert Sedgewick e Kevin Wayne.
 *
 */

/* interface para o uso da funcao deste módulo */
#include "digraph.h"


#include "bag.h"     /* add() e itens() */
#include <stdio.h>   /* fopen(), fclose(), fscanf(), ... */
#include <stdlib.h>  /* free() */
#include <string.h>  /* memcpy() */
#include "util.h"    /* emalloc(), ecalloc() */

#undef DEBUG
#ifdef DEBUG
#include <stdio.h>   /* printf(): para debuging */
#endif

/*----------------------------------------------------------*/
/* 
 * Estrutura básica de um Digraph
 * 
 * Implementação com vetor de listas de adjacência.
 */
struct digraph {
    int V, E; //Vertices and Edges
    Bag* adj;
    int* indegree;
};

/*------------------------------------------------------------*/
/* 
 * Protótipos de funções administrativas: tem modificador 'static'
 * 
 */

/*------------------------------------------------------------*/
/* 
 * Funções auxiliares.
 * 
 */

void validateVertex(Digraph G, int v) {
    if (v < 0 || v >= G->V)
        ERROR ("QUE ISSO! VERTICE %d NAO ESTAH NO INTERVALO");
}

/*-----------------------------------------------------------*/
/*
 *  newDigraph(V)
 *
 *  RECEBE um inteiro V.
 *  RETORNA um digrafo com V vértices e 0 arcos.
 * 
 */
Digraph newDigraph (int V) {
    if (V < 0)
        ERROR ("SOCORRO! NUMERO DE VERTICES MENOR QUE 0.\n");

    Digraph G = emalloc (sizeof (struct digraph));
    G->V = V;
    G->E = 0;

    //criar um vetor de bags
    G->adj = emalloc (V * sizeof (Bag));
    for (int i = 0; i < V; i++)
        G->adj[i] = newBag ();

    G->indegree = emalloc (V * sizeof (int));
    return G;
}

/*-----------------------------------------------------------*/
/*
 *  cloneDigraph(G)
 *
 *  RECEBE um digrafo G.
 *  RETORNA um clone de G.
 * 
 */
Digraph cloneDigraph (Digraph G) {
    Digraph aux = newDigraph (G->V);

    for (int v = 0; v < G->V; v++) 
        for (vertex w = itens (G->adj[v], TRUE); w != -1; w = itens (G->adj[v], FALSE))
            add (aux->adj[v], w);
    
    for (int i = 0; i < G->V; i++)
        aux->indegree[i] = G->indegree[i];

    return aux;
}

/*-----------------------------------------------------------*/
/*
 *  reverseDigraph(G)
 *
 *  RECEBE um digrafo G.
 *  RETORNA o digrafo R que é o reverso de G: 
 *
 *      v-w é arco de G <=> w-v é arco de R.
 * 
 */
Digraph reverseDigraph (Digraph G) {
    Digraph aux = newDigraph (G->V);

    for (int v = 0; v < G->V; v++) 
        for (int w = itens (G->adj[v], TRUE); w != -1; w = itens (G->adj[v], FALSE)) 
            addEdge (aux, w, v);
        
    return aux;
}

/*-----------------------------------------------------------*/
/*
 *  readDigraph(NOMEARQ)
 *
 *  RECEBE uma string NOMEARQ.
 *  RETORNA o digrafo cuja representação está no arquivo de nome NOMEARQ.
 *  O arquivo contém o número de vértices V, seguido pelo número de arestas E,
 *  seguidos de E pares de vértices, com cada entrada separada por espaços.
 *
 *  Veja os arquivos  tinyDG.txt, mediumDG.txt e largeDG.txt na página do 
 *  EP e que foram copiados do algs4, 
 * 
 */
Digraph readDigraph (String nomeArq) {
    printf ("HUEHAUEHAU");
    FILE* data = fopen (nomeArq, "r");
    String line = getLine (data);
    int V = atoi (getNextToken (line));
    Digraph G = newDigraph (V);

    int E = atoi (getNextToken (line));
    printf ("INT V E: %d, %d", V, E);
    for (int i = 0; i < E; i++) {
        String lineE = getLine (data);
        int v = atoi (getNextToken (lineE));
        int w = atoi (getNextToken (lineE));
        addEdge (G, v, w);
    }
    fclose (data);
    return G;
}

/*-----------------------------------------------------------*/
/*
 *  freeDigraph(G)
 *
 *  RECEBE um digrafo G e retorna ao sistema toda a memória 
 *  usada por G.
 *
 */
void freeDigraph (Digraph G) {
    for (int i = 0; i < G->V; i++)
        freeBag (G->adj[i]);

    free (G->indegree);
    free (G);
}    

/*------------------------------------------------------------*/
/*
 * OPERAÇÕES USUAIS: 
 *
 *     - vDigraph(), eDigraph(): número de vértices e arcos
 *     - addEdge(): insere um arco
 *     - adj(): itera sobre os vizinhos de um dado vértice
 *     - outDegree(), inDegree(): grau de saída e de entrada
 *     - toString(): usada para exibir o digrafo 
 */

/*-----------------------------------------------------------*/
/* 
 *  VDIGRAPH(G)
 *
 *  RECEBE um digrafo G e RETORNA seu número de vertices.
 *
 */
int vDigraph (Digraph G) {
    return G->V;
    
}

/*-----------------------------------------------------------*/
/* 
 *  EDIGRAPH(G)
 *
 *  RECEBE um digrafo G e RETORNA seu número de arcos (edges).
 *
 */
int eDigraph (Digraph G) {
    return G->E;
}

/*-----------------------------------------------------------*/
/*
 *  addEdge(G, V, W)
 * 
 *  RECEBE um digrafo G e vértice V e W e INSERE o arco V-W  
 *  em G.
 *
 */
void addEdge (Digraph G, vertex v, vertex w) {
    validateVertex (G, v);
    validateVertex (G, w);
    add (G->adj[v], w);
    G->indegree[w]++;
    G->E++;
}    


/*-----------------------------------------------------------*/
/* 
 *  ADJ(G, V, INIT)
 * 
 *  RECEBE um digrafo G, um vértice v de G e um Bool INIT.
 *
 *  Se INIT é TRUE,  ADJ() RETORNA o primeiro vértice na lista de adjacência de V.
 *  Se INIT é FALSE, ADJ() RETORNA o sucessor na lista de adjacência de V do 
 *                   último vértice retornado.
 *  Se a lista de adjacência de V é vazia ou não há sucessor do último vértice 
 *  retornada, ADJ() RETORNA -1.
 *
 *  Se entre duas chamadas de ADJ() a lista de adjacência de V é alterada, 
 *  o comportamento é  indefinido. 
 *  
 */
int adj (Digraph G, vertex v, Bool init) {
    validateVertex (G, v);
    int aux;
    if (init == TRUE) {
        aux = itens (G->adj[v], TRUE);
    }

    else {
        aux = itens (G->adj[v], FALSE);
    }

    return aux;
}

/*-----------------------------------------------------------*/
/*
 *  outDegree(G, V)
 * 
 *  RECEBE um digrafo G e vértice V.
 *  RETORNA o número de arcos saindo de V.
 *
 */
int outDegree(Digraph G, vertex v) {
    validateVertex (G, v);
    return size (G->adj[v]);
}

/*-----------------------------------------------------------*/
/*
 *  inDegree(G, V)
 * 
 *  RECEBE um digrafo G e vértice V.
 *  RETORNA o número de arcos entrando em V.
 *
 */
int inDegree(Digraph G, vertex v) {
    validateVertex (G, v);
    return G->indegree[v];
}


/*-----------------------------------------------------------*/
/*
 *  toString(G)
 * 
 *  RECEBE um digrafo G.
 *  RETORNA uma string que representa G. Essa string será usada
 *  para exibir o digrafo: printf("%s", toString(G)); 
 *    
 *  Sugestão: para fazer esta função inspire-se no método 
 *  toString() da classe Digraph do algs4.
 */
char* mystrcat (char* dest, char* src) {
    while (*dest) dest++;

    do {
        *dest++ = *src++;
    } while (*src);

    return --dest;
}

String toString(Digraph G) {
    int stringSize = strlen (" vertices, ") + strlen (" edges\n");
    int stringSize2 = strlen (": ") + strlen (" ");

    String s = emalloc (stringSize + 2 * sizeof (int) + G->V * stringSize2 + G->E * sizeof (int) + 1);
    sprintf (s, "%d vertices, %d edges\n", G->V, G->E);
    char* tmp = s;
    tmp = mystrcat (tmp, "\0");

    for (int v = 0; v < G->V; v++) {
        String aux = emalloc (strlen (": ") + sizeof (int));
        sprintf (aux, "%d: ", v);
        tmp = mystrcat (tmp, aux);

        for (vertex w = itens (G->adj[v], TRUE); w != -1; w = itens (G->adj[v], FALSE)) {
            String aux2 = emalloc (1 + sizeof(int)); //inteiro mais espaco (1)
            sprintf (aux2, "%d ", w);
            tmp = mystrcat (tmp, aux2);
        }
        tmp = mystrcat (tmp, "\n");
    }
    return s;
}

/*------------------------------------------------------------*/
/* 
 * Implementaçao de funções administrativas: têm o modificador 
 * static.
 */

