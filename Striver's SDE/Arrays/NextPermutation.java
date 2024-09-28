public class NextPermutation {
    private static int[] Nextpermuation(int[] arr) {
        int idx1 = -1;
        // Step 1: Find the first decreasing element from the right
        for(int i = arr.length-2;i>=0;i--) {
            if (arr[i] < arr[i + 1]) {
                idx1=i;
                break;
            }
        }
        // If no valid idx1 is found, reverse the array to get the smallest permutation
        if(idx1==-1) {
            reverse(arr, 0, arr.length - 1);
        }else{
            // If no valid idx1 is found, reverse the array to get the smallest permutation
            for(int j = arr.length-1;j>idx1;j--){
                if(arr[j] > arr[idx1]){
                    // Step 3: Swap arr[idx1] with arr[j]
                    swap(arr,idx1,j);
                    break;
                }
            }
            // Step 4: Reverse the suffix to get the smallest lexicographical order
            reverse(arr,idx1+1, arr.length-1);
        }
    return arr;
    }

    // Helper function to reverse elements between indices start and end
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    // Helper function to swap two elements in an array
    private static void swap(int[] arr, int idx1, int idx2) {
        int temp;
        temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    public static void main(String[] args){
        int[] arr = {3,2,1}; // Example where no larger permutation exists
        int[] result = Nextpermuation(arr);
        // Printing the result
        for(int res :result){
            System.out.print(res+" ");
        }
    }
}