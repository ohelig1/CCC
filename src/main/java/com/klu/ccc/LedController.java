package com.klu.ccc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.klu.error.CCCError;
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

	@RequestMapping("/on26")
	public String on() {
		GpioController gpio = GpioFactory.getInstance();
		pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26, "MyLED", PinState.LOW);
		pin.high();
		return checkState();
	}

	@RequestMapping("/off26")
	public String off() {
		GpioController gpio = GpioFactory.getInstance();
		pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26, "MyLED", PinState.LOW);
		getPin().low();
		return checkState();
	}

	@RequestMapping("/off25")
	public String off4() {
		GpioController gpio = GpioFactory.getInstance();
		pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "MyLED", PinState.LOW);
		pin.low();
		return checkState();
	}

	@RequestMapping("/on25")
	public String on4() throws CCCError {
		GpioController gpio = GpioFactory.getInstance();
		pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "MyLED", PinState.LOW);
		pin.high();
		return checkState();
	}

	@RequestMapping("/state25")
	public String status() {
		return getPin25().getState().toString();
	}

	@RequestMapping("/blink")
	public String blink() throws CCCError {
		getPin().blink(200L, 5000L);
		return "Light is blinking...";
	}

	@RequestMapping("/pulse")
	public String pulse() throws CCCError {
		getPin().pulse(5000L);
		return "Light is pulsing...";
	}

	private GpioPinDigitalOutput getPin() {
		if (pin == null) {
			GpioController gpio = GpioFactory.getInstance();
			pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);
		}
		return pin;
	}

	private GpioPinDigitalOutput getPin25() {
		if (pin == null) {
			GpioController gpio = GpioFactory.getInstance();
			pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "MyLED", PinState.LOW);
		}
		return pin;
	}
}
