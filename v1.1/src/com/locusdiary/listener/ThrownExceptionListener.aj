package com.locusdiary.listener;

/**
 * �쳣������־Aspect
 * @author Administrator
 *
 */
public aspect ThrownExceptionListener {
	pointcut ExceptionHandlerPointout(Exception exception) : handler(Exception) && args(exception);
    
    //�쳣�����ʼ�� Advice
    before(Exception exception) : ExceptionHandlerPointout(exception) {
    	String content = String.format("[�쳣��Ϣ] Դ�ļ���%s   ��������%s   ϸ�ڣ�%s\r\n", thisJoinPoint.getSourceLocation(),thisJoinPoint.getStaticPart(),exception.toString());
    	FileLogger.getInstance().logError(content);
    }
}
