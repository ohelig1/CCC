package com.klu.ccc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.error.CCCError;
import com.klu.utility.Constants;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RestController
public class LedController {
	private static GpioPinDigitalOutput pin;

	@RequestMapping("/")
	public String greeting() {
		
		return "Hello World!!!";
	}

	@RequestMapping("/home")
	public String home() throws CCCError {
		if(1==1) {
			throw new CCCError(Constants.DEVICE_NOT_FOUND, Constants.DEVICE_NOT_ACCESSIBLE);
			}
		return "Hello Home!!!";
	}

	@RequestMapping("/toggle")
	public String light() throws CCCError {
		try {
		getPin().toggle();
		}catch(Exception e) {
			throw new CCCError(Constants.DEVICE_NOT_FOUND, Constants.DEVICE_NOT_ACCESSIBLE);
		}
		return "OK";
	}

	private String checkState() {
		return (getPin().isHigh() ? "Light is ON" : "Light is OFF");
	}

	@RequestMapping("/on")
	public String on() throws CCCError {
		try {
		getPin().high();
		}catch(Exception e) {
			//throw new CCCError(Constants.DEVICE_NOT_FOUND, Constants.DEVICE_NOT_ACCESSIBLE);
			System.out.println("Exception Occured:"+e.getMessage());
			
		}
		return checkState();
	}

	@RequestMapping("/off")
	public String off() {
		getPin().low();
		return checkState();
	}

	@RequestMapping("/blink")
	public String blink() throws CCCError {
		try {
		getPin().blink(200L, 5000L);
		}catch(Exception e) {
			throw new CCCError(Constants.DEVICE_NOT_FOUND, Constants.DEVICE_NOT_ACCESSIBLE);
		}
		return "Light is blinking...";
	}

	@RequestMapping("/pulse")
	public String pulse() throws CCCError {
		try {
		getPin().pulse(5000L);
		}catch(Exception e) {
			throw new CCCError(Constants.DEVICE_NOT_FOUND, Constants.DEVICE_NOT_ACCESSIBLE);
		}
		return "Light is pulsing...";
	}

	private GpioPinDigitalOutput getPin() {
		if (pin == null) {
			GpioController gpio = GpioFactory.getInstance();
			pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);
		}
		return pin;
	}
}
