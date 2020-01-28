package com.parts.perf.jit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * JIT natively compiles the interpreted code for performance.
 * When we compare performance, it may not be accurate as initial runs might do JIT native compilation.
 * C1 and C2 compiler : C1 compiler can do 3 level of compilation. When method is very frequently used it
 * will be put in cache(%)  XX:PrintCompilation - this shows time, method name and isCache(%)
 * -XX:+UnlockDiagnosticVMOptions LogCompilation : this shows compiler number, method name and level (1-4)
 * -XX:+PrintCodeCache: shows cache available vs used. max size = 240 MB (64bit)
 * Monitoring: Jconsole - this can be used to monitor a PID.
 * XX:+TieredCompilation - this can turn off levels
 *
 *
 */
public class JITTest {

    public static Double[] merge(List<double[]> arrays)
    {
        return Stream.of(arrays)
                .flatMap(Stream::of)		// or use Arrays::stream
                .toArray(Double[]::new);
    }

    // Function to merge multiple arrays in Java 8
    public static Double[] merge2(List<Double[]> arrays)
    {
        Stream<Double> stream = Stream.of();
        for (Double[] s: arrays)
            stream = Stream.concat(stream, Arrays.stream(s));

        return stream.toArray(Double[]::new);
    }

    public static void main(String[] args) {

        Double[] arr1 = {1.0,2.0};
        Double[] arr2 = {2.0,3.0};
        List<Double[]> arrlist = new ArrayList();
        arrlist.add(arr1);
        arrlist.add(arr2);
        Double[] arr3 = merge2(arrlist);
        System.out.println(arr3.length);
        //RandomList<String> rs = check();
        //System.out.println(rs.get(0));


    }
    //public static void main(String[] args) {
      //  System.out.println("hi2");
    //}
}
