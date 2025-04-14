package com.portestech;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<TimeEntry> records = TimeEntryRepository.loadEntries();

        while (true) {
            System.out.println("\n== SISTEMA DE CONTROLE DE PONTO ==");
            System.out.println("1. Registrar entrada");
            System.out.println("2. Registrar saída");
            System.out.println("3. Mostrar relatório");
            System.out.println("0. Sair");
            System.out.println("Escolha: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 0) {
                break;
            }

            switch (option) {
                case 1:
                    System.out.println("Nome do funcionário: ");
                    String employeeName = scanner.nextLine();
                    records.add(new TimeEntry(employeeName));
                    TimeEntryRepository.saveEntries(records);
                    System.out.println("Entrada registrada com sucesso!");
                    break;
                case 2:
                    System.out.println("Nome do funcionário: ");
                    String nameToSearch = scanner.nextLine();
                    boolean found = false;
                    for (TimeEntry timeEntry : records) {
                        if (timeEntry.getEmployeeName().equalsIgnoreCase(nameToSearch) && timeEntry.getExitTime() == null) {
                            timeEntry.registerExit();
                            System.out.println("Saída registrada com sucesso!");
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        TimeEntryRepository.saveEntries(records);
                    } else {
                        System.out.println("Funcionário não encontrado ou saída já registrada.");
                    }
                    break;
                case 3:
                    for (TimeEntry timeEntry : records) {
                        System.out.println("\nFuncionário: " + timeEntry.getEmployeeName());
                        System.out.println("Entrada: " + timeEntry.getEntryTime());
                        System.out.println("Saída: " +
                                (timeEntry.getExitTime() == null ? "Saída não registrada" : timeEntry.getExitTime()));
                        System.out.println("Horas trabalhadas: " +
                                (timeEntry.getExitTime() == null ? "Não contabilizado" : timeEntry.calculateWorkedHours()));
                    }
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}