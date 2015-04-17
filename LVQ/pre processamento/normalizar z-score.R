# Script para normalizar usando a tecnica z-score
BDtreinamento = read.csv("http://archive.ics.uci.edu/ml/machine-learning-databases/optdigits/optdigits.tes", sep=',' , header=F)
normalizado = scale(BDtreinamento)

## na primeira coluna, ficou NaN pois todos os elemtnos dela sao 0 (entao foi dividido por 0)
