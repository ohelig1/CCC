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

	@RequestMapping("/{colour}/toggle")
	public String light(@PathVariable("colour") String colour) {
		GpioPinDigitalOutput gpioPinDigitalOutput =  gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), colour , PinState.LOW);
		gpioPinDigitalOutput.toggle();
		gpio.shutdown(); 
		gpio.unprovisionPin(gpioPinDigitalOutput);
		return "Toggle Successful";
	}

	@RequestMapping("/{colour}/status")
	private String getPinStatus(@PathVariable("colour") String colour) {
		GpioPinDigitalOutput gpioPinDigitalOutput =  gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), colour, PinState.LOW);
		String status = gpioPinDigitalOutput.isHigh() ? "Light is ON" : "Light is OFF";
		gpio.shutdown(); 
		gpio.unprovisionPin(gpioPinDigitalOutput);
		return status;
	}

	@RequestMapping("/{colour}/on")
	public String on(@PathVariable("colour") String colour) {
		GpioPinDigitalOutput gpioPinDigitalOutput =  gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), colour, PinState.LOW);
		gpioPinDigitalOutput.high();
		gpio.shutdown(); 
		gpio.unprovisionPin(gpioPinDigitalOutput);
		return "Light is ON!!!";
	}

	@RequestMapping("/{colour}/off")
	public String off(@PathVariable("colour") String colour) {
		GpioPinDigitalOutput gpioPinDigitalOutput =  gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), colour, PinState.LOW);
		gpioPinDigitalOutput.low();
		gpio.shutdown(); 
		gpio.unprovisionPin(gpioPinDigitalOutput);
		return "Light is OFF!!!";
	}

	@RequestMapping("/{colour}/blink")
	public String blink(@PathVariable("colour") String colour) {
		GpioPinDigitalOutput gpioPinDigitalOutput =  gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), colour, PinState.LOW);
		gpioPinDigitalOutput.blink(200L, 5000L);
		gpio.shutdown(); 
		gpio.unprovisionPin(gpioPinDigitalOutput);
		return "Light is blinking...";
	}

	@RequestMapping("/{colour}/pulse")
	public String pulse(@PathVariable("colour") String colour) {
		GpioPinDigitalOutput gpioPinDigitalOutput =  gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), colour, PinState.LOW);
		gpioPinDigitalOutput.pulse(5000L);
		gpio.shutdown(); 
		gpio.unprovisionPin(gpioPinDigitalOutput);
		return "Light is pulsing...";
	}
}
