import java.util.Scanner;
import java.util.*;
import java.math.BigInteger;
import static java.lang.System.out;

public class Solution  {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        int i=1;
        while (sc.hasNextLine()) {
            out.print("Case #"+(i++)+": ");
            String N = sc.nextLine();
            out.println(findNext(N));
        }
    }

    public static String findNext(String N) {
        char[] chars = N.toCharArray();
        List<Integer> list = new ArrayList<Integer>(N.length());
        List<Integer> prefix = new ArrayList<Integer>();
        List<Integer> suffix = new ArrayList<Integer>();

        for(char c : chars) {
            Integer i = Integer.parseInt(""+c);
            list.add(i);
        }

        partition(list, prefix, suffix);
        if(prefix.size() == 0) {
            // Number is as large as it gets for this number of digits
            // Add a digit and put the numbers in non-decreasing order
            list.add(0);
            Collections.sort(list);
            int minIndex = 0; 

            // The first non-zero digit will be the smallest non-zero digit
            // Swap it with the most significant digit to ensure we don't lead with a zero
            for (ListIterator<Integer> iter = list.listIterator(); iter.hasNext();) {
                if (iter.next() > 0) {
                    minIndex = iter.previousIndex();
                    break;
                }
            }
            Collections.swap(list, 0, minIndex);

        } else {
            // Find the smallest digit larger than the top of the prefix
            // swap it with the top of the prefix
            // sort the suffix in non-decreasing order
            Integer prefixTail = prefix.get(prefix.size() - 1);
            Integer suffixCandidate = 10;
            for (Integer i : suffix) {
                if (i < suffixCandidate && i > prefixTail) {
                    suffixCandidate = i;
                }
            }
            prefix.set(prefix.size()-1, suffixCandidate);
            suffix.set(suffix.indexOf(suffixCandidate), prefixTail);
            Collections.sort(suffix);
            prefix.addAll(suffix);
            list = prefix;
        }

        StringBuilder sb = new StringBuilder();
        for (Integer i : list) {
            sb.append(""+i);
        }

        return sb.toString();
    }

    public static void partition(List<Integer> list, List<Integer> prefix, List<Integer> suffix) {
        /**
         * Loop from right to left and partition when we find an increase
         */
        //out.println (list);
        Boolean isSuffix = true;
        Integer prev = 10;
        for (ListIterator<Integer> iter = list.listIterator(list.size()); iter.hasPrevious();) {
            Integer num = iter.previous();
            //   out.println("Num:" + num);
            isSuffix = (isSuffix && (suffix.size() == 0 || prev <= num));
            if (isSuffix) {
                suffix.add(num);
            } else {
                prefix.add(num);
            }
            prev = num;
        }
        Collections.reverse(prefix);
        Collections.reverse(suffix);

    }

}
