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

    Se for o caso, descreva a seguir 'bugs' e limitações do seu programa:

****************************************************************/

/******************************************************************************
 *  Compilation:  javac-algs4 Permutation.java
 *  Execution:    java Permutation k
 *
 *  Por convencao, 0 significa bloqueado
 *
 *  % java Permutation 4 < std.in
 *
 *  Output variavel devido aa natureza da classe
 *
 * Arquivo std.in (copie e cole) :
   a b c d e
 ******************************************************************************/
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {

    private int n;
    private byte grid[][];

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
      if (n <= 0)
        throw new IllegalArgumentException ("Digite n > 0.");
      grid = new byte [n][n]
      this.n = n;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
      if (row < 0 || row >= n || col < 0 || col >= n)
        throw new IllegalArgumentException ("Linha ou Coluna invalida.");
      grid[row][col] = 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
      if (row < 0 || row >= n || col < 0 || col >= n)
        throw new IllegalArgumentException ("Linha ou Coluna invalida.");
      return grid[row]col] > 0;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
      if (row < 0 || row >= n || col < 0 || col >= n)
        throw new IllegalArgumentException ("Linha ou Coluna invalida.");
      return grid[row][col] > 1;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
      int count;
      for (int i = 0; i < n; i++) 
        for (int j = 0; j < n; j++) {
          if (grid[i][j] > 0)
            count++;
        }
    }

    // does the system percolate?
    public boolean percolates() {

    }

    // unit testing (required)
    public static void main(String[] args)

}