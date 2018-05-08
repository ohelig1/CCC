package com.klu.ccc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RestController
public class LedController {
	private static final GpioController gpio = GpioFactory.getInstance();
	private static GpioPinDigitalOutput pin;

	@RequestMapping("/")
	public String greeting() {

		return "Hello World!!!";
	}

	@RequestMapping("/home")
	public String home() {
		return "Hello Home!!!";
	}

	@RequestMapping("/toggle")
	public String light() {
		getPin().toggle();
		return "OK";
	}

	private String checkState() {
		return (getPin().isHigh() ? "Light is ON" : "Light is OFF");
	}
	@RequestMapping("/on")
	public String on() {
		getPin().high();
		return checkState();
	}

	@RequestMapping("/off")
	public String off() {
		getPin().low();
		return checkState();
	}
	
	private GpioPinDigitalOutput getPin() {
		if (pin == null) {
			pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);
		}
		return pin;
	}
}
