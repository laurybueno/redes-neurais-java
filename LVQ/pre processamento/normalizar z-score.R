# Script para normalizar usando a tecnica z-score
BDtreinamento = read.csv("http://archive.ics.uci.edu/ml/machine-learning-databases/optdigits/optdigits.tes", sep=',' , header=F)
normalizadoZScore = scale(BDtreinamento)

range01 <- function(x){(x-min(x))/(max(x)-min(x))}
minMax = range01(BDtreinamento)

## na primeira coluna, ficou NaN pois todos os elemtnos dela sao 0 (entao foi dividido por 0)

#deleta colunas onde tem algum elemento NaN
final = normalizado[,colSums (is.na(normalizado)) != nrow(normalizado)]


#salva a matriz normalizada em um arquivo
write.table(final, file="optdigitsNormalizado.txt",sep="," ,row.names=FALSE, col.names=FALSE)


#fonte: http://stats.seandolinar.com/calculating-z-scores-with-r/

