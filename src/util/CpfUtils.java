package util;

public class CpfUtils {
    
    public static boolean isValidCpf(String cpf) {
        if (cpf == null) {
            return false;
        }

        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        if (cpfLimpo.length() != 11) {
            return false;
        }

        if (cpfLimpo.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                int digito = cpfLimpo.charAt(i) - '0';
                soma += digito * (10 - i);
            }
            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito >= 10) {
                primeiroDigito = 0;
            }

            soma = 0;
            for (int i = 0; i < 10; i++) {
                int digito = cpfLimpo.charAt(i) - '0';
                soma += digito * (11 - i);
            }
            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito >= 10) {
                segundoDigito = 0;
            }

            int digito9 = cpfLimpo.charAt(9) - '0';
            int digito10 = cpfLimpo.charAt(10) - '0';
            
            return primeiroDigito == digito9 && segundoDigito == digito10;

        } catch (Exception e) {
            return false;
        }
    }
    
    public static String formatarCpf(String cpf) {
        if (cpf == null) {
            return null;
        }
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        if (cpfLimpo.length() != 11) {
            return cpf;
        }
        return String.format("%s.%s.%s-%s", 
            cpfLimpo.substring(0, 3),
            cpfLimpo.substring(3, 6),
            cpfLimpo.substring(6, 9),
            cpfLimpo.substring(9, 11));
    }
}
