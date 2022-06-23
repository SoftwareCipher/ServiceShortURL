package com.service.shortLink.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.shortLink.entity.Shorter;
import com.service.shortLink.service.impl.ShorterServiceInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ShorterController.class})
@ExtendWith(SpringExtension.class)
class ShorterControllerTest {
    @Autowired
    private ShorterController shorterController;

    @MockBean
    private ShorterServiceInterface shorterServiceInterface;

    /**
     * Method under test: {@link ShorterController#createShortUrl(Shorter)}
     */
    @Test
    void testCreateShortUrl() throws Exception {
        when(this.shorterServiceInterface.createShortUrl((Shorter) any())).thenReturn("https://example.org/example");

        Shorter shorter = new Shorter();
        shorter.setCreatedAt(null);
        shorter.setHash("Hash");
        shorter.setId(123L);
        shorter.setOriginalUrl("https://example.org/example");
        String content = (new ObjectMapper()).writeValueAsString(shorter);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/url/addHash")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.shorterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("https://example.org/example"));
    }

    /**
     * Method under test: {@link ShorterController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        when(this.shorterServiceInterface.getAll()).thenReturn(new ResponseEntity(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/url/allHash");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.shorterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link ShorterController#getAll()}
     */
    @Test
    void testGetAll2() throws Exception {
        when(this.shorterServiceInterface.getAll()).thenReturn(new ResponseEntity(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/url/allHash");
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.shorterController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link ShorterController#redirectShorter(String)}
     */
    @Test
    void testRedirectShorter() throws Exception {
        when(this.shorterServiceInterface.responseEntity((String) any()))
                .thenReturn(new ResponseEntity(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/url/{hash}", "Hash");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.shorterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link ShorterController#redirectShorter(String)}
     */
    @Test
    void testRedirectShorter2() throws Exception {
        when(this.shorterServiceInterface.responseEntity((String) any()))
                .thenReturn(new ResponseEntity(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/url/{hash}", "Hash");
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.shorterController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }
}

