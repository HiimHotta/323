/****************************************************************
    Nome: Daniel Yoshio Hotta
    NUSP: 9922700

    Ao preencher esse cabe�alho com o meu nome e o meu n�mero USP,
    declaro que todas as partes originais desse exerc�cio programa (EP)
    foram desenvolvidas e implementadas por mim e que portanto n�o
    constituem desonestidade acad�mica ou pl�gio.
    Declaro tamb�m que sou respons�vel por todas as c�pias desse
    programa e que n�o distribui ou facilitei a sua distribui��o.
    Estou ciente que os casos de pl�gio e desonestidade acad�mica
    ser�o tratados segundo os crit�rios divulgados na p�gina da
    disciplina.
    Entendo que EPs sem assinatura devem receber nota zero e, ainda
    assim, poder�o ser punidos por desonestidade acad�mica.

    Abaixo descreva qualquer ajuda que voc� recebeu para fazer este
    EP.  Inclua qualquer ajuda recebida por pessoas (inclusive
    monitoras e colegas). Com exce��o de material de MAC0323, caso
    voc� tenha utilizado alguma informa��o, trecho de c�digo,...
    indique esse fato abaixo para que o seu programa n�o seja
    considerado pl�gio ou irregular.

    Exemplo:

        A monitora me explicou que eu devia utilizar a fun��o xyz().

        O meu m�todo xyz() foi baseada na descri��o encontrada na
        p�gina https://www.ime.usp.br/~pf/algoritmos/aulas/enumeracao.html.

    Descri��o de ajuda ou indica��o de fonte:

    Nada.

    Se for o caso, descreva a seguir 'bugs' e limita��es do seu programa:

****************************************************************/

/******************************************************************************
 *  Compilation:  javac-algs4 Combinacao.java
 *  Execution:    java Combinacao n k [opcao]
 *
 *  Enumera todas as combina��es dos n�meros em {1,2,...,n} k a k.
 *  Se opcao = 0 (defaul), gera e exibe todas as permuta��es em ordem
 *  lexicogr�fica
 *  Se opcao = 1 apenas, __gera todas__ as combina��es, mas __n�o__ as
 *  exibe; apenas exibe o total de combina��es.
 *
 * % java Combinacao 5 3 1
 * 10
 * elapsed time = 0.002
 * % java Combinacao 5 3
 * 1 2 3
 * 1 2 4
 * 1 2 5
 * 1 3 4
 * 1 3 5
 * 1 4 5
 * 2 3 4
 * 2 3 5
 * 2 4 5
 * 3 4 5
 * 10
 * elapsed time = 0.002
 * % java Combinacao 100 3 1
 * 161700
 * elapsed time = 0.004
 * % java Combinacao 1000 3 1
 * 166167000
 * elapsed time = 0.726
 *
 ******************************************************************************/

//import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.Stopwatch;

public class Combinacao {
    private static int count = 0; // contador de combina��es
    private static int opcao = 0;
    private static int[] array;

        // 0 imprimir as combina��es e o n�mero de combina��es (default)
        // 1 imprimir apenas o n�mero de combina��es
    public static void printArray (int[] array) {
        count++;
        if (opcao == 1)
            return;

        for (int i = 0; i < array.length; i++) 
            StdOut.print(array[i] + " ");
        StdOut.println();
    }

    public static void increment (int[] array, int pos, int k) {
        for (int i = pos; i < k; i++)
            array[i]++;
    }

    public static void reset (int[] array, int pos, int k) {
        for (int i = pos; i < k; i++)
            array[i] = array[pos - 1];
    }

    public static void printComb (int[] array, int posAtual, int n, int k) {
        if (posAtual == k)
          return;

        if (posAtual == 0 && array[posAtual] > n - k)
          return;

        if (posAtual == 0 && array[0] == 0) {
          for (int i = 0; i <= n - k; i++) {
            increment (array, posAtual, k);
            printComb (array, 1, n, k);
          }
        }

        else if (posAtual == 0)
          return;

        else if (posAtual < k - 1 && array[posAtual] < n - posAtual) {
          for (int i = 0; i < n - posAtual; i++) 
            increment (array, posAtual, k);
            printComb (array, ++posAtual, n, k);
        }

        else if (posAtual < k - 1 && array[posAtual] >= n - posAtual) {
          reset (array, posAtual, k);
          return;
        }

        else if (posAtual == k - 1 && array[posAtual] < n) {
          array[posAtual]++;
          printArray (array);
          return;
        }

        else if (posAtual == k - 1 && array[posAtual] == n) {
          reset (array, posAtual, k);
          return;
        }

        else
          return;
    }


    public static void combinacao(int n, int k) {
        array = new int[k];
        printComb (array, 0, n, k);
    }


    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        if (args.length == 3) {
            opcao = Integer.parseInt(args[2]);
        }
        Stopwatch timer = new Stopwatch();
        combinacao(n, k);
        StdOut.println(count);
        StdOut.println("elapsed time = " + timer.elapsedTime());
    }
}
