import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import test.testEnemy;
import test.testCharacter;

public class TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(testCharacter.class);
		
        for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
        }
      
      Result result2 = JUnitCore.runClasses(testEnemy.class);
        for (Failure failure : result2.getFailures()) {
         System.out.println(failure.toString());
        }	
        System.out.println(result2.wasSuccessful());
   }
}
