
class Magic_Square {
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