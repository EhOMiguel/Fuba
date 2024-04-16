package com.example.acfuba.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.ObjectUtils;

import com.example.acfuba.crypto.CalculaChavePublica;
import com.example.acfuba.util.GerarPrimoAleatorio;

import java.math.BigInteger;

@SpringBootApplication
@RestController
public class EndpointChavePublica {

    public static void main(String[] args) {
        SpringApplication.run(EndpointChavePublica.class, args);
    }

    @GetMapping("/chavesPublicas")
    public String obterChavesPublicas(@RequestParam(name = "token") BigInteger token) {
        // Verifica se o token está presente na URL (Normalmente nem chega nessa validação caso não tenha token)
        if(ObjectUtils.isEmpty(token)) {
            return "Token não fornecido na URL";
        }

        // Aqui você pode adicionar lógica para validar o token, se necessário

        int numDigitos = 10; // Número de dígitos para cada número primo

        // Gerar os números primos
        BigInteger p = GerarPrimoAleatorio.gerarPrimoAleatorio(numDigitos);
        BigInteger q = GerarPrimoAleatorio.gerarPrimoAleatorio(numDigitos);

        // Calcular n (produto de p e q)
        BigInteger n = CalculaChavePublica.calcularN(p, q);

        // Calcular fi(n)
        BigInteger fiN = CalculaChavePublica.calcularFiN(p, q);

        // Calcular a segunda chave pública "e"
        BigInteger e = CalculaChavePublica.calcularChavePublicaE(fiN);

        // Retornar as chaves públicas no formato desejado
        return "Chaves públicas: (" + e + ", " + n + ")";
    }
}
