package bytearray;

import com.sun.xml.internal.messaging.saaj.util.Base64;

import java.nio.charset.StandardCharsets;

public class ByteBase64Example {

    public static void main(String[] args) {

        String messagePlain = "Hello world";
        byte[] messagePlainBytes = messagePlain.getBytes(StandardCharsets.UTF_8);

        byte[] messageB64 = Base64.encode(messagePlainBytes);
        System.out.println("Plain string: " + messagePlain);
        System.out.println("Base 64 form: " + new String(messageB64, StandardCharsets.UTF_8));
        System.out.print("Byte array: ");
        for (byte messagePlainByte : messagePlainBytes) {
            System.out.print(messagePlainByte + ",");
        }
    }


}
