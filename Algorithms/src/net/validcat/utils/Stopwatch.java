package net.validcat.utils;

public class Stopwatch {
	private final long start;
	
	/**
	 * Start a timer. 
	 * @see <code>stopTimer()</code>
	 */
	public Stopwatch() {
		start = System.currentTimeMillis();
	}
	
	/**
	 * Stop the timer and get the elapsed time.
	 * @return the elapsed time from the <code>Stopwatch()</code> init
	 */
	public double stopTimer() {
		long stop = System.currentTimeMillis();
		return (stop-start) / 1000.0;
	}
	
}
