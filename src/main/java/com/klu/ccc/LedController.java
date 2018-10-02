package com.klu.ccc;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.utility.CommonUtils;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

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
		gpioPinDigitalOutput =  gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), colour , PinState.LOW);
		gpioPinDigitalOutput.toggle();
	//	cleanUpTask(gpioPinDigitalOutput);
		gpio.unprovisionPin(gpioPinDigitalOutput);
		return "Toggle Successful";
	}

	@RequestMapping("/{colour}/status")
	private String getPinStatus(@PathVariable("colour") String colour) {
		gpioPinDigitalOutput =  gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), colour, PinState.LOW);
		String status = gpioPinDigitalOutput.isHigh() ? "Light is ON" : "Light is OFF";
	//	cleanUpTask(gpioPinDigitalOutput);
		gpio.unprovisionPin(gpioPinDigitalOutput);
		return status;
	}

	@RequestMapping("/{colour}/on")
	public String on(@PathVariable("colour") String colour) {
		gpioPinDigitalOutput =  gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), colour, PinState.LOW);
		gpioPinDigitalOutput.high();
		gpio.unprovisionPin(gpioPinDigitalOutput);
	//	cleanUpTask(gpioPinDigitalOutput);
		return "Light is ON!!!";
	}

	@RequestMapping("/{colour}/off")
	public String off(@PathVariable("colour") String colour) {
		gpioPinDigitalOutput =  gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), colour, PinState.LOW);
		gpioPinDigitalOutput.low();
		gpio.unprovisionPin(gpioPinDigitalOutput);
	//	cleanUpTask(gpioPinDigitalOutput);
		return "Light is OFF!!!";
	}

	@RequestMapping("/{colour}/blink")
	public String blink(@PathVariable("colour") String colour) {
		final GpioPinDigitalOutput ledPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);
		ledPin.blink(200L, 5000L);
		gpio.unprovisionPin(gpioPinDigitalOutput);
	//	cleanUpTask(ledPin);
		return "Light is blinking...";
	}

	@RequestMapping("/{colour}/pulse")
	public String pulse(@PathVariable("colour") String colour) {
		gpioPinDigitalOutput =  gpio.provisionDigitalOutputPin(CommonUtils.getPinNumber(colour), colour, PinState.LOW);
		gpioPinDigitalOutput.pulse(5000L);
		gpio.unprovisionPin(gpioPinDigitalOutput);	
		return "Light is pulsing...";
	}
	
	/*public void cleanUpTask(GpioPinDigitalOutput  gpioPinDigitalOutput) {
		try {
			gpioPinDigitalOutput.clearProperties();
		}catch(Exception e) {
			System.out.println("Remove All Listeners:"+e.getMessage());
		}
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
