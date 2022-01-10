package com.green.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import com.green.biz.dto.UserVO;

@Service
@Aspect

public class AfterReturningAdvice {
	// @Pointcut("execution(* com.green.biz..*Impl.get*(..))")
	
	// public void getPointcut() { } 
	
	@AfterReturning(pointcut="PointcutCommon.getPointcut()", returning="returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {

		String method = jp.getSignature().getName();

		if (returnObj instanceof UserVO) { // returnOb가 UserVO 변환이 가능하면
			UserVO user = (UserVO) returnObj;

			if (user.getRole().equals("Admin")) {
				System.out.println(user.getName() + " 로그인(관리자)"); // 사용자 이름
			} else {
				System.out.println(user.getName() + " 로그인");
			}
		}
		System.out.printf("[사후처리] %s() 리턴값: %s\n", method, returnObj.toString());
	}
}
