#Python 3.0
import re
import os
import collections
import time
import math
import operator
import random
import math
from math import sqrt
from itertools import izip

class index:
		
	def __init__(self,path):
		self.path= path

	def buildIndex(self):
		start_time = time.time()
		dirs = os.listdir(self.path)
		file1= []

		for file in dirs:
			f= open(file, "r")
		 	co= f.read()
		 	file1.append(file)
			token= re.sub('\W+',' ', co)
			token= token.split()
		
		token = [' {0} '.format(elem) for elem in token]
		N= len(file1)
		lis=[]
		for term in token:
			if term not in lis:
				lis.append(term)
		lis_no_int = [x for x in lis if not (x.isdigit() or x[0] == '-' and x[1:].isdigit())]
		uid= []
		for i in range(len(file1)):
		    fi= i
		    uid.append(fi)
		    i = i+1
		global uidict
		uidict= {}
		uidict = dict(zip(file1,uid))
		# print uidict
		global dic
		dic= {}
		global dic1
		dic1 = {}
		global dic_z
		dic_z= {}
		s_w=[]
		sp= open("stop-list.txt", "r")
		# s_w= sp.readlines()
		s_w= sp.read().splitlines()
		s_w= [' {0} '.format(elem) for elem in s_w]
		s_w= [x.upper() for x in s_w]
		# print s_w
		dictdf={}

		for f in file1:
			fi= open(f, "r")
			co= fi.read()
			x=set(co.split())
			x = [' {0} '.format(elem) for elem in x]
			#lis_no_int = [x for x in lis if not (x.isdigit() or x[0] == '-' and x[1:].isdigit())]
			###################remove stop words################

			for t in x:
				if t not in dictdf:
					dictdf[t] = 1
					dictdf[t] = math.log10(float(N/dictdf[t]))
				else:
					dictdf[t] += 1
					dictdf[t] = math.log10(float(N/dictdf[t]))
			
		for f in file1:
			fi= open(f, "r")
			co= fi.read()
			x=set(co.split())
			# x = [' {0} '.format(elem) for elem in z]
			lis_no_int = [x for x in lis if not (x.isdigit() or x[0] == '-' and x[1:].isdigit())]
			###################remove stop words################
			
			list_f=[]
			for t in lis_no_int:
				if t not in s_w:
					list_f.append(t)
			
			for y in list_f:
				l1=[]
				lf=[]
				w=[]
				w1= []
				r=()
				if y in lis_no_int:
					for m in re.finditer(y,co):
						l1.append(m.start())
					if len(l1)>0:
						fre= len(l1)
						w=(1+ math.log10(float(fre)))
						r=(uidict[f], w ,l1)
						# w1= [wf,w]
						lf.append(uidict[f])
						# print lf
						if y in dic:
							temp=dic[y]
							temp.append(r)
							dic[y]=temp
							dic_z[y]= w
						else:
							dic[y]=[dictdf[y]]
							dic[y].append(r)
							# w1= [wf,w]
							dic_z[y]= w
		# print (dic_z)				
		####### indexing the terms using dic #######
		global dic3
		dic3= dic.copy()
		global dic_cha
		dic_cha= {}

		global dic4
		dic4= dic.copy()
		tfidfdict={}
		for k,v in dic3.iteritems():
			idf=v[0]
			t_t= ()
			for i in range(1,len(v)):
				doc_id=v[i][0]
				tf=v[i][1]

				if tfidfdict.has_key(doc_id):
					new = tfidfdict[doc_id]
					old=new[:]
					x=float(idf)*float(tf)
					t_t=(k,x)
					old.append(t_t)
					tfidfdict[doc_id]= old
				else:
					x=float(idf)*float(tf)
					t_t=(k,x)
					tfidfdict[doc_id]=[t_t]
					

		for k,v in dic.items():
			dic[k]= len(v)
			##########idf#########
			dic[k]= math.log10(N/float(len(v)))
		# print "Index built in %s seconds." % (time.time() - start_time)

		dic2= dic.copy()
		global di1	
		#######calculating tf-idf vectors#########
		di1={}
		# for k,v in dic.items():
		# 	for k,v1 in dic_z.items():
		# 		di1[k]= v**v1
		
		# return di1

	def exact_query(self, query_terms, k=4):
		k= 4
		start_time = time.time()
	###### tf of the query######
		global di1
		global query_tf
		query_tf= {}
		for x in query_terms:
			if x not in query_tf.keys():
				query_tf[x]= 1
			else:
				query_tf[x]= query_tf[x]+1
			query_tf[x] = (1+math.log10(query_tf[x]))				
	###### idf of the query######	
		
		global query_idf
		query_idf= {}
		for x in query_terms:
			if x in dic.keys():
				query_idf[x]= dic[x]
			else:
				pass

	###### tf-idf of the query######
		global q_tf_idf
		q_tf_idf= {}

		for k,v in query_tf.items():
			for k, v1 in query_idf.items():
				q_tf_idf[k]= v*v1
		##########final dictionary for query terms in query##########
		print q_tf_idf

		cosi={}
		for k,v in dic3.items():
			if k in query_terms:
				cosi[k]= v
		global tfidfdict
		tfidfdict={}
		for k,v in cosi.iteritems():
			idf=v[0]
			t_t= ()
			for i in range(1,len(v)):
				doc_id=v[i][0]
				tf=v[i][1]
				if tfidfdict.has_key(doc_id):
					new = tfidfdict[doc_id]
					old=new[:]
					x=float(idf)*float(tf)
					t_t=(k,x)
					old.append(t_t)
					tfidfdict[doc_id]= old
				else:
					x=float(idf)*float(tf)
					t_t=(k,x)
					tfidfdict[doc_id]=[t_t]
		##########final dictionary for query terms in document##########

 		gh = []
		for k, v in q_tf_idf.items():
			gh.append(q_tf_idf[k])
		

		values_big = []
		for k, v in tfidfdict.items():
				list_val = tfidfdict[k]
				sub = len(gh) - len(list_val)
				list_valnew = [(0,0)] * sub
				list_val.extend(list_valnew)
				values_small = []
				for (a, b) in list_val:
					if q_tf_idf.has_key(a):
						values_small.append(b)
					else:
						values_small.append(0)
				values_big.append(values_small)
						
		def cosine_similarity(a, b):
			start_time = time.time()
			d = [i*j for i,j in zip(a, b)]
			numerator = sum(d)
			a1 = [i*i for i in a]
			a2 = [i*i for i in b]
			sum_a1 = sum(a1)
			sum_a2 = sum(a2)
			b1 = math.sqrt(sum_a1)
			b2 = math.sqrt(sum_a2)
			denominator = (b1) * (b2)
			return numerator/denominator
		# print numerator
		final_v= []
		docs= []
		vid = [];

		for q,w in uidict.items():
			vid.append(q)

		for small in values_big:
				final_v.append(cosine_similarity(gh, small))

		ry = dict(zip(vid, final_v))
		# print ry

		sorted_x = sorted(ry.items(), key=operator.itemgetter(1), reverse= True)
		# print sorted_x
		k=4
		print sorted_x[:4]
    	
    	
		print "Time taken for exact retrieval by Cosine Similarity is %s seconds." % (time.time() - start_time)


	def create_champion_index(self, query_terms, k=4):
		start_time = time.time()
		dic_champ={}
		p=[]
		# for k in tfidfdict.items():
		# 	b= sorted([n for n in dic_z[k]],key=lambda x:x[1][0])  # sorting by the wt,d
		# print len(b)
		global di1
		global query_tf
		query_tf= {}
		for x in query_terms:
			if x not in query_tf.keys():
				query_tf[x]= 1
			else:
				query_tf[x]= query_tf[x]+1
			query_tf[x] = (1+math.log10(query_tf[x]))
		
		global query_idf
		query_idf= {}
		for x in query_terms:
			if x in dic.keys():
				query_idf[x]= dic[x]
			else:
				pass

		global q_tf_idf
		q_tf_idf= {}

		for k,v in query_tf.items():
			for k, v1 in query_idf.items():
				q_tf_idf[k]= v*v1


		cosi={}
		for k,v in dic3.items():
			if k in query_terms:
				cosi[k]= v
		global tfidfdict
		tfidfdict={}
		for k,v in cosi.iteritems():
			idf=v[0]
			t_t= ()
			for i in range(1,len(v)):
				doc_id=v[i][0]
				tf=v[i][1]
				if tfidfdict.has_key(doc_id):
					new = tfidfdict[doc_id]
					old=new[:]
					x=float(idf)*float(tf)
					t_t=(k,x)
					old.append(t_t)
					tfidfdict[doc_id]= old
				else:
					x=float(idf)*float(tf)
					t_t=(k,x)
					tfidfdict[doc_id]=[t_t]

 		gh = []
		for k, v in q_tf_idf.items():
			gh.append(q_tf_idf[k])
		

		values_big = []
		for k, v in tfidfdict.items():
				list_val = tfidfdict[k]
				sub = len(gh) - len(list_val)
				list_valnew = [(0,0)] * sub
				list_val.extend(list_valnew)
				values_small = []
				for (a, b) in list_val:
					if q_tf_idf.has_key(a):
						values_small.append(b)
					else:
						values_small.append(0)
				values_big.append(values_small)

		def cosine_similarity(a, b):
			start_time = time.time()
			d = [i*j for i,j in zip(a, b)]
			numerator = sum(d)
			a1 = [i*i for i in a]
			a2 = [i*i for i in b]
			sum_a1 = sum(a1)
			sum_a2 = sum(a2)
			b1 = math.sqrt(sum_a1)
			b2 = math.sqrt(sum_a2)
			denominator = (b1) * (b2)
			return numerator/denominator


		final_v= []
		docs= []
		vid = [];

		for q,w in uidict.items():
			vid.append(q)

		for small in values_big:
				final_v.append(cosine_similarity(gh, small))

		ry = dict(zip(vid, final_v))

		sorted_x = sorted(ry.items(), key=operator.itemgetter(1), reverse= True)

		k=4
		print (sorted_x[:4])

		print ("Time taken for inexact retrieval by Champion list is %s seconds." % (time.time() - start_time))

	def inexact_query_index_elimination(self, query_terms, k=4):
		k=4
		start_time = time.time()
		q=[]
		q_d={}
		for x in range((len(query_terms)/2)+1):
			q.append(query_terms[x])
			for k in q:
				for k, v in q_tf_idf.items():
					q_d[k]= q_tf_idf[k]
		
		##############dictionary of shortened query terms ########

		#########cosine similarity########

		gh = []
		for k, v in q_tf_idf.items():
			gh.append(q_d[k])
		

		values_big = []
		for k, v in tfidfdict.items():
				list_val = tfidfdict[k]
				sub = len(gh) - len(list_val)
				list_valnew = [(0,0)] * sub
				list_val.extend(list_valnew)
				values_small = []
				for (a, b) in list_val:
					if q_tf_idf.has_key(a):
						values_small.append(b)
					else:
						values_small.append(0)
				values_big.append(values_small)
		
		def cosine_similarity(a, b):
			start_time = time.time()
			d = [i*j for i,j in zip(a, b)]
			numerator = sum(d)
			a1 = [i*i for i in a]
			a2 = [i*i for i in b]
			sum_a1 = sum(a1)
			sum_a2 = sum(a2)
			b1 = math.sqrt(sum_a1)
			b2 = math.sqrt(sum_a2)
			denominator = (b1) * (b2)
			return numerator/denominator

		final_v= []
		docs= []
		vid = [];

		for q,w in uidict.items():
			vid.append(q)

		for small in values_big:
				final_v.append(cosine_similarity(gh, small))

		ry = dict(zip(vid, final_v))
		sorted_x = sorted(ry.items(), key=operator.itemgetter(1), reverse= True)
		
		k=4
		print (sorted_x[:4])
		print ("Time taken for inexact retrieval by index elimination is %s seconds." % (time.time() - start_time))

	def inexact_query_cluster_pruning(self, query_terms, k):
		start_time = time.time()
		s= []
		N= 423
		n= math.sqrt(N)

		dirs = os.listdir(self.path)
		file1= []
		fil1e_g1=[]

		for file in dirs:
			f= open(file, "r")
		 	co= f.read()
		 	file1.append(file)


	###### tf of the query######
		global di1
		global query_tf
		query_tf= {}
		for x in query_terms:
			if x not in query_tf.keys():
				query_tf[x]= 1
			else:
				query_tf[x]= query_tf[x]+1
			query_tf[x] = (1+math.log10(query_tf[x]))				
	###### idf of the query######	
		
		global query_idf
		query_idf= {}
		for x in query_terms:
			if x in dic.keys():
				query_idf[x]= dic[x]
			else:
				pass

	###### tf-idf of the query######
		global q_tf_idf
		q_tf_idf= {}

		for k,v in query_tf.items():
			for k, v1 in query_idf.items():
				q_tf_idf[k]= v*v1
		##########final dictionary for query terms in query##########

		cosi={}
		for k,v in dic3.items():
			if k in query_terms:
				cosi[k]= v
		global tfidfdict
		tfidfdict={}
		for k,v in cosi.iteritems():
			idf=v[0]
			t_t= ()
			for i in range(1,len(v)):
				doc_id=v[i][0]
				tf=v[i][1]
				if tfidfdict.has_key(doc_id):
					new = tfidfdict[doc_id]
					old=new[:]
					x=float(idf)*float(tf)
					t_t=(k,x)
					old.append(t_t)
					tfidfdict[doc_id]= old
				else:
					x=float(idf)*float(tf)
					t_t=(k,x)
					tfidfdict[doc_id]=[t_t]

 		gh = []
		for k, v in q_tf_idf.items():
			gh.append(q_tf_idf[k])
		

		values_big = []
		for k, v in tfidfdict.items():
				list_val = tfidfdict[k]
				sub = len(gh) - len(list_val)
				list_valnew = [(0,0)] * sub
				list_val.extend(list_valnew)
				values_small = []
				for (a, b) in list_val:
					if q_tf_idf.has_key(a):
						values_small.append(b)
					else:
						values_small.append(0)
				values_big.append(values_small)


		def cosine_similarity(a, b):
			start_time = time.time()
			d = [i*j for i,j in zip(a, b)]
			numerator = sum(d)
			a1 = [i*i for i in a]
			a2 = [i*i for i in b]
			sum_a1 = sum(a1)
			sum_a2 = sum(a2)
			b1 = math.sqrt(sum_a1)
			b2 = math.sqrt(sum_a2)
			denominator = (b1) * (b2)
			return numerator/denominator
		
		final_v= []
		docs= []
		vid = [];

		for q,w in uidict.items():
			vid.append(q)

		for small in values_big:
				final_v.append(cosine_similarity(gh, small))

		ry = dict(zip(vid, final_v))

		sorted_x = sorted(ry.items(), key=operator.itemgetter(1), reverse= True)

		k=4
		print (sorted_x[:4])
		print ("Time taken for inexact retrieval by cluster pruning is %s seconds." % (time.time() - start_time))


	def print_dict(self):
	# function to print the terms and posting list in the index 
		print (dic3)

	def print_doc_list(self):
	# 	 function to print the documents and their document id
		print (uidict)


def main():
	path = "/Users/Vidhy/Desktop/IR/collection/collection/"
	ind= index(path)
	ind.buildIndex()
	query = raw_input("Please enter the query ")
	print ("Results for the Query:", query)
	query_terms= re.findall('\w+', query.upper())
	query_terms = [' {0} '.format(elem) for elem in query_terms]
	ind.exact_query(query_terms, k=4)
	ind.create_champion_index(query_terms, k=4)
	ind.inexact_query_index_elimination(query_terms, k=4)
	ind.inexact_query_cluster_pruning(query_terms, k=4)


	
if __name__ == '__main__':
	main()

