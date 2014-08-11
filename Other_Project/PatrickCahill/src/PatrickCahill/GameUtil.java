package PatrickCahill;

/**
 *
 */
public class GameUtil {

  public static void packArray(Object[] array) {

    if (array == null) {
      return;
    }

    int i = 0;
    int j = 0;

    while (i < array.length && j < array.length) {
      if (array[i] == null) {
        if (j < i) j = i;
        if (array[j] != null) {
          array[i] = array[j];
          array[j] = null;
          i++;
        }
        j++;

      } else {
        i++;
      }
    }
  }


  private GameUtil() {
    // utility class.
  }
}
