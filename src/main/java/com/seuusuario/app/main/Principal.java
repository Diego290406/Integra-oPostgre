package com.seuusuario.app.main;

import com.seuusuario.app.dao.UsuarioDAO;
import com.seuusuario.app.model.Usuario;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private static final UsuarioDAO dao = new UsuarioDAO();
    private static final Scanner sc       = new Scanner(System.in);

    public static void main(String[] args) {
        int opc = 0;
        do {
            System.out.println("\n=== MENU USUÁRIOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Inserir");
            System.out.println("3. Excluir");
            System.out.println("4. Atualizar");
            System.out.println("5. Sair");
            System.out.print("Escolha: ");

            try {
                opc = Integer.parseInt(sc.nextLine());
                switch (opc) {
                    case 1: listar();    break;
                    case 2: inserir();   break;
                    case 3: excluir();   break;
                    case 4: atualizar(); break;
                    case 5: System.out.println("Encerrando..."); break;
                    default: System.out.println("Opção inválida");
                }

            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida. Digite um número de 1 a 5.");
            } catch (SQLException e) {
                System.err.println("Erro de banco de dados: " + e.getMessage());
            }

        } while (opc != 5);
    }

    private static void listar() throws SQLException {
        List<Usuario> lista = dao.listar();
        System.out.println("\nCódigo | Login               | Senha               | Sexo");
        for (Usuario u : lista) {
            System.out.printf("%6d | %-18s | %-18s | %s%n",
                u.getCodigo(), u.getLogin(), u.getSenha(), u.getSexo());
        }
    }

    private static void inserir() throws SQLException {
        System.out.print("Login: ");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Sexo (M/F): ");
        char sexo = sc.nextLine().toUpperCase().charAt(0);

        dao.inserir(new Usuario(0, login, senha, sexo));
        System.out.println("Usuário inserido com sucesso!");
    }

    private static void excluir() throws SQLException {
        System.out.print("Código a excluir: ");
        int codigo = Integer.parseInt(sc.nextLine());
        dao.excluir(codigo);
        System.out.println("Usuário excluído com sucesso!");
    }

    private static void atualizar() throws SQLException {
        System.out.print("Código a atualizar: ");
        int codigo = Integer.parseInt(sc.nextLine());
        System.out.print("Novo login: ");
        String login = sc.nextLine();
        System.out.print("Nova senha: ");
        String senha = sc.nextLine();
        System.out.print("Novo sexo (M/F): ");
        char sexo = sc.nextLine().toUpperCase().charAt(0);

        dao.atualizar(new Usuario(codigo, login, senha, sexo));
        System.out.println("Usuário atualizado com sucesso!");
    }
}

