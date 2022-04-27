package hyk.springframework.springtravellogrestapi.controller.api.v1;

import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

public class PasswordEncodingTest {

    static final String PASSWORD = "yanant";

    @Test
    void testMD5Hash() {
        // MD5 -> one-way cryptographic function
        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes()));
        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes()));

        // Change salt value every time and so, hashing algorithm will produce unique hash value
        String salt = PASSWORD + "saltvalue";
        // Without salt value, hash vale will not be changed for the same password.
        System.out.println(DigestUtils.md5DigestAsHex(salt.getBytes()));
    }
}
