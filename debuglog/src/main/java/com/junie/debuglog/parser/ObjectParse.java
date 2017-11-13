package com.junie.debuglog.parser;

import com.junie.debuglog.DebugLog;
import com.junie.debuglog.setting.Constant;
import com.junie.debuglog.util.ArrayUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import static com.junie.debuglog.setting.Constant.BR;
import static com.junie.debuglog.setting.Constant.MAX_CHILD_LEVEL;

/**
 * Created by niejun on 2017/4/12.
 */

public class ObjectParse {

    /**
     * 解析Object为String
     * @param obj
     * @return
     */
    public static String objectToString(Object obj) {
        return objectToString(obj,0);
    }

    /**
     * 解析Object为String，递归
     * @param obj
     * @return
     */
    public static String objectToString(Object obj,int level) {
        if (obj == null) {
            return Constant.OBJECT_NULL;
        }
        //Android 常用类型，可自定义增加
        List<IParse> parsers = DebugLog.getConfig().getParsers();
        if (parsers != null && parsers.size() > 0) {
            for (IParse parse : parsers) {
                if (parse != null && parse.parseClassType().isAssignableFrom(obj.getClass())) {
                    return parse.parseToString(obj);
                }
            }
        }
        //数组类型
        if(obj.getClass().isArray()) {
            return ArrayUtil.ArrayToString(obj);
        }
        //其余实体类型
        if(obj.toString().startsWith(obj.getClass().getName() + "@")) {
            StringBuilder builder = new StringBuilder();
            getClassFields(builder, obj.getClass(), obj, level);

            //打印父类元素
            Class superClass = obj.getClass().getSuperclass();
            while (!superClass.equals(Object.class)) {
                builder.append(BR + "=> ");
                getClassFields(builder, superClass, obj, level);
                superClass = superClass.getSuperclass();
            }
            return builder.toString();
        }
        return obj.toString();
    }



    //---------------------------------------------------------------------------

    /**
     * 拼接class的字段和值
     * @param superClass
     * @param builder
     * @param obj   对象
     * @param childLevel 递归解析属性的层级
     */
    private static void getClassFields(StringBuilder builder, Class superClass, Object obj, int childLevel) {
        if (superClass.equals(Object.class)) {
            //没有可解析的父类
            return;
        }
        if(childLevel > MAX_CHILD_LEVEL) {
            return;
        }
        builder.append(superClass.getSimpleName() + " {");
        Field[] fields = superClass.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            field.setAccessible(true);
            // 解决Instant Run下的新增变量
            if ((field.getName().equals("$change"))) {
                continue;
            }
            Object fieldValue;
            try {
                fieldValue = field.get(obj);
            } catch (IllegalAccessException e) {
                fieldValue = e;
            }
            if (fieldValue != null) {
                String format = "%s = %s ";
                builder.append(String.format(format, field.getName(), objectToString(fieldValue, childLevel + 1)));
            }
        }
        builder.append("}");
    }


    /**
     * 是否为静态内部类
     * @param cla
     * @return
     */
    public static boolean isStaticInnerClass(Class cla) {
        if (cla != null && cla.isMemberClass()) {
            int modifiers = cla.getModifiers();
            if ((modifiers & Modifier.STATIC) == Modifier.STATIC) {
                return true;
            }
        }
        return false;
    }


    /**
     * 拼接字符串
     * @param msg
     * @param args
     * @return
     */
    public static String StringParse(String msg,Object... args) {
        return args == null || args.length == 0 ? msg : String.format(msg,args);
    }
}
