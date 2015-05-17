# mostra a distribuição de dados de todos os arquivos de holdout

leTabelas <- function () {
  dados <- read.csv("../dados.csv",header=FALSE)
  
  # lê todos os arquivos de testes brutos preparados pelo holdout
  arquivos <- dir(".", pattern = glob2rx("teste_bruto*.csv"))
  teste_bruto <- lapply(arquivos,read.csv,header = FALSE)
  for(i in 1:10){
    teste_bruto[[i]]$V65 <- as.factor(teste_bruto[[i]]$V65)
    print(c("Arquivo teste_bruto",(i-1)),quote=FALSE)
    print(summary(teste_bruto[[i]]$V65))
  }
  
  teste_bruto
  
}

teste_bruto <- leTabelas()