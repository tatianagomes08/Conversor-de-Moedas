import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        boolean sair = false;
        Scanner leitura = new Scanner(System.in);

        while (!sair){
            ImprimeMenuDeOpcoes();

            var opcaoEmTexto = leitura.nextLine();
            int opcaoEscolhida;

            try{
                opcaoEscolhida = Integer.parseInt(opcaoEmTexto);
            }catch (NumberFormatException e){
                System.out.println("Opção inválida!!!");
                continue;
            }

            if (opcaoEscolhida < 1 || opcaoEscolhida > 7){
                System.out.println("Opção inválida!!!");
                continue;
            }

            String moedaOrigem;
            String moedaDestino;
            if (opcaoEscolhida == 1){
                moedaOrigem = "USD";
                moedaDestino = "ARS";
            } else if (opcaoEscolhida == 2) {
                moedaOrigem = "ARS";
                moedaDestino = "USD";
            } else if (opcaoEscolhida == 3) {
                moedaOrigem = "USD";
                moedaDestino = "BRL";
            } else if (opcaoEscolhida == 4) {
                moedaOrigem = "BRL";
                moedaDestino = "USD";
            } else if (opcaoEscolhida == 5) {
                moedaOrigem = "USD";
                moedaDestino = "COP";
            } else if (opcaoEscolhida == 6) {
                moedaOrigem = "COP";
                moedaDestino = "USD";
            } else {
                sair = true;
                System.out.println("Você saiu do conversor de moedas!");
                continue;
            }

            System.out.println("Digite o valor que deseja converter:");
            var valorEmTexto = leitura.nextLine();
            double valor;
            try{
                valor = Double.parseDouble(valorEmTexto);
            } catch (NumberFormatException e){
                System.out.println("Valor inválido!!!");
                continue;
            }


            var resultado = CalculaConversao(moedaOrigem, moedaDestino, valor);
            System.out.println("Valor " + valor + " [" + moedaOrigem + "] " + "corresponde ao valor final de =>>> " + resultado + " [" + moedaDestino + "]");
        }
    }

    private static void ImprimeMenuDeOpcoes() {
        System.out.println("*********************************************************");
        System.out.println("Seja bem vindo(a) ao Conversor de Moedas!\nVeja abaixo as opções que temos disponíveis!\n");
        System.out.println("1) Dólar =>> Peso Argentino");
        System.out.println("2) Peso Argentino =>> Dólar");
        System.out.println("3) Dólar =>> Real Brasileiro");
        System.out.println("4) Real Brasileiro =>> Dólar");
        System.out.println("5) Dólar =>> Peso Colombiano");
        System.out.println("6) Peso Colombiano =>> Dólar");
        System.out.println("7) Sair");
        System.out.println("\nEscolha uma opção válida:");
        System.out.println("*********************************************************");
    }

    private static Double CalculaConversao(String moedaOrigem, String moedaDestino, Double valorASerConvertido) throws IOException, InterruptedException {
        var cliente = new ExchangeRateCliente();
        var conversoes = cliente.consultaConversoes(moedaOrigem);
        if (conversoes.containsKey(moedaDestino)){
            var fator = conversoes.get(moedaDestino);
            return valorASerConvertido * fator;
        }
        return 0.0;
    }
}