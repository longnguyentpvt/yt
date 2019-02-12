/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.utility;

import com.ccpp.PKCS7;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.util.encoders.Base64;

/**
 *
 * @author nickn
 */
public class DataEncodingUtility {

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    private static final String PAYMENT_2C2P_PRIVATE_KEY = "AAAAAgAAABTyVq4bqz60iamLBDbTC2qSN1fNRAAABK4EACdsZS1jYzRhMDgyMi1h\n"
            + "MThmLTQ0NDQtOTdjYy1lZDUzOTZhYjVhZGUAAAFb1loQMQAAAAIABVguNTA5AAAF\n"
            + "KzCCBScwggQPoAMCAQICCh6D5sEAAAAAAAwwDQYJKoZIhvcNAQEFBQAwFjEUMBIG\n"
            + "A1UEAxMLU2luYXB0SVEgQ0EwHhcNMTEwODE5MDMwNjAxWhcNMTYwODE5MDMxNjAx\n"
            + "WjB4MQswCQYDVQQGEwJTRzESMBAGA1UECBMJU2luZ2Fwb3JlMRIwEAYDVQQHEwlT\n"
            + "aW5nYXBvcmUxFjAUBgNVBAoTDTJDMlAgUHRlIEx0ZC4xEDAOBgNVBAsTBzJDMlAg\n"
            + "SVQxFzAVBgNVBAMTDmRlbW8yLjJjMnAuY29tMIIBIjANBgkqhkiG9w0BAQEFAAOC\n"
            + "AQ8AMIIBCgKCAQEAzwP7zkAA4oase4SiEDrgrf9StIp8+epvh5MEPQloTEKBdrWy\n"
            + "kIHL5eClJVkNleowRikTv6LnNdYM+jrwuTWHxN93qD8iKEHFkMpUJ7QgDQlbDrqR\n"
            + "f8KjQnAhRpiorwUphPr7r6ZvwSyOD480zUn3BPfKbNE0bEI+XstJ7/C2vYZI6r1L\n"
            + "dDHCAvl9if8ozLhdK6+4p6W2YkPHlz4GFi9r//l1BcY8wvsSVRQVwDiRsqZI7lln\n"
            + "v3Fh/mknV5meOL/YdjJKJjks39K/AhjuiqaAlF3LMZFxTNSqhWbJ+tdEkqRuWKze\n"
            + "Ta05nhODxe/G2VXwK8vbiB4fKTVLraTAy3OJ0QIDAQABo4ICEzCCAg8wDgYDVR0P\n"
            + "AQH/BAQDAgTwMBMGA1UdJQQMMAoGCCsGAQUFBwMBMHgGCSqGSIb3DQEJDwRrMGkw\n"
            + "DgYIKoZIhvcNAwICAgCAMA4GCCqGSIb3DQMEAgIAgDALBglghkgBZQMEASowCwYJ\n"
            + "YIZIAWUDBAEtMAsGCWCGSAFlAwQBAjALBglghkgBZQMEAQUwBwYFKw4DAgcwCgYI\n"
            + "KoZIhvcNAwcwHQYDVR0OBBYEFOIJWrYz60jctSMXrAWYvoiQknCRMB8GA1UdIwQY\n"
            + "MBaAFFfIWuRdAMxubq6JPS3EwKRNNg2QMHkGA1UdHwRyMHAwbqBsoGqGM2h0dHA6\n"
            + "Ly93Mmszc3R3MTAzM28xMWcvQ2VydEVucm9sbC9TaW5hcHRJUSUyMENBLmNybIYz\n"
            + "ZmlsZTovL1xcVzJLM1NUVzEwMzNPMTFHXENlcnRFbnJvbGxcU2luYXB0SVEgQ0Eu\n"
            + "Y3JsMIGyBggrBgEFBQcBAQSBpTCBojBPBggrBgEFBQcwAoZDaHR0cDovL3cyazNz\n"
            + "dHcxMDMzbzExZy9DZXJ0RW5yb2xsL1cySzNTVFcxMDMzTzExR19TaW5hcHRJUSUy\n"
            + "MENBLmNydDBPBggrBgEFBQcwAoZDZmlsZTovL1xcVzJLM1NUVzEwMzNPMTFHXENl\n"
            + "cnRFbnJvbGxcVzJLM1NUVzEwMzNPMTFHX1NpbmFwdElRIENBLmNydDANBgkqhkiG\n"
            + "9w0BAQUFAAOCAQEACqwUQnW/+nhq8OYivlSfmA00tV/uqd4qLNJUxztDpx6Ei2t6\n"
            + "upkkgnRjCEEIoswZBV1PjYKqRj7PBH9zlAN2ODXx/au3944gLf1LPigk5JlZ5xwg\n"
            + "O8/v03qN4q1xYGN9kYA3vAMGxDT2ZJEKRvfWLs57SEnF/Z0XiVOZOKBgcFfkSUXm\n"
            + "95ZNDQi0V/TSIOaA2K4a56KpzlYLhkbpVb/P5d83BNRvhR6wPbLosbwLPig4MkyG\n"
            + "kgMJBsQezkKJlmBP2EDG0EoM8KCfWsW3JSTi780qIoBHTMv5AKTBrtirj1WMXw/a\n"
            + "s2XMK2sZAYZVdVmBntJSu0MMAkXXUsi1UZsf6wAFWC41MDkAAAOIMIIDhDCCAmyg\n"
            + "AwIBAgIQMvENS6aTT6ZDNQx3X/VrOTANBgkqhkiG9w0BAQUFADAWMRQwEgYDVQQD\n"
            + "EwtTaW5hcHRJUSBDQTAeFw0xMDExMTUwMDQyNTRaFw0yNTExMTUwMDUyMjhaMBYx\n"
            + "FDASBgNVBAMTC1NpbmFwdElRIENBMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIB\n"
            + "CgKCAQEAodmv6o0DPOPdGEkV6MJoATrQ3CjUL/E3N0XzDJ1v/2G98+hmz1Oyz9Ag\n"
            + "rCTpbs4Xn7iGH2Ma8ZgoYS5yLxbebSjlDuXQkhiC2jzlqeMAwY4aRj3TEAzc3YJQ\n"
            + "z6jldMZ0tsqWjSUurBPmFeVqKjSmuQZuJT8Puj9MrLj7Snen21nk3ZkAHcWXCr20\n"
            + "iF0fTqXfkd1XKKGHG+q+QyEXaaBvKagsCOmfvu/rCzI0L9PaP3gpU5wjAahnaObB\n"
            + "LasbYcQYi52352xxr9jFN2UBwCN3YbTn7vJaIdn1mAl94MUCDTm0yHP94gZnkw54\n"
            + "3dROQdWvaaaoYNLl2PW8EqnXIuaX+QIDAQABo4HNMIHKMAsGA1UdDwQEAwIBhjAP\n"
            + "BgNVHRMBAf8EBTADAQH/MB0GA1UdDgQWBBRXyFrkXQDMbm6uiT0txMCkTTYNkDB5\n"
            + "BgNVHR8EcjBwMG6gbKBqhjNodHRwOi8vdzJrM3N0dzEwMzNvMTFnL0NlcnRFbnJv\n"
            + "bGwvU2luYXB0SVElMjBDQS5jcmyGM2ZpbGU6Ly9cXFcySzNTVFcxMDMzTzExR1xD\n"
            + "ZXJ0RW5yb2xsXFNpbmFwdElRIENBLmNybDAQBgkrBgEEAYI3FQEEAwIBADANBgkq\n"
            + "hkiG9w0BAQUFAAOCAQEAkuDltJ+V55ilV/h2agQRPRGhhyhf5qqounSziVUtcjHA\n"
            + "PrQ474+p089vtRiP8gWQoiVY3HvFaz1OvykxCwpknxbKv7aB6mzxhsbglq1ICGG6\n"
            + "9q6ZFgyWVgr8K7GKWra8zV6D1xxIjeaWtjpr0qOenXbL/SygCby3GNlyZeNDOvTl\n"
            + "O07wWYx2QS12vwUIae2JqkeGgXKzGT6BsIQxngsB6GRzZA3kg9rkDw2NI+LaE4IW\n"
            + "UnH+YjU6Gpjw6TKmm5a6XPTVaXqR0+m7aouR4lP8L0kL97KguE3hdP9HZB5B9f8C\n"
            + "5wLEQBMD6frEwgbbo9mtl4o0fylZeIb2TMJEngJdyAAABPQAAAAUP+v2zeRttp+k\n"
            + "71ESlV+Cr3vE+rIAAAVQMImsFKwRZhlnljKBKiHvVJvjg4EuR89RFwokjMG4ZXYe\n"
            + "CpXXCB48bInT7fNpUUk3RluGwmbu42PPsLFQkWsqkNqqJAqcPwtxEGZ8THR8zirQ\n"
            + "Fljjy1zRWHYe5aoeeCMLJ9TfG9kGQmaacuRpjSs2ONzb1259J2HV7es/lzWkldjF\n"
            + "tZfYsdipPKh8U5GE/LZTvhcfUeaRN+PBGsTzmfK9SUC7sQKmLYxuA8qaySwT5GDe\n"
            + "zvwmS7TM76LgExqUqJIysItG03Z4ge1zeB+xZP3TEnNih/ngexfaBrHO1VpB6C1i\n"
            + "8j69JrlZx4UY2QAeF79+Mip/agAlTXI/2AX/uacSe5+crCdwi7QHxjMQLfgGjfnf\n"
            + "OYZ2VmmgsQ7XwTJwHeOcusFigVhGPUuEOSsrmlCubpeJp1Jmwez0VPXDomGiRu7Z\n"
            + "0Y4Efg/okg9rdMNYXP2l0vy1wfuppkO5eiPtcYdfFVwgDrST35RGbIO0y9T/zW8F\n"
            + "uGu17FrGdLOQsuY5lb36WZHryDcze3V6Fi1oyM6ZadJXSGt6QsHIOli2jf/z8bBk\n"
            + "L5E9twJ2LyoeGMCxmnDB6adoaLtIeaJWLQeWzkteNrEXkV+fzXaF1qMmPnh8Igg/\n"
            + "oilDy/WuRW96fanHSQ84LxDpIqWxj26tvcLfimJaT/SGG0QJ7eKvD2oHKvy/BdJF\n"
            + "CbbDnpQ5a8kSi59jYnyt11TTAGrPmHGBQZsCb5mzoBWrJR/1LBrGPRecfrtNngWW\n"
            + "1XYLIFsjKSlgSukF+AncW9tx66TbuqrDfmyI7r+4gcfYm+YgMdNmZh17bKyiItKt\n"
            + "smBxa7RBBbQ8QADJ+dLqGwdNHmNOMKxmNURHb+eUL56thSYXNmPSVFgZEcogz848\n"
            + "0ygaGtVEY0rdF8LFYtLCKSu9PI1fIBdAA/wKWf/mjy/HOITkdtduhDyJJ3xzoN92\n"
            + "2GpqQ3JiBeGQGcmiecfy7m3mOJKbVs+ZAeCGfYckBmzSAuuMrFQaj9uBfJZbO02j\n"
            + "06hetGCkIENL5n+PeOgynX3ZvSy8JIZ5HNYTML2JhHiWZCjIKgUPml5PzaTEbn8u\n"
            + "gOF6UPCd62cJ8dtdPcVRA3/UWjvI2Vd0+7LoVfvCUZOP2MoJUCrwWNVdaDr/1MS7\n"
            + "wG4h6tX7LrtmjYX1ykSQYzi1TgKc35DkXt0IZk31kyTbCsKPJ/kxEqFfPFgNdtY0\n"
            + "4c4OZnPdgKlPefIlEWFnX5A9V2Yqm6mb9MYdhrZ8USlk9GAsAdeCKq0j0cGPTDJc\n"
            + "xxGLnbu9ZAcUqjCJgGOxGBxsG5xy+hnSEbgmHEmC1qL6p4tJMqt+jaPxuB4Duw+7\n"
            + "xX/3x91J+Tcbpw8GoTL6e/Q7Tb5rOltBZENBM84j/UfS/hknnWZLCzzHOcs88BMP\n"
            + "7WgXtk1e+kGjJAfRqeDym98y1woWLFM8CIBgkIwiHkfg4euO+5IMkasJkOXT4zld\n"
            + "PzJ5T7SR40V9Idv5itAsethZocm3hmOIvuCzZW8Yqca/HiXLjz4kTbtgoXX/RXY0\n"
            + "XegXH3aM8nrdwpbjbGUa2rRDH37FOn9UjJwk+K9FDWNB2ziNYRVWeTBQejsnOaWd\n"
            + "dFtAdmbYcF0Oql787ytWfwhBoDOtRbUVW+P2tA4Z3AMl3ZpjRul23KDqfEVYkz5W\n"
            + "LkNBWq1G3ADq4NEZlWGMo2KTsDkUopZU5VHO4Q==";

    public static String calculateRFC2104HMAC(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
        byte[] bytes = mac.doFinal(data.getBytes());

        Formatter formatter = new Formatter();

        for (byte b : bytes) {
            formatter.format("%02x", b);
        }

        return formatter.toString();
    }

    public static String decryptPaymentResponse(String message) throws Exception {
        String decrypted = PKCS7.decrypt(PAYMENT_2C2P_PRIVATE_KEY, "2c2p", Base64.decode(message), "pwdpwdpwd");
        return decrypted;
    }
}
