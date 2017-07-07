""" The documents are read from TIME.ALL

The tf-idf values are calculated
"""
from collections import defaultdict
import codecs
import gzip
import math
import re

class tfidf(object):

    def __init__(self, docs, champion_threshold=10):
        self.documents = docs
        toked_docs = [self.tokenize(d) for d in self.documents]
        self.doc_freqs = self.count_doc_frequencies(toked_docs)
        self.tfidf_docs = self.create_tfidf_index(toked_docs, self.doc_freqs)

    #creating tf-idf index
    def create_tfidf_index(self, docs, doc_freqs):
        res = list()
        for doc_id in range(len(docs)):
            buf = defaultdict(lambda : 0)
            term_count = defaultdict(lambda: 0)
            for token in docs[doc_id]:
                term_count[token] += 1
            for key in term_count.keys():
                buf[key] = ( 1.0 * (1 + math.log10(float((term_count[key])))) * math.log10((float(len(docs)) / doc_freqs[key])))
            res.append(buf)

        return res

    #calculating document frequencies
    def count_doc_frequencies(self, docs):
        doc_term_freq = defaultdict(lambda: {})
        for doc_id in range(len(docs)):
            for token in docs[doc_id]:
                doc_term_freq[token][doc_id] = 1

        res = defaultdict(lambda: 0)
        for key in list(doc_term_freq.keys()):
            res[key] = len(list(doc_term_freq[key].keys()))

        return res

    #reading each document and tokenizing it
    def tokenize(self, document):
        return [t for t in re.findall(r"\w+(?:[-']\w+)*", document) if len(t) > 3]
