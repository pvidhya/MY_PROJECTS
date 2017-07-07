from __future__ import division
from collections import Counter
import math
import re
import os
import collections
import time


class index:
    def __init__(self, path):
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

        #####CAlculating the number of documents in which the particular term occurs.
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
        return dic

    ####reading the query, removing the stop words#####
    def query(self):
        with open('TIME.QUE') as f:
            buf = f.read()
        global dic_query
        dic_query = {}
        wrq = ''
        l = 0
        line = buf.splitlines()
        for m in (buf.splitlines()):
            if (len(m.strip()) != 0):
                if (m[0:5]) == '*FIND':
                    k = (m.split()[1])
                    if l != 0:
                        dic_query[int(k) - 1] = wrq + ' '
                    wrq = ''
                elif (m[0:5]) != '*STOP':
                    if wrq != '':
                        wrq = wrq + ' ' + m
                    else:
                        wrq = wrq + m
                    l = 1
        dic_query[int(k)] = wrq + ' '
        ####Tokenizing the query###
        q_terms = re.sub('\W+', ' ', buf)
        q_terms = q_terms.split()
        # print (len(q_terms))
        ####reading STOP words####
        f = open('TIME.STP', "r")
        co = f.read()
        token_stp = re.sub('\W+', ' ', co)
        token_stp = token_stp.split()

        ####removing STOP words from query####
        global query_terms
        query_terms = []
        for terms in q_terms:
            if terms not in token_stp:
                query_terms.append(terms)
        return query_terms

    ####Read relevent documents and create a diction where key is the query ID 
    ###and the values are the relevant documents for that particular query.
    def read_relevant(self):
        with open('TIME.REL') as f:
            buf = f.read()
        global dic_relevant
        dic_relevant = {}
        line = buf.splitlines()
        for m in (buf.splitlines()):
            if (len(m.strip()) != 0):
                k = (m.split())
                lis_relevant = []
                for j in range(1, len(k)):
                    lis_relevant.append(int(k[j]))
                dic_relevant[int(k[0])] = lis_relevant
        return (dic_relevant)

	def print_doc_list(self):
    # function to print the documents and their document id
		print (uidict)

	def print_dict(self):
    # function to print the terms and posting list in the index
		print (dic)

def fquery_vector(query_terms, dic_count):
    ######Generating the query idf####
    global query_vector
    query_vector = {}
    for x in query_terms:
        if x in dic_count.keys():
            query_vector[x] = math.log10(float(len(docs)) / float((dic_count[x])))
 
    return query_vector


def fcosine(query_vector, dic, docs, dic_count, tf_idf_norm):
    ####Calculating the Cosine Similarity by calculating the tf-idf of the document, 
    ####query vector an dthe normalized for the document.
    global cosine_dict
    cosine_dict = {}
    for query in query_vector.keys():
        for dlist in dic[query]:
            if dlist[0] in cosine_dict.keys():
                cosine_dict[dlist[0]] += ((1 + math.log10(dlist[1])) * math.log10(len(docs) / dic_count[query]) *
                                          query_vector[query]) / tf_idf_norm[dlist[0]]
            else:
                cosine_dict[dlist[0]] = ((1 + math.log10(dlist[1])) * math.log10(len(docs) / dic_count[query]) *
                                         query_vector[query]) / tf_idf_norm[dlist[0]]
    return cosine_dict


def rocchio(query_terms, pos_feedback, neg_feedback, alpha, beta, gamma, new_query_vector):
###multiplying alpha with the existing query vector
    global roc_dic_q
    roc_dic_q = {}
    if new_query_vector is None:
        for query in query_vector.keys():
            roc_dic_q[query] = alpha * query_vector[query]
    else:
        for query in new_query_vector.keys():
            roc_dic_q[query] = alpha * new_query_vector[query]
