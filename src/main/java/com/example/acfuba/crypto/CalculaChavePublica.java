package com.example.acfuba.crypto;

import java.math.BigInteger;

import com.example.acfuba.util.GerarPrimoAleatorio;

public class CalculaChavePublica {

    public static void main(String[] args) {
        int numDigitos = 10; // Número de dígitos para cada número primo

        // Gerar os números primos
        BigInteger p = GerarPrimoAleatorio.gerarPrimoAleatorio(numDigitos);
        BigInteger q = GerarPrimoAleatorio.gerarPrimoAleatorio(numDigitos);

        // Exibir os números primos gerados
        System.out.println("Número primo aleatório 1 (p): " + p);
        System.out.println("Número primo aleatório 2 (q): " + q);

        // Calcular n (produto de p e q)
        BigInteger n = calcularN(p, q);

        // Exibir n
        System.out.println("n: " + n);

        // Calcular fi(n)
        BigInteger fiN = calcularFiN(p, q);

        // Exibir fi(n)
        System.out.println("fi(n): " + fiN);

        // Calcular a segunda chave pública "e"
        BigInteger e = calcularChavePublicaE(fiN);

        // Exibir a segunda chave pública "e"
        System.out.println("Chave pública e: " + e);

        // Exibir as chaves públicas no formato desejado
        System.out.println("Chaves públicas: (" + e + ", " + n + ")");
    }

    public static BigInteger calcularN(BigInteger p, BigInteger q) {
        return p.multiply(q); // Multiplicar os primos para obter n
    }

    public static BigInteger calcularFiN(BigInteger p, BigInteger q) {
        // Calcular fi(n) = (p-1)*(q-1)
        return p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    }

    public static BigInteger calcularChavePublicaE(BigInteger fiN) {
        // Definir um valor arbitrário para "e" (pode ser um número primo aleatório menor que fi(n))
        BigInteger e = BigInteger.valueOf(65537); // Valor comum em práticas criptográficas

        // Verificar se "e" é válido (máximo divisor comum entre fi(n) e "e" deve ser igual a 1)
        while (!fiN.gcd(e).equals(BigInteger.ONE)) {
            // Incrementar "e" até encontrar um válido
            e = e.add(BigInteger.ONE);
        }

        return e;
    }
}
