import java.util.LinkedList;
import java.util.Scanner;

public class busPlanning {
    static boolean[][] graph;
    static int n, k, c, number = Integer.MAX_VALUE;
    static LinkedList<LinkedList<Integer>> person;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        k = input.nextInt();
        c = input.nextInt();
        graph = new boolean[n][n];
        String[] name = new String[n];

        for (int i = 0; i < n; i++) name[i] = input.next();
        for (int i = 0; i < k; i++) {
            int index1 = -1, index2 = -1;
            String name1 = input.next(), name2 = input.next();
            for (int j = 0; j < n; j++) {
                if (name1.equals(name[j])) index1 = j;
                else if (name2.equals(name[j])) index2 = j;
                if (index1 != -1 && index2 != -1) break;
            }
            graph[index1][index2] = true;
            graph[index2][index1] = true;
        }

        int[] coloring = new int[n], num_person = new int[n];
        color(0, 0, num_person, coloring);
        System.out.println(number);
        for (LinkedList<Integer> integers : person) {
            while (!integers.isEmpty())
                System.out.print(name[integers.removeFirst()] + " ");
            System.out.println();
        }

    }

    static void color(int i,int num_color, int[] num_person, int[] vertices_color) {
        if (i == n) {
            if (num_color < number) {
                number = num_color;
                person = null;
                person = new LinkedList<>();
                for (int j = 0; j < number; j++) person.add(new LinkedList<>());
                for (int j = 0; j < n; j++) person.get(vertices_color[j]).add(j);
            }
        } else {
            if (num_color < number) {
                for (int j = 0; j < num_color; j++) {
                    if (num_person[j] < c && promising(i, j, vertices_color)) {
                        num_person[j]++;
                        vertices_color[i] = j;
                        color(i + 1, num_color, num_person, vertices_color);
                        num_person[j]--;
                    }
                }
                if (num_person[num_color] < c) {
                    num_person[num_color]++;
                    vertices_color[i] = num_color;
                    color(i + 1, num_color + 1, num_person, vertices_color);
                    num_person[num_color]--;
                }
            }
        }
    }

    static boolean promising(int i, int color, int[] coloring) {
        for (int j = 0; j < i; j++)
            if (graph[i][j] && coloring[j] == color) return false;
        return true;
    }
}