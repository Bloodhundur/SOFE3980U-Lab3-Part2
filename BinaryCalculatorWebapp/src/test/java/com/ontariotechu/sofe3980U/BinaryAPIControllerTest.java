package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


	@RunWith(SpringRunner.class)
	@WebMvcTest(BinaryAPIController.class)
	public class BinaryAPIControllerTest {

		@Autowired
		private MockMvc mvc;


		@Test
		public void add() throws Exception {
			this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("10001"));
		}
		@Test
		public void add2() throws Exception {
			this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
				.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
				.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
				.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
		}

		//new 3 tests stuff
		@Test
		public void addHandlesZeroPlusZero() throws Exception {
			this.mvc.perform(get("/add").param("operand1","0").param("operand2","0"))
				.andExpect(status().isOk())
				.andExpect(content().string("0"));
		}

		@Test
		public void addHandlesCarry() throws Exception {
			this.mvc.perform(get("/add").param("operand1","1111").param("operand2","1"))
				.andExpect(status().isOk())
				.andExpect(content().string("10000"));
		}

		@Test
		public void addJsonHandlesCarryAndOperatorField() throws Exception {
			this.mvc.perform(get("/add_json").param("operand1","1111").param("operand2","1"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1111))
				.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10000))
				.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
		}

		//new operators
		//mul
		@Test
		public void mul_basic_string() throws Exception {
			this.mvc.perform(get("/mul").param("operand1","10").param("operand2","11"))
				.andExpect(status().isOk())
				.andExpect(content().string("110"));
		}

		@Test
		public void mul_byZero_string() throws Exception {
			this.mvc.perform(get("/mul").param("operand1","0").param("operand2","1011"))
				.andExpect(status().isOk())
				.andExpect(content().string("0"));
		}

		//and
		@Test
		public void and_basic_string() throws Exception {
			this.mvc.perform(get("/and").param("operand1","1101").param("operand2","1011"))
				.andExpect(status().isOk())
				.andExpect(content().string("1001"));
		}

		@Test
		public void and_withOnes_string() throws Exception {
			this.mvc.perform(get("/and").param("operand1","1111").param("operand2","1100"))
				.andExpect(status().isOk())
				.andExpect(content().string("1100"));
		}

		//or
		@Test
		public void or_basic_string() throws Exception {
			this.mvc.perform(get("/or").param("operand1","1100").param("operand2","0011"))
				.andExpect(status().isOk())
				.andExpect(content().string("1111"));
		}

		@Test
		public void or_keepsOnes_string() throws Exception {
			this.mvc.perform(get("/or").param("operand1","1000").param("operand2","0001"))
				.andExpect(status().isOk())
				.andExpect(content().string("1001"));
		}


	}
