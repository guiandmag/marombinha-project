/**
 * 
 */
package it.delicious.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.log4j.Logger;

/**
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
public class LoggerProducer {
	
	@Produces
	public Logger produceLogger(InjectionPoint injectionPoint){
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
}
