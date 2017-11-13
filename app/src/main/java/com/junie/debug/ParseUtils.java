//package com.iflytek.debug;
//
//import android.util.Log;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.lang.reflect.Field;
//import java.util.Arrays;
//
///**
// * Created by niejun on 2017/2/24.
// */
//
//public class ParseUtils {
//
//    // 基本数据类型
//    private final static String[] TYPES = {"int", "java.lang.String", "boolean", "char",
//            "float", "double", "long", "short", "byte"};
//
//
//
//
//    /**
//     * 解析Object
//     * @param obj
//     * @return
//     */
//    //FIXME  Object 数组
//    public static String ObjectParse(Object obj) {
//        String message = "";
//        if(("JSONObject").equals(obj.getClass().getSimpleName())) {
//            try {
//                message = ((JSONObject)obj).toString(3);
//            } catch (JSONException e) {
//                return Log.getStackTraceString(e);
//            }
//        } else if (("JSONArray").equals(obj.getClass().getSimpleName())) {
//            try {
//                message = ((JSONArray)obj).toString(3);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        } else if(obj.getClass().isArray()) {
//            message = Arrays.deepToString((Object[]) obj);
//        } else {
//            message = objectToString(obj);
//        }
//        return message;
//    }
//
//
//    /**
//     * 将对象转化为String
//     */
//    protected static <T> String objectToString(T object) {
//        if (object == null) {
//            return "Object{object is null}";
//        }
//        if (object.toString().startsWith(object.getClass().getName() + "@")) {
//            StringBuilder builder = new StringBuilder(object.getClass().getSimpleName() + "{");
//            Field[] fields = object.getClass().getDeclaredFields();
//            for (Field field : fields) {
//                field.setAccessible(true);
//                boolean flag = false;
//                for (String type : TYPES) {
//                    if (field.getType().getName().equalsIgnoreCase(type)) {
//                        flag = true;
//                        Object value = null;
//                        try {
//                            value = field.get(object);
//                        } catch (IllegalAccessException e) {
//                            value = e;
//                        } finally {
//                            builder.append(String.format("%s=%s, ", field.getName(),
//                                    value == null ? "null" : value.toString()));
//                        }
//                    }
//                }
//                if (!flag) {
//                    builder.append(String.format("%s=%s, ", field.getName(), "Object"));
//                }
//            }
//            return builder.replace(builder.length() - 2, builder.length() - 1, "}").toString();
//        } else {
//            return object.toString();
//        }
//    }
//}
