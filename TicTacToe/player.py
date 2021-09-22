import random


class Player:
    # Base Class of Player Type takes the player Playing Input ['X','O'] and initializes it.
    def __init__(self, letter):
        self.letter = letter

    def get_move(self):
        pass


class RandomComputerPlayer(Player):
    # This Class inherits from the Player class
    def __init__(self, letter):
        super().__init__(letter)

    # Base class function is overridden and in prameter the game instance is passed.
    # Returns random value from the available spaces in the game board.
    def get_move(self, game):
        return random.choice(game.available_space())


class HumanPlayer(Player):
    # Also Inherits from the Player class. but asks value from the user.
    def __init__(self, letter):
        super().__init__(letter)

    # Till the input is not a integer and in range (0-8) it will ask ask input
    def get_move(self, game) -> int:
        valid_move = False
        while not valid_move:
            move = input("Enter your move in between (0-8): ")
            try:
                move = int(move)
                if move not in game.available_space():
                    raise ValueError
                valid_move = True
            except ValueError:
                print("Invalid Input please Try again")
        return move
