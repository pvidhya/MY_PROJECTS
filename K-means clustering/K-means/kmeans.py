from __future__ import division
from collections import defaultdict
from collections import Counter
import re
from tfidf import tfidf
import  math
import time

class kmeans:

    def __init__(self,k):
        self.path = '.'
        self.documents = list()
        self.k = k

#creating clusters
    def cluster(self, documents, iters=20):
        self.doc_norm = defaultdict(lambda: 0.0)
        self.documents = documents

        self.mean_norms = []
        self.mean_vectors = []

        for doc_id in range(len(documents)):
            self.doc_norm[doc_id] = self.sqnorm(documents[doc_id])

#considering hardcoded values for the purpose of the experiment to show the RSS value variations
        self.mean_vectors.append(documents[2])
        self.mean_vectors.append(documents[5])
        self.mean_vectors.append(documents[7])
        self.mean_vectors.append(documents[9])
        self.mean_vectors.append(documents[10])
        self.mean_vectors.append(documents[11])
        self.mean_vectors.append(documents[12])
        self.mean_vectors.append(documents[13])
        self.mean_vectors.append(documents[14])
        self.mean_vectors.append(documents[15])
        self.mean_vectors.append(documents[16])
        self.mean_vectors.append(documents[17])
        self.mean_vectors.append(documents[18])            
        self.mean_vectors.append(documents[19])
        self.mean_vectors.append(documents[21])
        self.mean_vectors.append(documents[22])
        self.mean_vectors.append(documents[23])
        self.mean_vectors.append(documents[24])
        self.mean_vectors.append(documents[25])
        self.mean_vectors.append(documents[26])
        self.mean_vectors.append(documents[27])
        self.mean_vectors.append(documents[28])
        self.mean_vectors.append(documents[29])
        self.mean_vectors.append(documents[30])
        self.mean_vectors.append(documents[31])
        self.mean_vectors.append(documents[35])
        self.mean_vectors.append(documents[36])
        self.mean_vectors.append(documents[37])
        self.mean_vectors.append(documents[38])
        self.mean_vectors.append(documents[40])

        self.mean_norms.append(self.doc_norm[2])
        self.mean_norms.append(self.doc_norm[5])
        self.mean_norms.append(self.doc_norm[7])
        self.mean_norms.append(self.doc_norm[9])
        self.mean_norms.append(self.doc_norm[10])
        self.mean_norms.append(self.doc_norm[11])
        self.mean_norms.append(self.doc_norm[12])
        self.mean_norms.append(self.doc_norm[13])
        self.mean_norms.append(self.doc_norm[14])
        self.mean_norms.append(self.doc_norm[15])
        self.mean_norms.append(self.doc_norm[16])
        self.mean_norms.append(self.doc_norm[17])
        self.mean_norms.append(self.doc_norm[18])
        self.mean_norms.append(self.doc_norm[19])
        self.mean_norms.append(self.doc_norm[21])
        self.mean_norms.append(self.doc_norm[22])
        self.mean_norms.append(self.doc_norm[23])
        self.mean_norms.append(self.doc_norm[24])
        self.mean_norms.append(self.doc_norm[25])
        self.mean_norms.append(self.doc_norm[26])
        self.mean_norms.append(self.doc_norm[27])
        self.mean_norms.append(self.doc_norm[28])
        self.mean_norms.append(self.doc_norm[29])
        self.mean_norms.append(self.doc_norm[30])
        self.mean_norms.append(self.doc_norm[31])
        self.mean_norms.append(self.doc_norm[35])
        self.mean_norms.append(self.doc_norm[36])
        self.mean_norms.append(self.doc_norm[37])
        self.mean_norms.append(self.doc_norm[38])
        self.mean_norms.append(self.doc_norm[40])



        for j in range(iters):

            self.compute_clusters(documents)
            self.compute_means()

            num_of_docs = []
            for i in self.k_cluster:
                num_of_docs.append(len(self.k_cluster[i]))

        print (num_of_docs)
        print ("RSS:", self.error(documents))


