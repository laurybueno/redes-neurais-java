# Script para pegar o banco de dados do optdigits.tes e gerar um gráfico a fim de
# saber se existe ou não outlier
# Isso foi feito para saber se devemos normalizar usando a tecnica min-max (sem outlier)
#ou usando a tecnica z-score (tendo outlier)


#abre o arquivo e salva na variavel BDtreinamento.
#sep="," pois os atributos sao separados por "," e nao tem cabecalho, por isso header=F
BDtreinamento = read.csv("http://archive.ics.uci.edu/ml/machine-learning-databases/optdigits/optdigits.tes", sep=',' , header=F)

outlier(BDtreinamento, opposite = FALSE, logical = TRUE)

#para remover outlier: rm.outlier(BDtreinamento, fill = FALSE, median = FALSE, opposite = FALSE)


#ja peguei o package outliers e continua nao funcionando :(
#http://cran.r-project.org/web/packages/outliers/outliers.pdf






