import random
import re

__author__ = 'Nikhil Singh'
__email__ = 'nik.singh710@gmail.com'
__github__ = 'https://github.com/niksingh710'
__telegram__ = 'https://t.me/niksingh710'


class Board:
    def __init__(self, dimension, bomb_nums):
        self.dimension = dimension
        self.bomb_nums = bomb_nums

        # creating board and putting bombs in it
        self.board = self.make_new_board()
        self.assign_value_to_board()

        self.dug = set()

    def make_new_board(self):
        board = [[None for _ in range(self.dimension)]
                 for _ in range(self.dimension)]

        # planting bombs
        bombs_planted = 0
        while bombs_planted < self.bomb_nums:
            loc = random.randint(0, self.dimension**2-1)
            row = loc//self.dimension
            col = loc % self.dimension
            if board[row][col] == '*':
                continue
            board[row][col] = '*'
            bombs_planted += 1
        return board

    def assign_value_to_board(self):

        for r in range(self.dimension):
            for c in range(self.dimension):
                if self.board[r][c] == '*':
                    continue
                self.board[r][c] = self.get_neighbor_bomb_num(r, c)

    def get_neighbor_bomb_num(self, row, col):
        num_neighboring_bomb = 0
        for r in range(max(0, row-1), min(row+1, self.dimension-1)+1):
            for c in range(max(0, col-1), min(col+1, self.dimension-1)+1):
                if r == row and c == col:
                    continue
                if self.board[r][c] == '*':
                    num_neighboring_bomb += 1
        return num_neighboring_bomb

    def dig(self, row, col):
        self.dug.add((row, col))

        if self.board[row][col] == '*':
            return False
        elif self.board[row][col] > 0:
            return True
        for r in range(max(0, row-1), min(row+1, self.dimension-1)-1):
            for c in range(max(0, col-1), min(col+1, self.dimension-1)+1):
                if (r, c) in self.dug:
                    continue
                self.dig(r, c)

        return True

    def __str__(self):

        view_board = [[None for _ in range(self.dimension)]
                      for _ in range(self.dimension)]
        for r in range(self.dimension):
            for c in range(self.dimension):
                view_board[r][c] = str(self.board[r][c]) if (
                    r, c) in self.dug else ' '
        main_str = ""
        str_top = "   "
        str_top += "  ".join(str(i) for i in range(self.dimension))
        str_sep = '-' * (len(str_top)+2)
        cols = ""

        for i in range(self.dimension):
            cols += f"{i}|"
            for j in range(self.dimension):
                cols += ' '.join(f'{view_board[i][j]}|')
            cols += '\n'

        main_str = f"{str_top}\n{str_sep}\n{cols}"
        return main_str


def play(dim_size=10, num_bombs=10):
    board = Board(dim_size, num_bombs)

    safe = True

    while len(board.dug) < board.dimension ** 2 - num_bombs:
        print(board)
        user_input = re.split(
            ',(\\s)*', input("Where would you like to dig? Input as row,col: "))  # '0, 3'
        row, col = int(user_input[0]), int(user_input[-1])
        if row < 0 or row >= board.dimension or col < 0 or col >= board.dimension:
            print("Invalid location. Try again.")
            continue

        safe = board.dig(row, col)
        if not safe:
            break

    if safe:
        print("CONGRATULATIONS!!!! YOU ARE VICTORIOUS!")
    else:
        print("SORRY GAME OVER :(")
        board.dug = [(r, c) for r in range(board.dimension)
                     for c in range(board.dimension)]
        print(board)


if __name__ == '__main__':
    import argparse

    parser = argparse.ArgumentParser(
        description='Minesweeper (default size is 10x10 with 10 bomb)')
    parser.add_argument('-s', '--size', dest='size', type=int,
                        help='Size of the matrix')
    parser.add_argument('-b', '--bomb', dest='bombs', type=int,
                        help='Number of bombs')

    args = parser.parse_args()

    size = args.size or 10
    bombs = args.bombs or 10
    play(size, bombs)
