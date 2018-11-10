package io.messagebird.springbootstarter.timezone;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;

@RestController
public class TimeZone {
	
	@Timed(
			value="messagebird-covilha-time",
			histogram=true
	)
	@RequestMapping("/covilha")
	public String timeZone() {
		TimeConvertor pTime = new TimeConvertor();
		String time = pTime.timeZoneConvertor();
		System.out.println(time);
		return time;
	}

}
