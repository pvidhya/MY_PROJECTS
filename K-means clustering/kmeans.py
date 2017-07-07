#Python 3.0

# 	def clustering(self, kvalue):

# 	#function to implement kmeans clustering algorithm
# 	#Print out:
# 	##For each cluster, its RSS values and the document ID of the document closest to its centroid.
#     ##Average RSS value
# 	##Time taken for computation.

from __future__ import division
from itertools import islice
from collections import Counter
from collections import defaultdict
import math
import re
import os
import collections
import time


class kmeans:
	def __init__(self,path_to_collection):
		self.path = '.'

	def read_doc(self):
    	###reading the TIME.ALL document and creating a list if document contents.
		f = open('TIME.ALL', "r")
		co = f.read()
		token = re.sub('\W+', ' ', co)
		token = token.split()
		token = [x for x in token if not (x.isdigit() or x[0] == '-' and x[1:].isdigit())]

        ####reading STOP words####
		f = open('TIME.STP', "r")
		co = f.read()
		token_stp = re.sub('\W+', ' ', co)
		token_stp = token_stp.split()

        ####removing STOP words from document####
		token_terms = []
		for terms in token:
			if terms not in token_stp:
				token_terms.append(terms)

		with open('TIME.ALL') as f:
			file_name = []
			for line in f:
				if '*TEXT' in line:
					file_name.append(line[1:9])

		uid = []
		for i in range(len(file_name)):
			fi = i
			uid.append(fi)
			i = i + 1
        # global uidict
		uidict = {}
		uidict = dict(zip(file_name, uid))
        ###creating a list with its elements being the contents of the documents.
		with open('TIME.ALL') as f:
			buf = f.read()

		li_docs = []
		x = 0
		line = buf.splitlines()
		for m in (buf.splitlines()):
			if (len(m.strip()) != 0):
				if m[0:5] == "*TEXT" or m[0:5] == "*STOP":
					if x != 0:
						li_docs.append(wr + ' ')
					wr = ""
					x = 1
				else:
					if len(wr) == 0:
						wr = m
					else:
						wr = wr + ' ' + m
		global docs
		docs = []
		for x in li_docs:
			t = re.sub('\W+', ' ', x)
			t = t.split()
			docs.append(t)
		return docs

	def dic_cont(self, docs):

        #####Calculating the number of documents in which the particular term occurs.
		global dic_count
		dic_count = {}
		for x in docs:
		    so_list = []
		    for term in x:
		        if term not in dic_count.keys():
		            dic_count[term] = 1.0
		        else:
		            if term not in so_list:
		                dic_count[term] += 1.0
		        so_list.append(term)
		return dic_count

	def dictn(self, docs):
        ######calculating the doc_id and term frequencies for each document#######
		global dic
		dic = {}
		for x in range(len(docs)):
		    for n in (docs[x]):
		        if n not in dic.keys():
		            dic[n] = [[x + 1, 1.0]]
		        else:
		            if (x + 1) not in [b[0] for b in [a for a in dic[n]]]:
		                dic[n].append([x + 1, 1.0])
		            else:
		                for a in range(len(dic[n])):
		                    if (dic[n][a][0]) == x + 1:
		                        dic[n][a][1] += 1
		# print (dic)
		return dic

def ftf_idf_norm(dic, dic_count):
    ######tf-idf vector form######
    n_docs = 423
    global tf_idf_norm
    tf_idf_norm = defaultdict(lambda: defaultdict(lambda: 0))
    for m in dic.keys():
      for n in dic[m]:
        tf_idf_norm[n[0]][m] = ((1 + math.log10(n[1])) * math.log10(n_docs / dic_count[m])) ** 2
    # print (tf_idf_norm)
    return (tf_idf_norm)

def means(k, tf_idf_norm):
    i = 0
    means = []
    means = {x: tf_idf_norm[x] for x in list(tf_idf_norm.keys())[:5]}
    # for x  in tf_idf_norm.items():
    #   while len(means)<k:
    #     if x not in means:
    #       means.append(x)
    #       i = i+1 
    print (means)    
def main():
    path_to_collection = "/Users/Vidhy/Desktop/COURSES/IR/IR_ASG4"
    ind = kmeans(path_to_collection)
    total_docs = ind.read_doc()
    dic_id = ind.dictn(total_docs)
    dic_count = ind.dic_cont(docs)
    tf_idf_norm = ftf_idf_norm(dic, dic_count)
    k=5
    m = means(k, tf_idf_norm)
    print ("Hello")


if __name__ == '__main__':
    main()
    