###user prompted positive documents 
###multiplying beta value to the tf-idf value of positive documents.
    global dic_new_p
    dic_new_p = {}
    for x in pos_feedback:
        for k, v in dic.items():
            for item in v:
                if item[0] == x:
                    dic_new_p[k] = item

    global roc_dic_p
    roc_dic_p = {}
    for k, v in dic_new_p.items():
        if k not in roc_dic_p.keys():
            roc_dic_p[k] = ((((1 + math.log10(v[1])) * math.log10(len(docs) / dic_count[k])) / tf_idf_norm[v[0]]) / len(pos_feedback))

    for key in roc_dic_p:
        roc_dic_p[key] *= beta

###user prompted negative documents 
###multiplying gamma value to the tf-idf value of negative documents.

    global dic_new_n
    dic_new_n = {}
    for x in neg_feedback:
        for k, v in dic.items():
            for item in v:
                if item[0] == x:
                    dic_new_n[k] = item

    global roc_dic_n
    roc_dic_n = {}
    for k, v in dic_new_p.items():
        if k not in roc_dic_n.keys():
            roc_dic_n[k] = (
                (((1 + math.log10(v[1])) * math.log10(len(docs) / dic_count[k])) / tf_idf_norm[v[0]]) / len(neg_feedback))

    for key in roc_dic_n:
        roc_dic_n[key] *= gamma

    #####ROCCHIO ALGORITHM#####
    A = Counter(roc_dic_q)
    B = Counter(roc_dic_p)
    C = Counter(roc_dic_n)
    global Query_roc
    Q = A + B - C
    new_query_vector = dict(Q)

    return new_query_vector

###the retrieved documents are the once received my cosine similarity in decreasing order
def retrieved_docs(cosine_dict, k):
    retrieved = []
    sorted_cos = sorted(cosine_dict, key=cosine_dict.get, reverse=True)
    for key in sorted_cos[:k]:
        retrieved.append(key)
    #print(retrieved[:5])

    return retrieved


def fprecision(retrieved, relevant):
    ###Calculating Precision####
    pre = []

    for m in retrieved:
        if m in relevant:
            pre.append(m)

    precision = ( (len(pre) * 1.0 ) / (len(retrieved)))
    print("Precision: %f" % precision)
    return precision

def frecall(retrieved, relevant):
	###Calculating recall
    re = []
    for m in relevant:
        if m in retrieved:
            re.append(m)

    recall = ( (len(re) * 1.0 )/(len(relevant)))
    print("Recall: %f" % recall)
    return recall

def MAP(retrieved, relevant):
####CalculatingMAP####
    count = 0
    Prec = 0
    dic_map = {}
    length = len(relevant)
    for m in retrieved:
        count = count + 1
        if m in relevant:
            Prec = Prec + 1
            dic_map[m] = (Prec * 1.0 / count)

    total = 0.0
    for m in relevant:
        if m in dic_map.keys():
            total = total + dic_map[m]
        else:
            total = total + 0.0

    print ("MAP: %f" %(total * 1.0 /length))
    return total * 1.0 /length

def ftf_idf_norm(dic, dic_count):
    ######tf-idf vector form######
    n_docs = 423
    global tf_idf_norm
    tf_idf_norm = {}
    for m in dic.keys():
        for n in dic[m]:
            if n[0] not in tf_idf_norm.keys():
                tf_idf_norm[n[0]] = ((1 + math.log10(n[1])) * math.log10(n_docs / dic_count[m])) ** 2
            else:
                tf_idf_norm[n[0]] += ((1 + math.log10(n[1])) * math.log10(n_docs / dic_count[m])) ** 2

    for m in tf_idf_norm.keys():
        tf_idf_norm[m] = math.sqrt(tf_idf_norm[m])
    return (tf_idf_norm)

