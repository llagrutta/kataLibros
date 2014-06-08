package models;

import play.Logger;

public class SUT {
	private final DependencyAbc abc = new DependencyAbc();

	public void doSomething()
	{
		int n = abc.intReturningMethod();

		for (int i = 0; i < n; i++) {
			String s;

			try {
				s = abc.stringReturningMethod();
			}
			catch (SomeCheckedException e) {
				Logger.error("!UN ERROR! !SOCORRO! ¿ALGUIEN PUEDE PENSAR EN LOS NIÑOS");
			}

		}
	}
}
