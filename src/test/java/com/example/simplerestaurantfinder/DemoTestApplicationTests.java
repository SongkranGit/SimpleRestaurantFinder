package com.example.simplerestaurantfinder;

import com.example.simplerestaurantfinder.model.Restaurant;
import com.example.simplerestaurantfinder.service.RestaurantService;
import com.example.simplerestaurantfinder.utils.DateTimeUtil;
import org.assertj.core.util.DateUtil;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTestApplicationTests {


	@Autowired
	RestaurantService restaurantService;

	@Test
	public void openNowRestaurantsTest(){
		DateTime dateTime = new DateTime()
				.withHourOfDay(10)
				.withMinuteOfHour(20)
				.withSecondOfMinute(0);

		List<Restaurant> restaurantList = restaurantService.getRestaurantsOpenNow(DateTimeUtil.convertDateTimeToSqlTime(dateTime));
		Assert.assertNotNull(restaurantList);
	}
}