def pseudo_rel(cosine_dict, k):
    retrieved = retrieved_docs(cosine_dict, k)
    k = 3
    sorted_cos = sorted(cosine_dict, key=cosine_dict.get, reverse=True)
    relevant = []
    for key in sorted_cos[:3]:
        relevant.append(key)
    print (relevant)

    precision = fprecision(retrieved, relevant)
    recall = frecall(retrieved, relevant)
    map_v = MAP(retrieved,relevant)
    
    for i in range(4):
        alpha = 1.0
        beta = 0.75
        gamma = 0.15
        sorted_cos = sorted(cosine_dict, key=cosine_dict.get, reverse=True)
        pos_feedback = []
        ###top 3 retrived docs are considered as the positive docs
        for key in sorted_cos[:3]:
            pos_feedback.append(key)
        print ("Positive Documents:"), (pos_feedback)
        
        ###last 2 retrived docs are considered as the negative docs
        neg_feedback = []
        for key in sorted_cos[-3:]:
            neg_feedback.append(key)
        print ("Negative Documents:"), (neg_feedback)

        
        k = input("Number of retrieved documents")
        k = int(k)
        new_query_vector = rocchio(query_terms, pos_feedback, neg_feedback, alpha, beta, gamma, new_query_vector)
        cosine_dict = fcosine(query_vector, dic, docs, dic_count, tf_idf_norm)
        retrieved = retrieved_docs(cosine_dict, k)

        for k,v in query_vector[:15]:
            print (k), (v)


        for query_id, query_text in dic_query.items():
            relevant = sorted(list(dic_relevant[query_id]))

        precision = fprecision(retrieved, relevant)
        recall = frecall(retrieved, relevant)
        map_v = MAP(retrieved,relevant)
        cosine_dict.clear()
        retrieved.clear()
        relevant.clear()
    return recall, precision, map_v


def main():
    path = "/Users/Vidhy/Desktop/COURSES/IR/IR_ASG3/time"
    ind = index(path)
    total_docs = ind.read_doc()
    dic_relevant = ind.read_relevant()
    dic_id = ind.dictn(total_docs)
    dic_count = ind.dic_cont(docs)
    tf_idf_norm = ftf_idf_norm(dic, dic_count)
    query_terms = ind.query()
    query_vector = fquery_vector(query_terms, dic_count)
    cosine_dict = fcosine(query_vector, dic, docs, dic_count, tf_idf_norm)
    alpha = 1.0
    beta = 0.75
    gamma = 0.15
    pos_feedback = input("enter the positive documents")
    pos_feedback = [int(x) for x in pos_feedback.split()]

    neg_feedback = input("enter the negative documents")
    neg_feedback = [int(x) for x in neg_feedback.split()]

    k = int(input("Number of retrieved documents"))
    retrieved = retrieved_docs(cosine_dict, k)
    
    for query_id, query_text in dic_query.items():
        relevant = sorted(list(dic_relevant[query_id]))

    precision = fprecision(retrieved, relevant)
    recall = frecall(retrieved, relevant)
    map_v = MAP(retrieved,relevant)

    print("Query text: %s" % query_text)
    print("Query ID: %d" % query_id)

    new_query_vector = {}
    for i in range(4):
        alpha = 1.0
        beta = 0.75
        gamma = 0.15
        pos_feedback = 0
        neg_feedback = 0
        print("\n")
        pos_feedback = input("enter the positive documents")
        pos_feedback = [int(x) for x in pos_feedback.split()]

        neg_feedback = input("enter the negative documents")
        neg_feedback = [int(x) for x in neg_feedback.split()]

        k = int(input("Number of retrieved documents"))
            ####the retrived query terms and values from the rocchio algorithm act as the new query vector for the next iteration
        new_query_vector = rocchio(query_terms, pos_feedback, neg_feedback, alpha, beta, gamma, new_query_vector)
        cosine_dict = fcosine(query_vector, dic, docs, dic_count, tf_idf_norm)
        retrieved = retrieved_docs(cosine_dict, k)

        for query_id, query_text in dic_query.items():
            relevant = sorted(list(dic_relevant[query_id]))

        precision = fprecision(retrieved, relevant)
        recall = frecall(retrieved, relevant)
        map_v = MAP(retrieved,relevant)
        cosine_dict.clear()
        retrieved.clear()
        relevant.clear()
    print ("PSEUDO RELEVANCE")
    cosine_dict = fcosine(query_vector, dic, docs, dic_count, tf_idf_norm)
    pseudo = pseudo_rel(cosine_dict, k) 

if __name__ == '__main__':
    main()
