
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Quick;

public class BurrowsWheeler {

    public static int alphabetL = 256; //alphabet.length

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output 
    public static void transform () {
        while (!BinaryStdIn.isEmpty ()) {
            String s = BinaryStdIn.readString ();
            char[] t = s.toCharArray ();
    	    CircularSuffixArray circular = new CircularSuffixArray (s);

            //first
            for (int i = 0; i < t.length; i++) {
                if (circular.index (i) == 0) 
                    BinaryStdOut.write (i);
            }

            //t[]
            for (int i = 0; i < t.length; i++) {
                BinaryStdOut.write (t[(t.length - 1 + circular.index (i)) % t.length]);
            }
        }
        BinaryStdOut.flush ();
        BinaryStdOut.close ();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform () {
        while (!BinaryStdIn.isEmpty ()) {
            int first = BinaryStdIn.readInt ();
            String s = BinaryStdIn.readString ();
            char[] t = s.toCharArray ();
            int[] next = new int[t.length];
            Queue <Integer> queue[] = new Queue[alphabetL];

            //read occurrences
            for (int i = 0; i < t.length - 1; i++) {
                queue[t[i]] = new Queue ();
                queue[t[i]].enqueue (i);
            }

            //create next
            int aux = 0;
            for (int i = 0; i < alphabetL; i++) {
                if (queue[i] != null) {
                    while (!queue[i].isEmpty ())
                        next[aux++] = queue[i].dequeue ();
                }
            }

            aux = first;
            for (int i = 1; i < t.length; i++) {
                BinaryStdOut.write (t[aux]);
                aux = next [aux];
            }
        }
        
        BinaryStdOut.flush ();
        BinaryStdOut.close ();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main (String[] args) {
        if (args[0].equals ("+")) 
            transform ();

        if (args[0].equals ("-"))
            inverseTransform ();
    }

}