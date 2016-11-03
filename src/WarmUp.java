import java.util.Arrays;

/**
 * WarmUp
 * <p>
 * The class where you will implement all of
 * the warm up methods for project 2.
 * <p>
 * You MUST SUBMIT this file.
 * Do NOT modify variable & method defintion.
 *
 * @author Yash Gupta
 * @pso 17
 * @date 10/7/16
 **/

public class WarmUp {
    private MagicBox magicBox = new MagicBox();

    /**
     * <p>
     * returns the minimum element of the given array as determined using 8Min
     *
     * @param array - the array of integers being checked
     * @return min: the value of the least element in array
     */
    public int min8Min(int[] array) {

        // First pads the array and then returns the minimum of the array
        if (array.length < 8) {
            int oldLength = array.length;
            int addLength = 8 - array.length;
            array = Arrays.copyOf(array, 8);
            for (int i = oldLength; i < 8; i++) {
                array[i] = Integer.MAX_VALUE;
            }
            int min = magicBox.eightMin(array);
            return min;
        }

        else {
            //Returns the minimum of the array
            if (array.length == 8) {
                return magicBox.eightMin(array);
            }
            //Sorts the array using 8min selection sort and returns the element at the index 0
            else {
                Selection selection = new Selection();
                int tempArray[] = array.clone();
                selection.sortSelection8Min(tempArray);
                return tempArray[0];
            }
        }
    }

    /**
     * <p>
     * checks if the given array is sorted in increasing order using the 8-Sort Magic Box
     *
     * @param array - the array of integers being checked
     * @return: true if sorted, false otherwise
     */
    public boolean isSorted8Sort(int[] array) {

        //First pads the array and then finds the eightSort of the array.
        //If the indexes of the eightSort are in increasing order, returns true
        if (array.length < 8) {
            int addLength = 8 - array.length;
            int newArray[] = Arrays.copyOf(array, 8);
            for (int i = array.length; i < 8; i++) {
                newArray[i] = Integer.MAX_VALUE;
            }
            array = newArray;
            int sortedArray[] = magicBox.eightSort(array);
            for (int i = 0; i < 8; i++) {
                if (sortedArray[i] != i) {
                    return false;
                }
            }
            return true;
        }


        else {
            //For length 8, sorts using 8sort and checks if the indexes are in increasing order
            if (array.length == 8) {
                int arr[] = magicBox.eightSort(array);

                for (int i = 0; i < 8; i++) {
                    if (arr[i] != i) {
                        return false;
                    }
                }
                return true;

            }

            //goes through sets of 8, while sorting them
            // and keeps checking if the indexes are in increasing order
            else {
                int numChecks = array.length - 8;
                for (int i = 0; i < numChecks - 1; i++) {
                    int newArray[] = Arrays.copyOfRange(array, i, i + 8);
                    int sortedArray[] = magicBox.eightSort(newArray);
                    for (int k = 0; k < 8; k++) {
                        if (sortedArray[k] != k) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
    }

    /**
     * <p>
     * checks if the given array is sorted in increasing order using the 8-Min Magic Box
     *
     * @param array - the array of integers being checked
     * @return: true if sorted, false otherwise
     */
    public boolean isSorted8Min(int[] array) {

        //Pads the array while going thorough each element, keeping track that the
        // current element in the array is the smallest element in the eightSort array
        if (array.length < 8) {
            int length = array.length;
            int newArray[] = Arrays.copyOf(array, 8);
            for (int i = array.length; i < 8; i++) {
                newArray[i] = Integer.MAX_VALUE;
            }
            array = newArray;
            for (int i = 0; i < 8 - length; i++) {
                int min = magicBox.eightMin(array);
                if (min != i) {
                    return false;
                }
                array[i] = Integer.MAX_VALUE;
            }
            return true;
        }

        else {
            //Goes through the array while padding it, keeping track that the current element
            //is the smallest element in the array
            if (array.length == 8) {
                int arr[] = array;

                for (int i = 0; i < 8; i++) {
                    if (magicBox.eightMin(arr) != i) {
                        return false;
                    }
                    arr[i] = Integer.MAX_VALUE;
                }
                return true;
            }

            //Goes through the array while padding it, keeping track that the current element
            //is the smallest element in the array
            else {
                for (int i = 0; i < array.length; i++) {
                    int newArray[] = Arrays.copyOfRange(array, i, i + 8);
                    if (array.length - i < 8) {
                        int padPosition = array.length - i;
                        for (int j = padPosition; j < 8; j++) {
                            newArray[j] = Integer.MAX_VALUE;
                        }
                    }
                    int min = magicBox.eightMin(newArray);
                    if (min != 0) {
                        return false;
                    }
                }
                return true;
            }
        }
    }
}
