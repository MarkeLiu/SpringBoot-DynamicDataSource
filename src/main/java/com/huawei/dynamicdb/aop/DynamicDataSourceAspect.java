package com.huawei.dynamicdb.aop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.huawei.dynamicdb.dynamicdbconfig.DynamicDataSourceHolder;
import com.huawei.dynamicdb.dynamicdbconfig.TargetDataSource;

/**
 * AOP拦截器，负责处理在类头部或者方法头部带有@TargetDataSource注解
 * 需要保证在数据库事物开始之前进行拦截
 * @Before 判断需要访问的数据库名称  并将该名称放入DynamicDataSourceHolder类中线程池
 * @After 在当前数据库使用完成后 清楚位于DynamicDataSourceHolder类线程池相关变量
 * @author Marke
 *
 */
@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {
	private static final Logger logger = Logger
			.getLogger(DynamicDataSourceAspect.class);
	
	/**
	 * 前置拦截 负责数据库调用前的数据库切换任务
	 * @param point
	 */
	@Before("execution(* com.huawei.*.dao.*.*(..))")
	public void changeDataSource(JoinPoint point) {
		Class<?> target = point.getTarget().getClass();
		MethodSignature signature = (MethodSignature) point.getSignature();
		// 默认使用目标类型的注解，如果没有则使用其实现接口的注解
		for (Class<?> clazz : target.getInterfaces()) {
			resolveDataSource(clazz, signature.getMethod());
		}
		resolveDataSource(target, signature.getMethod());
		
	}
	
	/**
	 * 服务数据库调用完成后 清楚当前选择的数据库
	 * @param point
	 */
	@After("execution(* com.huawei.*.dao.*.*(..))")
	public void restoreDataSource(JoinPoint point) {
		DynamicDataSourceHolder.clearDataSource();
	}
	
	/**
	 * 提取目标对象方法注解和类型注解中的数据源标识
	 * 
	 * @param clazz
	 * @param method
	 */
	private void resolveDataSource(Class<?> clazz, Method method) {
		try {
			Class<?>[] types = method.getParameterTypes();
			// 默认使用类型注解
			if (clazz.isAnnotationPresent(TargetDataSource.class)) {
				TargetDataSource source = clazz
						.getAnnotation(TargetDataSource.class);
				DynamicDataSourceHolder.setDataSource(source.name());
			}
			// 方法注解可以覆盖类型注解
			Method m = clazz.getMethod(method.getName(), types);
			if (m != null && m.isAnnotationPresent(TargetDataSource.class)) {
				TargetDataSource source = m
						.getAnnotation(TargetDataSource.class);
				DynamicDataSourceHolder.setDataSource(source.name());
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}
	
}
