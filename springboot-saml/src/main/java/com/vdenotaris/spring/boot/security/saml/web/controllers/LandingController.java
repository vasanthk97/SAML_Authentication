/*
 * Copyright 2017 Vincenzo De Notaris
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package com.vdenotaris.spring.boot.security.saml.web.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LandingController {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(LandingController.class);

	@RequestMapping("/landing")
	public String landing( Model model, HttpServletResponse httpServletResponse) {
		//logger.info("logged in user: "+user.getUsername());

//		Cookie cookie = new Cookie("Auth", "zemoso1234");
//		cookie.setPath("/protected");
//		httpServletResponse.addCookie(cookie);
//
//		Authentication authentication = new UsernamePasswordAuthenticationToken(user,null,null);

		model.addAttribute("username", 	"sudheer-ds");
		return "landing";
	}

	/*@PostMapping("/signoff")
	public String singoff(@AuthenticationPrincipal Authentication authentication, Model model, HttpServletResponse httpServletResponse){
		logger.info("logout is requested");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForEntity("http://localhost:8082/saml/logout",Boolean.class, HttpServletResponse.class);
		return "ok";
	}

	@GetMapping("/landing2")
	public String landing2(@CurrentUser User user, Model model, HttpServletResponse httpServletResponse) {
		return "landing2";
	}*/
}
