package capstone.bookitty.domain.service;

import capstone.bookitty.domain.entity.Member;
import capstone.bookitty.domain.entity.Star;
import capstone.bookitty.domain.repository.MemberRepository;
import capstone.bookitty.domain.repository.StarRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static capstone.bookitty.domain.dto.StarDTO.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StarService {

    private final StarRepository starRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public IdResponse saveStar(SaveRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(()->new EntityNotFoundException(
                        "Member with ID "+request.getMemberId()+" not found."));

        if(starRepository.existsByMemberIdAndIsbn(request.getMemberId(),request.getIsbn()))
            throw new IllegalArgumentException("star already exists.");

        Star star = Star.builder()
                .score(request.getScore())
                .isbn(request.getIsbn())
                .member(member)
                .createdAt(LocalDateTime.now())
                .build();

        starRepository.save(star);
        return new IdResponse(star.getId());
    }

    public InfoResponse findStarByStarId(Long starId) {
        return starRepository.findById(starId)
                .map(InfoResponse::of)
                .orElseThrow(()->new EntityNotFoundException("Star with ID "+starId+" not found."));
    }

    public List<InfoResponse> findStarByISBN(String isbn) {
        return starRepository.findByIsbn(isbn).stream()
                .map(InfoResponse::of)
                .collect(Collectors.toList());
    }

    public List<InfoResponse> findStarByMemberId(Long memberId) {
        return starRepository.findByMemberId(memberId).stream()
                .map(InfoResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateStar(Long starId, UpdateRequest request) {
        Star star = starRepository.findById(starId)
                .orElseThrow(()-> new EntityNotFoundException("Star with ID "+starId+" not found."));
        star.updateStar(request.getScore());
    }

    @Transactional
    public void deleteStar(Long starId) {
        Star star = starRepository.findById(starId)
                .orElseThrow(()-> new EntityNotFoundException("Star with ID "+starId+" not found."));
        starRepository.delete(star);
    }
}
