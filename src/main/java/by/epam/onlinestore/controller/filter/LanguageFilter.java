package by.epam.onlinestore.controller.filter;

import by.epam.onlinestore.controller.context.RequestContext;
import by.epam.onlinestore.controller.context.RequestContextHelper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter("/*")
public class LanguageFilter implements Filter {
    private static final String ATTRIBUTE = "language";
    private static final String RU = "ru";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        RequestContextHelper requestContextHelper = new RequestContextHelper(httpServletRequest);
        RequestContext requestContext = requestContextHelper.createRequestContext();
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String sessionLanguage = (String) requestContext.getSessionAttribute(ATTRIBUTE);
        if (sessionLanguage == null) {
            requestContext.addSessionAttribute(ATTRIBUTE, RU);
            requestContextHelper.updateRequest(requestContext);

        }

        String requestLanguage = httpServletRequest.getParameter(ATTRIBUTE);
        if (requestLanguage != null) {
            requestContext.addSessionAttribute(ATTRIBUTE, requestLanguage);
            String requestString = deleteLanguageParameter(httpServletRequest);
            requestContextHelper.updateRequest(requestContext);
            httpServletResponse.sendRedirect(requestString);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    private String deleteLanguageParameter(HttpServletRequest request) {
        Map<String, String[]> requestParameterMap = request.getParameterMap();
        StringBuilder requestString = new StringBuilder(request.getContextPath() + "/OnlineStore?");
        requestParameterMap.entrySet().stream()
                .filter(e -> !ATTRIBUTE.equals(e.getKey()))
                .forEach(e -> requestString.append(e.getKey()).append("=").append(e.getValue()[0]).append("&"));
        requestString.deleteCharAt(requestString.length() - 1);
        return requestString.toString();
    }

    @Override
    public void destroy() {
    }
}
