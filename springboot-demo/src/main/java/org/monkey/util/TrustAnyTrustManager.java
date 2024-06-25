package org.monkey.util;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * TrustAnyTrustManager
 *
 * @author cc
 * @since 2024/6/17 18:59
 */
public class TrustAnyTrustManager implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] var1, String var2) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] var1, String var2) throws CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
