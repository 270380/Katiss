import java.util.*;

public class programmingTeamSelection {
    static int[][] group;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n;

        while ((n = input.nextInt()) != 0) {
            int count = 0;
            HashMap<String, Integer> names = new HashMap<>();
            String[][] person_is_match = new String[n][2];
            for (int i = 0; i < n; i++) {
                String name1 = input.next(), name2 = input.next();
                person_is_match[i][0] = name1;
                person_is_match[i][1] = name2;
                if (!names.containsKey(name1)) names.put(name1, count++);
                if (!names.containsKey(name2)) names.put(name2, count++);
            }

            boolean[][] graph = new boolean[names.size()][names.size()];
            for (int i = 0; i < n; i++) {
                graph[names.get(person_is_match[i][0])][names.get(person_is_match[i][1])] = true;
                graph[names.get(person_is_match[i][1])][names.get(person_is_match[i][0])] = true;
            }

            if (names.size() % 3 == 0) {
                group = new int[names.size() / 3][3];
                group[0][0] = 0;
                if (solve(names.size() / 3, names.size() - 1, 1, 0, 1, graph))
                    System.out.println("possible");
                else System.out.println("impossible");
            } else System.out.println("impossible");

        }
    }

    static boolean solve(int size, int number_person, int count, int row, int col, boolean[][] graph) {
        boolean b = false;
        if (number_person == 0) return true;
        else if (count + number_person != size * 3) return false;
        else {
            if (col == 3) {
                for (int i = 0; i < size * 3; i++)
                    if (check(row, 3, i)) {
                        group[row + 1][0] = i;
                        b = solve(size, number_person - 1, count + 1, row + 1, 1, graph);
                        group[row + 1][0] = 0;
                        if (b) break;
                    }
            } else {
                for (int i = 0; i < size * 3; i++) {

                    int j;
                    for (j = 0; j < col; j++) {
                        if (!graph[i][group[row][j]]) break;
                    }
                    if (j == col) {
                        if (check(row, col, i)) {
                            group[row][col] = i;
                            b = solve(size, number_person - 1, count + 1, row, col + 1, graph);
                            group[row][col] = 0;
                            if (b) break;
                        }
                    }

                }
            }
            return b;
        }
    }

    static boolean check(int row, int col, int i) {
        for (int j = 0; j < row; j++) {
            for (int k = 0; k < 3; k++) {
                if (group[j][k] == i) return false;
            }
        }
        for (int j = 0; j < col; j++)
            if (group[row][j] == i) return false;
        return true;
    }
}
