package life.icetea.learn.algorithm.sort;

/**
 * 回文数字判断
 */
public class Palindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome_method2(0));
    }

    public static boolean isPalindrome_method2(int x) {
        if (x < 0 || (x > 0 && x % 10 == 0)) {
            return false;
        }

        int a = 0;

        while (a < x) {
            a = a * 10 + (x % 10);
            x = x / 10;
        }

        return a == x || x == a / 10;
    }

    public static boolean isPalindrome_method1(int x) {
        if (x < 0) {
            return false;
        } else if (x == 0) {
            return true;
        }

        char[] arr = String.valueOf(x).toCharArray();
        char[] arr2 = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr2[i] = arr[arr.length - i - 1];
        }

        for (int i = 0; i < arr2.length; i++) {
            if (arr2[i] != arr[i]) {
                return false;
            }
        }

        return true;
    }

}
