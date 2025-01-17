package com.plcoding.cryptotracker.core.data.networking

import com.plcoding.cryptotracker.BuildConfig


fun constructUrl(url: String): String {
    return when {
        url.contains(BuildConfig.BASE_URL) -> url
        url.startsWith("/") -> BuildConfig.BASE_URL + url.drop(1)
        else -> BuildConfig.BASE_URL + url
    }
}

/**
Objetivo: Garantir que qualquer URL passada para a função esteja completa, prefixando-a com a URL base (BuildConfig.BASE_URL) se necessário.

Como funciona:

A função usa uma expressão when para verificar três condições:

url.contains(BuildConfig.BASE_URL) -> url:

Verifica se a URL passada (url) já contém a URL base (BuildConfig.BASE_URL).
Se a URL já contém a base, significa que ela já está completa. Nesse caso, a função simplesmente retorna a URL original sem modificações.
url.startsWith("/") -> BuildConfig.BASE_URL + url.drop(1):

Verifica se a URL passada começa com uma barra (/). Isso geralmente indica um caminho relativo a partir da raiz do servidor.
Se a URL começa com uma barra, a função concatena a URL base (BuildConfig.BASE_URL) com a URL passada, removendo o primeiro caractere (a barra) usando url.drop(1). Isso evita ter duas barras consecutivas na URL final (ex: https://example.com//path).
else -> BuildConfig.BASE_URL + url:

Esta é a condição padrão (o else). Ela é executada se nenhuma das condições anteriores for verdadeira.
Neste caso, a função simplesmente concatena a URL base (BuildConfig.BASE_URL) com a URL passada. Isso cobre os casos em que a URL passada é um caminho relativo sem a barra inicial.
Exemplo prático:

Suponha que BuildConfig.BASE_URL seja "https://api.example.com".

Caso 1: constructUrl("https://api.example.com/users")

A primeira condição (url.contains(BuildConfig.BASE_URL)) é verdadeira.
A função retorna "https://api.example.com/users" (a URL original).
Caso 2: constructUrl("/products")

A segunda condição (url.startsWith("/")) é verdadeira.
A função retorna "https://api.example.com/products" (a URL base concatenada com o caminho, removendo a barra inicial).
Caso 3: constructUrl("orders")

Nenhuma das condições anteriores é verdadeira.
A função retorna "https://api.example.comorders" (a URL base concatenada com o caminho).
 **/