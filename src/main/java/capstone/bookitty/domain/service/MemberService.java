package capstone.bookitty.domain.service;

import capstone.bookitty.domain.entity.Member;
import capstone.bookitty.domain.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static capstone.bookitty.domain.dto.MemberDTO.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public IdResponse saveMember(MemberSaveRequest request) {
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
    public void login(MemberLoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new EntityNotFoundException("Member not found."));
        if(!member.getPassword().equals(request.getPassword()))
            throw new IllegalArgumentException("Invalid login credentials.");
    }

    public MemberInfoResponse getMemberInfoWithId(Long memberId) {
        return memberRepository.findById(memberId)
                .map(MemberInfoResponse::of)
                .orElseThrow(()-> new EntityNotFoundException("Member not found."));
    }

    public Page<MemberInfoResponse> getAllMemberInfo(Pageable pageable) {
        return memberRepository.findAll(pageable)
                .map(MemberInfoResponse::of);
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
