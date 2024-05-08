package capstone.bookitty.domain.service;

import capstone.bookitty.global.api.dto.AladinBestSellerResponseDTO;
import capstone.bookitty.global.api.dto.AladinBookListResponseDTO;
import capstone.bookitty.global.api.dto.AladinBookSearchResponseDTO;
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

    public Mono<AladinBookSearchResponseDTO> searchByKeyword(String keyword){
        return aladinOpenApi.searchByKeyword(keyword);
    }

    public Mono<AladinBestSellerResponseDTO> getBestSeller(){
        return aladinOpenApi.getAllBestSeller();
    }

    public Mono<AladinBestSellerResponseDTO> getBestSellerByGenre(int cid){
        return aladinOpenApi.getBestSellerByGenre(cid);
    }

    public Mono<AladinBestSellerResponseDTO> getBestSellerNewBook(){
        return aladinOpenApi.getNewBook();
    }

    public Mono<AladinBestSellerResponseDTO> getBlogChoice(){
        return aladinOpenApi.getBlogChoice();
    }

}
