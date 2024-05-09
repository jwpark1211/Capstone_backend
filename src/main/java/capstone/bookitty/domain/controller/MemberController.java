package capstone.bookitty.domain.controller;

import capstone.bookitty.domain.dto.ResponseType.BasicResponse;
import capstone.bookitty.domain.dto.ResponseType.ResponseCounter;
import capstone.bookitty.domain.dto.ResponseType.ResponseString;
import capstone.bookitty.domain.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static capstone.bookitty.domain.dto.MemberDTO.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(path = "/new")
    public ResponseEntity<? extends BasicResponse> save(
            @RequestBody @Valid SaveRequest request){
        return ResponseEntity.ok()
                .body(new ResponseCounter<IdResponse>(
                       memberService.saveMember(request)));
    }

    @PostMapping(path = "/email/{email}/unique")
    public ResponseEntity<? extends BasicResponse> isEmailUnique(
            @PathVariable("email") String email
    ){
        return ResponseEntity.ok()
                .body(new ResponseCounter<BoolResponse>(
                        memberService.isEmailUnique(email)));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<? extends BasicResponse> login(
            @RequestBody @Valid LoginRequest request
    ){
        memberService.login(request);
        return ResponseEntity.ok()
                .body(new ResponseString("login success!"));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<? extends BasicResponse> findOneMember(
            @PathVariable("id") Long memberId
    ){
        return ResponseEntity.ok(
                new ResponseCounter<InfoResponse>(
                        memberService.getMemberInfoWithId(memberId)));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<? extends BasicResponse> findAllMembers(){
        return ResponseEntity.ok(
                new ResponseCounter<List<InfoResponse>>(
                        memberService.getAllMemberInfo()));
    }

    /* TODO : S3
    @PostMapping(path = "/{id}/profile")
    public ResponseEntity<? extends BasicResponse> updateMemberProfile(
            @PathVariable("id") Long memberId,
            @RequestPart(value = "profile") MultipartFile profileImg
            ) throws IOException {
        memberService.updateProfile(memberId, profileImg);
        return ResponseEntity.ok().build();
    }*/

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<? extends BasicResponse> deleteMember(
            @PathVariable("id") Long memberId
    ){
        memberService.deleteMember(memberId);
        return ResponseEntity.ok()
                .body(new ResponseString("delete member!"));
    }

}
