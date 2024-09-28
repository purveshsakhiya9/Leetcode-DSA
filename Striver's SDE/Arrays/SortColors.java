public class SortColors {
    public static void SortColors(int[] nums){
        int ones = 0;
        int twos = 0;
        for(int i = 0;i< nums.length; i++){
            if(nums[i]==1){
                ones++;
            }else if(nums[i]==2){
                twos++;
            }
        }
        for(int i =0;i<nums.length;i++){
            if(i<nums.length-ones-twos){
                nums[i]=0;
            }else if(nums.length-ones-twos<=i && i<nums.length-twos){
                nums[i]=1;
            }else{
                nums[i]=2;
            }
        }
    }
    public static void main(String[] ars){
        int[] input = {2,0,2,1,1,0};
        SortColors(input);
        for(int num:input){
            System.out.print(num+" ");
        }
    }
}