package org.unamedgroup.conferencemanagement.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class Test {
    /**
     * @Author: liumengxiao
     * @Date: 2019-03-06
     * @Description: 通过HttpServletResponse 写json到客户端
     * @Params: JSONObject, HttpServletResponse
     * @Return: void
     */
    @RequestMapping(value = "/test/test.do", method = RequestMethod.POST)
    public void writeByResp(@RequestBody JSONObject jsonParam, HttpServletResponse resp) {

        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "HttpServletResponse");
        result.put("data", jsonParam);

        //写json数据到客户端
        this.writeJson(resp, result);
    }

    /** 
     * @Author: liumengxiao
     * @Date: 2019-03-06
     * @Description: 写数据到浏览器上
     * @Params: resp, json
     * @Return: void
     */
    public void writeJson(HttpServletResponse resp ,JSONObject json ){
        PrintWriter out = null;
        try {
            //设定类容为json的格式
            resp.setContentType("application/json;charset=UTF-8");
            out = resp.getWriter();
            //写到客户端
            out.write(json.toJSONString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(out != null){
                out.close();
            }
        }
    }


}
