package indi.dwq.orderingSys.util;


import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * json 转换工具类
 */
public class JsonUtil {

    //用来区分是获取get方法还是set方法，用在 private static Method[] getMethods(Class<?> cls, Type type) 函数中
    enum Type {
        Get, Set
    }

    /**
     * json字符串转换成对象
     */
    @Deprecated
    public static <T> T jsonToObject(String string, Class<T> cls) {
        T o;//= null;
        try {
            o = cls.newInstance();
            Map map = jsonStrToMap(string);

            Method[] methods = getMethods(o.getClass(), Type.Set);
            assert methods != null;
            for (Method method : methods) {
                try {
                    String valueName = method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4);
                    assert map != null;
                    Object value = map.get(valueName);
                    if (value == null) continue;

                    Class type = o.getClass().getMethod("get" + method.getName().substring(3)).getReturnType();
                    if (type.isArray()) {
                        Object[] os = (Object[]) value;
                        //创建对应数组
                        value = Array.newInstance(type.getComponentType(), os.length);
                        for (int i = 0; i < os.length; i++) {
                            Array.set(value, i, typeCon(type.getComponentType(), os[i]));
                        }
                    } else {
                        value = typeCon(type, value);
                    }
                    //将json字符串中属性值set进对象属性
                    method.invoke(o, value);
                } catch (Exception e) {
                    //e.printStackTrace();
                    throw new NullPointerException("json转换失败！");
                }
            }
        } catch (ReflectiveOperationException | NullPointerException reflectiveOperationException) {
            return null;
        }
        return o;
    }

    /**
     * json字符串转换成对象
     */
    public static <T> T jsonToObject2(String string, Class<T> cls) {
        T o;
        try {
            if (cls.getName().contains("$")) {
                //这是一个内部类
                Constructor<?> c = cls.getConstructors()[0];
                Object sup = Class.forName(cls.getName().substring(0,cls.getName().indexOf('$'))).newInstance();
                o = (T) c.newInstance(sup);
            } else {
                o = cls.newInstance();
            }

            Map map = jsonStrToMap2(string);
            Method[] methods = getMethods(o.getClass(), Type.Set);
            assert methods != null;
            for (Method method : methods) {
                try {
                    String valueName = method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4);
                    assert map != null;
                    Object value = map.get(valueName);
                    if (value == null) continue;

                    Class type = o.getClass().getMethod("get" + method.getName().substring(3)).getReturnType();
                    if (type.isArray()) {
                        //Object[] os = (Object[]) value;
                        String vstr = (String) value;
                        vstr = vstr.substring(1, vstr.length() - 1);
                        String[] os = vstr.split(",");
                        //创建对应数组
                        value = Array.newInstance(type.getComponentType(), os.length);
                        for (int i = 0; i < os.length; i++) {
                            Array.set(value, i, typeCon(type.getComponentType(), os[i]));
                        }
                    } else if (List.class.isAssignableFrom(type)) {
                        List<Object> co;//= null;
                        if (type.isInterface()) {
                            co = new ArrayList<>(10);
                        } else {
                            co = (List<Object>) type.newInstance();

                        }
                        String[] vs = splitCollection((String) value);
                        java.lang.reflect.Type genericFieldType = cls.getDeclaredField(valueName).getGenericType();
                        ParameterizedType aType = (ParameterizedType) genericFieldType;
                        Class fieldArgClass = (Class) aType.getActualTypeArguments()[0];
                        for (String v : vs) {
                            co.add(typeCon(fieldArgClass, v));
                        }
                        value = co;
                    } else if (Map.class.isAssignableFrom(type)) {
                        Map m;//= null;
                        if (type.isInterface()) {
                            m = new HashMap();
                        } else {
                            m = (Map) type.newInstance();
                        }
                        java.lang.reflect.Type genericFieldType = cls.getDeclaredField(valueName).getGenericType();
                        ParameterizedType aType = (ParameterizedType) genericFieldType;
                        Class fieldArg2Class = (Class) aType.getActualTypeArguments()[1];
                        Map tempMap = jsonStrToMap2((String) value);
                        Map finalM = m;
                        tempMap.forEach((k, v) -> finalM.put(k, typeCon(fieldArg2Class, v)));
                        value = finalM;
                    } else {
                        value = typeCon(type, value);
                    }
                    //将json字符串中属性值set进对象属性
                    method.invoke(o, value);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new NullPointerException("json转换失败！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return o;
    }


    private static String[] splitCollection(String ss) {
        StringBuilder str = new StringBuilder(ss);
        List<String> list = new ArrayList<>(10);
        //去左右大括号
        str.deleteCharAt(0);
        str.deleteCharAt(str.length() - 1);
        str.append(',');
        while (str.length() > 0) {
            String value;
            if (str.charAt(0) == '{' || str.charAt(0) == '[') {
                int[] temp = str.chars().toArray();
                int dk = 0;
                int xk = 0;
                if (str.charAt(0) == '{') dk++;
                else xk++;
                int i;//= 0;
                for (i = 1; i < temp.length; i++) {
                    switch (temp[i]) {
                        case '{':
                            dk++;
                            break;
                        case '}':
                            dk--;
                            break;
                        case '[':
                            xk++;
                            break;
                        case ']':
                            xk--;
                            break;
                        default:
                    }
                    if (dk == 0 && xk == 0) break;
                }
                value = str.substring(0, i + 1);
                str.delete(0, i + 2);
            } else {
                value = str.substring(0, str.indexOf(","));
                str.delete(0, str.indexOf(",") + 1);
            }
            list.add(value);
        }
        return list.toArray(new String[0]);
    }

    /**
     * 类型转换函数
     */
    private static Object typeCon(Class<?> type, Object value) {
        if (((String) value).contains("{")) /*不是java自带类型*/ {
            value = jsonToObject2((String) value, type);
        } else {
            if (type == boolean.class || type == Boolean.class) {
                value = Boolean.parseBoolean((String) value);
            } else if (type == byte.class || type == Byte.class) {
                value = Byte.parseByte((String) value);
            } else if (type == short.class || type == Short.class) {
                value = Short.parseShort((String) value);
            } else if (type == int.class || type == Integer.class) {
                value = Integer.parseInt((String) value);
            } else if (type == Long.class || type == long.class) {
                value = Long.parseLong((String) value);
            } else if (type == float.class || type == Float.class) {
                value = Float.parseFloat((String) value);
            } else if (type == double.class || type == Double.class) {
                value = Double.parseDouble((String) value);
            } else if (type == char.class || type == Character.class) {
                value = ((String) value).charAt(1);
            } else if (type.getName().contains("String")) /*字符串类型*/ {
                try {
                    Constructor c = type.getConstructor(String.class);//获取有参构造
                    value = c.newInstance(((String) value).substring(1, ((String) value).length() - 1));    //通过有参构造创建对象
                } catch (Exception e) {
                    value = null;
                }
            } else {
                return null;
            }
        }
        return value;
    }

    /**
     * 将json字符串转换成map
     */
    @Deprecated
    private static Map<String, Object> jsonStrToMap(String jsonStr) {
        jsonStr = jsonStr.trim();
        if (jsonStr.isEmpty()) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        StringBuilder str = new StringBuilder(jsonStr);

        //去左右大括号
        str.deleteCharAt(0);
        str.deleteCharAt(str.length() - 1);

        while (str.length() != 0) {
            int index = str.indexOf(":");
            String key = str.substring(1, index - 1);
            str.delete(0, index + 1);
            Object value;//= null;
            //解析json 属性值类型
            if (str.charAt(0) == '{')       /*对象*/ {
                value = str.substring(0, str.indexOf("}") + 1);
                str.delete(0, str.indexOf("}") + 2);
            } else if (str.charAt(0) == '[') /*数组*/ {
                String valueStr = str.substring(1, str.indexOf("]"));
                String[] strings = valueStr.split(",");

                if (strings[0].charAt(0) == '"')/*字符串数组*/ {
                    for (int i = 0; i < strings.length; i++) {
                        strings[i] = strings[i].substring(1, strings[i].length() - 1);
                    }
                }
                value = strings;
                str.delete(0, str.indexOf("]") + 2);
            } else if (str.charAt(0) == '"') /*字符型*/ {
                str.deleteCharAt(0);
                value = str.substring(0, str.indexOf("\""));
                str.delete(0, str.indexOf("\"") + 2);
            } else/*其他型*/ {
                value = str.substring(0, str.indexOf(","));
                str.delete(0, str.indexOf(",") + 1);
            }
            map.put(key, value);
        }
        return map;
    }


    private static Map<String, String> jsonStrToMap2(String jsonStr) {
        jsonStr = jsonStr.trim();
        if (jsonStr.isEmpty()) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        StringBuilder str = new StringBuilder(jsonStr);

        //去左右大括号
        str.deleteCharAt(0);
        str.deleteCharAt(str.length() - 1);
        str.append(',');
        while (str.length() > 0) {
            String key, value;//= null;
            key = str.substring(1, str.indexOf(":") - 1);
            str.delete(0, str.indexOf(":") + 1);
            if (str.charAt(0) == '{' || str.charAt(0) == '[') {
                int[] temp = str.chars().toArray();
                int dk = 0;
                int xk = 0;
                if (str.charAt(0) == '{') dk++;
                else xk++;
                int i;// = 0;
                for (i = 1; i < temp.length; i++) {
                    switch (temp[i]) {
                        case '{':
                            dk++;
                            break;
                        case '}':
                            dk--;
                            break;
                        case '[':
                            xk++;
                            break;
                        case ']':
                            xk--;
                            break;
                    }
                    if (dk == 0 && xk == 0) break;
                }
                value = str.substring(0, i + 1);
                str.delete(0, i + 2);
            } else {
                value = str.substring(0, str.indexOf(","));
                str.delete(0, str.indexOf(",") + 1);
            }
            map.put(key, value);
        }
        return map;
    }

    /**
     * 将对象转换成JSON
     */
    public static String objectToJson(Object obj) {
        // Class cls = obj.getClass();

        JsonObject jsonString = new JsonObject();
        Method[] m = getMethods(obj.getClass(), Type.Get);
        assert m != null;
        for (Method method : m) {
            try {
                Object value = method.invoke(obj);
                String key = method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4);
                if (value == null) {
                    throw new NullPointerException("属性值为空!");
                } else {
                    jsonString.append(key, value);
                }
            } catch (Exception ignored) {
            }
        }

        return jsonString.toString();
    }

    /**
     * 获取所有get/set方法
     *
     * @param cls  需要被获取的对象
     * @param type 需要获取的类型（Get,Set)
     * @return get/set方法数组
     */
    private static Method[] getMethods(Class<?> cls, Type type) {
        Method[] methods = cls.getMethods();
        List<Method> methodList = new ArrayList<>(10);
        for (Method getMethod : methods) {
            if (getMethod.getName().indexOf("get") == 0) {
                try {
                    Method setMethod = cls.getMethod(getMethod.getName().replaceFirst("get", "set"), getMethod.getReturnType());
                    if (type == Type.Get) {
                        methodList.add(getMethod);
                    } else if (type == Type.Set) {
                        methodList.add(setMethod);
                    } else {
                        return null;
                    }
                } catch (NoSuchMethodException ignored) {
                }
            }
        }
        return methodList.toArray(new Method[0]);
    }
}
