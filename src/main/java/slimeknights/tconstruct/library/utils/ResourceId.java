package slimeknights.tconstruct.library.utils;

import net.minecraft.resources.Identifier;

import javax.annotation.Nullable;
import java.util.function.BiFunction;

/**
 * Helper for use with our extensions of identifier for some type safety in IDs.
 * Note we left {@link Identifier#withPath(String)} and alike as returning {@link Identifier} as there is not much use extending an ID.
 * @see IdParser
 */
public abstract class ResourceId extends Identifier {
  public ResourceId(String namespace, String path) {
    super(namespace, path);
  }

  public ResourceId(Identifier location) {
    super(location.getNamespace(), location.getPath());
  }

  public ResourceId(String location) {
    super(location.contains(":") ? location.substring(0, location.indexOf(':')) : "minecraft",
          location.contains(":") ? location.substring(location.indexOf(':') + 1) : location);
  }

  /* Helpers for static constructors */

  /**
   * Creates a new ID from the given string
   * @param string  String
   * @return  ID, or null if invalid
   */
  @Nullable
  protected static <T extends Identifier> T tryParse(String string, BiFunction<String,String,T> constructor) {
    String[] parts = IdParser.decompose("minecraft", string, ':');
    return tryBuild(parts[0], parts[1], constructor);
  }

  /**
   * Creates a new ID from the given namespace and path
   * @param namespace  Namespace
   * @param path       Path
   * @return  ID, or null if invalid
   */
  @Nullable
  protected static <T extends Identifier> T tryBuild(String namespace, String path, BiFunction<String,String,T> constructor) {
    if (isValidNamespace(namespace) && isValidPath(path)) {
      return constructor.apply(namespace, path);
    }
    return null;
  }
}
