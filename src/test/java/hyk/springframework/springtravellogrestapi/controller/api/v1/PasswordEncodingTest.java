package hyk.springframework.springtravellogrestapi.controller.api.v1;

//import org.junit.jupiter.api.Test;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.StandardPasswordEncoder;
//import org.springframework.util.DigestUtils;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordEncodingTest {

//    static final String PASSWORD = "yanant";
//
//    @Test
//    void testMD5Hash() {
//        // MD5 -> one-way cryptographic function
//        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes()));
//        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes()));
//
//        // Change salt value every time and so, hashing algorithm will produce unique hash value
//        String salt = PASSWORD + "saltvalue";
//        // Without salt value, hash vale will not be changed for the same password.
//        System.out.println(DigestUtils.md5DigestAsHex(salt.getBytes()));
//    }
//
//    @Test
//    void testNoOp() {
//        PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();
//        // Not recommend to use in real-world app
//        System.out.println(encoder.encode(PASSWORD));
//    }
//
//    @Test
//    void testLdap() {
//        PasswordEncoder ldap = new LdapShaPasswordEncoder();
//        // LDAP algorithm use random salt value and produce different hash value every time
//        System.out.println(ldap.encode(PASSWORD));
//        System.out.println(ldap.encode(PASSWORD));
//        System.out.println(ldap.encode("guest"));
//
//        String encodedPwd = ldap.encode(PASSWORD);
//        assertTrue(ldap.matches(PASSWORD, encodedPwd));
//    }
//
//    @Test
//    void testSha256() {
//        PasswordEncoder sha256 = new StandardPasswordEncoder();
//        System.out.println(sha256.encode(PASSWORD));
//        System.out.println(sha256.encode(PASSWORD));
//        System.out.println(sha256.encode("guest"));
//    }
//
//    @Test
//    void testBCrypt() {
//        // strength can be passed as argument parameter, default is 10.
////        PasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
//        PasswordEncoder bcrypt = new BCryptPasswordEncoder();
//        System.out.println(bcrypt.encode(PASSWORD));
//        System.out.println(bcrypt.encode(PASSWORD));
//        System.out.println(bcrypt.encode("editor"));
//    }
}
