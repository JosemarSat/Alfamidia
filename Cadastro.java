/*
 * Classe Cadastro
 * Autor Josemar Sátiro
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Cadastro {
    public static void main(String[] args) {
        dadosPessoas();
    }

    public static void dadosPessoas()  {

        Scanner ler = new Scanner(System.in);
        Pessoa pessoa;
        Aluno aluno;
        List<Pessoa> listaPessoas = new ArrayList<>();
        List<Aluno> listaAlunos = new ArrayList<>();
        String resp = "";
        String busca;
        int opcao = 0;

        do {
            System.out.println();
            System.out.println(" Sistema de Cadastro de Pessoas/Alunos");
            System.out.println("|=====================================|");
            System.out.println("|    Escolha uma das opções abaixo    |");
            System.out.println("|-------------------------------------|");
            System.out.println("| Opção 1 - Cadastrar pessoas / alunos|");
            System.out.println("| Opção 2 - Alterar                   | ");
            System.out.println("| Opção 3 - Excluir                   |");
            System.out.println("| Opção 4 - Imprime                   | ");
            System.out.println("| Opção 0 - Sair do programa          |");
            System.out.println("|_____________________________________|");

            System.out.print("Digite aqui sua opção: ");
            try {
                opcao = Integer.parseInt(ler.nextLine());
            } catch(Exception e) {
                System.out.println("Digite um número de 0 a 4 !!!");
            }
            System.out.println();
            if(opcao == 1){
                //Cria um novo objeto
                pessoa = new Pessoa();
                aluno = new Aluno();

                System.out.print("Digite o nome: ");
                String nome = ler.nextLine();
                pessoa.setNome(nome);

                System.out.print("Telefone: ");
                String telefone = ler.nextLine();
                pessoa.setTelefone(telefone);
                String dns="";
                //verifica se a data digitada é válida
                System.out.print("Data de Nascimento: (dd/mm/aaaa): ");
                dns = ler.nextLine();
                while (!checkData(dns)) {
                    System.out.print("Data de Nascimento: (dd/mm/aaaa): ");
                    dns = ler.nextLine();
                    pessoa.setDtaNasc(dns);
                }
                String dct="";
                while (!checkData(dct)) {
                    System.out.print("Data do cadastro: (dd/mm/aaaa): ");
                    dct = ler.nextLine();
                    pessoa.setDtaCad(dct);
                    pessoa.setDtaAlt(dct);
                }
                System.out.print("Inserir Nota ? ( S / N ): ");
                resp = (ler.nextLine());
                if (resp.equalsIgnoreCase("s")) {

                    System.out.print("Digite a Nota: ");
                    try {
                        aluno.setNota((ler.nextFloat()));
                        aluno.setNome(nome);
                        aluno.setTelefone(telefone);
                        aluno.setDtaNasc(dns);
                        aluno.setDtaCad(dct);
                        aluno.setDtaAlt(dct);
                    } catch (Exception e) {
                        System.out.println("Digite uma nota de 1 a 10. ");
                    }
                }
                if (confirma("INCLUSÃO")) {
                    ler.nextLine();
                    System.out.println();
                    if (resp.equalsIgnoreCase("s")) {
                        //guarda o objeto aluno em uma lista
                        listaAlunos.add(aluno);
                    } else {
                        //guarda o objeto pessoa em uma lista
                        listaPessoas.add(pessoa);
                    }
                } else {
                    System.out.println("Cadastro não realizado");
                }
            } else if(opcao == 2) {
                //Alterar um registro
                System.out.print("Digite o Nome para alterar: ");
                busca = ler.nextLine();
                System.out.println("Deseja alterar um [ A ] luno ou [ P ] essoa ?");
                resp = ler.nextLine();
                int indice;
                if (resp.equalsIgnoreCase("A")) {
                    for (int a = 0; a < listaAlunos.size(); a++) {
                        indice = a;
                        if (busca.equalsIgnoreCase(listaAlunos.get(a).getNome())) {
                            imprimirAlunos(listaAlunos);
                            alterarAluno(listaAlunos, indice, busca);
                            break;
                        } else {
                            if (indice == (listaAlunos.size()-1)) {
                                System.out.println("Aluno não cadastrado !");
                            }
                        }
                    }
                } else {
                    for (int p = 0; p < listaPessoas.size(); p++) {
                        indice = p;
                        if (busca.equalsIgnoreCase(listaPessoas.get(p).getNome())) {
                            imprimir(listaPessoas);
                            alterar(listaPessoas, indice, busca);
                            break;
                        } else {
                            if (indice == (listaPessoas.size()-1)) {
                                System.out.println("Pessoa não cadastrada !");
                            }
                        }
                    }
                }
            } else if(opcao == 3) {
                //Excluir um ítem cadastrado
                System.out.print("Digite o Nome para Excluir: ");
                String excluir = ler.nextLine();
                int indice;
                if (listaPessoas.isEmpty() && listaAlunos.isEmpty()) {
                    System.out.println("Não existem pessoas ou alunos cadastrados !!!");
                }
                for (int exa = 0; exa < listaAlunos.size(); exa++){
                    indice = exa;
                    if (listaAlunos.get(exa).getNome().equalsIgnoreCase(excluir)) {
                        System.out.println();
                        imprimirAlunos(listaAlunos);

                        if (confirma("exclusão")) {
                            listaAlunos.remove(indice);
                            System.out.print("Registro do aluno " + excluir + " excluído!!!");
                        } else {
                            System.out.println(excluir + " não foi excluido(a) !!!");
                        }
                    } else {
                        System.out.println(excluir + " não está cadastrado(a) !!!");
                    }
                }
                for (int exp = 0; exp < listaPessoas.size(); exp++) {
                    indice = exp;
                    if (listaPessoas.get(exp).getNome().equalsIgnoreCase(excluir)) {
                        System.out.println();
                        imprimir(listaPessoas);
                        if (confirma("exclusão")) {
                            listaPessoas.remove(indice);
                            System.out.print("Registro pessoal de " + excluir + " excluído!!!");
                        }else {
                            System.out.println(excluir + " não foi excluido(a) !!!");
                        }
                    } else {
                        System.out.println(excluir + " não está cadastrado(a) !!!");
                    }
                }
            }else if(opcao == 4) {
                //verifica se houve inserção de dados em um dos objetos criados e imprime o resultado
                if (listaPessoas.isEmpty() && listaAlunos.isEmpty()){
                    System.out.println("Não existem pessoas cadastradas!!!");
                }else{
                    if (!listaPessoas.isEmpty() || !listaAlunos.isEmpty()) {
                        imprimir(listaPessoas);
                        imprimirAlunos(listaAlunos);
                        //só chega aqui se houver inserção em um dos Arraylists
                    } else if (resp.equalsIgnoreCase("s")) {
                        imprimirAlunos(listaAlunos);
                    }else
                        imprimir(listaPessoas);
                }
                System.out.println("Pressione qualquer tecla para continuar.");
                ler.nextLine();
            }
        } while (opcao != 0);

        ler.close();
    }
    /*
     * Imprime a lista dos alunos cadastrados
     * @param listaAlunos
     */
    private static void imprimirAlunos(List<Aluno> listaAlunos) {
        for (int impA = 0; impA < listaAlunos.size();impA++) {
            if (impA == 0) {
                System.out.println();
                System.out.println("==========ALUNOS===========");
                System.out.println("Nome: " + listaAlunos.get(impA).getNome());
                System.out.println("Telefone: " + listaAlunos.get(impA).getTelefone());
                System.out.println("Dta. Nascimento: " +listaAlunos.get(impA).getDtaNasc());
                System.out.println("Dta. Cadastro: " + listaAlunos.get(impA).getDtaCad());
                System.out.println("Dta. Alteração: " + listaAlunos.get(impA).getDtaAlt());
                System.out.println("Nota: "+ listaAlunos.get(impA).getNota());
            } else {
                System.out.println("===========================");
                System.out.println("Nome: " + listaAlunos.get(impA).getNome());
                System.out.println("Telefone: " + listaAlunos.get(impA).getTelefone());
                System.out.println("Dta. Nascimento: " + listaAlunos.get(impA).getDtaNasc());
                System.out.println("Dta. Cadastro: " + listaAlunos.get(impA).getDtaCad());
                System.out.println("Dta. Alteração: " + listaAlunos.get(impA).getDtaAlt());
                System.out.println("Nota: "+ listaAlunos.get(impA).getNota());
            }
        }
    }
    /*
     * Imprime a lista de pessoas cadastradas
     * @param listaPessoas
     */
    private static void imprimir(List<Pessoa> listaPessoas) {
        for (int impP = 0; impP < listaPessoas.size();impP++) {
            if (impP == 0) {
                System.out.println();
                System.out.println("=========PESSOAS===========");
                System.out.println("Nome: " + listaPessoas.get(impP).getNome());
                System.out.println("Telefone: " + listaPessoas.get(impP).getTelefone());
                System.out.println("Dta. Nascimento: " +listaPessoas.get(impP).getDtaNasc());
                System.out.println("Dta. Cadastro: " + listaPessoas.get(impP).getDtaCad());
                System.out.println("Dta. Alteração: " + listaPessoas.get(impP).getDtaAlt());
            } else {
                System.out.println("===========================");
                System.out.println("Nome: " + listaPessoas.get(impP).getNome());
                System.out.println("Telefone: " + listaPessoas.get(impP).getTelefone());
                System.out.println("Dta. Nascimento: " + listaPessoas.get(impP).getDtaNasc());
                System.out.println("Dta. Cadastro: " + listaPessoas.get(impP).getDtaCad());
                System.out.println("Dta. Alteração: " + listaPessoas.get(impP).getDtaAlt());
            }
        }
    }
    /**
     * Alterar os dados das pessoas
     */
    public static void alterar(List<Pessoa> listaPessoas, int indice, String subst) {
        Scanner ler = new Scanner(System.in);
        if (pergunta("nome")) {
            System.out.print(subst + " é o nome atual. Digite o Novo Nome: ");
            String nome = ler.nextLine();

            if (confirma("alteração")) {
                listaPessoas.get(indice).setNome(nome);
            }
        }
        if (pergunta("telefone")) {
            System.out.print(listaPessoas.get(indice).getTelefone() + " é o telefone atual. Digite o Novo número: ");
            String fone = ler.nextLine();

            if (confirma("alteração")) {
                listaPessoas.get(indice).setTelefone(fone);
            }
        }
        if (pergunta("Data de Nascimento")) {
            System.out.print(listaPessoas.get(indice).getDtaNasc() + " é a data atual. Digite a nova Data: ");
            String dnas = ler.nextLine();

            if (confirma("alteração")) {
                if (checkData(dnas)) {
                    listaPessoas.get(indice).setDtaNasc(dnas);
                }
            }
        }
        if (pergunta("Data do Cadastro")) {
            System.out.print(listaPessoas.get(indice).getDtaCad() + " é a data atual. Digite a nova Data: ");
            String dcad = ler.nextLine();

            if (confirma("alteração")) {
                if (checkData(dcad))
                    listaPessoas.get(indice).setDtaCad(dcad);
            }
        }
        if (pergunta("Data da alteração do Cadastro: (dd/mm/aaaa): ")) {
            System.out.print(listaPessoas.get(indice).getDtaAlt() + " é a data atual. Digite a nova Data: ");
            String dAlt = ler.nextLine();
            if (confirma("alteração")) {
                if (checkData(dAlt))
                    listaPessoas.get(indice).setDtaAlt(dAlt);
            }
        }
    }
     //Alterar os dados dos alunos
    public static void alterarAluno(List<Aluno> listaAlunos, int indice, String subst) {
        Scanner ler = new Scanner(System.in);

        if (pergunta("nome")) {
            System.out.print(subst + " é o nome atual. Digite o Novo Nome: ");
            String nome = ler.nextLine();

            if (confirma("alteração")) {
                listaAlunos.get(indice).setNome(nome);
            }
        }
        if (pergunta("telefone")) {
            System.out.print(listaAlunos.get(indice).getTelefone() +" é o telefone atual. Digite o Novo número: ");
            String fone = ler.nextLine();

            if (confirma("alteração")) {
                listaAlunos.get(indice).setTelefone(fone);
            }
        }
        if (pergunta("Data de Nascimento")) {
            System.out.print(listaAlunos.get(indice).getDtaNasc() +" é a data atual. Digite a nova Data: " );
            String dnas = ler.nextLine();

            if (confirma("alteração")) {
                if (checkData(dnas))
                    listaAlunos.get(indice).setDtaNasc(dnas);
            }
        }
        if (pergunta("Data do Cadastro")) {
            System.out.print(listaAlunos.get(indice).getDtaCad() + " é a data atual. Digite a nova Data: ");
            String dcad = ler.nextLine();

            if (confirma("alteração")) {
                if (checkData(dcad))
                    listaAlunos.get(indice).setDtaCad(dcad);
            }
        }
        if (pergunta("Data da alteração do Cadastro: (dd/mm/aaaa): ")) {
            System.out.print(listaAlunos.get(indice).getDtaAlt() + " é a data atual. Digite a nova Data: ");
            String dAlt = ler.nextLine();

            if (confirma("alteração")) {
                if (checkData(dAlt))
                    listaAlunos.get(indice).setDtaAlt(dAlt);
            }
        }
        if (pergunta("Nota")) {
            System.out.print(listaAlunos.get(indice).getNota() +" nota atual. Digite a nova Nota: " );
            Float nota=0F;
            try {
                nota = Float.valueOf(ler.nextLine());
            }catch(Exception e) {
                System.out.println("Erro na digitação da nota !!! ");
            }

            if (confirma("alteração")) {
                listaAlunos.get(indice).setNota(nota);
            }
        }

    }
    //rotina auxiliar para verificar os campos que serão alterados
    public static boolean pergunta(String campo) {
        Scanner ler = new Scanner(System.in);
        String resp;
        System.out.println("Deseja alterar " + campo + " ? ( S / N )");
        resp = ler.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            return true;
        } else {
            System.out.println(campo.toUpperCase() + " não foi alterado(a).");
            System.out.println();
            return false;
        }
    }
     //rotina auxiliar para confirmação de operação
    public static boolean confirma(String opcao) {
        Scanner ler = new Scanner(System.in);
        String resp;
        System.out.println("Confirma a " + opcao + "? ( S / N )");
        resp = ler.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            System.out.println(opcao.toUpperCase() + " realizada com sucesso !!!");
            System.out.println();
            return true;
        } else {
            System.out.println(opcao.toUpperCase() + " não foi realizada.");
            System.out.println();
            return false;
        }
    }
    //rotina para verificar a validade da data digitada
    private static boolean checkData(String dns) {

        try {
            Integer dia = Integer.parseInt(dns.substring(0,2));
            Integer mes = Integer.parseInt(dns.substring(3,5));
            Integer ano = Integer.parseInt(dns.substring(6,10));

            if (dia < 1 || dia > 31) {
                System.out.println("Data inválida !!");
                return false;
            }

            if (mes < 1 || mes > 12) {
                System.out.println("Data inválida !!");
                return false;
            }
            if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                if (dia < 1 || dia > 30) {
                    System.out.println("Mês só tem 30 dias. Data inválida !!");
                    return false;
                }
            } else {
                if (dia < 1 || dia > 31) {
                    System.out.println("Data inválida !!");
                    return false;
                }
            }
            if (mes == 2) {
                if (ano % 4 == 0 && (ano % 100 != 0 ) || ( ano % 400 == 0 )) {
                    if (dia < 1 || dia > 29) {
                        System.out.println("Data inválida !!");
                        return false;
                    }

                }else if(dia < 1 || dia > 28) {
                    System.out.println("Ano não é bissexto. Data inválida !!");
                    return false;
                }
            }
            if (ano < 1900 || ano > 2022) {
                System.out.println("Data inválida !!");
                return false;
            }

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date data = formato.parse(dns);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

