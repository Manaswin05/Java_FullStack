package streams;

import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStreamDemo {
	public static void process(int i) {
		try {
			Thread.sleep(1);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		List<Integer> numbers = IntStream.range(1, 99).boxed().collect(Collectors.toList());
		long startTime = System.currentTimeMillis();
		numbers.stream().forEach(ParallelStreamDemo::process);
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken by sequential Stream : "+(endTime-startTime));
		
		startTime = System.currentTimeMillis();
		numbers.stream().forEach(ParallelStreamDemo::process);
		endTime = System.currentTimeMillis();
		System.out.println("Time taken by parallel Stream : "+(endTime-startTime));		

	}
}