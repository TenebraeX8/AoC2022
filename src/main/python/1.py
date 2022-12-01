from aoc import *

content = open(f"input/1.inp").read()
sections = content.split("\n\n")
sums = [sum([int(x) for x in section.split("\n")]) for section in sections]
sorted_sums = sorted(sums)
print(f"Part 1: {sorted_sums[-1]}")
print(f"Part 2: {sum(sorted_sums[-3:])}")


#One liner
print((lambda inp: (lambda sums: (sums[-1], sum(sums[-3:])))(sorted([sum([int(x) for x in section.split("\n")]) for section in sections])))(open(f"input/1.inp").read()))