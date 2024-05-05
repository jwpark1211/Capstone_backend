package capstone.bookitty.domain.service;

import capstone.bookitty.domain.entity.Member;
import capstone.bookitty.domain.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static capstone.bookitty.domain.dto.MemberDTO.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public IdResponse saveMember(SaveRequest request) {
        if(memberRepository.existsByEmail(request.getEmail()))
            throw new IllegalArgumentException("Email already in use.");

        Member member = Member.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(request.getPassword())
                .birthDate(request.getBirthdate())
                .gender(request.getGender())
                .build();

        memberRepository.save(member);
        return new IdResponse(member.getId());
    }

    public BoolResponse isEmailUnique(String email) {
        return new BoolResponse(!memberRepository.existsByEmail(email));
    }

    @Transactional
    public void login(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new EntityNotFoundException("Member not found."));
        if(!member.getPassword().equals(request.getPassword()))
            throw new IllegalArgumentException("Invalid login credentials.");
    }

    public InfoResponse getMemberInfoWithId(Long memberId) {
        return memberRepository.findById(memberId)
                .map(InfoResponse::of)
                .orElseThrow(()-> new EntityNotFoundException("Member not found."));
    }

    public List<InfoResponse> getAllMemberInfo() {
        return memberRepository.findAll().stream()
                .map(InfoResponse::of)
                .collect(Collectors.toList());
    }

    /*TODO: S3 관련 처리
    @Transactional
    public void updateProfile(Long memberId, MultipartFile profileImg)
    throws MultipartException {
        if(profileImg.isEmpty())
            throw new MultipartException("The file is not valid.");
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new EntityNotFoundException("member not found."));
        member.updateImg(url);
    }*/

    //TODO : SPRING SECURITY AUTHORITY SETTING
    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new EntityNotFoundException("member not found."));
        memberRepository.delete(member);
    }
}
