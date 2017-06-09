import sys
import nltk

argv = sys.argv[1:]
sentence = " ".join(argv)
sentences = nltk.sent_tokenize(sentence)
print(";".join(sentences))