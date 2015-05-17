# mostra a distribuição de dados de todos os arquivos de holdout

raw.leNormalizacao <- function (nome) {
  # dados <- read.csv("../dados.csv",header=FALSE)
  
  # lê todos os arquivos de preparados pelo holdout de acordo com o nome determinado
  arquivos <- dir(".", pattern = glob2rx(paste(nome,"*.csv",sep="")))
  retorno <- lapply(arquivos,read.csv,header = FALSE)
  
  # descobre qual é a última coluna do arquivo lido (coluna de classes)
  dimensoes <- dim(retorno[[1]])
  uCol <- dimensoes[2]
  
  # encontra a classe de todas as tabelas e printa um sumário de suas distribuições por arquivo
  for(i in 1:10){
    retorno[[i]][,uCol] <- as.factor(retorno[[i]][,uCol])
    print(paste("Arquivo de ",nome,(i-1),".csv",sep = ""))
    print(summary(retorno[[i]][,uCol]))
  }
  
  retorno
  
}

# le uma tabela e decide qual coluna sera seu factor
raw.leTabela <- function(nome){
  teste <- read.csv(nome,header=FALSE)
  
  # descobre qual é a última coluna do arquivo lido (coluna de classes)
  dimensoes <- dim(teste)
  uCol <- dimensoes[2]
  
  teste[,uCol] <- as.factor(teste[,uCol])
  print(paste("Arquivo de ",nome,".csv",sep = ""))
  print(summary(teste[,uCol]))
  
}


