package autofixture.specification.acceptance.testfixtures;

public class ObjectWithPrimitiveConstructorParameters {

  public int intParameter;
  public String stringParameter;
  public double doubleParameter;

  public ObjectWithPrimitiveConstructorParameters(int parameter1, String parameter2, double parameter3) {
    this.intParameter = parameter1;
    this.stringParameter = parameter2;
    this.doubleParameter = parameter3;
  }

}
