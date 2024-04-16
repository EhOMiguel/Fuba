package com.example.acfuba.util;

import java.math.BigInteger;
import java.util.Random;

public class GerarPrimoAleatorio {

    public static void main(String[] args) {
        int numDigitos = 10; // Número de dígitos para cada número primo

        BigInteger primo1 = gerarPrimoAleatorio(numDigitos);
        BigInteger primo2 = gerarPrimoAleatorio(numDigitos);

        System.out.println("Número primo aleatório 1: " + primo1);
        System.out.println("Número primo aleatório 2: " + primo2);
    }

public static BigInteger gerarPrimoAleatorio(int numDigitos) {
    BigInteger primo;
    Random random = new Random();

    // Calcula o número de bits necessários
    int numBits = numDigitos * 4; // Ajustar conforme necessário

    // Loop até encontrar um número primo
    do {
        // Gera um número aleatório com o número de bits especificado
        BigInteger candidato = new BigInteger(numBits, random);

        // Se o número for par, adiciona 1 para torná-lo ímpar
        if (!candidato.testBit(0)) {
            candidato = candidato.add(BigInteger.ONE);
        }

        // Testa se o número gerado é primo
        if (candidato.isProbablePrime(1000)) {
            primo = candidato;
            break;
        }
    } while (true);

    return primo;
}

}
