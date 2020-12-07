package com.scienceminer.interviewcode.tests;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {

	static class H {
		private int data = 5;
		public void bump(int inc) {
			inc++;
			data = data + inc;
		}
	}
	
	
	public static void main (String[] args ) {
	
		String first = "first";
		String sec = new String("first");
		
		System.out.println(first.equals(sec));
		System.out.println(first == sec);
		
		List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
		
		List<String> result = givenList.stream().collect(toList());
		
		result.stream().forEach(System.out::println);
		
		result.stream().filter(s -> !s.contains("c")).forEach(System.out::println);
		
		IntStream.rangeClosed(1,4).forEach(System.out::println);
		
		
		IntStream.rangeClosed(1,4).map(n -> n*2)
		.average().ifPresent(System.out::println);
		
		Double d = null;
		System.out.println((d instanceof Double) ? "t " : "f" );
		
		Supplier<String> i = () -> "Car";
		Consumer<String> c = x -> System.out.print(x.toLowerCase());
		Consumer<String> d1 = x -> System.out.print(x.toUpperCase());
		c.andThen(d1).accept(i.get());
		System.out.println();
		
		System.out.println(Stream.of("green","yellow","blue").max((s1,s2)-> s1.compareTo(s2)).filter(s -> s.endsWith("n")).orElse("yellow"));
	}
}
