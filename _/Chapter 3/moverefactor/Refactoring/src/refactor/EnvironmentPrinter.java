/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package refactor;

/**
 *
 * @author david
 */
public class EnvironmentPrinter {

    public static void main(String[] args) {
        
        EnvironmentPrinter ep = new EnvironmentPrinter();
        ep.print("JAVA_HOME");
    }
    
    public void print(String environmentVariableName) {
        String envVariableValue = System.getenv(environmentVariableName);
        System.out.println(envVariableValue);        
    }
    
}
