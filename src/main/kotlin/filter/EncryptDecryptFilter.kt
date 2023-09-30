package filter

import com.wanchalerm.tua.gateway.service.AESService
import org.bouncycastle.util.Strings
import org.reactivestreams.Publisher
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.cloud.gateway.filter.factory.rewrite.MessageBodyDecoder
import org.springframework.cloud.gateway.filter.factory.rewrite.MessageBodyEncoder
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DataBufferUtils
import org.springframework.core.io.buffer.DefaultDataBufferFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpRequestDecorator
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.http.server.reactive.ServerHttpResponseDecorator
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors

@Component
class EncryptDecryptFilter( messageBodyDecoders: Set<MessageBodyDecoder>,
     messageBodyEncoders: Set<MessageBodyEncoder>,
     private val aesService: AESService)  : AbstractGatewayFilterFactory<>(Config::class.java) {


    private val messageBodyDecoders: Map<String, MessageBodyDecoder>
    private val messageBodyEncoders: Map<String, MessageBodyEncoder>

    init {
        this.messageBodyDecoders = messageBodyDecoders.map { it.encodingType() to it }.toMap()
        this.messageBodyEncoders = messageBodyEncoders.map { it.encodingType() to it }.toMap()
    }

    override fun apply(config: Config): GatewayFilter? {
      /*  return OrderedGatewayFilter(
            { exchange, chain ->
                val mutatedHttpResponse = getser
            }, -2) */
            return null
     }

     private fun getServerHttpResponse(exchange: ServerWebExchange) : ServerHttpResponse {
        val key = exchange.request.headers.getFirst("x-message")
        val originalResponse = exchange.response
        return ServerHttpResponseDecorator(originalResponse) {

        }
     }

    class Config
}
