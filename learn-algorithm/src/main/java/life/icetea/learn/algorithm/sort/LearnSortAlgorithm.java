package life.icetea.learn.algorithm.sort;

import java.util.Arrays;

/**
 * @author icetea
 * @date 2023-01-31 14:29
 */
public class LearnSortAlgorithm {

    public static void main(String[] args) {
        int[] arr = {3, 2, 45, 22, 38, 44, 26, 19, 46, 78, 50};

        bubbleSort(arr);
        selectiveSort(arr);
        insertSort(arr);
        shellSort(arr);
    }

    /**
     * 冒泡排序
     * 时间复杂度：最佳O(n); 最差O(n²)
     * 空间复杂度：O(1)
     * 排序方式：In-place
     */
    private static void bubbleSort(int[] arr) {
        int[] cloneArr = arr.clone();
        for (int i = 1; i < cloneArr.length; i++) {
            for (int j = 0; j < cloneArr.length - i; j++) {
                if (cloneArr[j] > cloneArr[j + 1]) {
                    int temp = cloneArr[j];
                    cloneArr[j] = cloneArr[j + 1];
                    cloneArr[j + 1] = temp;
                }
            }
        }
        Arrays.stream(cloneArr).forEach(v -> System.out.print(v + ","));
        System.out.println();
    }

    /**
     * 选择排序
     * 时间复杂度：最佳O(n²); 最差O(n²)
     * 空间复杂度：O(1)
     * 排序方式：In-place
     */
    private static void selectiveSort(int[] arr) {
        int[] cloneArr = arr.clone();
        for (int i = 0; i < cloneArr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < cloneArr.length; j++) {
                if (cloneArr[minIndex] > cloneArr[j]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                int temp = cloneArr[i];
                cloneArr[i] = cloneArr[minIndex];
                cloneArr[minIndex] = temp;
            }
        }
        Arrays.stream(cloneArr).forEach(v -> System.out.print(v + ","));
        System.out.println();
    }

    /**
     * 插入排序
     * 时间复杂度：最佳O(n)；最差O(n²)
     * 空间复杂度：O(1)
     * 排序方式：In-place
     */
    private static void insertSort(int[] arr) {
        int[] cloneArr = arr.clone();
        for (int i = 1; i < cloneArr.length; i++) {
            int preIndex = i - 1;
            int temp = cloneArr[i];
            while (preIndex >= 0 && temp < cloneArr[preIndex]) {
                cloneArr[preIndex + 1] = cloneArr[preIndex];
                preIndex--;
            }
            cloneArr[preIndex + 1] = temp;
        }
        Arrays.stream(cloneArr).forEach(v -> System.out.print(v + ","));
        System.out.println();
    }

    /**
     * 希尔排序
     * 时间复杂度：最佳O()；最差O()
     * 空间复杂度：O(1)
     * 排序方式：In-place
     */
    private static void shellSort(int[] arr) {
        int[] cloneArr = arr.clone();

        int gap = arr.length / 2;
        while (gap > 0) {
            for (int i = 0; i < cloneArr.length; i = i + gap) {
                int preIndex = i - 1;
                int temp = cloneArr[i];
                while (preIndex >= 0 && temp < cloneArr[preIndex]) {
                    cloneArr[preIndex + 1] = cloneArr[preIndex];
                    preIndex--;
                }
                cloneArr[preIndex + 1] = temp;
            }
            gap = gap / 2;
        }

        Arrays.stream(cloneArr).forEach(v -> System.out.print(v + ","));
        System.out.println();
    }

}
