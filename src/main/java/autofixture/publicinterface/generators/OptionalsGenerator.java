package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;
import autofixture.publicinterface.ObjectCreationException;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

/**
 * Created by astral on 02.10.14.
 */
public class OptionalsGenerator implements InstanceGenerator {
  @Override
  public <T> boolean appliesTo(InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(Optional.class)
      || instanceType.isRawTypeAssignableFrom(OptionalInt.class)
      || instanceType.isRawTypeAssignableFrom(OptionalDouble.class)
      || instanceType.isRawTypeAssignableFrom(OptionalLong.class);

  }

  @Override
  public <T> T next(InstanceType<T> instanceType, FixtureContract fixture) {
    if (instanceType.isRawTypeAssignableFrom(Optional.class)) {
      return (T) Optional.of(fixture.create(instanceType.getNestedGenericType()));
    } else if (instanceType.isRawTypeAssignableFrom(OptionalInt.class)) {
      return (T) OptionalInt.of(fixture.create(Integer.class));
    } else if (instanceType.isRawTypeAssignableFrom(OptionalLong.class)) {
      return (T) OptionalLong.of(fixture.create(Long.class));
    } else if (instanceType.isRawTypeAssignableFrom(OptionalDouble.class)) {
      return (T) OptionalDouble.of(fixture.create(Double.class));
    } else {
      throw new ObjectCreationException(instanceType);
    }
  }

  @Override
  public void setOmittingAutoProperties(boolean isOn) {

  }
}
