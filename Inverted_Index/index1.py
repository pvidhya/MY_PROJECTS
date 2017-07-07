#Python 2.7.3
import re
import os
import collections
import time

class index:
		
	def __init__(self,path):
		# path = "/Users/Vidhy/Desktop/IR/collection/collection/"
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
		for f in file1:
			fi= open(f, "r")
			co= fi.read()
			z=set(co.split())
			x = [' {0} '.format(elem) for elem in z]
			for y in x:
				l1=[]
				lf=[]
				r=()
				if y in lis_no_int:
					for m in re.finditer(y,co):
						l1.append(m.start())
					r=(uidict[f],l1)
					lf.append(uidict[f])
					if y in dic:
						temp=dic[y]
						temp.append(r)
						dic[y]=temp	
					else:
						dic[y]=[r]
						# dic1[y]=[lf]
					if y in dic1:
						temp=dic1[y]
						temp.append(lf)
						dic1[y]=temp
					else:
						dic1[y]=[lf]
		print dic1[y]
		print "Index built in %s seconds." % (time.time() - start_time)


	def and_query(self, query_terms):
		start_time = time.time()
		# lisI=[]
		global li1
		li1= []
		for term in query_terms:
			if term in dic1:
				# print term
				li1.append(dic1[term])
		print li1

		
		# li1.sort(key=len)
		# list1=li1[0]
		# terms_in_file = []
		# # print len(li1)
		# for t in range(len(li1)-1):
		# 	list2= li1[t+1]
		# 	l1=len(list1)
		# 	l2=len(list2)
		# 	pt1=0
		# 	pt2=0
		# 	global lis
		# 	lis=[]
		# 	while(pt1<l1) and (pt2<l2):
		# 	    if(list1[pt1]==list2[pt2]):
		# 	        lis.append(list1[pt1])
		# 	        pt1=pt1+1
		# 	        pt2=pt2+1
		# 	    elif list1[pt1]>list2[pt2]:
		# 	        pt2=pt2+1
		# 	    else:
		# 	        pt1=pt1+1
		# 	li1[0] = lis
	
		# global lst2
		# lst2=[]
		# lst2 = [item[0] for item in li1[0]]
		# p=[]
		# for k,v in uidict.items():
		# 	for l in lst2:
		# 		if v == l:
		# 			p.append(k)
		# print len(set(p))
		# print "Total Docs retrieved:", len(p)
		# for z in p:
		# 	print z
		# print "Retrived in %s seconds." % (time.time() - start_time)


	def print_dict(self):
		for k,v in dic.items():
			print k ,":",v 


	def print_doc_list(self):
		for k,v in uidict.items():
			print "Doc ID:", v,"==>", k

def main():
	path = "/Users/Vidhy/Desktop/IR/collection/collection/"
	# print execfile(path)
	ind= index(path)
	ind.buildIndex()
	query = raw_input("Please enter the query ")
	query_terms= re.findall('\w+', query.upper())
	query_terms = [' {0} '.format(elem) for elem in query_terms]
	print "Results for the Query:", query
	ind.and_query(query_terms)
	# ind.print_dict()
	ind.print_doc_list()
	

if __name__ == '__main__':
	    main()
