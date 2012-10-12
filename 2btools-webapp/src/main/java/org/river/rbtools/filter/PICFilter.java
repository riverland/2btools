package org.river.rbtools.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.io.FileUtils;

public class PICFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 byte[] bts=FileUtils.readFileToByteArray(new File("/mnt/workspace/workspace/work-src/linux/river/java/base-projects/base-modules/img-utils/src/test/resources/fanjuan.png"));
		 response.setContentType("image/jpeg");
		 response.getOutputStream().write(bts);
	}

	@Override
	public void destroy() {

	}

}
