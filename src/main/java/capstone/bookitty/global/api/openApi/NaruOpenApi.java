package capstone.bookitty.global.api.openApi;

import capstone.bookitty.global.api.dto.NaruPopularBookListDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class NaruOpenApi {

    //@Value("${api.naru.auth}")
    private String auth;

    private final WebClient naruWebClientApi;

    public NaruOpenApi(@Qualifier("NaruWebClient") WebClient naruWebClientApi){
        this.naruWebClientApi = naruWebClientApi;
    }

    private static final String POPULAR_BOOK_URI = "/loanItemSrch";
    public NaruPopularBookListDto getPopularBook(int gender, int age) {

        Mono<NaruPopularBookListDto> NaruPopularBookListDtoMono = naruWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(POPULAR_BOOK_URI)
                        .queryParam("authKey",auth)
                        .queryParam("gender",gender)
                        .queryParam("age",20)
                        .queryParam("pageSize",10)
                        .queryParam("format","json")
                        .build())
                .retrieve()
                .bodyToMono(NaruPopularBookListDto.class);
        return NaruPopularBookListDtoMono.block();
    }
}
