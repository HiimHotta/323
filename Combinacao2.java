/****************************************************************
    Nome: Daniel Yoshio Hotta
    NUSP: 9922700

    Ao preencher esse cabecalho com o meu nome e o meu numero USP,
    declaro que todas as partes originais desse exercicio programa (EP)
    foram desenvolvidas e implementadas por mim e que portanto nao
    constituem desonestidade acadêmica ou plagio.
    Declaro tambem que sou responsavel por todas as copias desse
    programa e que nao distribui ou facilitei a sua distribuicao.
    Estou ciente que os casos de plagio e desonestidade acadêmica
    serao tratados segundo os criterios divulgados na pagina da
    disciplina.
    Entendo que EPs sem assinatura devem receber nota zero e, ainda
    assim, poderao ser punidos por desonestidade acadêmica.

    Abaixo descreva qualquer ajuda que você recebeu para fazer este
    EP.  Inclua qualquer ajuda recebida por pessoas (inclusive
    monitoras e colegas). Com excecao de material de MAC0323, caso
    você tenha utilizado alguma informacao, trecho de codigo,...
    indique esse fato abaixo para que o seu programa nao seja
    considerado plagio ou irregular.

    Exemplo:

        A monitora me explicou que eu devia utilizar a funcao xyz().

        O meu metodo xyz() foi baseada na descricao encontrada na
        pagina https://www.ime.usp.br/~pf/algoritmos/aulas/enumeracao.html.

    Descricao de ajuda ou indicacao de fonte:

    Nada.

    Se for o caso, descreva a seguir 'bugs' e limitacoes do seu programa:

****************************************************************/

/******************************************************************************
 *  Compilation:  javac-algs4 Combinacao.java
 *  Execution:    java Combinacao n k [opcao]
 *
 *  Enumera todas as combinacoes dos numeros em {1,2,...,n} k a k.
 *  Se opcao = 0 (defaul), gera e exibe todas as permutacoes em ordem
 *  lexicografica
 *  Se opcao = 1 apenas, __gera todas__ as combinacoes, mas __nao__ as
 *  exibe; apenas exibe o total de combinacoes.
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
    private static int count = 0; // contador de combinacoes
    private static int opcao = 0;
    private static int[] array;

        // 0 imprimir as combinacoes e o numero de combinacoes (default)
        // 1 imprimir apenas o numero de combinacoes
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
