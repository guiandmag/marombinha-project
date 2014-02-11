/**
 * 
 */
package it.delicious.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(final Exception e) {
		return Response.status(500).entity(getClass()).build();
	}

}
