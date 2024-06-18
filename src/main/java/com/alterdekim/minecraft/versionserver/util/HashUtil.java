package com.alterdekim.minecraft.versionserver.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class HashUtil {
    public static String getSHA(File file) {
        return getDigest(file, "SHA", 40);
    }

    public static String getDigest(File file, String algorithm, int hashLength) {
        DigestInputStream stream = null;

        try {
            stream = new DigestInputStream(new FileInputStream(file), MessageDigest.getInstance(algorithm));
            byte[] ignored = new byte[65536];

            int read;
            do {
                read = stream.read(ignored);
            } while (read > 0);

            return String.format(java.util.Locale.ROOT, "%1$0" + hashLength + "x", new BigInteger(1, stream.getMessageDigest().digest()));
        } catch (Exception ignored) {
        } finally {
            close(stream);
        }

        return null;
    }

    private static void close(Closeable a) {
        try {
            a.close();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}
