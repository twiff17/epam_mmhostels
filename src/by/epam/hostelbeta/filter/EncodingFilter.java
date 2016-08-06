package by.epam.hostelbeta.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/Controller")
public class EncodingFilter implements Filter {
	private static final String ENCODING = "UTF-8";
	
	public EncodingFilter() {
	}
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String encoding = request.getCharacterEncoding();
		if (!ENCODING.equalsIgnoreCase(encoding)){
			request.setCharacterEncoding(ENCODING);
			response.setCharacterEncoding(ENCODING);
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
