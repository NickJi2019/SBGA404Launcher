package com.woznes.sbga404launcher;

import java.io.*;
import com.woznes.sbga404launcher.*;

public class Test {
    static String con(File f) throws Exception {
        byte[] data = new FileInputStream(f).readAllBytes();

        var bais=new ByteArrayInputStream(data);
        var baos = new ByteArrayOutputStream();
        var isr = new InputStreamReader(bais,"GBK");
        var osw = new OutputStreamWriter(baos, "UTF-8");
        int len = 0;
        while ((len=isr.read())!=-1){
            osw.write(len);
        }
        osw.close();
        isr.close();
        return new String(baos.toByteArray());
    }
    public static void main(String[] args) throws Exception {
        var f=new File("/Users/nickji/Documents/Program/PROJECT/SBGA404Launcher/src/main/resources/log.txt");
        System.out.println(con(f));
    }
}
