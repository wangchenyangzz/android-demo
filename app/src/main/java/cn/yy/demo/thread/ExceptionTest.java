package cn.yy.demo.thread;

import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * author : cy.wang
 * date   : 2020/11/4
 * desc   :
 */
class ExceptionTest {
    public static void writeIO() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(Environment.getExternalStorageDirectory() + "/test.txt");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        bufferedOutputStream.write("hello".getBytes());
    }

    public static String readIO() throws IOException {
        FileInputStream inputStream = new FileInputStream(Environment.getExternalStorageDirectory() + "/test.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] str = new byte[100];
        bufferedInputStream.read(str);
        return Arrays.toString(str);
    }
}
