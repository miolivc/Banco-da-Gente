﻿CREATE TABLE AGENCIA ( 
	CODIGO SERIAL PRIMARY KEY, 
	NOME VARCHAR(30) NOT NULL
);

CREATE TABLE TELEFONE_AGENCIA (
	CODAG INT,
	TELEFONE VARCHAR(14),
	PRIMARY KEY(CODAG, TELEFONE),
	FOREIGN KEY (CODAG) REFERENCES AGENCIA(CODIGO)
	ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE ENDERECO_AGENCIA (
	CODAG INT,
	RUA VARCHAR(100),
	BAIRRO VARCHAR(50), 
	CIDADE VARCHAR(50),
	CEP VARCHAR(9),
	NUMERO VARCHAR(6),
	UF VARCHAR(2),
	PRIMARY KEY (CODAG,RUA,BAIRRO,CIDADE,CEP),
	FOREIGN KEY (CODAG) REFERENCES AGENCIA(CODIGO)
	ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE FUNCIONARIO (
	CPF VARCHAR(14) PRIMARY KEY, 
	NOME VARCHAR(50) NOT NULL, 
	RG VARCHAR(15) NOT NULL, 
	DATANASC DATE NOT NULL, 
	EMAIL VARCHAR(30) NOT NULL, 
	SENHA VARCHAR(10) NOT NULL, 
	TIPO VARCHAR(30) NOT NULL, 
	CODAG INT,
	FOREIGN KEY (CODAG) REFERENCES AGENCIA(CODIGO)
	ON UPDATE CASCADE ON DELETE RESTRICT
);
	
CREATE TABLE TELEFONE_FUNCIONARIO (
	CPF VARCHAR(14),
	TELEFONE VARCHAR(14),
	PRIMARY KEY(CPF, TELEFONE),
	FOREIGN KEY (CPF) REFERENCES FUNCIONARIO(CPF)
	ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE ENDERECO_FUNCIONARIO (
	CPF VARCHAR(14),
	RUA VARCHAR(100),
	BAIRRO VARCHAR(50), 
	CIDADE VARCHAR(50),
	CEP VARCHAR(9),
	NUMERO VARCHAR(6),
	PRIMARY KEY (CPF,RUA,BAIRRO,CIDADE,CEP),
	FOREIGN KEY (CPF) REFERENCES FUNCIONARIO(CPF)
	ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE CLIENTE (
	CPF VARCHAR(14) PRIMARY KEY, 
	CNPJ VARCHAR(18), 
	NOME VARCHAR(30) NOT NULL, 
	RG VARCHAR(15) NOT NULL, 
	DATANASC DATE NOT NULL, 
	EMAIL VARCHAR(30) NOT NULL, 
	SENHA VARCHAR(10) NOT NULL
);

CREATE TABLE TELEFONE_CLIENTE (
	CPF VARCHAR(14),
	TELEFONE VARCHAR(14),
	PRIMARY KEY(CPF,TELEFONE),
	FOREIGN KEY(CPF) REFERENCES CLIENTE(CPF)
	ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE ENDERECO_CLIENTE (
	CPF VARCHAR(14), 
	RUA VARCHAR(100),
	BAIRRO VARCHAR(50), 
	CIDADE VARCHAR(50),
	CEP VARCHAR(9),
	NUMERO VARCHAR(6),
	PRIMARY KEY (CPF,RUA,BAIRRO,CIDADE,CEP),
	FOREIGN KEY (CPF) REFERENCES CLIENTE(CPF)
	ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE CONTA (
	COD SERIAL PRIMARY KEY, 
	SALDO DOUBLE PRECISION NOT NULL,  
	DATAOPEN DATE NOT NULL, 
	CODAG INT,
	FOREIGN KEY (CODAG) REFERENCES AGENCIA(CODIGO)
	ON UPDATE CASCADE ON DELETE RESTRICT
); 

CREATE TABLE CONTA_BANCO (
	CODCLIENTE VARCHAR(14),
	CONTA INT,
	PRIMARY KEY(CODCLIENTE, CONTA),
	FOREIGN KEY (CODCLIENTE) REFERENCES CLIENTE(CPF)
	ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY (CONTA) REFERENCES CONTA(COD)
	ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE MOVIMENTACAO_FINANCEIRA (
	DATA DATE, 
	HORA TIME WITHOUT TIME ZONE, 
	CONTA INT,
	TITULAR VARCHAR(14),
	TIPO VARCHAR(30),
	VALOR DOUBLE PRECISION,
	PRIMARY KEY(DATA, HORA, CONTA, TITULAR, TIPO, VALOR),
	FOREIGN KEY (CONTA) REFERENCES CONTA(COD)
	ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY (TITULAR) REFERENCES CLIENTE(CPF)
	ON UPDATE CASCADE ON DELETE RESTRICT
);

ALTER TABLE CLIENTE
ADD COLUMN FOTO VARCHAR;

ALTER TABLE ENDERECO_CLIENTE
ADD COLUMN UF VARCHAR(2);

ALTER TABLE ENDERECO_FUNCIONARIO
ADD COLUMN UF VARCHAR(2);