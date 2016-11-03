import java.util.Arrays;

/**
 * Merge.java, you MUST SUBMIT this file.
 * <p>
 * Multiple Implementations of merge sort involving Magic Boxes
 *
 * @author Yash Gupta
 * @pso 17
 * @date 10/7/16
 */

public class Merge {
    public MagicBox magicBox = new MagicBox();
    public int count; //Counter for the number of comparisons in the standard algorithm

    /**
     * sortMerge
     * A standard merge sort
     *
     * @param array: The array to be sorted.
     */
    public void sortMerge(int[] array) {
        //Implemented merge sort and increment "count"
        //to keep track of the number of pairwise comparisons
        if (array.length <= 1) return;

        // Split the array in half
        int[] first = new int[array.length / 2];
        int[] second = new int[array.length - first.length];
        System.arraycopy(array, 0, first, 0, first.length);
        System.arraycopy(array, first.length, second, 0, second.length);

        // Sort each half
        sortMerge(first);
        sortMerge(second);

        // Merge the halves together, overwriting the original array
        standardMerge(first, second, array);
    }

    /**
     * standardMerge
     * <p>
     * merges two arrays back together for merge sort
     *
     * @param a      the first array being merged
     * @param b      the second array being merged
     * @param result the target array where the other two arrays are being merged
     */
    private void standardMerge(int[] a, int[] b, int[] result) {
        // Merge both halves into the result array
        // Next element to consider in the first array
        int aIndex = 0;
        // Next element to consider in the second array
        int bIndex = 0;

        // Next open position in the result
        int j = 0;
        // As long as neither iFirst nor iSecond is past the end, move the
        // smaller element into the result.

        while (aIndex < a.length && bIndex < b.length) {
            count++;
            if (a[aIndex] < b[bIndex]) {
                result[j] = a[aIndex];
                aIndex++;
            } else {
                result[j] = b[bIndex];
                bIndex++;
            }
            j++;
        }
        // copy what's left
        System.arraycopy(a, aIndex, result, j, a.length - aIndex);
        System.arraycopy(b, bIndex, result, j, b.length - bIndex);
    }


    /**
     * TODO: sortMerge8Sort
     * Merge Sort the array using the 8-Sort Box
     *
     * @param array: The array to be sorted.
     */
    public void sortMerge8Sort(int[] array) {
        //Implemented merge sort and increment "count"
        //to keep track of the number of pairwise comparisons

        //Base case to send a sorted array of size 8
        if (array.length <= 8) {
            int tempArray[] = sorter(array);
            System.arraycopy(tempArray, 0, array, 0, array.length);
            return;
        }

        // Split the array in half
        int[] first = new int[array.length / 2];
        int[] second = new int[array.length - first.length];
        System.arraycopy(array, 0, first, 0, first.length);
        System.arraycopy(array, first.length, second, 0, second.length);

        // Sort each half
        sortMerge8Sort(first);
        sortMerge8Sort(second);

        // Merge the halves together, overwriting the original array
        magicMerge(first, second, array);
    }

    /**
     *
     * @param a array to be merged
     * @param b array to be merged
     * @param result final merged and sorted array
     */
    public void magicMerge(int[] a, int[] b, int[] result) {

        int tempArray[] = new int[8];

        a = padder(a);      //Pads the array to a multiple of 8
        b = padder(b);      //Pads the array to a multiple of 8

        int thisCount = 0;
        int sortedArray[] = new int[a.length + b.length];   //Stores the final sorted array

        while ((a.length > 0 || b.length > 0) && thisCount != sortedArray.length) {

            //Pads the arrays to the length of 4 if their lengths are smaller than 4
            if (a.length < 4) {
                int a1[] = new int[4];
                for (int z = 0; z < a.length; z++) {
                    a1[z] = a[z];
                }
                for (int x = a.length; x < 4; x++) {
                    a1[x] = Integer.MAX_VALUE;
                }
                a = a1;
            }
            if (b.length < 4) {
                int b1[] = new int[4];
                for (int z = 0; z < b.length; z++) {
                    b1[z] = a[z];
                }
                for (int x = b.length; x < 4; x++) {
                    b1[x] = Integer.MAX_VALUE;
                }
                b = b1;
            }



            for (int i = 0; i < 4; i++) {
                tempArray[i] = a[i];
                //Selecting first 4 elements from both arrays
                tempArray[i + 4] = b[i];
                //And filling them up into the tempArray
            }
            int numElementsA = 4;       //keeps a track of the number of elements used from either arrays
            int numElementsB = 4;       //keeps a track of the number of elements used from either arrays
            int eightSort[] = magicBox.eightSort(tempArray);
            this.count++;
            int i = 0;
            while (thisCount < sortedArray.length) {

                //Pushes the elements to the sorted array if the index of that element is less than 4
                if (eightSort[i] < 4) {
                    sortedArray[thisCount] = tempArray[eightSort[i]];
                    i++;
                    thisCount++;
                    numElementsA--;
                    a = Arrays.copyOfRange(a, 1, a.length);

                }
                else {
                    sortedArray[thisCount] = tempArray[eightSort[i]];
                    i++;
                    thisCount++;
                    numElementsB--;
                    b = Arrays.copyOfRange(b, 1, b.length);
                }
                if (numElementsA == 0 || numElementsB == 0) {
                    break;
                }
            }
        }
        System.arraycopy(sortedArray, 0, result, 0, result.length);

    }

    /**
     *
     * @param array the array to be padded
     * @return the padded array of size 8 or the multiple of 8
     */
    public int[] padder(int array[]) {
        if (array.length == 8) {
            return array;
        }

        if (array.length < 8) {
            int tempArray[] = new int[8];
            int len = array.length;
            for (int i = len; i < 8; i++) {
                tempArray[i] = Integer.MAX_VALUE;
            }
            return tempArray;
        } else {
            int len = array.length;
            if (len % 8 == 0) {
                return array;
            } else {
                int addLength = 8 - (len % 8);
                int newLen = len + addLength;
                int[] tempArray = Arrays.copyOf(array, newLen);
                for (int i = len; i < newLen; i++) {
                    tempArray[i] = Integer.MAX_VALUE;
                }
                return tempArray;
            }
        }
    }


    /**
     *
     * @param array to be sorted
     * @return the padded and sorted array
     */
    public int[] sorter(int[] array) {
        array = padder(array);
        int[] eightSort = magicBox.eightSort(array);
        int[] sortedArray = new int[8];
        for (int i = 0; i < 8; i++) {
            sortedArray[i] = array[eightSort[i]];
        }
        return sortedArray;
    }
}

 /* Merge.java, you MUST SUBMIT this file.
  * Do not modify any variable definition
  */

