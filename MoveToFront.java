/****************************************************************
    Nome: Daniel Yoshio Hotta
    NUSP: 9922700

    Ao preencher esse cabeÃ§alho com o meu nome e o meu nÃºmero USP,
    declaro que todas as partes originais desse exercÃ­cio programa (EP)
    foram desenvolvidas e implementadas por mim e que portanto nÃ£o 
    constituem desonestidade acadÃªmica ou plÃ¡gio.
    Declaro tambÃ©m que sou responsÃ¡vel por todas as cÃ³pias desse
    programa e que nÃ£o distribui ou facilitei a sua distribuiÃ§Ã£o.
    Estou ciente que os casos de plÃ¡gio e desonestidade acadÃªmica
    serÃ£o tratados segundo os critÃ©rios divulgados na pÃ¡gina da 
    disciplina.
    Entendo que EPs sem assinatura devem receber nota zero e, ainda
    assim, poderÃ£o ser punidos por desonestidade acadÃªmica.

    Abaixo descreva qualquer ajuda que vocÃª recebeu para fazer este
    EP.  Inclua qualquer ajuda recebida por pessoas (inclusive
    monitoras e colegas). Com exceÃ§Ã£o de material de MAC0323, caso
    vocÃª tenha utilizado alguma informaÃ§Ã£o, trecho de cÃ³digo,...
    indique esse fato abaixo para que o seu programa nÃ£o seja
    considerado plÃ¡gio ou irregular.

    Exemplo:

        A monitora me explicou que eu devia utilizar a funÃ§Ã£o xyz().

        O meu mÃ©todo xyz() foi baseada na descriÃ§Ã£o encontrada na 
        pÃ¡gina https://www.ime.usp.br/~pf/algoritmos/aulas/enumeracao.html.

    DescriÃ§Ã£o de ajuda ou indicaÃ§Ã£o de fonte:



    Se for o caso, descreva a seguir 'bugs' e limitaÃ§Ãµes do seu programa:

****************************************************************/

/*************************************************************************
 *  Compilation:  javac MeuTST.java
 *  Execution:    java MeuTST wiktionary.txt
 *
 *  Symbol table with string keys, implemented using a ternary search
 *  trie (TST).
 *
 *  Remarks
 *  --------
 *    - can't use a key that is the empty string ""
 *
 *************************************************************************/

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;

public class MoveToFront {

	static Alphabet alphabet;

	public MoveToFront () {
		alphabet = new Alphabet ();
	}

	private static void changeAlphabet (String alphabet) {
		MoveToFront.alphabet = new Alphabet (alphabet);
	}

	static int[] array (int beg, int end) {
		int[] tmp = new int[end - beg];
		for (int i = beg; i < end; i++)
			tmp[i - beg] = i;
		return tmp;
	}

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
    	while (!BinaryStdIn.isEmpty ()) {
			char c = BinaryStdIn.readChar (); 
			int index = alphabet.toIndex (c);
			int[] low = array (0, index); int[] high = array (index + 1, alphabet.radix ()); 
			String newAlphabet = c + alphabet.toChars (low) + alphabet.toChars (high);
			changeAlphabet (newAlphabet);
			BinaryStdOut.write ((char) index);
		}
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
     	while (!BinaryStdIn.isEmpty ()) {
			int index = BinaryStdIn.readChar ();
			char c = alphabet.toChar (index);
			int[] low = array (0, index); int[] high = array (index + 1, alphabet.radix ()); 
			String newAlphabet = c + alphabet.toChars (low) + alphabet.toChars (high);
			changeAlphabet (newAlphabet);
			BinaryStdOut.write (c);
		}
		BinaryStdOut.write ("\n");   	
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
    	MoveToFront mtf = new MoveToFront ();

    	if (args[0].equals ("-")) 
    		MoveToFront.encode ();
    	
    	else if (args[0].equals ("+"))
    		MoveToFront.decode ();

    	else 
    		BinaryStdOut.write ("ERRO, use somente '+' ou '-'.");

    	BinaryStdOut.flush ();
    	BinaryStdOut.close ();
    }

}