package com.klu.ccc;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.klu.utility.CommonUtils;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

@RestController
public class LedController {
	private static final GpioController gpio = GpioFactory.getInstance();
	private GpioPinDigitalOutput gpioPinDigitalOutput;
	@RequestMapping("/")
	public String greeting() {
		return "Hello World!!!";
	}

	@RequestMapping("/{colour}/toggle")
	public String light(@PathVariable("colour") String colour) {
		LocalDateTime starttime = LocalDateTime.now();
		gpioPinDigitalOutput =  gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), colour , PinState.LOW);
		gpioPinDigitalOutput.toggle();
		gpio.unprovisionPin(gpioPinDigitalOutput);
		Duration duration = Duration.between(starttime,LocalDateTime.now());
		System.out.println("Store in MiliSecond:"+duration.toMillis());
		return String.valueOf(duration.toMillis());
	}

	@RequestMapping("/{colour}/status")
	private String getPinStatus(@PathVariable("colour") String colour) {
		LocalDateTime starttime = LocalDateTime.now();
		gpioPinDigitalOutput =  gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), colour, PinState.LOW);
		//String status = gpioPinDigitalOutput.isHigh() ? "Light is ON" : "Light is OFF";
		gpio.unprovisionPin(gpioPinDigitalOutput);
		Duration duration = Duration.between(starttime,LocalDateTime.now());
		System.out.println("Store in MiliSecond:"+duration.toMillis());
		return String.valueOf(duration.toMillis());
	}

	@RequestMapping("/{colour}/on")
	public String on(@PathVariable("colour") String colour) {
		LocalDateTime starttime = LocalDateTime.now();
		gpioPinDigitalOutput =  gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), colour, PinState.LOW);
		gpioPinDigitalOutput.high();
		gpio.unprovisionPin(gpioPinDigitalOutput);
		Duration duration = Duration.between(starttime,LocalDateTime.now());
		System.out.println("Store in MiliSecond:"+duration.toMillis());
		return String.valueOf("Light is on:"+duration.toMillis());
	}

	@RequestMapping("/{colour}/off")
	public String off(@PathVariable("colour") String colour) {
		LocalDateTime starttime = LocalDateTime.now();
		gpioPinDigitalOutput =  gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), colour, PinState.LOW);
		gpioPinDigitalOutput.low();
		gpio.unprovisionPin(gpioPinDigitalOutput);
		Duration duration = Duration.between(starttime,LocalDateTime.now());
		System.out.println("Store in MiliSecond:"+duration.toMillis());
		return String.valueOf("Light is off:"+duration.toMillis());
	}

	@RequestMapping("/{colour}/blink")
	public String blink(@PathVariable("colour") String colour) {
		LocalDateTime starttime = LocalDateTime.now();
		gpioPinDigitalOutput= gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), colour, PinState.LOW);
		gpioPinDigitalOutput.blink(200L, 5000L);
		gpio.unprovisionPin(gpioPinDigitalOutput);
		Duration duration = Duration.between(starttime,LocalDateTime.now());
		System.out.println("Store in MiliSecond:"+duration.toMillis());
		return String.valueOf("Light is blinking"+duration.toMillis());
	}

	@RequestMapping("/{colour}/pulse")
	public String pulse(@PathVariable("colour") String colour) {
		LocalDateTime starttime = LocalDateTime.now();
		gpioPinDigitalOutput =  gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), colour, PinState.LOW);
		gpioPinDigitalOutput.pulse(5000L);
		gpio.unprovisionPin(gpioPinDigitalOutput);	
		Duration duration = Duration.between(starttime,LocalDateTime.now());
		System.out.println("Store in MiliSecond:"+duration.toMillis());
		return String.valueOf("Light is pulsing:"+duration.toMillis());
	}
	
	/*	To Remove For reference:::
	 * 
	 * public void cleanUpTask(GpioPinDigitalOutput  gpioPinDigitalOutput) {
	 *  For Clearing Properties. 
		try {
			gpioPinDigitalOutput.clearProperties();
		}catch(Exception e) {
			System.out.println("Remove All Listeners:"+e.getMessage());
		}
		//Only use shutdown for terminating. It's not recommended to use shutdown in running applicaiton.
		try {
			gpio.shutdown();
		}catch(Exception e) {
			System.out.println("GPIO UnprovisionPin:"+e.getMessage());
		}
		try {
			gpio.unprovisionPin(gpioPinDigitalOutput);
		}catch(Exception e) {
			System.out.println("GPIO Shutdown:"+e.getMessage());
		}		
	}*/
}
