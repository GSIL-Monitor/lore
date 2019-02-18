package mng.web.demo;

import mng.util.Util;

import java.io.IOException;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
public class Test {
    public static void main(String[] args) throws IOException {
        Util u = new Util();
        String a = Util.getProperties("to");
        System.out.println(a);
    }
}
