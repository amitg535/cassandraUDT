package smcr.exceptions.resolver;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

public class SmcrExceptionHandlerExceptionResolver extends
		ExceptionHandlerExceptionResolver {

	public SmcrExceptionHandlerExceptionResolver() {
		// Turn logging on by default
				setWarnLogCategory(getClass().getName());

				// Make sure this handler runs before the default
				// ExceptionHandlerExceptionResolver
				setOrder(LOWEST_PRECEDENCE - 1);
	}

	@Override
	protected ModelAndView doResolveHandlerMethodException(
			HttpServletRequest request, HttpServletResponse response,
			HandlerMethod handlerMethod, Exception exception) {
		
		// Get the ModelAndView to use
		ModelAndView mav = super.doResolveHandlerMethodException(request,
				response, handlerMethod, exception);

		// Make more information available to the view
		mav.addObject("exception", exception);
		mav.addObject("url", request.getRequestURL());
		mav.addObject("timestamp", new Date());
		mav.addObject("status", 500);
		return mav;
		
	}
		

	@Override
	protected String buildLogMessage(Exception ex, HttpServletRequest request) {
		return "MVC exception: " + ex.getLocalizedMessage();
	}
	
	

}
