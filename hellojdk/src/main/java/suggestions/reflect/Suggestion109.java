package suggestions.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by min on 17-3-15.
 */
public class Suggestion109 {
}

class Utils {
    public static <T> Class<T> getGenericClassType(Class clz) {
        Type type = clz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            Type[] types = pt.getActualTypeArguments();
            if (types.length > 0 && types[0] instanceof Class) {
                return (Class) types[0];
            }
        }
        return (Class) Object.class;
    }
}
abstract class BaseDao<T> {

    private Class<T> clz = Utils.getGenericClassType(getClass());

    public void get(long id) {
        //session.get(clz, id);
    }
}

class UserDao extends BaseDao<String> {

}