#computing means after every cluster assignment
    def compute_means(self):

        self.mean_vectors = []
        for i in range(self.k):
            term_freq = Counter()
            for doc_id in self.k_cluster[i]:
                term_freq.update(self.documents[doc_id])
            if (len(self.k_cluster[i]) > 0):
                for term in term_freq:
                    term_freq[term] = 1.0 * term_freq[term] / len(self.k_cluster[i])
                self.mean_vectors.append(term_freq)
            self.mean_norms = []
            for t in self.mean_vectors:
                self.mean_norms.append(self.sqnorm(t))

#cluster assignment
    def compute_clusters(self, documents):

        self.k_cluster = defaultdict(lambda: [])
        for doc_id in range(len(documents)):
            assign_cluster = -1
            min_distance = -1
            for cluster in range(self.k):
                distance = self.distance(documents[doc_id], self.mean_vectors[cluster],
                                         self.mean_norms[cluster] + self.doc_norm[doc_id])
                if (distance < min_distance or assign_cluster == -1):
                    assign_cluster = cluster
                    min_distance = distance
            self.k_cluster[assign_cluster].append(doc_id)

#finding squared normalization for every document in order to remove negative values while computing distances
    def sqnorm(self, d):
        sqsum = 0.0
        for key in d.keys():
            sqsum += (d[key] ** 2)
        return sqsum


#computing distances between a prior mean and the new document that needs to be clustered
    def distance(self, doc, mean, mean_norm):
        distance = mean_norm
        value = 0.0
        for term in doc:
            value += (2.0 * doc[term] * mean[term])
        distance = round((distance - value),6)
        return float(math.sqrt(distance))

#computing Residual Sum of Squares value
    def error(self, documents):
        error = 0.0
        self.k_cluster_dist = defaultdict(lambda: [])
        for cluster in self.k_cluster.keys():
            for doc_id in self.k_cluster[cluster]:
                distance = self.distance(documents[doc_id], self.mean_vectors[cluster],
                                         self.mean_norms[cluster] + self.doc_norm[doc_id])
                error += distance
                self.k_cluster_dist[cluster].append((documents[doc_id], distance))

        return error
#printing the top documents 
    def print_top_docs(self, n=20):
        for cluster in self.k_cluster.keys():
            print("CLUSTER ", cluster)
            topdocs = sorted(self.k_cluster_dist[cluster], key=lambda x: x[1])
            count = 0
            for doc_id in range(len(topdocs)):
                if (len(topdocs[doc_id][0]) > 3):
                    str = ' '.join(sorted(topdocs[doc_id][0].keys())).encode('utf-8')
                    print(str.decode('utf-8'))
                    count += 1
                if count == n:
                    break

#reading the text and creating different documents 
    def read_doc(self):

        buf = open('TIME.STP', "r").readlines()
        stopwords = list()
        for stop in buf:
            stop = stop.strip("\n")
            if stop is not '':
                stopwords.append(stop)

        buf = open('TIME.ALL',"r").readlines()
        lines = list()
        for line in buf:
            line = line.strip("\n")
            words = line.split()
            line = [word for word in words if word not in stopwords]
            line = ' '.join(line)
            if line is not '':
                lines.append(line)

        doc = ''
        for line in lines:
            if line.startswith("*TEXT"):
                self.documents.append(doc)
                doc = ''
            if line.startswith("*STOP"):
                self.documents.append(doc)
                break
            else:
                doc += line + ' '


def main():
    # k value needs to be changed according to the user
    kmean = kmeans(30)
    start_time = time.time()
    kmean.read_doc()
    kmean.documents = kmean.documents[1:]
    tfidf_v = tfidf(kmean.documents)
    kmean.cluster(tfidf_v.tfidf_docs)
    print("Total time taken:",time.time() - start_time)

if __name__ == '__main__':
    main()