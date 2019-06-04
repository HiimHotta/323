/*
 * MAC0323 Algoritmos e Estruturas de Dados II
 * 
 * ADT Bag implementada com lista ligada de itens. 
 *  
 *    https://algs4.cs.princeton.edu/13stacks/
 *    https://www.ime.usp.br/~pf/estruturas-de-dados/aulas/bag.html
 * 
 * ATENÇÃO: por simplicidade Bag contém apenas inteiros (int) não 
 * negativos (>=0) que são 'nomes' de vértices (vertex) de um 
 * digrafo.
 */

/* interface para o uso da funcao deste módulo */
#include "bag.h"  

#include <stdlib.h>  /* free() */
#include <string.h>  /* memcpy() */
#include "util.h"    /* emalloc() */

#undef DEBUG
#ifdef DEBUG
#include <stdio.h>   /* printf(): para debuging */
#endif

/*----------------------------------------------------------*/
/* 
 * Estrutura Básica da Bag
 * 
 * Implementação com listas ligada dos itens.
 */
typedef struct node {
   vertex        item;     
   struct node* next;
} Node;

//"construtor"
Node* newNode (const vertex item) {
    Node *tmp = emalloc (sizeof (Node));
    tmp->item = item;
    tmp->next = NULL;
    return tmp;
}

/*----------------------------------------------------------*/
/* 
 * Estrutura Básica da Bag
 * 
 * Implementação com listas ligada dos itens.
 */

struct bag {
	Node* first;    //começo da bag
	Node* iterator; //usada em Itens ()
	int   n;        //quant de itens
};

/*------------------------------------------------------------*/
/* 
 * Protótipos de funções administrativas: tem modificador 'static'
 * 
 */

/*-----------------------------------------------------------*/
/*
 *  newBag()
 *
 *  RETORNA (referência/ponteiro para) uma bag vazia.
 * 
 */
Bag newBag () {
	Bag tmp       = emalloc (sizeof (struct bag));
	tmp->first    = NULL;
	tmp->iterator = NULL;
	tmp->n        = 0;
	return tmp;
}

/*-----------------------------------------------------------*/
/*
 *  freeBag(BAG)
 *
 *  RECEBE uma Bag BAG e devolve ao sistema toda a memoria 
 *  utilizada.
 *
 */
void freeBag (Bag bag) {
	while (bag->first != NULL) {
		Node* aux = bag->first->next;
		free (bag->first);
		bag->first = aux;
	}
	free (bag->iterator);
	free (bag);
}    

/*------------------------------------------------------------*/
/*
 * OPERAÇÕES USUAIS: add(), size(), isEmpty() e itens().
 */

/*-----------------------------------------------------------*/
/*
 *  add(BAG, ITEM, NITEM)
 * 
 *  RECEBE uma bag BAG e um ITEM e insere o ITEM na BAG.
 *  NITEM é o número de bytes de ITEM.
 *
 *  Para criar uma copia/clone de ITEM é usado o seu número de bytes NITEM.
 *
 */
void add (Bag bag, vertex item) {
	Node* oldfirst = bag->first;
    bag->first = newNode (item);
    bag->first->next = oldfirst;
    bag->n++;

    if (bag->first->next == NULL)
		bag->iterator = bag->first; 
}    

/*-----------------------------------------------------------*/
/* 
 *  SIZE(BAG)
 *
 *  RECEBE uma bag BAG
 * 
 *  RETORNA o número de itens em BAG.
 */
int size (Bag bag) {
    return bag->n;
}

/*-----------------------------------------------------------*/
/* 
 *  ISEMPTY(BAG)
 *
 *  RECEBE uma bag BAG.
 * 
 *  RETORNA TRUE se BAG está vazia e FALSE em caso contrário.
 *
 */
Bool isEmpty (Bag bag) {
    return bag->first == NULL;
}

/*-----------------------------------------------------------*/
/* 
 *  ITENS(BAG, INIT)
 * 
 *  RECEBE uma bag BAG e um Bool INIT.
 *
 *  Se INIT é TRUE,  ITENS() RETORNA uma cópia/clone do primeiro item na lista de itens na BAG.
 *  Se INIT é FALSE, ITENS() RETORNA uma cópia/clone do item sucessor do último item retornado.
 *  Se BAG está vazia ou não há sucessor do último item retornado, ITENS() RETORNA -1.
 *
 *  Se entre duas chamadas de ITENS() a BAG é alterada, o comportamento é  indefinido. 
 *  
 */
vertex itens(Bag bag, Bool init) {
	if (bag->iterator == NULL)
		return -1;

	vertex tmp = bag->iterator->item;
	
	if (init == TRUE) 
		bag->iterator = bag->first->next;

	else 
		bag->iterator = bag->iterator->next;

    return tmp;
}

/*------------------------------------------------------------*/
/* 
 * Implementaçao de funções administrativas
 */

