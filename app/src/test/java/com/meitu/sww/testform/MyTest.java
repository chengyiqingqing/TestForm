package com.meitu.sww.testform;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author ShaoWenWen
 * @date 2019/5/31
 */
public class MyTest {

    @Test
    public void testList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("哈哈0");
        arrayList.add("哈哈1");
        arrayList.add("哈哈2");
        arrayList.add("哈哈3");
        arrayList.add(1,"sww");
        for (int i = arrayList.size() - 1; i > 1; i--) {
            arrayList.remove(i);
        }
        for (int index = 0; index < arrayList.size(); index++) {
            System.out.println(" ---- " + (index) + " ---- " + arrayList.get(index));
        }
    }

}
