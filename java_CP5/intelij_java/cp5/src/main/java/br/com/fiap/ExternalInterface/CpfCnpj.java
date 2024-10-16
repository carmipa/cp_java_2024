package br.com.fiap.ExternalInterface;

public class CpfCnpj {

    private static final int CPF_LENGTH = 11;
    private static final int CNPJ_LENGTH = 14;

    // Método para validar documento e informar se é CPF ou CNPJ
    public static String validarDocumento(String documento) {
        documento = documento.replaceAll("\\D", ""); // Remove caracteres não numéricos

        if (documento.length() == CPF_LENGTH) {
            if (isCPF(documento)) {
                return "CPF";
            } else {
                return "Documento CPF inválido.";
            }
        } else if (documento.length() == CNPJ_LENGTH) {
            if (isCNPJ(documento)) {
                return "CNPJ";
            } else {
                return "Documento CNPJ inválido.";
            }
        } else {
            return "Documento com comprimento inválido.";
        }
    }

    // Método para validar CPF
    public static boolean isCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", ""); // Remove todos os caracteres não numéricos

        if (cpf.length() != CPF_LENGTH || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            char dig10 = calcularDigitoCPF(cpf, 9);
            char dig11 = calcularDigitoCPF(cpf, 10);

            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }

    // Método para validar CNPJ
    public static boolean isCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", ""); // Remove todos os caracteres não numéricos

        if (cnpj.length() != CNPJ_LENGTH || cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        try {
            char dig13 = calcularDigitoCNPJ(cnpj, 12);
            char dig14 = calcularDigitoCNPJ(cnpj, 13);

            return (dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13));
        } catch (Exception e) {
            return false;
        }
    }

    // Métodos auxiliares para calcular dígitos do CPF e CNPJ
    private static char calcularDigitoCPF(String documento, int posicao) {
        int soma = 0;
        int peso = posicao + 1;

        for (int i = 0; i < posicao; i++) {
            int num = documento.charAt(i) - '0';
            soma += num * peso--;
        }

        int resto = 11 - (soma % 11);
        return (resto == 10 || resto == 11) ? '0' : (char) (resto + '0');
    }

    private static char calcularDigitoCNPJ(String documento, int posicao) {
        int[] pesos = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;

        for (int i = 0; i < posicao; i++) {
            int num = documento.charAt(i) - '0';
            soma += num * pesos[pesos.length - posicao + i];
        }

        int resto = soma % 11;
        return (resto < 2) ? '0' : (char) ((11 - resto) + '0');
    }
}
