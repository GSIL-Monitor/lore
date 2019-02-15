package mng.interceptor;

import mng.common.Constants;
import mng.model.user.FundUser;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author oac
 */
public class WatchInterceptor implements HandlerInterceptor {
    private String loginUri = "login";
    private String errorUri = "error";
    private String timeoutUri = "timeout";
    private String redirectUri = "redirect";
    private String baseUri = "/";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String uri = request.getRequestURI();
        if(checkUri(uri)){
            return true;
        }else{
            String tmpUri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/";
            if(baseUri.equalsIgnoreCase(uri)){
                return true;
            }else{
                FundUser u = (FundUser)request.getSession().getAttribute(Constants.SESSION_USER);
                if(u != null && u.getId() != null){
                    return true;
                }else{
                    response.sendRedirect(tmpUri + loginUri);
                }
            }
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (null != modelAndView && null != modelAndView.getViewName()) {
            String viewName = modelAndView.getViewName().toUpperCase();
            if (!viewName.startsWith(redirectUri.toUpperCase())) {
                ModelMap model = modelAndView.getModelMap();
                model.put("base", request.getContextPath());

                String baseUri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                        + request.getContextPath() + "/";
                request.setAttribute("baseUri", baseUri);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private boolean checkUri(String uri){
        return uri.contains(loginUri) || uri.equals(errorUri);
    }
}
