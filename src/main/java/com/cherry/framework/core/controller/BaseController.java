package com.cherry.framework.core.controller;

import com.cherry.framework.core.model.PageSort;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * BaseController
 *
 * @author Leon
 * @version 2019/3/18 21:47
 */
public abstract class BaseController {

    private static final Logger lg = LoggerFactory.getLogger(BaseController.class);

    public BaseController() {}

    public static void printRequestParameters(HttpServletRequest req) {
        lg.debug("------------------ Request Parameters ------------------");
        Enumeration params = req.getParameterNames();

        while(params.hasMoreElements()) {
            String name = (String)params.nextElement();
            lg.debug("{}-->{}", name, req.getParameter(name).length() <= 100 ? req.getParameter(name) : req.getParameter(name).substring(0, 100) + "...");
        }

        lg.debug("------------------ Request Parameters ------------------");
    }

    public static void printRequestHeaders(HttpServletRequest req) {
        lg.debug("------------------ Request Headers  ------------------");
        Enumeration headerNames = req.getHeaderNames();

        while(headerNames.hasMoreElements()) {
            String key = (String)headerNames.nextElement();
            lg.debug("{}-->{}", key, req.getHeader(key));
        }

        lg.debug("------------------ Request Headers ------------------");
    }

    public <T> T mapping(HttpServletRequest req, Class<T> clazz) throws Exception {
        T obj = clazz.newInstance();
        BeanUtils.populate(obj, req.getParameterMap());
        return obj;
    }

    protected PageSort getPageSort(HttpServletRequest req) {
        PageSort ps = new PageSort();
        if (StringUtils.isNotBlank(req.getParameter("page"))) {
            ps.setPageNumber(Long.valueOf(req.getParameter("page")));
        }

        if (StringUtils.isNotBlank(req.getParameter("rows"))) {
            ps.setPageSize(Integer.valueOf(req.getParameter("rows")));
        }

        ps.setSortName(req.getParameter("sort"));
        ps.setSortOrder(req.getParameter("order"));
        return ps;
    }

    public static boolean isAjaxRequest(HttpServletRequest req) {
        return StringUtils.isNotBlank(req.getHeader("X-Requested-With")) && "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
    }
}
