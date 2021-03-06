package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

import java.math.BigDecimal;

public class BigDecimalGenerator implements InstanceGenerator {

  @Override
  public <T> boolean appliesTo(InstanceType<T> typeToken) {
    return typeToken.isAssignableFrom(BigDecimal.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T next(InstanceType<T> typeToken, FixtureContract fixture) {
    return (T) new BigDecimal(fixture.create(Integer.class));
  }

  @Override
  public void setOmittingAutoProperties(boolean isOn) {
  }
}
