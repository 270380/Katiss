import java.util.Scanner;

public class coloringGraphs {
    static int n, number = Integer.MAX_VALUE;
    static boolean[][] graph;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        input.nextLine();
        int[] coloring = new int[n];
        graph = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String[] s = input.nextLine().split(" ");
            for (String value : s) graph[i][Integer.parseInt(value)] = true;
        }
        color(0, 0, coloring);
        System.out.println(number);
    }

    static void color(int i,int num_color, int[] coloring) {
        if (i == n) {
            if (num_color < number) number = num_color;
        } else {
            for (int j = 0; j < num_color; j++) {
                if (promising(i, j, coloring)) {
                    coloring[i] = j;
                    color(i + 1, num_color, coloring);
                }
            }
            coloring[i] = num_color;
            color(i + 1, num_color + 1, coloring);
        }
    }

    static boolean promising(int i, int color, int[] coloring) {
        for (int j = 0; j < i; j++)
            if (graph[i][j] && coloring[j] == color) return false;
        return true;
    }
}
