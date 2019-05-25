import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.StdOut;

public class CircularSuffixArray {

	int[] index; 
	char[] array;
	int n;
    // circular suffix array of s
    public CircularSuffixArray(String s) {
    	if (s == null)
    		throw new IllegalArgumentException ("Passe String valida!!!\n");

    	n = s.length ();
    	array = s.toCharArray ();

        //construtor basico
        index = new int[n];
    	for (int i = 0; i < n; i++)
    		index[i] = i;

    	sort (index);
    	
    }

    // length of s
    public int length() {
    	return n;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
    	if (i < 0 || i >= n) 
    		throw new IllegalArgumentException ("Coloque i valido!!!\n");
    	
    	return index[i];
    }

    // Mergesort da classe Merge.java algs4
    private void sort (int[] a) {
        int[] aux = new int[a.length];
        sort (a, aux, 0, a.length - 1);
    }


    private void sort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) 
        	return;

        int mid = lo + (hi - lo) / 2;
        sort (a, aux, lo, mid);
        sort (a, aux, mid + 1, hi);
        merge (a, aux, lo, mid, hi);
    }

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    private void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        // copy to aux[]
        for (int k = lo; k <= hi; k++) 
            aux[k] = a[k]; 
        

        // merge back to a[]
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)               a[k] = aux[j++];
            else if (j > hi)                a[k] = aux[i++];
            else if (less (aux[j], aux[i])) a[k] = aux[j++];
            else                            a[k] = aux[i++];
        }
    }

    // is v < w ?
    private boolean less (int v, int w) {
        for (int i = 0; i < n; i++) {
        	if (array[(v + i) % n] < array[(w + i) % n])
        		return true;
        	if (array[(v + i) % n] > array[(w + i) % n])
        		return false;
        }
        return false;
    }

    private void checkClass () {
      	StdOut.println ("CIRCULAR!\n");  	

    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			StdOut.print (array[(i + j) % n]);
    		}
    		StdOut.println ();
    	}

    	StdOut.println ("\nORDENADO!\n");
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			StdOut.print (array[(index[i] + j) % n]);
    		}
    		StdOut.println ();
    	}
    }

    private void checkClass2 () {
    	StdOut.println ("\ni 	index\n");
    	for (int i = 0; i < n; i++) {
    		StdOut.println (i + "	" + index[i]);
    	}
    }
    // unit testing (required)
    public static void main(String[] args) {
    	CircularSuffixArray csa = new CircularSuffixArray (args[0]);

    	if (args.length == 2) {
    		if (args[1].equals ("+"))
    	    	csa.checkClass ();
    	    else if (args[1].equals ("-"))
    			csa.checkClass2 ();
    		else 
    			StdOut.println ("B-BAKA!");
        }

    	else {
     	   	csa.checkClass ();
    	    csa.checkClass2 ();   	    
    	}
    }

}