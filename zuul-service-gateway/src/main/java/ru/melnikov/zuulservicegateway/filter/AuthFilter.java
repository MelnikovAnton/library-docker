package ru.melnikov.zuulservicegateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

@Component
@Slf4j
public class AuthFilter extends ZuulFilter {
    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        log.info("Entered into pre filter ->>>>" + ctx.getRequest().getRequestURI());

        if (log.isDebugEnabled()) {
            Iterator<String> iter = req.getHeaderNames().asIterator();
            while (iter.hasNext()) {
                String header = iter.next();
                System.out.println(header + " " + req.getHeader(header));
            }
        }
        return null;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }
}
