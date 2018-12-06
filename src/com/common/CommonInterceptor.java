package com.common;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.google.gson.Gson;
import com.lqmrSys.bean.OperateBean;
import com.lqmrSys.dao.ModuleDao;
import com.lqmrSys.service.OperateService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;

public class CommonInterceptor implements HandlerInterceptor {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
	private OperateService operateService;
    @Autowired
	private ModuleDao moduleDao;
    //	@Resource
    //	private CheckService checkService;

    /**
     * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * <p/>
     * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 返回响应报文中的头部信息
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Accept, X-Token");

        String reqMethod = request.getMethod();//请求方式
        String reqUrl = request.getRequestURI();//请求路径
        //根据请求方式，判断是否从body中获取参数
        OperateBean operate = new OperateBean();//操作日志类
        operate.setOperateInterface(reqUrl);
        if("GET".equals(reqMethod)){
        	//记录日志
            StringBuilder sb = new StringBuilder();
            Enumeration<?> a = request.getParameterNames();
            while (a.hasMoreElements()) {
                Object name = (Object) a.nextElement();
                String value = request.getParameter(String.valueOf(name));
                sb.append(name).append("=").append(value).append("&");
                //将值放入操作类中
                if(name.equals("operatorId")){
                	operate.setOperatorId(Integer.valueOf(value));
                }else if(name.equals("operatorName")){
                	operate.setOperatorName(value);
                }else if(name.equals("operateInterfaceName")){
                	operate.setOperateInterfaceName(value);
                }
//                else if(name.equals("operateInterface")){
//                	operate.setOperateInterface(value);
//                }
            }
            if (sb.length() > 0) {
                sb.delete(sb.length() - 1, sb.length());
            }
            StringBuilder sbLog = new StringBuilder();
            sbLog.append(reqMethod)
                 .append("->")
                 .append(reqUrl)
                 .append("[")
                 .append(sb.toString())
                 .append("]");
            operate.setOperateContent(sbLog.toString());//放入操作内容
        }else{
        	// 先实例化一个文件解析器
            CommonsMultipartResolver coMultipartResolver = new CommonsMultipartResolver(request.getSession()
                    .getServletContext());
            // 判断request请求中是否有文件上传
            if (coMultipartResolver.isMultipart(request)) {
            	return true;
            }
            
        	//request.getInputStream()需在此处转码，在下方进行字符串转码会出现gbk奇数乱码问题
        	InputStreamReader readStr = new InputStreamReader(request.getInputStream(), "UTF-8");
        	BufferedReader br = null;
            StringBuilder sb = new StringBuilder();
            try{
//                br = request.getReader();
            	br = new BufferedReader(readStr);
                String str;
                while ((str = br.readLine()) != null){
                    sb.append(str);
                }
                if("/lqmrsys/trial/addList".equals(reqUrl)){
                	 JSONArray arrayList = JSONArray.fromObject(sb.toString());
                	 if(arrayList!=null && arrayList.size()>0){
                		 JSONObject jsonObject = (JSONObject) arrayList.get(0);
                		 if(jsonObject!=null){
                         	operate.setOperatorId(jsonObject.getInt("operatorId"));
                         	operate.setOperatorName(jsonObject.getString("operatorName"));
                         	operate.setOperateInterfaceName(jsonObject.getString("operateInterfaceName"));
                         }
                	 }
                }else{
                	JSONObject jsonObject=JSONObject.fromObject(sb.toString());
                	if(jsonObject!=null){
                    	operate.setOperatorId(jsonObject.getInt("operatorId"));
                    	operate.setOperatorName(jsonObject.getString("operatorName"));
                    	operate.setOperateInterfaceName(jsonObject.getString("operateInterfaceName"));
                    }
                }
//                String operatorName = jsonObject.getString("operatorName");
//                int operatorId = jsonObject.getInt("operatorId");
//                String operateInterfaceName = jsonObject.getString("operateInterfaceName");
//                System.out.println(operatorId+"/"+operatorName+"/"+operateInterfaceName);
//                OperateBean bean = (OperateBean)JSONObject.toBean(jsonObject, OperateBean.class);
//                if(jsonObject!=null){
//                	operate.setOperatorId(jsonObject.getInt("operatorId"));
//                	operate.setOperatorName(jsonObject.getString("operatorName"));
//                	operate.setOperateInterfaceName(jsonObject.getString("operateInterfaceName"));
//                }
                StringBuilder sbLog = new StringBuilder();
                sbLog.append(reqMethod)
                .append("->")
                .append(reqUrl)
                .append("[")
                .append(sb.toString())
                .append("]");
                operate.setOperateContent(sbLog.toString());
                br.close();
            }catch (IOException e){
                e.printStackTrace();
            }finally{
                if (null != br){
                    try{
                        br.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        
        if(operate.getOperateInterfaceName()!=null && !"".equals(operate.getOperateInterfaceName())){
        	//判断人员权限
        	String moduleCode = operate.getOperateInterface();
        	String moduleEndContent = "";
        	if("借款到期列表".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?listType=expire";
        	}else if("借款逾期列表".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?listType=overdue";
        	}else if("法人附件管理".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?dataFrom=sys_company";
        	}else if("法人资金账号维护".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?cardType=sys_company";
        	}else if("法人人物关系表".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?dataFrom=sys_company";
        	}else if("自然人附件管理".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?dataFrom=sys_customer";
        	}else if("自然人资金账号维护".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?cardType=sys_customer";
        	}else if("自然人人物关系表".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?dataFrom=sys_customer";
        	}else if("供应方附件管理".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?dataFrom=sys_supplier";
        	}else if("供应方资金账号维护".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?cardType=sys_supplier";
        	}else if("需求方附件管理".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?dataFrom=sys_demand";
        	}else if("需求方资金账号维护".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?cardType=sys_demand";
        	}else if("需求方审核审批".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?trialType=sys_demand";
        	}else if("贷前审核".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?trialType=sys_demand";
        	}else if("借款审核审批".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?trialType=sys_loan_receipt";
        	}else if("放款审批".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?trialType=sys_loan_receipt";
        	}else if("公司报表".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?type=company";
        	}else if("人员报表".equals(operate.getOperateInterfaceName())){
        		moduleEndContent = "?type=user";
        	}
        	moduleCode = moduleCode + moduleEndContent;
        	if(moduleDao.hasModule(operate.getOperatorId(), moduleCode)==0){
        		writerJson(response, new Res<String>(ResultCodeEnum.NO_JURISDICTION.getCode(), ResultCodeEnum.NO_JURISDICTION.getMessage(), null));
                return false;
        	}
        	//新增人员操作日志
        	operateService.addOperate(operate);
        }
        
        // 统一处理跨域请求方法options请求
        if ("options".equals(reqMethod.toLowerCase())) {
            writerJson(response, new Res<String>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), null));
            return false;
        }

        return true;
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用
     * <p/>
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

	private void writerJson(HttpServletResponse response, Res<String> res) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            out.print(new Gson().toJson(res));
            out.flush();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }}
