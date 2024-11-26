# Banco Malvader

## Descrição do Projeto  
O Banco Malvader é uma aplicação bancária desenvolvida em **Java** com interface gráfica utilizando **Swing** e persistência de dados em **MySQL**. O sistema permite o gerenciamento de contas bancárias, incluindo cadastro de funcionários, clientes, geração de relatórios e diversas operações financeiras. Este projeto foi desenvolvido como parte do Trabalho Final da disciplina de **Programação Lab. Banco de Dados**, com foco em **Programação Orientada a Objetos (POO)** e integração com banco de dados.  

---

## Funcionalidades  

### Acesso ao Programa  
- Autenticação inicial com menu para selecionar:  
  - Funcionário  
  - Cliente  
  - Encerrar Programa  

### Menu Funcionário  
- **Abertura de Conta**  
  - Opções: Conta Poupança (CP) e Conta Corrente (CC).  
  - Campos: agência, número, nome do cliente, CPF, data de nascimento, endereço, etc.  
- **Encerramento de Conta**  
  - Requer senha de administrador.  
- **Consulta de Dados**  
  - Detalhes de contas, funcionários e clientes.  
- **Alteração de Dados**  
  - Alteração de limites, vencimentos, cargos e informações pessoais.  
- **Cadastro de Funcionários**  
  - Inserção de dados completos.  
- **Geração de Relatórios**  
  - Movimentações financeiras exportadas para Excel.  
- **Sair**  
  - Retorna ao menu principal.  

### Menu Cliente  
- **Operações de Conta**  
  - Consultar saldo, realizar depósitos, saques e visualizar extratos.  
- **Encerrar Programa**  
  - Finaliza a aplicação.  

---

## Requisitos Técnicos  

### Linguagem e Ferramentas  
- **Java SDK**: Padrão SDK 21 configurado.  
- **Banco de Dados**: MySQL.  
- **Interface Gráfica**: Swing.  

### Arquitetura do Projeto  
- **DAO**: Gerenciamento da persistência de dados.  
- **Model**: Representação das tabelas do banco de dados.  
- **View**: Interfaces Swing.  
- **Controller**: Conexão entre interface e lógica de negócio.  
- **Util**: Conexão e utilitários gerais.  

---

## Instruções de Uso  

1. **Configurar o SDK do Java**:  
   - Certifique-se de que o Java SDK 21 esteja instalado e configurado corretamente.  

2. **Criar o Banco de Dados**:  
   - Utilize o script SQL fornecido para criar as tabelas no MySQL.  

3. **Configurar a Senha do Banco de Dados**:  
   - Atualize as credenciais no arquivo de configuração (`ConnectionFactory`).  

4. **Criar o Primeiro Funcionário**:  
   - Configure manualmente o primeiro registro no banco de dados.  

5. **Executar o Código**:  
   - Navegue até a pasta `controller` e execute o arquivo `BancoMalvader.java`.  

---

## Estrutura de Classes  

### Classes Principais  
- **BancoMalvader**: Classe inicial do programa.  
- **Usuario** (abstrata): Base para `Funcionario` e `Cliente`.  
- **Conta** (abstrata): Base para `ContaCorrente` e `ContaPoupança`.  
- **Relatorio**: Geração de relatórios financeiros.  
- **ConexaoBanco**: Gerencia a conexão com o banco de dados.  

### Relacionamentos  
- **Funcionario** e **Cliente** herdam de **Usuario**.  
- **ContaCorrente** e **ContaPoupança** herdam de **Conta**.  
- **Cliente** possui relacionamento com **Conta**.  
- **Endereco** é um atributo de composição em **Usuario**.  

---

## Requisitos de Software  
- **Java Development Kit (JDK) 21**  
- **MySQL Server**  
- **Editor/IDE**: IntelliJ IDEA, Eclipse ou NetBeans.  

### Bibliotecas Externas  
- **Conector MySQL para Java** (`mysql-connector-java`).  

---

## Contribuição  

1. Faça um fork do projeto.  
2. Crie uma branch para sua feature:  
   ```bash
   git checkout -b feature/nova-feature