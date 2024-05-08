package capstone.bookitty.domain.controller;

import capstone.bookitty.domain.service.OpenApiBookService;
import capstone.bookitty.global.api.dto.AladinBestSellerResponseDTO;
import capstone.bookitty.global.api.dto.AladinBookListResponseDTO;
import capstone.bookitty.global.api.dto.AladinBookSearchResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open")
public class OpenApiBookController {

    private final OpenApiBookService openApiBookService;

    @GetMapping(path = "/search/book/{isbn}")
    public Mono<AladinBookListResponseDTO> getBookbyISBN(
            @PathVariable("isbn") String isbn
    ) {
        return openApiBookService.searchByBookISBN(isbn);
    }

    @GetMapping(path = "/search/keyword/{keyword}")
    public Mono<AladinBookSearchResponseDTO> getBooksByKeyword(
            @PathVariable("keyword") String keyword
    ){
        return openApiBookService.searchByKeyword(keyword);
    }

    @GetMapping(path = "/bestseller")
    public Mono<AladinBestSellerResponseDTO> getBestSeller(){
        return openApiBookService.getBestSeller();
    }

    /* [ 170 : 국내 경제경영 ] [ 987 : 과학 ] [ 2551 : 만화 ][ 798 : 사회 ]
    [ 1 : 소설/시/희곡 ] [ 656 : 인문 ] [ 336 : 자기계발 ] [ 351 : 컴퓨터/모바일 ] */
    @GetMapping(path = "/bestseller/category/{category-id}")
    public Mono<AladinBestSellerResponseDTO> getBestSellerByGenre(
            @PathVariable("category-id") int cid
    ){
        return openApiBookService.getBestSellerByGenre(cid);
    }

    @GetMapping(path = "/bestseller/newBook")
    public Mono<AladinBestSellerResponseDTO> getBestSellerNewBook(){
        return openApiBookService.getBestSellerNewBook();
    }

    @GetMapping(path = "/bestseller/blogChoice")
    public Mono<AladinBestSellerResponseDTO> getBestSellerBlogChoice(){
        return openApiBookService.getBlogChoice();
    }
}
