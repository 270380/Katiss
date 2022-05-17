import java.util.Scanner;

public class balancedDiet {
    static int value1, value2, min_different;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number_cans;
        while ((number_cans = input.nextInt()) != 0) {
            int sum1 = 0;
            int[] cans = new int[number_cans];
            for (int i = 0; i < number_cans; i++) {
                sum1 += cans[i] = input.nextInt();
            }
            min_different = Integer.MAX_VALUE;
            solve(0, number_cans, sum1, 0, cans);
            System.out.println(value1 + " " + value2);
        }
    }

    static void solve (int i, int n, int sum1, int sum2, int[] cans) {
        if (i == n) {
            if (sum1 - sum2 < min_different) {
                min_different = sum1 - sum2;
                value1 = sum1; value2 = sum2;
            }
        }
        else {
            solve(i + 1, n, sum1, sum2, cans);
            if (sum2 + cans[i] <= sum1 - cans[i])
                solve(i + 1, n, sum1 - cans[i], sum2 + cans[i], cans);
        }
    }
}
