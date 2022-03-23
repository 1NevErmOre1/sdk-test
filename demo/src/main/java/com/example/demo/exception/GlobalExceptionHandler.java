//package com.example.demo.exception;
//
//import com.yd.core.common.model.OmgRobotRequest;
//import com.yd.core.interceptor.WebRequestLogAspect;
//import com.yd.core.util.OmgOpenApiUtil;
//import com.yd.core.utils.R;
//import com.yd.core.utils.RedisKeys;
//import com.yd.core.utils.RedisUtils;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.util.Arrays;
//
///**
// * 全局异常拦截器
// *
// * @auther wuyc
// * @date 2018/12/18 17:35
// */
//@ControllerAdvice
//@ResponseBody
//@Slf4j
//public class GlobalExceptionHandler extends DefaultHandlerExceptionResolver {
//
//    private static final int THRESHOLD = 4096;
//
//
//    @Value("${spring.profiles.active}")
//    private String activeProfile;
//
//    @Resource
//    private RedisUtils redisUtils;
//
//
//    @ExceptionHandler(value = Exception.class)
//    public R exceptionHandler(HttpServletRequest request, Exception e) {
//        ThreadLocal<String> traceIdThreadLocal = WebRequestLogAspect.TRACE_ID_THREAD_LOCAL;
//        String traceId = traceIdThreadLocal.get();
//        if (StringUtils.isNotBlank(traceId)) {
//            String remoteAddr = request.getRemoteAddr();
//            log.error("当前请求来源地址:{},traceId:{}", remoteAddr, traceId, e);
//        }
//        String errMsg = "系统错误，请联系管理员";
//        Integer code = null;
//        if (e instanceof RRException) {
//            RRException re = (RRException) e;
//            errMsg = re.getMsg();
//            code = re.getCode();
//            log.error(errMsg, e);
//        }
//        if (StringUtils.isNotBlank(traceId)) {
//            errMsg = traceId + errMsg;
//        }
//        if (e instanceof RBException) {
//            RBException re = (RBException) e;
//            errMsg = re.getMsg();
//            code = re.getCode();
//            log.error(errMsg, e);
//        }
//        if ("prod".equals(activeProfile)) {
//            StringWriter sw = new StringWriter();
//            PrintWriter pw = new PrintWriter(sw);
//            e.printStackTrace(pw);
//            String sendMsg = "";
//            if (StringUtils.isNotBlank(traceId)) {
//                String remoteAddr = request.getRemoteAddr();
//                sendMsg = String.format("当前请求来源地址:%s,traceId:%s%n", remoteAddr, traceId);
//            }
//            String requestURI = "";
//            // 添加用户 id 和公司编号
//            if (request != null) {
//                String token = request.getHeader("token");
//                token = StringUtils.isNotBlank(token) ? token : request.getParameter("token");
//                UserInfo userInfo = redisUtils.get(RedisKeys.getToken(token), UserInfo.class);
//                sendMsg += userInfo == null ? "" : String
//                        .format("current userId: %s ,companyNum: %s.  %n", userInfo.getUserId(), userInfo.getCompanyNum());
//                requestURI = request.getRequestURI();
//                if (e instanceof HttpRequestMethodNotSupportedException) {
//                    HttpRequestMethodNotSupportedException notSupportedException = (HttpRequestMethodNotSupportedException) e;
//                    sendMsg += String.format("%s请求方式有误,当前请求方式为 %s,允许的请求方式为 %s", requestURI,
//                            request.getMethod(), Arrays.toString(notSupportedException.getSupportedMethods()));
//                } else {
//                    sendMsg += String.format("请求接口为%s", requestURI);
//                }
//                // 换行分隔
//                sendMsg += "\n-----------------------------\n";
//            }
//            String text = String.format("errorStackTrace:%s", sw);
//            sendMsg += text;
//            int length = sendMsg.length();
//            // 分段发送
//            int remain = length % THRESHOLD;
//            int blocks = length / THRESHOLD + (remain != 0 ? 1 : 0);
//            if (!(("/yddycallback".equals(requestURI) &&
//                    e instanceof MissingServletRequestParameterException) ||
//                    e instanceof IOException
//                    || "/xmCallback/sql".equals(requestURI))) {
//                for (int i = 0; i < blocks; i++) {
//                    String tmp = sendMsg.substring(i * THRESHOLD, Math.min(length, (i + 1) * THRESHOLD));
//                    OmgRobotRequest omgRequest = OmgRobotRequest.builder().text(tmp).isAtAll(false).build();
//                    omgOpenApiUtil.sendMsg2WechatRobot(omgRequest);
//                }
//            }
//        }
//        traceIdThreadLocal.remove();
//        return code == null ? R.error(errMsg) : R.error(code, errMsg);
//    }
//
//    @ExceptionHandler(value = RBException.class)
//    public R checkError(RBException e) {
//        return e.result();
//    }
//
//    @Data
//    private static class UserInfo {
//
//        private Long userId;
//
//        private Long companyNum;
//
//    }
//}