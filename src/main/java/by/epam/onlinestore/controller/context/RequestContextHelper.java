package by.epam.onlinestore.controller.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestContextHelper {
    private final HttpServletRequest httpServletRequest;

    public RequestContextHelper(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public RequestContext createRequestContext() {
        Enumeration<String> requestParameterNames = httpServletRequest.getParameterNames();
        Enumeration<String> requestAttributesNames = httpServletRequest.getAttributeNames();
        HttpSession httpSession = httpServletRequest.getSession();
        Enumeration<String> sessionAttributesNames = httpSession.getAttributeNames();
        Map<String, String> requestParameters = createRequestParametersMap(requestParameterNames);
        Map<String, Object> requestAttributes = createRequestAttributesMap(requestAttributesNames);
        Map<String, Object> sessionAttributes = createSessionAttributesMap(sessionAttributesNames, httpSession);

        return new RequestContext(requestParameters, requestAttributes, sessionAttributes);
    }

    public void updateRequest(RequestContext context) {
        Map<String, Object> requestAttributes = context.getAllRequestAttributes();
        Map<String, Object> sessionAttributes = context.getAllSessionAttributes();
        HttpSession session = httpServletRequest.getSession();
        fillSessionAttributes(session, sessionAttributes);
        fillRequestAttributes(requestAttributes);
    }

    private void fillRequestAttributes(Map<String, Object> attributes) {
        for (Map.Entry<String, Object> attribute : attributes.entrySet()) {
            httpServletRequest.setAttribute(attribute.getKey(), attribute.getValue());
        }
    }

    private void fillSessionAttributes(HttpSession session, Map<String, Object> attributes) {
        for (Map.Entry<String, Object> attribute : attributes.entrySet()) {
            session.setAttribute(attribute.getKey(), attribute.getValue());
        }
    }

    private Map<String, String> createRequestParametersMap(Enumeration<String> names) {
        Map<String, String> requestParametersMap = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = httpServletRequest.getParameter(name);
            requestParametersMap.put(name, value);
        }
        return requestParametersMap;
    }

    private Map<String, Object> createRequestAttributesMap(Enumeration<String> names) {
        Map<String, Object> requestAttributesMap = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            Object value = httpServletRequest.getAttribute(name);
            requestAttributesMap.put(name, value);
        }
        return requestAttributesMap;
    }

    private Map<String, Object> createSessionAttributesMap(Enumeration<String> names, HttpSession session) {
        Map<String, Object> sessionAttributesMap = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            Object value = session.getAttribute(name);
            sessionAttributesMap.put(name, value);
        }
        return sessionAttributesMap;
    }
}
