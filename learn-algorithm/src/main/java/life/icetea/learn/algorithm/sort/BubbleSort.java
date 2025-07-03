package life.icetea.learn.algorithm.sort;

public class BubbleSort {


    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 4, 5, 6, 2, 3412, 123};
        bubbleSort(arr);
        for (int a = 0; a < arr.length; a++) {
            System.out.print(arr[a] + ", ");
        }
        System.out.println();
    }


    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int x = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = x;
                }
            }
        }
    }

}
