package capstone.bookitty.domain.service;

import capstone.bookitty.domain.entity.BookState;
import capstone.bookitty.domain.entity.Member;
import capstone.bookitty.domain.entity.State;
import capstone.bookitty.domain.repository.BookStateRepository;
import capstone.bookitty.domain.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

import static capstone.bookitty.domain.dto.BookStateDTO.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookStateService {

    private final BookStateRepository stateRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public IdResponse saveState(SaveRequest request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(()->new EntityNotFoundException(
                        "Member not found for ID: "+request.getMemberId()));

        if(stateRepository.existsByMemberIdAndIsbn(request.getMemberId(), request.getIsbn()))
            throw new IllegalArgumentException("bookState already exists.");

        BookState bookState = BookState.builder()
                .member(member)
                .state(request.getState())
                .isbn(request.getIsbn())
                .bookTitle(request.getBookTitle())
                .bookAuthor(request.getBookAuthor())
                .bookImgUrl(request.getBookImgUrl())
                .categoryName(request.getCategoryName())
                .build();

        if(request.getState() == State.READ_ALREADY) bookState.readAtNow();

        stateRepository.save(bookState);
        return new IdResponse(bookState.getId());
    }

    public Page<InfoResponse> findStateByISBN(String isbn, Pageable pageable) {
        return stateRepository.findByIsbn(isbn, pageable)
                .map(InfoResponse::of);
    }

    public InfoResponse findStateByStateId(Long stateId) {
        return stateRepository.findById(stateId)
                .map(InfoResponse::of)
                .orElseThrow(() -> new EntityNotFoundException("BookState with ID " + stateId + " not found."));
    }

    public Page<InfoResponse> findStateByMemberId(Long memberId, Pageable pageable) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new EntityNotFoundException(
                        "Member with ID: "+memberId+" not found."));

        return stateRepository.findByMemberId(memberId,pageable)
                .map(InfoResponse::of);
    }

    @Transactional
    public void updateState(Long stateId, UpdateRequest request) {
        BookState bookState = stateRepository.findById(stateId)
                .orElseThrow(() -> new EntityNotFoundException("BookState not found for ID: " + stateId));

        bookState.updateState(request.getState());
    }

    @Transactional
    public void deleteState(Long stateId) {
        BookState bookState = stateRepository.findById(stateId)
                .orElseThrow(() -> new EntityNotFoundException("BookState not found for ID: " + stateId));
        stateRepository.delete(bookState);
    }
}
