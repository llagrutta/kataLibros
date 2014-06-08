package mock;

import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Verifications;
import mockit.VerificationsInOrder;
import models.DependencyAbc;
import models.SUT;
import models.SomeCheckedException;

import org.junit.Test;


public class MockTest {

	@Test
	public void testWithRecordAndReplayOnly(@Mocked final DependencyAbc abc) throws SomeCheckedException
	{
		new Expectations() {{
			new DependencyAbc();

			abc.intReturningMethod(); result = 3;

			abc.stringReturningMethod();
			returns("str1", "str2");
			result = new SomeCheckedException();
		}};

		new SUT().doSomething();
	}

	@Test
	public void testWithReplayAndVerifyOnly(@Mocked final DependencyAbc abc) throws SomeCheckedException
	{
		new SUT().doSomething();

		new Verifications() {{
			abc.intReturningMethod();
		}};
	}

	@Test
	public void testWithBothRecordAndVerify(@Mocked final DependencyAbc abc) throws SomeCheckedException
	{
		new NonStrictExpectations() {{
			new DependencyAbc();

			abc.intReturningMethod(); result = 2;
		}};

		new SUT().doSomething();

		new VerificationsInOrder() {{
			new DependencyAbc();
			
			abc.intReturningMethod();
			times = 1;
			
			abc.stringReturningMethod();
			times = 2;
		}};

	}

}
