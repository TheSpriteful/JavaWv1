package ca5.event.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

/**
 * A simple event bus.  Possibly not the most efficient
 */
public class EventBus {
  private static final Logger logger = Logger.getLogger(EventBus.class.getName());
  private final ExecutorService executorService;

  private interface TraverseFunction {
    void onMatch(Class<?> parameterType, Function runnable);
  }

  private interface Function {
    void run(Object o) throws Exception;
  }

  private final HashMap<Class<?>, Set<Function>> registrations = new HashMap<Class<?>, Set<Function>>();

  public EventBus(ExecutorService executorService) {
    this.executorService = executorService;
  }

  public void fire(final Object event) {
    if (event == null) {
      return;
    }
    Set<Function> functions = registrations.get(event.getClass());
    if (functions == null || functions.isEmpty()) {
      return;
    }
    for (final Function function : functions) {
      executorService.submit(new Runnable() {
        @Override
        public void run() {
          try {
            function.run(event);
          } catch (Exception e) {
            logger.warning("Received exception from event firing: " + e);
          }
        }
      });
    }
  }


  public void register(Object subscriber) {
    traverseSubscriber(subscriber, new TraverseFunction() {
      @Override
      public void onMatch(Class<?> parameterType, Function runnable) {
        if (!registrations.containsKey(parameterType)) {
          registrations.put(parameterType, new HashSet<Function>());
        }
        registrations.get(parameterType).add(runnable);
      }
    });
  }

  private void traverseSubscriber(final Object subscriber, TraverseFunction function) {
    if (subscriber == null) {
      throw new IllegalArgumentException("EventBus subscriber cannot be null");
    }

    Method[] declaredMethods = subscriber.getClass().getDeclaredMethods();
    for (final Method declaredMethod : declaredMethods) {
      final Subscribe subscribe = declaredMethod.getAnnotation(Subscribe.class);
      if (subscribe != null) {
        logger.finest("Found subscriber: " + declaredMethod);
        if (!declaredMethod.getReturnType().equals(void.class)) {
          throw new IllegalStateException("@Subscribe method can only operate on void return types but found: " + declaredMethod);
        }
        Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
        if (parameterTypes.length != 1) {
          throw new IllegalStateException("@Subscribe method should contain exactly 1 parameter but found: " + declaredMethod);
        }

        function.onMatch(parameterTypes[0], new Function() {
          @Override
          public void run(Object event) throws Exception {
            declaredMethod.invoke(subscriber, event);
          }
        });
      }
    }
  }
}
