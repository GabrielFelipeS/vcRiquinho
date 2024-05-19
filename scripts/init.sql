create database vcriquinho;

\c vcriquinho

CREATE TYPE TIPO_PRODUTO AS ENUM ('renda_fixa', 'renda_variavel');
create table produto (
  id_produto SERIAL PRIMARY KEY,
  nome VARCHAR(50) UNIQUE NOT NULL,
  descricao VARCHAR(255) NOT NULL,
  rendimento_mensal DECIMAL(10, 2) NOT NULL,
  carencia INT,
  tipo_produto TIPO_PRODUTO NOT NULL,
  ativo BOOLEAN DEFAULT TRUE,
  data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  data_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TYPE TIPO_PESSOA AS ENUM ('fisica', 'juridica');
CREATE TABLE pessoa (
  id SERIAL,
  documento_titular VARCHAR(25) PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  email VARCHAR(50) UNIQUE NOT NULL,
  tipo_pessoa TIPO_PESSOA NOT NULL,
  ativo BOOLEAN DEFAULT TRUE,
  data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  data_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TYPE TIPO_CONTA AS ENUM ('cdi', 'corrente', 'invesimento_automatico');
CREATE TABLE conta (
  id SERIAL PRIMARY KEY,
  documento_titular VARCHAR(30) REFERENCES pessoa(documento_titular),
  montante_financeiro DECIMAL(10, 2) NOT NULL,
  id_produto INT REFERENCES produto(id_produto),
  cdi DECIMAL(10, 2),
  tipo_conta TIPO_CONTA NOT NULL,
  ativo BOOLEAN DEFAULT TRUE,
  data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  data_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE conta
ADD CONSTRAINT sem_duplicidade_conta_documento UNIQUE (documento_titular, tipo_conta);

CREATE OR REPLACE FUNCTION atualiza_data_modificacao()
RETURNS TRIGGER AS $$
BEGIN
  NEW.data_modificacao = NOW();
  RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER set_data_modificacao_produto
BEFORE UPDATE ON produto
FOR EACH ROW
EXECUTE PROCEDURE atualiza_data_modificacao();

CREATE TRIGGER set_data_modificacao_pessoa
BEFORE UPDATE ON pessoa
FOR EACH ROW
EXECUTE PROCEDURE atualiza_data_modificacao();

CREATE TRIGGER set_data_modificacao
BEFORE UPDATE ON conta
FOR EACH ROW
EXECUTE PROCEDURE atualiza_data_modificacao();

INSERT INTO produto (nome, descricao, carencia, rendimento_mensal, tipo_produto) 
	VALUES 
		('Fundo de Renda Fixa ABC', 'Fundo de investimento em renda fixa com baixo risco',  1825, 0.015, 'renda_fixa'),
		('Ações Tech Growth', 'Fundo de investimento em empresas de tecnologia', NULL, 0.05, 'renda_variavel'),
		('Fundo Imobiliário Prime Realty', 'Fundo de investimento em imóveis comerciais', 1095, 0.02, 'renda_fixa'),
		('Tesouro Direto IPCA+', 'Título do Tesouro Direto indexado ao IPCA',  730, 0.03, 'renda_fixa'),
		('Fundo de Ações Sustentáveis ESG', 'Fundo de investimento em empresas com práticas sustentáveis', NULL, 0.04,  'renda_variavel'),
		('CDB Banco X', 'Certificado de Depósito Bancário do Banco X', 365, 0.02, 'renda_fixa'),
		('Fundo Multimercado Global', 'Fundo de investimento diversificado globalmente', NULL, 0.03, 'renda_variavel'),
		('Fundo de Previdência Conservador', 'Fundo de previdência com alocação conservadora', 2190, 0.02, 'renda_fixa'),
		('Fundo de Investimento em Ouro', 'Fundo de investimento em barras de ouro', NULL, 0.01, 'renda_variavel'),
		('Fundo de Criptomoedas Blockchain', 'Fundo de investimento em criptomoedas baseadas em blockchain',180, 0.08, 'renda_fixa');

INSERT INTO pessoa (documento_titular, nome, email, tipo_pessoa)
	VALUES 
		('12345678901', 'João Silva', 'joaosilva@email.com', 'fisica'),
		('98765432100', 'Maria Santos', 'mariasantos@email.com', 'fisica'),
		('11122233344', 'Pedro Costa', 'pedrocosta@email.com', 'fisica'),
		('55566677788', 'Ana Pereira', 'anapereira@email.com', 'fisica'),
		('99900011122', 'Paulo Souza', 'paulosouza@email.com', 'fisica'),
		('33344455566', 'Carla Lima', 'carlalima@email.com', 'fisica'),
		('77788899900', 'Luiz Rocha', 'luizrocha@email.com', 'fisica'),
		('12345678000190', 'Empresa X', 'empresax@email.com', 'juridica'),
		('98765432000110', 'Empresa Y', 'empresay@email.com', 'juridica'),
		('11223344000155', 'Empresa Z', 'empresaz@email.com', 'juridica'),
		('66777888000199', 'Empresa W', 'empresaw@email.com', 'juridica'),
		('22333444000177', 'Empresa V', 'empresav@email.com', 'juridica'),
		('88999000000111', 'Empresa U', 'empresau@email.com', 'juridica'),
		('44555666000133', 'Empresa T', 'empresat@email.com', 'juridica'),
		('00111222000144', 'Empresa S', 'empresas@email.com', 'juridica');
		

INSERT INTO conta (documento_titular, montante_financeiro, id_produto, cdi, tipo_conta)
	VALUES 
	('00111222000144', 15000.00, 1, NULL, 'invesimento_automatico'),
	('11223344000155', 10000.00, NULL, 0.065, 'cdi'),
	('11223344000155', 15000.00, 2, NULL, 'invesimento_automatico'),
	('11122233344', 15000.00, NULL, 0.065, 'cdi'),
	('11122233344', 20000.00, NULL, NULL, 'corrente'),
	('11122233344', 25000.00, 3, NULL, 'invesimento_automatico'),
	('12345678000190', 10000.00, NULL, 0.065, 'cdi'),
	('12345678000190', 15000.00, 4, NULL, 'invesimento_automatico'),
	('12345678901', 10000.00, NULL, 0.065, 'cdi'),
	('12345678901', 20000.00, NULL, NULL, 'corrente'),
	('12345678901', 25000.00, 5, NULL, 'invesimento_automatico'),
	('22333444000177', 12000.00, 6, NULL, 'invesimento_automatico'),
	('33344455566', 8000.00, 7, NULL, 'invesimento_automatico'),
	('33344455566', 10000.00, NULL, 0.065, 'cdi'),
	('44555666000133', 14000.00, 8, NULL, 'invesimento_automatico'),
	('55566677788', 2000.00, NULL, NULL, 'corrente'),
	('55566677788', 10000.00, NULL, 0.065, 'cdi'),
	('55566677788', 15000.00, 9, NULL, 'invesimento_automatico'),
	('66777888000199', 11000.00, 10, NULL, 'invesimento_automatico'),
	('77788899900', 20000.00, NULL, NULL, 'corrente'),
	('77788899900', 25000.00, 1, NULL, 'invesimento_automatico'),
	('88999000000111', 13000.00, 2, NULL, 'invesimento_automatico'),
	('98765432100', 5000.00, NULL, NULL, 'corrente'),
	('98765432100', 10000.00, NULL, 0.065, 'cdi'),
	('98765432100', 15000.00, 3, NULL, 'invesimento_automatico'),
	('98765432000110', 20000.00, NULL, NULL, 'corrente'),
	('98765432000110', 25000.00, 4, NULL, 'invesimento_automatico'),
	('99900011122', 12000.00, NULL, 0.065, 'cdi'),
	('99900011122', 20000.00, NULL, NULL, 'corrente'),
	('99900011122', 25000.00, 5, NULL, 'invesimento_automatico');


create database dbtest_vcriquinho;

\c dbtest_vcriquinho

CREATE TYPE TIPO_PRODUTO AS ENUM ('renda_fixa', 'renda_variavel');
create table produto (
  id_produto SERIAL PRIMARY KEY,
  nome VARCHAR(50) UNIQUE NOT NULL,
  descricao VARCHAR(255) NOT NULL,
  rendimento_mensal DECIMAL(10, 2) NOT NULL,
  carencia INT,
  tipo_produto TIPO_PRODUTO NOT NULL,
  ativo BOOLEAN DEFAULT TRUE,
  data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  data_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TYPE TIPO_PESSOA AS ENUM ('fisica', 'juridica');
CREATE TABLE pessoa (
  id SERIAL,
  documento_titular VARCHAR(25) PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  email VARCHAR(50) UNIQUE NOT NULL,
  tipo_pessoa TIPO_PESSOA NOT NULL,
  ativo BOOLEAN DEFAULT TRUE,
  data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  data_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TYPE TIPO_CONTA AS ENUM ('cdi', 'corrente', 'invesimento_automatico');
CREATE TABLE conta (
  id SERIAL PRIMARY KEY,
  documento_titular VARCHAR(30) REFERENCES pessoa(documento_titular),
  montante_financeiro DECIMAL(10, 2) NOT NULL,
  id_produto INT REFERENCES produto(id_produto),
  cdi DECIMAL(10, 2),
  tipo_conta TIPO_CONTA NOT NULL,
  ativo BOOLEAN DEFAULT TRUE,
  data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  data_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE conta
ADD CONSTRAINT sem_duplicidade_conta_documento UNIQUE (documento_titular, tipo_conta);

CREATE OR REPLACE FUNCTION atualiza_data_modificacao()
RETURNS TRIGGER AS $$
BEGIN
  NEW.data_modificacao = NOW();
  RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER set_data_modificacao_produto
BEFORE UPDATE ON produto
FOR EACH ROW
EXECUTE PROCEDURE atualiza_data_modificacao();

CREATE TRIGGER set_data_modificacao_pessoa
BEFORE UPDATE ON pessoa
FOR EACH ROW
EXECUTE PROCEDURE atualiza_data_modificacao();

CREATE TRIGGER set_data_modificacao
BEFORE UPDATE ON conta
FOR EACH ROW
EXECUTE PROCEDURE atualiza_data_modificacao();


INSERT INTO pessoa (documento_titular, nome, email, tipo_pessoa)
		VALUES 
			('12345678901', 'João Silva', 'joaosilva@email.com', 'fisica'),
			('98765432100', 'Maria Santos', 'mariasantos@email.com', 'fisica'),
			('11122233344', 'Pedro Costa', 'pedrocosta@email.com', 'fisica'),
			('55566677788', 'Ana Pereira', 'anapereira@email.com', 'fisica'),
			('99900011122', 'Paulo Souza', 'paulosouza@email.com', 'fisica'),
			('33344455566', 'Carla Lima', 'carlalima@email.com', 'fisica'),
			('77788899900', 'Luiz Rocha', 'luizrocha@email.com', 'fisica'),
			('12345678000190', 'Empresa X', 'empresax@email.com', 'juridica'),
			('98765432000110', 'Empresa Y', 'empresay@email.com', 'juridica'),
			('11223344000155', 'Empresa Z', 'empresaz@email.com', 'juridica'),
			('66777888000199', 'Empresa W', 'empresaw@email.com', 'juridica'),
			('22333444000177', 'Empresa V', 'empresav@email.com', 'juridica'),
			('88999000000111', 'Empresa U', 'empresau@email.com', 'juridica'),
			('44555666000133', 'Empresa T', 'empresat@email.com', 'juridica'),
			('00111222000144', 'Empresa S', 'empresas@email.com', 'juridica');

INSERT INTO produto (nome, descricao, carencia, rendimento_mensal, tipo_produto) 
	VALUES 
		('Fundo de Renda Fixa ABC', 'Fundo de investimento em renda fixa com baixo risco',  1825, 0.015, 'renda_fixa'),
		('Ações Tech Growth', 'Fundo de investimento em empresas de tecnologia', NULL, 0.05, 'renda_variavel'),
		('Fundo Imobiliário Prime Realty', 'Fundo de investimento em imóveis comerciais', 1095, 0.02, 'renda_fixa'),
		('Tesouro Direto IPCA+', 'Título do Tesouro Direto indexado ao IPCA',  730, 0.03, 'renda_fixa'),
		('Fundo de Ações Sustentáveis ESG', 'Fundo de investimento em empresas com práticas sustentáveis', NULL, 0.04,  'renda_variavel'),
		('CDB Banco X', 'Certificado de Depósito Bancário do Banco X', 365, 0.02, 'renda_fixa'),
		('Fundo Multimercado Global', 'Fundo de investimento diversificado globalmente', NULL, 0.03, 'renda_variavel'),
		('Fundo de Previdência Conservador', 'Fundo de previdência com alocação conservadora', 2190, 0.02, 'renda_fixa'),
		('Fundo de Investimento em Ouro', 'Fundo de investimento em barras de ouro', NULL, 0.01, 'renda_variavel'),
		('Fundo de Criptomoedas Blockchain', 'Fundo de investimento em criptomoedas baseadas em blockchain',180, 0.08, 'renda_fixa');

INSERT INTO conta (documento_titular, montante_financeiro, id_produto, cdi, tipo_conta)
		VALUES 
		('00111222000144', 15000.00, 1, NULL, 'invesimento_automatico'),
		('11223344000155', 10000.00, NULL, 0.065, 'cdi'),
		('11223344000155', 15000.00, 2, NULL, 'invesimento_automatico'),
		('11122233344', 15000.00, NULL, 0.065, 'cdi'),
		('11122233344', 20000.00, NULL, NULL, 'corrente'),
		('11122233344', 25000.00, 3, NULL, 'invesimento_automatico'),
		('12345678000190', 10000.00, NULL, 0.065, 'cdi'),
		('12345678000190', 15000.00, 4, NULL, 'invesimento_automatico'),
		('12345678901', 10000.00, NULL, 0.065, 'cdi'),
		('12345678901', 20000.00, NULL, NULL, 'corrente'),
		('12345678901', 25000.00, 5, NULL, 'invesimento_automatico'),
		('22333444000177', 12000.00, 6, NULL, 'invesimento_automatico'),
		('33344455566', 8000.00, 7, NULL, 'invesimento_automatico'),
		('33344455566', 10000.00, NULL, 0.065, 'cdi'),
		('44555666000133', 14000.00, 8, NULL, 'invesimento_automatico'),
		('55566677788', 2000.00, NULL, NULL, 'corrente'),
		('55566677788', 10000.00, NULL, 0.065, 'cdi'),
		('55566677788', 15000.00, 9, NULL, 'invesimento_automatico'),
		('66777888000199', 11000.00, 10, NULL, 'invesimento_automatico'),
		('77788899900', 20000.00, NULL, NULL, 'corrente'),
		('77788899900', 25000.00, 1, NULL, 'invesimento_automatico'),
		('88999000000111', 13000.00, 2, NULL, 'invesimento_automatico'),
		('98765432100', 5000.00, NULL, NULL, 'corrente'),
		('98765432100', 10000.00, NULL, 0.065, 'cdi'),
		('98765432100', 15000.00, 3, NULL, 'invesimento_automatico'),
		('98765432000110', 20000.00, NULL, NULL, 'corrente'),
		('98765432000110', 25000.00, 4, NULL, 'invesimento_automatico'),
		('99900011122', 12000.00, NULL, 0.065, 'cdi'),
		('99900011122', 20000.00, NULL, NULL, 'corrente'),
		('99900011122', 25000.00, 5, NULL, 'invesimento_automatico');



CREATE OR REPLACE FUNCTION reset_table_in_pessoa()
RETURNS BOOLEAN AS $$
BEGIN
  	TRUNCATE pessoa RESTART IDENTITY CASCADE;

	INSERT INTO pessoa (documento_titular, nome, email, tipo_pessoa)
		VALUES 
			('12345678901', 'João Silva', 'joaosilva@email.com', 'fisica'),
			('98765432100', 'Maria Santos', 'mariasantos@email.com', 'fisica'),
			('11122233344', 'Pedro Costa', 'pedrocosta@email.com', 'fisica'),
			('55566677788', 'Ana Pereira', 'anapereira@email.com', 'fisica'),
			('99900011122', 'Paulo Souza', 'paulosouza@email.com', 'fisica'),
			('33344455566', 'Carla Lima', 'carlalima@email.com', 'fisica'),
			('77788899900', 'Luiz Rocha', 'luizrocha@email.com', 'fisica'),
			('12345678000190', 'Empresa X', 'empresax@email.com', 'juridica'),
			('98765432000110', 'Empresa Y', 'empresay@email.com', 'juridica'),
			('11223344000155', 'Empresa Z', 'empresaz@email.com', 'juridica'),
			('66777888000199', 'Empresa W', 'empresaw@email.com', 'juridica'),
			('22333444000177', 'Empresa V', 'empresav@email.com', 'juridica'),
			('88999000000111', 'Empresa U', 'empresau@email.com', 'juridica'),
			('44555666000133', 'Empresa T', 'empresat@email.com', 'juridica'),
			('00111222000144', 'Empresa S', 'empresas@email.com', 'juridica');

	PERFORM  reset_table_in_produto_and_conta();
	
  RETURN TRUE;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION reset_table_in_produto_and_conta()
RETURNS BOOLEAN AS $$
BEGIN
	TRUNCATE produto RESTART IDENTITY CASCADE;
	
	INSERT INTO produto (nome, descricao, carencia, rendimento_mensal, tipo_produto) 
	VALUES 
		('Fundo de Renda Fixa ABC', 'Fundo de investimento em renda fixa com baixo risco',  1825, 0.015, 'renda_fixa'),
		('Ações Tech Growth', 'Fundo de investimento em empresas de tecnologia', NULL, 0.05, 'renda_variavel'),
		('Fundo Imobiliário Prime Realty', 'Fundo de investimento em imóveis comerciais', 1095, 0.02, 'renda_fixa'),
		('Tesouro Direto IPCA+', 'Título do Tesouro Direto indexado ao IPCA',  730, 0.03, 'renda_fixa'),
		('Fundo de Ações Sustentáveis ESG', 'Fundo de investimento em empresas com práticas sustentáveis', NULL, 0.04,  'renda_variavel'),
		('CDB Banco X', 'Certificado de Depósito Bancário do Banco X', 365, 0.02, 'renda_fixa'),
		('Fundo Multimercado Global', 'Fundo de investimento diversificado globalmente', NULL, 0.03, 'renda_variavel'),
		('Fundo de Previdência Conservador', 'Fundo de previdência com alocação conservadora', 2190, 0.02, 'renda_fixa'),
		('Fundo de Investimento em Ouro', 'Fundo de investimento em barras de ouro', NULL, 0.01, 'renda_variavel'),
		('Fundo de Criptomoedas Blockchain', 'Fundo de investimento em criptomoedas baseadas em blockchain',180, 0.08, 'renda_fixa');

	
	PERFORM  reset_table_in_conta();

	 
  RETURN TRUE;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION reset_table_in_conta()
RETURNS BOOLEAN AS $$
BEGIN
  	TRUNCATE conta RESTART IDENTITY CASCADE;

	INSERT INTO conta (documento_titular, montante_financeiro, id_produto, cdi, tipo_conta)
		VALUES 
		('00111222000144', 15000.00, 1, NULL, 'invesimento_automatico'),
		('11223344000155', 10000.00, NULL, 0.065, 'cdi'),
		('11223344000155', 15000.00, 2, NULL, 'invesimento_automatico'),
		('11122233344', 15000.00, NULL, 0.065, 'cdi'),
		('11122233344', 20000.00, NULL, NULL, 'corrente'),
		('11122233344', 25000.00, 3, NULL, 'invesimento_automatico'),
		('12345678000190', 10000.00, NULL, 0.065, 'cdi'),
		('12345678000190', 15000.00, 4, NULL, 'invesimento_automatico'),
		('12345678901', 10000.00, NULL, 0.065, 'cdi'),
		('12345678901', 20000.00, NULL, NULL, 'corrente'),
		('12345678901', 25000.00, 5, NULL, 'invesimento_automatico'),
		('22333444000177', 12000.00, 6, NULL, 'invesimento_automatico'),
		('33344455566', 8000.00, 7, NULL, 'invesimento_automatico'),
		('33344455566', 10000.00, NULL, 0.065, 'cdi'),
		('44555666000133', 14000.00, 8, NULL, 'invesimento_automatico'),
		('55566677788', 2000.00, NULL, NULL, 'corrente'),
		('55566677788', 10000.00, NULL, 0.065, 'cdi'),
		('55566677788', 15000.00, 9, NULL, 'invesimento_automatico'),
		('66777888000199', 11000.00, 10, NULL, 'invesimento_automatico'),
		('77788899900', 20000.00, NULL, NULL, 'corrente'),
		('77788899900', 25000.00, 1, NULL, 'invesimento_automatico'),
		('88999000000111', 13000.00, 2, NULL, 'invesimento_automatico'),
		('98765432100', 5000.00, NULL, NULL, 'corrente'),
		('98765432100', 10000.00, NULL, 0.065, 'cdi'),
		('98765432100', 15000.00, 3, NULL, 'invesimento_automatico'),
		('98765432000110', 20000.00, NULL, NULL, 'corrente'),
		('98765432000110', 25000.00, 4, NULL, 'invesimento_automatico'),
		('99900011122', 12000.00, NULL, 0.065, 'cdi'),
		('99900011122', 20000.00, NULL, NULL, 'corrente'),
		('99900011122', 25000.00, 5, NULL, 'invesimento_automatico');
	
  RETURN TRUE;
END;
$$ LANGUAGE plpgsql;

SELECT reset_table_in_pessoa();
SELECT reset_table_in_produto_and_conta();
SELECT reset_table_in_conta();

