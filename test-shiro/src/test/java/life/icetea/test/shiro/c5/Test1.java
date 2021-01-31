package life.icetea.test.shiro.c5;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;
import org.junit.Test;

import java.security.Key;

public class Test1 {

    @Test
    public void base64Test() {
        String str = "hello";
        String encodeStr = Base64.encodeToString(str.getBytes());
        System.out.println(encodeStr);
        String decodeStr = Base64.decodeToString(encodeStr.getBytes());
        System.out.println(decodeStr);
        Assert.assertEquals(str, decodeStr);
    }

    @Test
    public void hexTest() {
        String str = "hello";
        String encodeStr = Hex.encodeToString(str.getBytes());
        System.out.println(encodeStr);
        String decodeStr = new String(Hex.decode(encodeStr.getBytes()));
        System.out.println(decodeStr);
        Assert.assertEquals(str, decodeStr);
    }

    @Test
    public void saltMd5Test() {
        String str = "hello";
        String salt = "123";
        String s = new Md5Hash(str, salt).toString();
        System.out.println(s);


        String s2 = new Md5Hash(str, salt, 5).toString();
        System.out.println(s2);
    }


    @Test
    public void saltSHATest() {
        String str = "hello";
        String salt = "123";
        String s = new Sha256Hash(str, salt).toString();
        System.out.println(s);


        String s2 = new Sha256Hash(str, salt, 5).toString();
        System.out.println(s2);
    }

    @Test
    public void simpleHashTest() {
        String str = "hello";
        String salt = "123";
        String s = new SimpleHash("SHA-1", str, salt).toString();
        System.out.println(s);
    }

    @Test
    public void hashServiceTest() {
        DefaultHashService hashService = new DefaultHashService(); //默认算法SHA-512
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource("123")); //私盐，默认无
        hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个
        hashService.setHashIterations(1); //生成Hash值的迭代次数

        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();
    }

    @Test
    public void aesTest() {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        // 生成key
        Key key = aesCipherService.generateNewKey();
        System.out.println(Hex.encodeToString(key.getEncoded()));

        // 加密
        String str = "hello";
        String encrypt = aesCipherService.encrypt(str.getBytes(), key.getEncoded()).toHex();
        System.out.println(encrypt);
        // 解密
        String decrypt = new String(aesCipherService.decrypt(Hex.decode(encrypt), key.getEncoded()).getBytes());
        System.out.println(decrypt);
    }


}
