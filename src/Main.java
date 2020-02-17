import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] input = line.split("\\s+");
        int length = Integer.parseInt(input[0]);
        int width = Integer.parseInt(input[1]);

        int[][] matrix = new int[length][width];
        for (int[] row : matrix) {
            for (int x : row) {
                x= 0;
            }
        }

        line = in.nextLine();
        int count = Integer.parseInt(line);
        List<int[]> vehicles = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            line = in.nextLine();
            input = line.split("\\s+");
            int l = Integer.parseInt(input[0]);
            int w = Integer.parseInt(input[1]);
            if (l < w) {
                int temp = l;
                l = w;
                w = temp;
            }
            int[] v = {i + 1, l, w, l*w};
            vehicles.add(v);
        }

        Comparator<int[]> comparebysize = (int[] a, int [] b) -> b[3] - a[3];

        Collections.sort(vehicles, comparebysize);

        int k = 0;
        boolean free = false;
        int ilimit = 0;
        int jlimit = 0;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 0) {
                    free = false;
                    if (width - j >= vehicles.get(k)[1] && length - i >= vehicles.get(k)[2]) {
                        free = true;
                        ilimit = i + vehicles.get(k)[2];
                        jlimit = j + vehicles.get(k)[1];
                        for (int ib = i; ib < ilimit && free; ib++) {
                            for (int jb = j; jb < jlimit && free; jb++) {
                                if (matrix[ib][jb] != 0)
                                    free = false;
                            }
                        }
                    }
                    int num = (int)Math.ceil((double)(length)/15);
                    if (!free && width - j >= vehicles.get(k)[2] && length - i-num >= vehicles.get(k)[1]) {
                        free = true;
                        ilimit = i + vehicles.get(k)[1];
                        jlimit = j + vehicles.get(k)[2];
                        for (int ib = i; ib < ilimit && free; ib++) {
                            for (int jb = j; jb < jlimit && free; jb++) {
                                if (matrix[ib][jb] != 0)
                                    free = false;
                            }
                        }
                    }
                    if (free) {
                        for (int ib = i; ib < ilimit; ib++) {
                            for (int jb = j; jb < jlimit; jb++) {
                                matrix[ib][jb] = vehicles.get(k)[0];
                            }
                        }
                        k++;
                        if (k == count)
                            break;
                        i = 0;
                        j = 0;
                    }
                }
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(matrix[i][j]);
                if (j < width-1)
                    System.out.print("\t");
                else if (i < length-1)
                    System.out.println();
            }
        }
    }
}
