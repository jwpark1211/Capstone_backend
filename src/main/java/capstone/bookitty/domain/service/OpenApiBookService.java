package capstone.bookitty.domain.service;

import capstone.bookitty.global.api.dto.AladinBookListResponseDTO;
import capstone.bookitty.global.api.openApi.AladinOpenApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OpenApiBookService {

    private final AladinOpenApi aladinOpenApi;

    public Mono<AladinBookListResponseDTO> searchByBookISBN(String isbn) {
        return aladinOpenApi.searchByBookISBN(isbn);
    }

}
