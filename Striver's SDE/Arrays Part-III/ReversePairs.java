public class ReversePairs {

    public static int ReversePairs(int[] array) {
        return reversesortandCount(array, 0, array.length - 1);
    }

    private static int reversesortandCount(int[] array, int left, int right) {
        int count = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;
            count += reversesortandCount(array, left, mid);
            count += reversesortandCount(array, mid + 1, right);
            count += reversemergeandcount(array, left, mid, right);
        }
        return count;
    }

    private static int reversemergeandcount(int[] array, int left, int mid, int right) {
        int[] leftarray = new int[mid - left + 1];
        int[] rightarray = new int[right - mid];

        for (int i = 0; i < leftarray.length; i++) {
            leftarray[i] = array[left + i];
        }
        for (int j = 0; j < rightarray.length; j++) {
            rightarray[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        int count = 0;

        // Count reverse pairs
        for (int m = 0,n=0; m < leftarray.length; m++) {
            while (n < rightarray.length && leftarray[m] > 2L * rightarray[n]) {
                n++;
            }
            count += n;
        }

        while (i < leftarray.length && j < rightarray.length) {
            if (leftarray[i] <= rightarray[j]) {
                array[k] = leftarray[i];
                i++;
            } else {
                array[k] = rightarray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements
        while (i < leftarray.length) {
            array[k] = leftarray[i];
            i++;
            k++;
        }
        while (j < rightarray.length) {
            array[k] = rightarray[j];
            j++;
            k++;
        }

        return count;
    }

    public static void main(String[] args) {
        int[] array = {2,4,3,5,1};
        System.out.println(ReversePairs(array));  // Output should be 2
    }
}
