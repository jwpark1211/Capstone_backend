package capstone.bookitty.domain.controller;

import capstone.bookitty.domain.service.OpenApiBookService;
import capstone.bookitty.global.api.dto.AladinBookListResponseDTO;
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
    public Mono<AladinBookListResponseDTO> getBook(
            @PathVariable("isbn") String isbn
    ) {
        return openApiBookService.searchByBookISBN(isbn);
    }

}
