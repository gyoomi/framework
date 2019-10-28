package com.gyoomi.adam.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyoomi.adam.core.model.Response;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    protected static void printRequestParameters(HttpServletRequest req) {
        lg.debug("------------------ Request Parameters ------------------");
        Enumeration params = req.getParameterNames();

        while(params.hasMoreElements()) {
            String name = (String)params.nextElement();
            lg.debug("{}-->{}", name, req.getParameter(name).length() <= 100 ? req.getParameter(name) : req.getParameter(name).substring(0, 100) + "...");
        }

        lg.debug("------------------ Request Parameters ------------------");
    }

    protected static void printRequestHeaders(HttpServletRequest req) {
        lg.debug("------------------ Request Headers  ------------------");
        Enumeration headerNames = req.getHeaderNames();

        while(headerNames.hasMoreElements()) {
            String key = (String)headerNames.nextElement();
            lg.debug("{}-->{}", key, req.getHeader(key));
        }

        lg.debug("------------------ Request Headers ------------------");
    }

    protected <T> T mapping(HttpServletRequest req, Class<T> clazz) throws Exception {
        T obj = clazz.newInstance();
        BeanUtils.populate(obj, req.getParameterMap());
        return obj;
    }

    /**
     * 获取分页数据对象
     *
     * @param req req
     * @return 分页数据对象
     */
    protected IPage getPageSort(HttpServletRequest req) {
        return getPageSort(req, true);
    }

    /**
     * 获取分页数据对象
     *
     * @param req req
     * @param camelToUnderLine 是否开启驼峰转下划线
     * @return 分页数据对象
     */
    protected IPage getPageSort(HttpServletRequest req, boolean camelToUnderLine) {
        IPage page = new Page();

        if (StringUtils.isNotBlank(req.getParameter("pageNum"))) {
            page.setCurrent(Long.parseLong(req.getParameter("pageNum")));
        }
        if (StringUtils.isNotBlank(req.getParameter("pageSize"))) {
            page.setSize(Long.parseLong(req.getParameter("pageSize")));
        }

        if (StringUtils.isNotBlank(req.getParameter("orderField"))) {
            OrderItem orderItem = new OrderItem();
            if (camelToUnderLine) {
                orderItem.setColumn(com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(req.getParameter("orderField")));
            } else {
                orderItem.setColumn(req.getParameter("orderField"));
            }

            if (StringUtils.isNotBlank(req.getParameter("orderSort")) && "desc".equalsIgnoreCase(req.getParameter("orderSort"))) {
                orderItem.setAsc(false);
            }
            page.orders().add(orderItem);
        }
        return page;
    }

    protected static boolean isAjaxRequest(HttpServletRequest req) {
        return StringUtils.isNotBlank(req.getHeader("X-Requested-With")) && "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
    }

    /**
     * 返回Json回应给客户端
     *
     * @param rsp       回应信息
     * @param httpRsp   HttpServletResponse
     */
    public static void writeJsonResponse(Response rsp, HttpServletResponse httpRsp)
    {
        String json = JSON.toJSONString(rsp,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNonStringValueAsString);
        lg.debug("JSON:\r\n{}", json);

        try
        {
            httpRsp.setContentType("application/json;charset=utf-8");
            httpRsp.setStatus(HttpServletResponse.SC_OK);
            httpRsp.getWriter().write(json);
            httpRsp.getWriter().flush();
        }
        catch (Exception e)
        {
            lg.error("Response Json回写失败：", e);
        }
    }

    /**
     * 获取Spring Bean
     *
     * @param clazz 类
     * @param <T> 泛型
     * @return 容器中的实例Bean
     */
    protected static <T> T getSpringBean(Class<T> clazz) {
        return CHERRY.SPRING_CONTEXT.getBean(clazz);
    }
}
