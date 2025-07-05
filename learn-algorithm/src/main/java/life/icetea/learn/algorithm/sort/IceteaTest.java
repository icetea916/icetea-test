package life.icetea.learn.algorithm.sort;

import java.util.Arrays;

public class IceteaTest {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 6, 26, 10};
        int s = 18;

        int sum = sum(arr, s);
        System.out.println(sum);
    }

    public static int sum(int[] arr, int s) {
        // 排序
        Arrays.sort(arr);
        int offset = Integer.MAX_VALUE;
        int sum = -1;
        for (int i = 0; i < arr.length; i++) {
            int leftIndex = i + 1;
            int rightIndex = arr.length - 1;
            int total = 0;
            while (leftIndex < rightIndex) {
                total = arr[leftIndex] + arr[rightIndex] + arr[i];
                if (offset > Math.abs(total - s)) {
                    offset = Math.abs(total - s);
                    sum = total;
                }
                if (s > total) {
                    leftIndex++;
                } else if (s < total) {
                    rightIndex--;
                } else {
                    // 相等
                    break;
                }
            }
        }
        return sum;
    }

}
