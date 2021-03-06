package autofixture.publicinterface.generators;

import autofixture.implementationdetails.InstanceField;
import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;
import autofixture.publicinterface.ObjectCreationException;

import java.util.ArrayList;

public class ConcreteObjectGenerator implements InstanceGenerator {

  private boolean omittingAutoProperties = false;

  @Override
  public <T> boolean appliesTo(InstanceType<T> typeToken) {
    return true;
  }

  @Override
  public <T> T next(InstanceType<T> type, FixtureContract fixture) {
    T instance = createInstanceOf(type, fixture);
    try {
      if (!omittingAutoProperties) {
        makeBestEffortAttemptToInvokeAllSettersOn(instance, type, fixture);
        makeBestEffortAttemptToSetAllPublicFields(instance, type, fixture);
      }

    } catch (IllegalAccessException e) {
      throw new ObjectCreationException(type, e);
    }
    return instance;
  }

  private <T> void makeBestEffortAttemptToSetAllPublicFields(T instance,
                                                             InstanceType<T> type, FixtureContract fixture) throws IllegalAccessException {
    ArrayList<InstanceField<T>> publicFields = type.getAllPublicFieldsOf(instance);
    for (InstanceField<T> publicField : publicFields) {
      publicField.setValueUsing(fixture);
    }
  }

  private <T> T createInstanceOf(InstanceType<T> type, FixtureContract fixture) {
    Call<T, T> currentConstructor = type.findPublicConstructorWithLeastParameters();
    T instance = currentConstructor.invokeWithArgumentsCreatedUsing(fixture);
    return instance;
  }

  private <T> void makeBestEffortAttemptToInvokeAllSettersOn(T instance,
                                                             InstanceType<T> type, FixtureContract fixture) {
    ArrayList<Call<T, Object>> setters = type.getAllSetters();
    for (Call<T, Object> setter : setters) {
      makeBestEffortAttemptToInvoke(setter, instance, fixture);
    }
  }

  private <T> void makeBestEffortAttemptToInvoke(Call<T, Object> setter,
                                                 T instance, FixtureContract fixture) {
    try {
      setter.invokeWithArgumentsCreatedUsing(fixture, instance);
    } catch (Throwable t) {
      //silently invoke any failed attempt
    }
  }

  @Override
  public void setOmittingAutoProperties(boolean isOn) {
    this.omittingAutoProperties = isOn;

  }

}
