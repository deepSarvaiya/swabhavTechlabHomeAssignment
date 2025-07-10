package com.tss.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ReflectionTest {

	public static void main(String[] args) throws Exception {

		Class<?> stringClass = Class.forName("java.lang.String");

		Method methods[] = stringClass.getMethods();

		for (Method method : methods) {
			System.out.println(method.getName() + "\t" + method.getParameterCount());
			Parameter parameters[] = method.getParameters();
			for (Parameter parameter : parameters) {
				System.out.println(parameter.getName() + "\t" + parameter.getType());
			}
		}

		Constructor<?> constructor = stringClass.getConstructor();
		Object strObject = constructor.newInstance();

		String str = (String) strObject;

		System.out.println("String created using reflection: \"" + str + "\"");
	}

}
