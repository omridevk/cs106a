import acm.program.ConsoleProgram;

public class Fiba extends ConsoleProgram {
	
	private static final int MAX_TERM_VALUE = 10000;
	
	public void run() {
		int fib0 = 0;
		int fib1 = 1;
		while (fib0 < MAX_TERM_VALUE){
			 int fib2 = fib0 + fib1;
			 println(fib0);
			 fib0 = fib1;
			 fib1 = fib2;
			
		}
	}
}