# Python 3.0
from collections import defaultdict
import re
import os
import collections
import time


class pagerank:
	def __init__(self, path):
		self.path = '.'

		###reading the text file
	def read_doc(self, filename):
		with open(filename) as f:
			input_file = f.read()
		
		n = input_file[0]

		inlinks = defaultdict(list)
		outlinks = defaultdict(list)
		###creating two dictionaries for inlinks and oulinks, respectively
		for line in open(filename).readlines():
			lis = list(map(int, line.split()))
			if len(lis) > 1:
				u = lis[0]
				u1 = lis[1]
				outlinks[u].append(lis[1])
				inlinks[u1].append(lis[0])
		return input_file, inlinks, outlinks

	def page_rank(self, input_file, inlinks, outlinks):
		###creating a list of all pages in the text file
		pages = []
		for value in outlinks:
			pages.append(value)

		for value in inlinks:
			pages.append(value)

		pages_list = list(set(pages))

		b = 0.14
		iters = 13

		###using defaultdict with default values 1.0 for the initial probabilty values for each page
		P_R = defaultdict(lambda: 1.0)
		for p in pages_list:
			P_R[p] = 1.0 / len(pages_list)
		P_R_new = defaultdict()	
		
		###running the loop for number of times mentioned in the iters
		###running the power method to find the page rank for each page	
		for i in range(iters):
			for p in pages_list:
				sum_ = 0.0
				for w in pages_list:
					if w in inlinks[p]:
						if len(outlinks[w])!=0:
							const1 = 1.0 / len(outlinks[w])
						else:
							const1 = 1.0 / len(pages_list)
					else:
						const1 = 0
					const1 = (b / len(pages_list)) + ((1.0 - b) * const1)
					
					sum_ += const1*P_R[w]
				P_R_new[p] = sum_
			P_R = P_R_new	
		###sorting the dictionary of page ranks
		global sort_dic
		sort_dic = sorted(P_R.items(), key = lambda x:x[1], reverse = True)
		print (sort_dic)
		return sort_dic

def main():
	path = "/Users/Vidhy/Desktop/COURSES/IR/IR_ASG4"
	pr = pagerank(path)
	filename = "test2.txt"
	input_file, inlinks, outlinks = pr.read_doc(filename)
	rank = pr.page_rank(input_file, inlinks, outlinks)
	###writing the values of each page and its page rank into the "Output.txt" file
	with open('Output.txt', 'w') as fp:
			fp.write(filename+ '\n')
			fp.write('\n'.join('%s %s' % x for x in sort_dic))
if __name__ == '__main__':
    main()

	
