package capstone.bookitty.global.api.openApi;

import capstone.bookitty.global.api.dto.AladinBookListResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class AladinOpenApi {

    @Value("${api.aladin.ttb-key}")
    private String ttb;

    private final WebClient aladinWebClientApi;

    public static final String BOOK_ISBN_URI = "/ItemLookUp.aspx";

    public Mono<AladinBookListResponseDTO> searchByBookISBN(String isbn) {
        return aladinWebClientApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(BOOK_ISBN_URI)
                        .queryParam("ttbkey", ttb)
                        .queryParam("ItemId", isbn)
                        .queryParam("ItemIdType","ISBN13")
                        .queryParam("Version",20131101)
                        .queryParam("Cover","Big")
                        .queryParam("OptResult","ratingInfo,cardReviewImgList")
                        .queryParam("output","js")
                        .build())
                .retrieve()
                .bodyToMono(AladinBookListResponseDTO.class);
    }

}
