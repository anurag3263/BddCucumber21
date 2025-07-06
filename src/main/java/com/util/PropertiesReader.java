package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.function.BiConsumer;

public class PropertiesReader {
	
	private static BiConsumer<Object, Object> biConsumer =(k,v)->System.out.println(k+"-->"+v);

	public static void main(String[] args) {

		String path = "src/test/resources/system.properties";
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(path);
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(prop.getProperty("properties value"));

		/**
		 * @author anuragchaturvedi 
		 * With Java 7 or earlier we can do like this.
		 */

		for (Entry<Object, Object> entry : prop.entrySet()) {
			System.out.println(entry.getKey() + "-->" + entry.getValue());
		}

		/**
		 * @author anuragchaturvedi
		 *  With java 8 or higher we can Use @Bi Consumer
		 *  interface to achieve this
		 */

		System.out.println("With java 8 or higher using BI consumer interface");

		//prop.forEach((k, v) -> System.out.println(k + "-->" + v));
		
		/**
		 * or like below using static Bi consumer
		 */
		prop.forEach(biConsumer);
		
		/**
		 * or we can use Consumer as well like below
		 */
		
		prop.entrySet().forEach(e->System.out.println(e.getKey()+" : "+e.getValue()));

	}

}
