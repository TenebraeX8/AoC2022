from aoc import *


#Rock(0), Paper(1), Scissor(2)
# y(you) - x(enemy)
# 0 - 0 = 0         //draw
# 1 - 1 = 0         //draw
# 2 - 2 = 0         //draw
# 0 - 1 = -1 = 2%3  //lose
# 1 - 2 = -1 = 2%3  //lose
# 2 - 0 = 2         //lose
# 0 - 2 = -2 = 1%3  //win
# 1 - 0 = 1         //win
# 2 - 1 = 1         //win
# add 1 in modulo class 3 to shift 0 (draw)-> 1, 1 (win) -> 2, 2 (lose) -> 0

x = ["A Y","B X","C Z"]
inp = [(ord(line[0]) - ord('A'), ord(line[2]) - ord('X')) for line in read_input(2)]
sums = [3*((y-x + 4) % 3) + y + 1 for (x,y) in inp]
print(f"Part 1: {sum(sums)}")

sums = [3 * y + ((x + y + 2) % 3) + 1 for (x,y) in inp]
print(f"Part 2: {sum(sums)}")

#One Liner
print((lambda inp: (sum([3*((y-x + 4) % 3) + y + 1 for (x,y) in inp]), sum([3 * y + ((x + y + 2) % 3) + 1 for (x,y) in inp])))([(ord(line[0]) - ord('A'), ord(line[2]) - ord('X')) for line in read_input(2)]))