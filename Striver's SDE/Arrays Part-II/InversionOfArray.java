public class InversionOfArray {
    public static int BruteForceApproach(int[] nums){
        int count = 0;
        for(int i = 0; i< nums.length; i++){
            for(int j = i; j< nums.length; j++){
                if(nums[i]>nums[j]){
                    count++;
                }
            }
        }
        return count;
    }

    private static int OptimalAproach(int[] nums) {
        return InversionSortandCount(nums,0,nums.length-1);
    }

    private static int InversionSortandCount(int[] array, int left, int right) {
        int count = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;
            count += InversionSortandCount(array, left, mid);
            count += InversionSortandCount(array, mid + 1, right);
            count += Inversionmergeandcount(array, left, mid, right);
        }
        return count;
    }

    private static int Inversionmergeandcount(int[] array, int left, int mid, int right) {
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

        while (i < leftarray.length && j < rightarray.length) {
            if (leftarray[i] <= rightarray[j]) {
                array[k] = leftarray[i];
                i++;
            } else {
                count += (leftarray.length - i);
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

    public static void main(String[] args){
        int[] nums = {2,5,1,3,4};
        System.out.println(BruteForceApproach(nums));
        System.out.println(OptimalAproach(nums));
    }


}
