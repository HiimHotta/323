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
 *  Compilation:  javac-algs4 STPerms.java
 *  Execution:    java STPerms n s t opcao
 *
 *  Enumera todas as (s,t)-permutações das n primeiras letras do alfabeto.
 *  As permutações devem ser exibidas em ordem lexicográfica.
 *  Sobre o papel da opcao, leia o enunciado do EP.
 *
 *  % java STPerms 4 2 2 0
 *  badc
 *  bdac
 *  cadb
 *  cdab
 *  % java STPerms 4 2 2 1
 *  4
 *  % java STPerms 4 2 2 2
 *  badc
 *  bdac
 *  cadb
 *  cdab
 *  4
 *
 ******************************************************************************/

//import edu.princeton.cs.algs4.StdOut;

public class STPerms {
  private static int count = 0; // contador de combinacoes
  private static int opcao = 0;
  private static char[] array;
  private static int n, s, t;

  public  static void perm1(String s) {
    perm1("", s);
  }

  private static void perm1(String prefix, String s) {
    int n = s.length();
    if (n == 0)
      PermST (prefix);

    else
      for (int i = 0; i < n; i++)
        perm1 (prefix + s.charAt (i), s.substring (0, i) + s.substring (i + 1, n));
  }

  public static void PermST (String s) {
    array = s.toCharArray ();

    if (TestS (array, 0, 0, 0) && TestT (array, 0, 0, 0)) {
      if (opcao == 0) 
        StdOut.println (s);
      count++;
    }
  }

  //
  private static boolean TestS (char[] vetor, int anterior, int atual, int contador) {    
    StdOut.println (vetor[anterior] + " " + vetor[atual]);

    if (contador > s)
      return false;

    else if (contador == 0) 
      for (int i = 0; i < n - s; i++)
        for (int j = i + 1; j < n - s + 1; j++)
          if (!TestS (vetor, i, j, 1))
            return false; 

    else if (vetor [anterior] < vetor [atual]) {
      StdOut.println ("k");
      for (int k = 1; k <= n - atual; k++) {
        if (!TestS (vetor, atual, atual + k, contador + 1))
          return false;
      }
    }
    else if (vetor [anterior] > vetor [atual])
      StdOut.println ("krl");
    else if (vetor [anterior] == vetor [atual])
      StdOut.println ("PORRA" + vetor[anterior] + vetor[atual] + contador);    
    return true;
  }

  private static boolean TestT (char[] vetor, int anterior, int atual, int contador) {
    if (contador > t)
      return false;

    else if (contador == 0) 
      for (int i = 0; i < n - t; i++)
        for (int j = i + 1; j < n - t + 1; j++)
          if (!TestT (vetor, i, j, 1))
            return false; 

    else if (vetor [anterior] > vetor [atual]) 
      for (int k = 1; k <= n - atual; k++)
        if (!TestT (vetor, atual, atual + k, contador + 1))
          return false;

    return true;
  }  

  // swap the characters at indices i and j
  private static void swap(char[] a, int i, int j) {
    char c = a[i]; a[i] = a[j]; a[j] = c;
  }

  //diz como eh o uso da classe
  private static void mostre_uso() {
      String msg = "uso:   java STPerms n s t (opcional)\n\n"        +
                   "      com n = natural, incluso 0 <= 26\n"        +
                   "      com s = natural <= n\n"                    +
                   "      com t = natural <= n\n"                    +
                   "      (opcao) estah definido no escopo do codigo\n";

      StdOut.println(msg);
  }

  public static void main (String[] args) {
    if (args.length < 3) {
      mostre_uso();
      return;
    }

    n = Integer.parseInt (args[0]);
    s = Integer.parseInt (args[1]);
    t = Integer.parseInt (args[2]);

    if (args.length == 4)
      opcao = Integer.parseInt (args[3]);

    Stopwatch timer = new Stopwatch ();

    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    String elements = alphabet.substring(0, n);
    perm1(elements);

    if (opcao > 0)
      StdOut.println (count);

    StdOut.println ("elapsed time = " + timer.elapsedTime ());
  }
}