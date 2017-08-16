import java.util.Comparator;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Michael Goodrum
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement cocktail sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Cannot sort with null data.");
        }
        boolean swapped = true;
        int start = 0;
        int end = arr.length - 1;
        while (swapped) {
            swapped = false;
            for (int x = start; x < end; x++) {
                if (comparator.compare(arr[x], arr[x + 1]) > 0) {
                    T temp = arr[x];
                    arr[x] = arr[x + 1];
                    arr[x + 1] = temp;
                    swapped = true;
                }
            }
            end = end - 1;
            if (swapped) {
                swapped = false;
                for (int x = end; x > start; x--) {
                    if (comparator.compare(arr[x], arr[x - 1]) < 0) {   
                        T temp = arr[x];
                        arr[x] = arr[x -1];
                        arr[x - 1] = temp;
                        swapped = true;
                    }
                }
            }
            start = start + 1;
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Cannot sort with null data.");
        }
        T temp;
        for (int x = 1; x < arr.length; x++) {
            for (int y = x; y > 0; y--) {
                if (comparator.compare(arr[y], arr[y - 1]) < 0) {
                    temp = arr[y];
                    arr[y] = arr[y - 1];
                    arr[y - 1] = temp;
                }
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * Note that there may be duplicates in the array, but they may not
     * necessarily stay in the same relative order.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Cannot sort with null data.");
        }
        for (int x = 0; x < arr.length - 1; x++) {
            int index = x;
            for (int y = x + 1; y < arr.length; y++) {
                if (comparator.compare(arr[y], arr[index]) < 0) {
                    index = y;
                }
            }
            T smaller = arr[index];
            arr[index] = arr[x];
            arr[x] = smaller;
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Cannot sort with null data.");
        }
        quickSort(arr, 0, arr.length - 1, rand, comparator);
    }

    private static <T> void quickSort(T[] arr, int left, int right, Random rand, Comparator<T> comparator) {
        if (right > left) {
            int index = part(arr, left, right, rand.nextInt(right - left) + left, comparator);
            quickSort(arr, left, index - 1, rand, comparator);
            quickSort(arr, index + 1, right, rand, comparator);
        }
    }

    private static <T> int part(T[] arr, int left, int right, int pivotIndex, Comparator<T> comparator) {
        T pivot = arr[pivotIndex];
        T temp = arr[pivotIndex];
        arr[pivotIndex] = arr[right];
        arr[right] = temp;
        int index = left;
        for (int x = left; x < right; x++) {
            if (comparator.compare(arr[x], pivot) <= 0) {
                temp = arr[index];
                arr[index++] = arr[x];
                arr[x] = temp;
            }
        }
        temp = arr[index];
        arr[index] = arr[right];
        arr[right] = temp;
        return index;
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Cannot sort null data.");
        }
        if (arr.length > 1) {
            T[] left = (T[]) new Comparable[arr.length / 2];
            T[] right = (T[]) new Comparable[arr.length - arr.length / 2];
            for (int i = 0; i < arr.length / 2; i++) {
                left[i] = arr[i];
            }
            for (int i = 0; i < right.length; i++) {
                
                right[i] = arr[arr.length / 2 + i];
            }
            mergeSort(left, comparator);
            mergeSort(right, comparator);
            arr = merge(arr, left, right, comparator);
        }
    }

    private static <T> T[] merge(T[] arr, T[] left, T[] right, Comparator<T> comparator) {
        int indL = 0;
        int indR = 0;
        int index = 0;
        while (indL < left.length && indR < right.length) {
            if (comparator.compare(left[indL], right[indR]) <= 0) {
                arr[index++] = left[indL++];
            } else {
                arr[index++] = right[indR++];
            }
        }
        
        if (indL == left.length) {  
            for (int i = indR; i < right.length; i++) {
                arr[index++] = right[i];
            }
        } else if (indR == right.length) {
            for (int i = indL; i < left.length; i++) {
                arr[index++] = left[i];
            }
        }
        return arr;
    }



    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort null array.");
        }
        int[] array = new int[arr.length];
        int largest = -1;
        for (int x = 0; x < arr.length; x++) {
            array[x] = arr[x];
            if (Math.abs(arr[x]) > largest) {
                largest = Math.abs(arr[x]);
            }
        }
        largest = 1 + (int) Math.log10(largest);
        for (int x = 0; x < largest; x++) {
            int[][] bucket = new int[10][arr.length];
            int[] counter = new int[10];
            for (int y = 0; y < arr.length; y++) {
                int value = Math.abs((int) (array[y] / pow(10, x) % 10));
                bucket[value][counter[value]++] = array[y];
            }
            int index = 0;
            for (int w = 0; w < 10; w++) {
                for (int z = 0; z < counter[w]; w++) {
                    array[index++] = bucket[w][z];
                }
            }
        }
        return array;
    }

    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sorts instead of {@code Math.pow()}.
     *
     * DO NOT MODIFY THIS METHOD.
     *
     * @throws IllegalArgumentException if both {@code base} and {@code exp} are
     * 0
     * @throws IllegalArgumentException if {@code exp} is negative
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * pow(base, (exp / 2) + 1);
        }
    }
}