import java.util.Arrays;

/**
 * Selection.java, you must SUBMIT this file.
 * Do not modify any variable definition
 * <p>
 * Multiple Implementations of Selection sort involving Magic Boxes
 *
 * @author Yash Gupta
 * @pso 17
 * @date 10/11/2016
 */

public class Selection {
    public MagicBox magicBox = new MagicBox();
    public int count;

    /**
     * sortSelection
     * A standard selection sort
     *
     * @param array: The array to be sorted.
     */
    public void sortSelection(int[] array) {
        // a for loop to control the number of elements that finish sorting
        for (int left = 0; left < array.length - 1; left++) {
            int right = array.length - 1;
            int min = right; //index of the minimum element

            // from last to first unsorted element, find the min
            // and place it into the first unsorted position
            while (right >= left) {
                if (array[right] < array[min]) {
                    min = right;
                }
                count++;
                right--;
            }

            if (min != left) {
                int temp = array[left];
                array[left] = array[min];
                array[min] = temp;
            }
        }
    }

    /**
     * Selection Sort the array using the 8-Sort Box
     *
     * @param array: The array to be sorted.
     */
    public void sortSelection8Sort(int[] array) {

        int eightSort[] = new int[8];
        int minArray[] = new int[8];
        int oldLength = array.length;

        //Padding the array, sorting and returning sorted array
        if (array.length < 8) {
            int addLength = 8 - array.length;
            int newArray[] = Arrays.copyOf(array, 8);

            for (int i = array.length; i < 8; i++) {
                newArray[i] = Integer.MAX_VALUE;
            }

            int tempArray[] = newArray.clone();
            eightSort = magicBox.eightSort(newArray);
            count++;

            //Sorting using eightSort
            for (int i = 0; i < 8; i++) {
                tempArray[i] = newArray[eightSort[i]];
            }
            newArray = tempArray;

            //changing the length of array to old length
            tempArray = new int[oldLength];
            for (int i = 0; i < oldLength; i++) {
                tempArray[i] = newArray[i];
            }
            System.arraycopy(tempArray, 0, array, 0, array.length);
        }

        else {
            //Padding the array to a multiple of 4
            if (array.length % 4 != 0) {
                int addLength = 4 - (array.length % 4);
                int newLength = oldLength + addLength;
                array = Arrays.copyOf(array, newLength);
                for (int i = oldLength; i < newLength; i++) {
                    array[i] = Integer.MAX_VALUE;
                }
            }

            int newArray[] = array.clone();

            //Traversing through the array, while skipping 4 elements at a time.
            for (int i = 0; i < array.length - 3; i = i + 4) {
                for (int j = i; j < i + 4; j++) {
                    minArray[j - i] = array[j];
                }

                //selecting the set of the first 4 elements in the array
                for (int j = i + 4; j < array.length; j = j + 4) {
                    //Filling up the last 4 elements in minArray
                    for (int k = j; k < j + 4; k++) {
                        minArray[k - j + 4] = array[k];
                    }

                    /**
                     * This section of the code selects the first
                     * four elements and compares them to the other sets of four
                     * once an array of size 8 is filled, it sorts that array.
                     * Once that array is sorted, it selects the last 4 elements
                     * from the sorted array and pushes it to another array so that
                     * the elements are not copied.
                     *
                     * Once the first 4 minimum elements are computed, it pushes those elements
                     * to the final sorted array and repeats this until the entire list is sorted.
                     */

                    eightSort = magicBox.eightSort(minArray);
                    count++;
                    //Sorting the minArray
                    for (int k = 0; k < 8; k++) {
                        int min = minArray[eightSort[k]];
                        minArray[eightSort[k]] = minArray[k];
                        minArray[k] = min;
                        for (int x = k; x < 8; x++) {
                            if (eightSort[x] == k) {
                                int temp = eightSort[x];
                                eightSort[x] = eightSort[k];
                                eightSort[k] = temp;
                            }
                        }
                        //Copying the elements into the original array so that the elements are NOT REPEATED
                        for (int x = 4; x < 8; x++) {
                            array[j + x - 4] = minArray[x];
                        }
                    }
                }
                //Putting the elements into the final sorted array
                for (int k = 0; k < 4; k++) {
                    newArray[k + i] = minArray[k];
                }
            }
            //Removing the extra pads that we added to the array to make it work with eightSort
            int tempArray[] = Arrays.copyOfRange(newArray, 0, oldLength);
            System.arraycopy(tempArray, 0, array, 0, tempArray.length);
        }
    }


    /**
     *
     * Selection Sort the array using the 8-Min Box
     *
     * @param array: The array to be sorted.
     */
    public void sortSelection8Min(int[] array) {

        int count1 = 0;
        for (int left = 0; left < array.length - 1; left++) {
            int right = array.length - 1;
            int min = right; //index of the minimum element

            // from last to first unsorted element, find the min
            // and place it into the first unsorted position
            while (right >= left) {
                /**
                 * Sends a padded tempArray of size 8 to eightMin() and recieves the minimum element.
                 * We use this element to compare array[right] and array[min]
                 */
                int tempArray[] = new int[8];
                tempArray[0] = array[right];
                tempArray[1] = array[min];
                for (int i = 2; i < 8; i++) {
                    tempArray[i] = Integer.MAX_VALUE;
                }
                int min1 = magicBox.eightMin(tempArray);
                count++;
                count1++;
                if (min1 == 0) {
                    min = right;
                }
                count++;
                right--;
            }

            if (min != left) {
                int temp = array[left];
                array[left] = array[min];
                array[min] = temp;
            }
        }
        System.out.println(count1);
    }
}

/**
 * Selection.java, you should SUBMIT this file.
 * Do not modify any variable definition
 */



