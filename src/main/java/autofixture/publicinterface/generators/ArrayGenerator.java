package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

public class ArrayGenerator implements InstanceGenerator {

  @Override
  public <T> boolean appliesTo(InstanceType<T> clazz) {
    return clazz.isArray();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T next(InstanceType<T> type, FixtureContract fixture) {
    InstanceType<?> componentType = type.getArrayElementType();
    Object array = componentType.createArray(fixture.createMany(componentType).toArray());
    T stronglyTypedArray = (T) array;
    return stronglyTypedArray;
  }

  @Override
  public void setOmittingAutoProperties(boolean isOn) {
  }

}
