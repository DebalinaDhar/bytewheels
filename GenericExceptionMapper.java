/**

 * This class is used so that we can provide developer
 * With a compact JSON object containing error information
 * and not show an HTML page for Tomcat Error. The @Provider
 * annotation helps JAX-RS to Map a exception thrown by 
 * Service/Resources and convert it to a JSON object 
 */
package org.bytemark.bytewheels.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.bytemark.bytewheels.models.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable arg0) {
		// TODO Auto-generated method stub
		ErrorMessage em;
		int code=500;
		if(arg0.getClass().getName().contains("CarNotAvailableExection")|| arg0.getClass().getName().contains("InvalidRequestException"))
			code=400;
		
		arg0.printStackTrace();
		
		em=new ErrorMessage(arg0.getMessage(),code);
		return Response
				.status(code)
				.entity(em)
				.build();
	}

}
