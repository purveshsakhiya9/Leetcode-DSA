public class ReverseWordsInString {
    public static String ReverseWordsInString(String s){
        String[] arr = s.trim().split("\\s");
        for(int i = 0; i< arr.length/2; i++){
            String temp = arr[i];
            arr[i]= arr[arr.length-i-1];
            arr[arr.length-i-1] = temp;
        }
        return String.join(" ",arr);
    }
    public static void main(String[] args){
        String arr = "Hello world";
        System.out.println(ReverseWordsInString(arr));
    }
}