/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package refactor.factory;


public class BuilderBuilderBuilder {
    private int a = 7;
    private int b;

    public BuilderBuilderBuilder() {
    }

    public BuilderBuilderBuilder setA(int a) {
        this.a = a;
        return this;
    }

    public BuilderBuilderBuilder setB(int b) {
        this.b = b;
        return this;
    }

    public BuilderBuilder createBuilderBuilder() {
        return new BuilderBuilder(a, b);
    }
    
}
