package ui.juvenilecase;
/**
 * This security token is a helper class which is used for PACT token authentication.
 * When this file is updated or modified, same needs to be done on the JUV PACT WEB SERVICES Code base.
 */
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import javax.naming.AuthenticationException;

import org.apache.log4j.Logger;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;
import com.thoughtworks.xstream.core.util.Base64Encoder;

public class SecurityToken {
	private static final String ISSUER = "JIMS 2";
	private static final String SUBJECT = "JUV PACT Integration";
	private static final String USER_ID_CLAIM = "UserId";
	private static final String SUBJECT_ID_CLAIM = "SubjectId";
	
	private String subjectId = "";
	private String userId = "";
	private long expirationSeconds = 10 * 60;

	private static String base64KeyBytes = "XY4e6DZxq0HjW0aCWlL3d6FgHzl1yPWPQMX4NsrGsJc=";
	
	private JWTClaimsSet claimsSet;

	static Logger log = Logger.getLogger(SecurityToken.class.getName());
	
	private SecurityToken() {
	}
	
	public static class Builder {
		private SecurityToken tokenToBuild;
		
		public Builder(String userId) {
			tokenToBuild = new SecurityToken();
			this.tokenToBuild.userId = userId;
		}
		
		public SecurityToken build() {
			tokenToBuild.buildClaimsSet();
			return tokenToBuild;
		}

		public Builder SubjectId(String subjectId) {
			tokenToBuild.subjectId = subjectId;
			return this;
		}

		public Builder ExpirationSeconds(long expirationSeconds) {
			tokenToBuild.expirationSeconds = expirationSeconds;
			return this;
		}
	}

	/**
	 * @return the subjectId
	 */
	public String getSubjectId() {
		return subjectId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @return the expirationSeconds
	 */
	public long getExpirationSeconds() {
		return expirationSeconds;
	}
	
	/**
	 * generateKey
	 * @return String
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateKey() throws NoSuchAlgorithmException {
		Base64Encoder base64Encoder = new Base64Encoder();
		KeyGenerator keyGen;
		try {
			keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(256);
			SecretKey secretKey = keyGen.generateKey();
			byte[] encodedSecretKey =  secretKey.getEncoded();
			String base64SecretKey = base64Encoder.encode(encodedSecretKey);
			log.debug("base 64 encoded key: [" + base64SecretKey + "]");
			return base64SecretKey;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	/**
	 * serialize
	 * @return payload
	 * @throws KeyLengthException
	 * @throws JOSEException
	 */
	public String serialize() throws KeyLengthException, JOSEException {
		log.debug("*************INSIDE SecurityToken.serialize. Creating JWEObject**************");
		
		SecretKey secretKey = SecurityToken.getSecretKey();

		// Create HMAC signer
		JWSSigner signer = new MACSigner(secretKey.getEncoded());

		// Apply the HMAC
		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
		signedJWT.sign(signer);

		// Create JWE object with signed JWT as payload
		JWEObject jweObject = new JWEObject(
		    new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256)
		        .contentType("JWT") // required to signal nested JWT
		        .build(),
		    new Payload(signedJWT));

		// Perform encryption
		jweObject.encrypt(new DirectEncrypter(secretKey.getEncoded()));

		// Output to URL-safe format
		String payload = jweObject.serialize();
		return payload;
	}
	
	/**
	 * parse
	 * @param authToken
	 * @return SecurityToken
	 * @throws ParseException
	 * @throws KeyLengthException
	 * @throws JOSEException
	 * @throws AuthenticationException
	 */
	public static SecurityToken parse(String authToken) throws ParseException, KeyLengthException, JOSEException, AuthenticationException {
		Logger log = Logger.getLogger(SecurityToken.class.getName());
		log.debug("*************INSIDE SecurityToken.parse. Creating SecurityToken**************");
		
		SecretKey secretKey = SecurityToken.getSecretKey(); 
		
		// Parse the JWE string
		JWEObject jweObject = JWEObject.parse(authToken);

		// Decrypt with key
		jweObject.decrypt(new DirectDecrypter(secretKey.getEncoded()));

		// Extract payload
		SignedJWT signedJWT = jweObject.getPayload().toSignedJWT();

		// Check the HMAC
		if (!signedJWT.verify(new MACVerifier(secretKey.getEncoded())))
		{
			throw new AuthenticationException("Authentication token cannot be verified");
		}
		
		// Retrieve the JWT claims...
		JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
		
		// Check the token expiration
		if (claimsSet.getExpirationTime().before(new Date()))
		{
			throw new AuthenticationException("Authenthication token has expired");
		}
		
		// Check the issuer
		if (!claimsSet.getIssuer().equals(SecurityToken.ISSUER))
		{
			throw new AuthenticationException("Authenthication token has the wrong issuer");
		}
		
		// Check the subject
		if (!claimsSet.getSubject().equals(SecurityToken.SUBJECT))
		{
			throw new AuthenticationException("Authenthication token has the wrong subject");
		}

		// Create and return security token 
		SecurityToken token = new SecurityToken();
		token.userId = (String) claimsSet.getClaim(SecurityToken.USER_ID_CLAIM);
		token.subjectId = (String) claimsSet.getClaim(SecurityToken.SUBJECT_ID_CLAIM);
		Date issueTime = claimsSet.getIssueTime();
		Date expirationTime = claimsSet.getExpirationTime();
		token.expirationSeconds = (expirationTime.getTime() - issueTime.getTime()) / 1000;
		token.claimsSet = claimsSet;
		
		return token;
	}
	
	/**
	 * buildClaimsSet
	 */
	private void buildClaimsSet() {
		log.debug("*************INSIDE SecurityToken.buildClaimsSet. Creating JWTClaimsSet**************");
		
		// Prepare JWT with claims set
		Date issueTime = new Date();
		Date expirationTime = new Date(issueTime.getTime() + expirationSeconds * 1000);
		
		this.claimsSet = new JWTClaimsSet.Builder()
			.issuer(SecurityToken.ISSUER)
			.issueTime(issueTime)
			.expirationTime(expirationTime)
			.subject(SecurityToken.SUBJECT)
			.claim(SecurityToken.USER_ID_CLAIM, this.getUserId())
			.claim(SecurityToken.SUBJECT_ID_CLAIM, this.getSubjectId())
			.build();
	}
	
	/**
	 * getSecretKey
	 * @return SecretKey
	 */
	private static SecretKey getSecretKey() {
		Base64Encoder base64Encoder = new Base64Encoder();
		byte[] secretKeyBytes = base64Encoder.decode(base64KeyBytes);
		SecretKey secretKey = new SecretKeySpec(secretKeyBytes, 0, secretKeyBytes.length, "AES");
		return secretKey;
	}
}
