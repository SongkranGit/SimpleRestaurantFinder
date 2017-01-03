package com.example.simplerestaurantfinder;

import com.example.simplerestaurantfinder.model.Restaurant;
import com.example.simplerestaurantfinder.service.RestaurantService;
import com.example.simplerestaurantfinder.utils.DateTimeUtil;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTestApplicationTests {


	@Autowired
	RestaurantService restaurantService;

	@Test
	public void getRestaurantsOpenNowTest(){
		DateTime dateTime = new DateTime()
				.withHourOfDay(10)
				.withMinuteOfHour(20)
				.withSecondOfMinute(0);

		List<Restaurant> restaurants = restaurantService.getRestaurantsOpenNow(DateTimeUtil.convertDateTimeToSqlTime(dateTime));
		Assert.assertNotNull(restaurants);
	}


	@Test
	public void getNearbyRestaurantWithinRadiusTest(){
		double latitude = 10 ;
		double longitude = 20 ;
		double radius = 10;

		List<Restaurant> restaurants = restaurantService.getNearbyRestaurantsWithInRadius(latitude , longitude , radius);
		Assert.assertNotNull(restaurants);

	}

	@Test
	public void getNearbyRestaurantWithinRadiusAndOpenNowTest(){
		double latitude = 10 ;
		double longitude = 20 ;
		double radius = 10;
		DateTime dateTime = new DateTime()
				.withHourOfDay(10)
				.withMinuteOfHour(20)
				.withSecondOfMinute(0);

		Time currentTime = DateTimeUtil.convertDateTimeToSqlTime(dateTime);
		List<Restaurant> restaurants = restaurantService.getNearbyRestaurantsWithInRadiusAndOpenNow(latitude , longitude , radius , currentTime);
		Assert.assertNotNull(restaurants);

	}
}
