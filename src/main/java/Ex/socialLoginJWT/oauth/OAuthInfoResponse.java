package Ex.socialLoginJWT.oauth;

import Ex.socialLoginJWT.domain.OAuthProvider;

public interface OAuthInfoResponse {
    String getEmail();
    String getNickname();
    OAuthProvider getOAuthProvider();
}
