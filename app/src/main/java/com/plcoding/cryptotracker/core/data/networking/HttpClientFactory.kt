package com.plcoding.cryptotracker.core.data.networking

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {

    fun create(engine: HttpClientEngine): HttpClient {
        return HttpClient(engine) {
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.ANDROID
            }
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }
}

/**
 * 1. Objeto Singleton HttpClientFactory:
 *
 * object é uma declaração de objeto singleton no Kotlin.
 * Isso significa que apenas uma instância deste objeto será criada ao longo de todo o ciclo de vida da sua aplicação.
 * 2. Função create(engine: HttpClientEngine): HttpClient:
 *
 * Esta função pública estática (pode ser chamada sem instanciar o objeto) leva um parâmetro engine do tipo HttpClientEngine.
 * O engine define o motor HTTP subjacente que o HttpClient usará para fazer requisições (por exemplo, OkHttp, ktor, etc.).
 * 3. Construção do HttpClient:
 *
 * A função retorna um novo HttpClient construído com o motor HTTP fornecido (engine).
 * Dentro da construção do HttpClient, o código usa blocos install para configurar interceptadores (middlewares) para o cliente HTTP.
 * 4. Configuração do HttpClient:
 *
 * install(Logging) { ... }: Instala o interceptor de logging.
 * Define o nível de log como LogLevel.ALL para registrar todas as informações de log.
 * Define o logger como Logger.ANDROID para utilizar o log do Android.
 * install(ContentNegotiation) { ... }: Instala o interceptor de negociação de conteúdo.
 * Configura o formato JSON para o corpo da requisição e resposta.
 * Dentro do bloco json, define ignoreUnknownKeys = true para ignorar chaves desconhecidas no JSON de resposta.
 * defaultRequest { ... }: Configura o comportamento padrão para todas as requisições feitas com este cliente.
 * Define o tipo de conteúdo padrão como ContentType.Application.Json. Isso indica que o corpo da requisição estará no formato JSON por padrão.
 */