public class MergeTwoSortedArrays {
    public static int[] MergeTwoSortedArrays(int[] arr1, int[] arr2, int m, int n){
        int end = arr1.length-1;
        m--;
        n--;
        //Iterate over both elements from last and put in the correct position form end
        while(m>=0 && n>=0){
            if(arr1[m]>=arr2[n]){
                arr1[end] = arr1[m];
                m--;
            }else{
                arr1[end] = arr2[n];
                n--;
            }
            end--;
        }
        //edge case where second array is left 
        while(n>=0){
            arr1[end] = arr2[n];
            end--;
            n--;
        }
        return arr1;
    }
    public static void main(String[] args){
        int[] arr1 = {1,2,3,0,0,0};
        int[] arr2 = {2,5,6};
        int m = arr1.length - arr2.length;
        int n = arr2.length;
        MergeTwoSortedArrays(arr1,arr2,m,n);
        for(int num: arr1){
            System.out.print(num+" ");
        }
    }
}