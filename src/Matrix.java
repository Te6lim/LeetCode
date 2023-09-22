import java.util.Arrays;
import java.util.Stack;

public class Matrix {

    public int maxProductPath(int[][] grid) {
        int size = grid.length * grid[0].length;
        Stack<Integer> stack = new Stack<>();
        stack.add(0);

        Integer[] total = new Integer[size];
        Integer[] parent = new Integer[size];
        Arrays.fill(total, null);
        Arrays.fill(parent, null);
        int current;
        while (!stack.isEmpty()) {
            current = stack.pop();
            if (parent[current] == null) total[current] = grid[row(current, grid[0].length)][col(current, grid[0].length)];
            else {
                int t = total[parent[current]] * grid[row(current, grid[0].length)][col(current, grid[0].length)];
                if (current == size - 1) {
                    if (total[current] != null) {
                        if (t % (int)(Math.pow(10, 9) + 7) > total[current]) total[current] = t % (int)(Math.pow(10, 9) + 7);
                    } else total[current] = t % (int)(Math.pow(10, 9) + 7);
                } else total[current] = t % (int)(Math.pow(10, 9) + 7);
            }

            if (col(current, grid[0].length) < grid[0].length - 1) {
                stack.push(current + 1);
                parent[current + 1] = current;
            }
            if (current + grid[0].length < size) {
                stack.push(current + grid[0].length);
                parent[current + grid[0].length] = current;
            }
        }

        if (total[size - 1] < 0) return -1;
        return total[size - 1];
    }

    private int row(int c, int length) {
        return c / length;
    }

    private int col(int c, int length) {
        return c % length;
    }

    public static void main(String[] args) {
        int v = -1 % 10;
        System.out.println(v);
    }
}