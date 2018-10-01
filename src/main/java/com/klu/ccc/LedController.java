package com.klu.ccc;

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

	@RequestMapping("/")
	public String greeting() {
		return "Hello World!!!";
	}

	@RequestMapping("/home")
	public String home() {
		return "Hello Home!!!";
	}

	@RequestMapping("/{colour}/toggle")
	public String light(@PathVariable("colour") String colour) {
		getPin(colour).toggle();
		return "OK";
	}

	@RequestMapping("/{colour}/status")
	private String getPinStatus(@PathVariable("colour") String colour) {
		return (getPin(colour).isHigh() ? "Light is ON" : "Light is OFF");
	}

	@RequestMapping("/{colour}/on")
	public String on(@PathVariable("colour") String colour) {
		getPin(colour).high();
		return checkState(colour);
	}

	@RequestMapping("/{colour}/off")
	public String off(@PathVariable("colour") String colour) {
		getPin(colour).low();
		return checkState(colour);
	}

	@RequestMapping("/{colour}/blink")
	public String blink(@PathVariable("colour") String colour) {
		getPin(colour).blink(200L, 5000L);
		return "Light is blinking...";
	}

	@RequestMapping("/{colour}/pulse")
	public String pulse(@PathVariable("colour") String colour) {
		getPin(colour).pulse(5000L);
		return "Light is pulsing...";
	}

	private String checkState(String colour) {
		return (getPin(colour).isHigh() ? "Light is ON" : "Light is OFF");
	}

	private GpioPinDigitalOutput getPin(String colour) {
		return gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), "MyLED", PinState.LOW);
	}
}
