package DesignPatterns;

// only one instance of an object can be created
// interferes with unit testing
public class Singleton {
	private static Singleton _instance = null;
	protected Singleton() {
		// constructor
	}
	public static Singleton getInstance() {
		if(_instance == null) {
			_instance = new Singleton();
		}
		
		return _instance;
	}
}
