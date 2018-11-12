package io.messagebird.springbootstarter.homer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.micrometer.core.annotation.Timed;

@Controller
public class HomerSimpson {

	@Timed(
			value="messagebird.homersimpson.homer",
			histogram=true
	)
	@RequestMapping("homersimpson")
	public String homer() {
		return "/homer.jsp";
	}
}
