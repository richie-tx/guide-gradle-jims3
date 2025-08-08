package mojo.km.security;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;
/**
 * U.S 79250
 * @author sthyagarajan
 *
 */
public class ServerTrustManger implements X509TrustManager{

	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
	}

	@Override
	public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
		arg0[0].checkValidity();
		arg0[0].getIssuerUniqueID();
		arg0[0].getSubjectDN();
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}
