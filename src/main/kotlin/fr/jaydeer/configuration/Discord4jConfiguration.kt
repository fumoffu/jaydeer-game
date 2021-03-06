package fr.jaydeer.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Mono
import sx.blah.discord.api.ClientBuilder
import sx.blah.discord.api.IDiscordClient
import sx.blah.discord.handle.impl.events.ReadyEvent


@Configuration
class Discord4jConfiguration {

    @Bean
    fun discordClient(): Mono<IDiscordClient> {
        val clientBuilder = ClientBuilder()
        clientBuilder.withToken("MzE1NzgyOTk4MTEwODk2MTM4.DAL32g.NvQR5Zb4WvWRenl8QcSE6kzL8ig")
        val client = clientBuilder.login()
        val mono = Mono.create<IDiscordClient> { sink ->
            client.dispatcher.registerListener {
                if(it is ReadyEvent) {
                    println("THIS IS A SUCCESS")
                    sink.success(client)
                }
            }
        }
        mono.subscribe {
            println("SENDING HELLO WORLD...")
            it.channels.forEach {
                it.sendMessage("Hello World !")
            }
        }
        return mono
    }
}