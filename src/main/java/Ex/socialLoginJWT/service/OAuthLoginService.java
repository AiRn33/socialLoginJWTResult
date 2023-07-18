package Ex.socialLoginJWT.service;

import Ex.socialLoginJWT.domain.Member;
import Ex.socialLoginJWT.jwt.AuthTokensGenerator;
import Ex.socialLoginJWT.jwt.AuthTokens;
import Ex.socialLoginJWT.oauth.OAuthInfoResponse;
import Ex.socialLoginJWT.oauth.OAuthLoginParams;
import Ex.socialLoginJWT.oauth.RequestOAuthInfoService;
import Ex.socialLoginJWT.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        System.out.println("바보 : " + oAuthInfoResponse.getEmail());
        System.out.println("바보 : " + oAuthInfoResponse.getNickname());
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        System.out.println("바보 : " + memberId);
        return authTokensGenerator.generate(memberId);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return memberRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(Member::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
        Member member = Member.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return memberRepository.save(member).getId();
    }
}