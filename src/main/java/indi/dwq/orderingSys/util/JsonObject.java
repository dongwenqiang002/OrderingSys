package indi.dwq.orderingSys.util;


import java.util.Collection;
import java.util.Map;

public class JsonObject {
    private StringBuilder jsonStr;

    /**
     * 根据键值对加入到json字符串中
     *
     * @param key 对应json中的key ，若为空则只附加值，
     * @return 返回 this
     */
    public JsonObject append(String key, Object value) {
        if (key != null) {
            jsonStr.append("\"").append(key).append("\":");
        }
        if (value instanceof CharSequence || value instanceof Character || value.getClass() == char.class) {
            jsonStr.append("\"").append(value).append("\",");
        } else if (value.getClass().isArray()) {
            jsonStr.append("[");
            if (value.getClass() == byte[].class) {
                byte[] os = (byte[]) value;
                for (byte o : os) {
                    this.append(null, o);
                }
            } else {
                Object[] os = (Object[]) value;
                for (Object o : os) {
                    this.append(null, o);
                }
            }
            this.jsonStr.deleteCharAt(this.jsonStr.length() - 1).append("],");

        } else if (value.getClass().isPrimitive() || value.getClass().getPackage().getName().contains("java.lang")) {
            jsonStr.append(value).append(',');
        } else if (value instanceof Collection) {
            jsonStr.append("[");
            Object[] os = ((Collection) value).toArray();
            for (Object o : os) {
                this.append(null, o);
            }
            this.jsonStr.deleteCharAt(this.jsonStr.length() - 1).append("],");
        } else if (value instanceof Map) {
            jsonStr.append("{");
            Map map = (Map) value;
            map.forEach((k, v) -> {
                this.append(k.toString(), v);
            });
            this.jsonStr.deleteCharAt(this.jsonStr.length() - 1).append("},");
        } else {
            jsonStr.append(JsonUtil.objectToJson(value)).append(',');
        }
        return this;
    }


    public JsonObject() {
        this.jsonStr = new StringBuilder(20);
        this.jsonStr.append('{');
    }

    public String getJsonStr() {
        this.jsonStr.deleteCharAt(this.jsonStr.length() - 1).append("}");
        return jsonStr.toString();
    }

    @Override
    public String toString() {
        return this.getJsonStr();
    }
}
