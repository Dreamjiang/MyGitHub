package wj.code;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import wj.entity.User;

public class CodeUtils {
	//����ȫ�ֱ���
	private static final String CLASS ="class";
	
	public static Map<String, Object> entityToMap(Object bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // ����class����
                if (!key.equals(CLASS)) {
                    // �õ�property��Ӧ��getter����
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(bean);
                    map.put(key, value);
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return map;
    }
	public static void main(String[] args) {
		User  user = new User("0001","admin","admin");
		
		Map<String, Object> map=CodeUtils.entityToMap(user);
		
		System.out.println(map);
	} 
}
