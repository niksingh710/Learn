import sys
from player import HumanPlayer, RandomComputerPlayer
from tictactoe import TicTacToe

__author__ = 'Nikhil Singh'
__email__ = 'nik.singh710@gmail.com'
__github__ = 'https://github.com/niksingh710'
__telegram__ = 'https://t.me/niksingh710'


def play(game, x_player, o_player, verbose=False):
    if verbose:
        game.default_board()
    letter = 'X'
    while game.contains_space():
        move = x_player.get_move(
            game) if letter == "X" else o_player.get_move(game)

        if game.make_move(move, letter):
            if verbose:
                print(f"{letter} has made the move {move}")
                print(game)
            if game.winner:
                if verbose:
                    print(f"{letter} has won!")
                return letter
            letter = "O" if letter == "X" else "X"
    if verbose:
        print("It's a tie.")


if __name__ == '__main__':
    x_player, o_player = HumanPlayer("X"), RandomComputerPlayer("O")
    game = TicTacToe()
    play(game, x_player, o_player, True)
