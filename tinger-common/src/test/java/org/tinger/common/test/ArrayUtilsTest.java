package org.tinger.common.test;

import org.tinger.common.utils.ArrayUtils;

/**
 * Created by tinger on 2022-10-19
 */
public class ArrayUtilsTest {
    public static void main(String[] args) {
        Integer[] arr1 = null;
        Integer[] arr2 = new Integer[]{4, 5, 6};
        Integer[] attach = ArrayUtils.attach(arr1, arr2);
        System.out.println(attach);
    }
}
