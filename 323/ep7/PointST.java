/****************************************************************
    Nome: Daniel Yoshio Hotta
    NUSP: 9922700

    Ao preencher esse cabeçalho com o meu nome e o meu número USP,
    declaro que todas as partes originais desse exercício programa (EP)
    foram desenvolvidas e implementadas por mim e que portanto não
    constituem desonestidade acadêmica ou plágio.
    Declaro também que sou responsável por todas as cópias desse
    programa e que não distribui ou facilitei a sua distribuição.
    Estou ciente que os casos de plágio e desonestidade acadêmica
    serão tratados segundo os critérios divulgados na página da
    disciplina.
    Entendo que EPs sem assinatura devem receber nota zero e, ainda
    assim, poderão ser punidos por desonestidade acadêmica.

    Descrição de ajuda ou indicação de fonte:

    Base : https://introcs.cs.princeton.edu/java/43stack/Queue.java

    Se for o caso, descreva a seguir 'bugs' e limitações do seu programa:

****************************************************************/
/******************************************************************************
 *  Compilation:  javac-algs4 Deque.java
 *  Execution:    java Deque
 *
 *  Implementa a estrutura Deque, em que podemos alterar os elementos da lista
 *  ligada tanto pelo começo quanto pelo fim.
 *  
 ******************************************************************************/

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;

public class PointST <Value> {

    RedBlackBST <Point2D, Value> st; 
    int size; 


    // construct an empty symbol table of points 
    public PointST () {
      st = new RedBlackBST <Point2D, Value> ();
    }

    // is the symbol table empty? 
    public boolean isEmpty () {
      return size == 0;
    }

    // number of points
    public int size () {
      return size;
    }

    // associate the value val with point p
    public void put (Point2D p, Value val) {
      if (get (p) == null)
        size++;
      st.put (p, val);
    }

    // value associated with point p 
    public Value get (Point2D p) {
      return st.get (p);
    }

    // does the symbol table contain point p? 
    public boolean contains (Point2D p) {
      return get (p) != null;
    }

    // all points in the symbol table 
    public Iterable <Point2D> points () {

    }

    // all points that are inside the rectangle (or on the boundary) 
    public Iterable <Point2D> range(RectHV rect) {

    }

    // a nearest neighbor of point p; null if the symbol table is empty 
    public Point2D nearest(Point2D p) {

    }

    // unit testing (required)
    public static void main(String[] args){
      
    }

}
