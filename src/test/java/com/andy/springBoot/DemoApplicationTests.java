package com.andy.springBoot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringApplicationConfiguration(classes = Application.class)  
@WebAppConfiguration  
public class DemoApplicationTests {

	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext webApplicationConnect;
	
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationConnect).build();  
    }
    @Test
    public void getHello() throws Exception {
    	 String uri = "/demo";  
         MvcResult mvcResult =mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    
    int status = mvcResult.getResponse().getStatus();  
    String content = mvcResult.getResponse().getContentAsString();  
    String expectedResult="springBoot Controller demo!";
    Assert.assertTrue("错误，正确的返回值为200", status == 200);  
    Assert.assertFalse("错误，正确的返回值为200", status != 200);  
    Assert.assertTrue("数据一致", expectedResult.equals(content));  
    Assert.assertFalse("数据不一致", !expectedResult.equals(content));  
    }

}
