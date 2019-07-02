package bytearray;

import com.sun.xml.internal.messaging.saaj.util.Base64;

import java.nio.charset.StandardCharsets;

public class ByteBase64Example {
    public static void main(String[] args) {
//        byte[] ba = {0,1,8,9, 125, -128, 127};
//        for (int i = 0; i < ba.length; i++) {
//            System.out.println(ba[i]);
//        }

        String messagePlain = "Hello world";
        byte[] messagePlainBytes = messagePlain.getBytes(StandardCharsets.UTF_8);

        byte[] messageB64 = Base64.encode(messagePlainBytes);
        System.out.println(messagePlain);
        System.out.println(new String(messageB64, StandardCharsets.UTF_8));
        for (int i = 0; i < messagePlainBytes.length; i++) {
            System.out.print(messagePlainBytes[i]+",");
        }
    }


}
