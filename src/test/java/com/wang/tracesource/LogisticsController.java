package com.wang.tracesource;

import com.wang.tracesource.service.LogisticsService.LogisticsServiceIml;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

//import static java.lang.CharacterName.get;

public class LogisticsController {
    LogisticsServiceIml logisticsServiceIml=new LogisticsServiceIml();
    @Test
    public void testList(){
        List<LogisticsServiceIml> list;


        //Mock请求
//        MockHttpServletRequestBuilder  builder=get("/list");
    }

}
