import csv
from jnius import autoclass
import re
import sys

#################################################
##########FINISH FEATURE SELECTION##############
################################################

directory = sys.argv[1]
training = []
with open(directory+"/predict.csv", "r") as f:
    label = csv.DictReader(f)
    for row in label:
        training.append(row)

unigram_words = []
bigram_words = []
sp_indicator = []
cp_indicator = []
tp_indicator = []
unigram_tags = []
bigram_tags = []

with open(directory+"/model/fitur/unigram.txt", "r") as f:
    words = [line.rstrip('\n') for line in f]
    unigram_words = words[:250]

with open(directory+"/model/fitur/bigram.txt", "r") as f:
    words = [line.rstrip('\n') for line in f]
    bigram_words = words[:250]

with open(directory+"/model/fitur/indikator-sp.txt", "r") as f:
    sp_indicator = [line.rstrip('\n') for line in f]

with open(directory+"/model/fitur/indikator-tp.txt", "r") as f:
    tp_indicator = [line.rstrip('\n') for line in f]

with open(directory+"/model/fitur/indikator-cp.txt", "r") as f:
    cp_indicator = [line.rstrip('\n') for line in f]

with open(directory+"/model/fitur/pos-unigram.txt", "r") as f:
    unigram_tags = [line.rstrip('\n') for line in f]

with open(directory+"/model/fitur/pos-bigram.txt", "r") as f:
    tags = [line.rstrip('\n') for line in f]
    bigram_tags = tags[:250]

with open(directory+"/predict.arff", "w") as f:
    f.write("% 'predict' dataset; normalised version.\n")
    f.write("@relation 'predict: -C 3'\n")
    f.write("\n")
    f.write("@attribute social {0,1}\n")
    f.write("@attribute teaching {0,1}\n")
    f.write("@attribute cognitive {0,1}\n")

    for word in unigram_words:
        f.write("@attribute has_"+ word +" {0,1}\n")

    for word in bigram_words:
        f.write("@attribute has_"+ "_".join(word.split()) +" {0,1}\n")

    f.write("@attribute indikator_sp numeric\n")
    f.write("@attribute indikator_tp numeric\n")
    f.write("@attribute indikator_cp numeric\n")

    for tag in unigram_tags:
        f.write("@attribute tag_"+tag+" {0,1}\n")

    for tag in bigram_tags:
        f.write("@attribute tag_"+"_".join(tag.split())+" {0,1}\n")

    f.write("\n")
    f.write("@data\n")
    f.write("\n")

    Parser = autoclass('parser.Parser')
    parser = Parser()

    for idx, row in enumerate(training):
        vec2Write = ""
        sentence = row["sentence"]

        vec2Write = vec2Write + "0,"
        vec2Write = vec2Write + "0,"
        vec2Write = vec2Write + "0,"

        for word in unigram_words:
            if word in sentence:
                vec2Write = vec2Write + "1,"
            else:
                vec2Write = vec2Write + "0,"

        for word in bigram_words:
            if word in sentence:
                vec2Write = vec2Write + "1,"
            else:
                vec2Write = vec2Write + "0,"

        countSP = 0
        countTP = 0
        countCP = 0

        for word in sp_indicator:
            if word in sentence:
                countSP = countSP + 1

        for word in tp_indicator:
            if word in sentence:
                countTP = countTP + 1

        for word in cp_indicator:
            if word in sentence:
                countCP = countCP + 1

        vec2Write = vec2Write + str(countSP) + "," + str(countTP) + "," + str(countCP) + ","

        tags = []
        words = sentence.split()
        taggedSentence = parser.parse(sentence)
        i = 0
        taggedWords = taggedSentence.split()
        for idx, word in enumerate(taggedWords):
            if words[i] in word:
                theTag = re.sub(r'[\(\),]', '', taggedWords[idx - 1])
                tags.append(theTag)
                i = i + 1

        for tag in unigram_tags:
            if tag in tags:
                vec2Write = vec2Write + "1,"
            else:
                vec2Write = vec2Write + "0,"

        tags = []
        words = sentence.split()
        taggedSentence = parser.parse(sentence)
        i = 0
        taggedWords = taggedSentence.split()
        for idx, word in enumerate(taggedWords):
            if words[i] in word:
                theTag = re.sub(r'[\(\),]', '', taggedWords[idx - 1])
                tags.append(theTag)
                i = i + 1

        joinTags = " ".join(tags)
        for tag in bigram_tags:
            if tag in joinTags:
                vec2Write = vec2Write + "1,"
            else:
                vec2Write = vec2Write + "0,"

        vec2Write = vec2Write[:-1] + "\n"
        f.write(vec2Write)