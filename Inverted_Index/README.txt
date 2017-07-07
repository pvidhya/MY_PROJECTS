Please check the path before ruuning the program.
My program will not prompt the user for the file path.
Due the specified path, please run the program inside this IR folder!
Submitted a doc file for output beacuse will converting into .txt format the images were getting spoilt.

def __init__(self,path):
	The path of the folder where all the files are present.
def buildIndex(self):
	All the term index are built.
def and_query(self, query_terms):

******************************MERGE ALGORITHM**********************
1. The initial time of execution is stored and the tokenized query terms are passed.
2. If the query terms are present in the documents, the index of those terms are fetched nad saved in a list of list.
3. The list is sorted in ascending order of their posting list length.
4. The merge algorithm takes two sublists form the list at a time, starting from li1[0] and li1[1]. 
5. Compares the values in both the list simultaneously and if found a match appends the value into  anew list.
6. This new list acts as the li1[0] for next loop with li1[1+1]= li1[2] becomes the second list. Again the two pointers move simultaneously.
7. Thi scontinues until all the sublists are traversed.
8. The final list contains the ids of files in which the given query terms are present. 


def print_dict(self): #########Commented in the code in order to save executiontime ########
	Prints the terms and posting list in the index

def print_doc_list(self):
	print the documents and their document id

def main():
	Defines the procedure of function execution


if __name__ == '__main__':
	    main()
	Informing the interpreter to execute the main() function first.
