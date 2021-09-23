import java.io.*;

class Magic_Square {
    static BufferedReader kl = new BufferedReader(new InputStreamReader(System.in));
    static Magic_Square obj = new Magic_Square();

    public static void main(String[] args) throws IOException {
        System.out.println("Enter any Odd Number as a length and breadth of Magic Square");
        int length = Integer.parseInt(kl.readLine());
        int array[][] = new int[length][length];
        if (length % 2 == 0) {
            System.out.println("Error not an odd number");
        } else {
            array = obj.array(length);
            obj.print(length, array);
        }

    }

    void print(int length, int[][] array) {

        // Printing the array
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }

    int[][] array(int length) {
        int array[][] = new int[length][length];
        int row, column, r, c, Number;
        row = 0;
        column = (length - 1) / 2;
        r = row;
        c = column;
        Number = 1;
        // Filling Array
        while (Number <= (length * length)) {
            array[row][column] = Number;
            r--;
            c++;
            if (r < 0) {
                r = length - 1;
            }
            if (c > length - 1) {
                c = 0;
            }
            if (array[r][c] != 0) {
                row++;
                r = row;
                c = column;
            } else {
                row = r;
                column = c;
            }
            Number++;
        }
        return array;
    }
}