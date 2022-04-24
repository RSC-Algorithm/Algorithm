package tixi.class04;

/**
 * Description:
 *
 * @author madanmei
 * @version 1.0.0
 */

public class Code04_Reverse {
    public static int reverPairNumber(int[] arr) {
        //notice: 边界条件判定
        if (arr==null || arr.length<2){
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);

        //notice: sum 不用传入进去。递归想最外一层，sum 等于左右两个子集计算好的结果，再加上左右子集merge过程出现的数据即可
        return process(arr, l, m)
                +
                process(arr, m + 1, r)
                +
                merge(arr, l, m, r);

    }

    private static int merge(int[] arr, int l, int m, int r) {
        int p1 = m;
        int p2 = r;
        int res = 0;
        int i = r-l;
        int[] help = new int[r - l + 1];
        while (p1 >= l && p2 > m) {
            res += arr[p1] > arr[p2] ? (p2-m): 0;

            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        //notice: L 要大写！
        while (p1>=l){
            help[i--]=arr[p1--];
        }
        while (p2>m){
            help[i--]=arr[p2--];
        }
        for ( i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }

    public static int merge1(int[] arr, int L, int m, int r) {
        int[] help = new int[r - L + 1];
        int i = help.length - 1;
        int p1 = m;
        int p2 = r;
        int res = 0;
        while (p1 >= L && p2 > m) {
            res += arr[p1] > arr[p2] ? (p2 - m) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= L) {
            help[i--] = arr[p1--];
        }
        while (p2 > m) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 100;
        int maxSize = 20;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (reverPairNumber(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
} 
