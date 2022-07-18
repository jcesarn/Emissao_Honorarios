-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 18-Maio-2020 às 18:39
-- Versão do servidor: 10.4.11-MariaDB
-- versão do PHP: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `emissao`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE `clientes` (
  `id` int(3) NOT NULL,
  `id_empresa` int(3) NOT NULL,
  `nome` varchar(50) COLLATE utf8_bin NOT NULL,
  `endereco` varchar(255) COLLATE utf8_bin NOT NULL,
  `latitude` varchar(255) COLLATE utf8_bin NOT NULL,
  `longitude` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `empresa`
--

CREATE TABLE `empresa` (
  `id` int(3) NOT NULL,
  `nome` varchar(50) COLLATE utf8_bin NOT NULL,
  `endereco` varchar(255) COLLATE utf8_bin NOT NULL,
  `cnpj` varchar(18) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `honorarios`
--

CREATE TABLE `honorarios` (
  `id` int(3) NOT NULL,
  `id_cliente` int(3) NOT NULL,
  `data` date NOT NULL,
  `desc_1` varchar(200) COLLATE utf8_bin NOT NULL,
  `desc_2` varchar(200) COLLATE utf8_bin NOT NULL,
  `desc_3` varchar(200) COLLATE utf8_bin NOT NULL,
  `desc_4` varchar(200) COLLATE utf8_bin NOT NULL,
  `valor_1` float NOT NULL,
  `valor_2` float NOT NULL,
  `valor_3` float NOT NULL,
  `valor_4` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `operadores`
--

CREATE TABLE `operadores` (
  `id` int(3) NOT NULL,
  `nome` varchar(50) COLLATE utf8_bin NOT NULL,
  `usuario` varchar(255) COLLATE utf8_bin NOT NULL,
  `senha` varchar(255) COLLATE utf8_bin NOT NULL,
  `id_empresa` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `relatorios`
--

CREATE TABLE `relatorios` (
  `id` int(3) NOT NULL,
  `id_operador` int(3) NOT NULL,
  `id_cliente` int(3) NOT NULL,
  `id_honorario` int(3) NOT NULL,
  `descricao` varchar(50) COLLATE utf8_bin NOT NULL,
  `data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tel_empresa`
--

CREATE TABLE `tel_empresa` (
  `id` int(3) NOT NULL,
  `id_empresa` int(3) NOT NULL,
  `telefone` varchar(15) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_cliente_empresa` (`id_empresa`);

--
-- Índices para tabela `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `honorarios`
--
ALTER TABLE `honorarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_honorario_cliente` (`id_cliente`);

--
-- Índices para tabela `operadores`
--
ALTER TABLE `operadores`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_operador_empresa` (`id_empresa`) USING BTREE;

--
-- Índices para tabela `relatorios`
--
ALTER TABLE `relatorios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_relatorio_cliente` (`id_cliente`),
  ADD KEY `fk_relatorio_honorario` (`id_honorario`),
  ADD KEY `fk_relatorio_operador` (`id_operador`) USING BTREE;

--
-- Índices para tabela `tel_empresa`
--
ALTER TABLE `tel_empresa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_telefone_empresa` (`id_empresa`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `empresa`
--
ALTER TABLE `empresa`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `honorarios`
--
ALTER TABLE `honorarios`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `operadores`
--
ALTER TABLE `operadores`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `relatorios`
--
ALTER TABLE `relatorios`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `tel_empresa`
--
ALTER TABLE `tel_empresa`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `fk_cliente_empresa` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`);

--
-- Limitadores para a tabela `honorarios`
--
ALTER TABLE `honorarios`
  ADD CONSTRAINT `fk_honorario_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`);

--
-- Limitadores para a tabela `operadores`
--
ALTER TABLE `operadores`
  ADD CONSTRAINT `fk_funcionario_empresa` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`);

--
-- Limitadores para a tabela `relatorios`
--
ALTER TABLE `relatorios`
  ADD CONSTRAINT `fk_relatorio_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`),
  ADD CONSTRAINT `fk_relatorio_funcionario` FOREIGN KEY (`id_operador`) REFERENCES `operadores` (`id`),
  ADD CONSTRAINT `fk_relatorio_honorario` FOREIGN KEY (`id_honorario`) REFERENCES `honorarios` (`id`);

--
-- Limitadores para a tabela `tel_empresa`
--
ALTER TABLE `tel_empresa`
  ADD CONSTRAINT `fk_telefone_empresa` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
