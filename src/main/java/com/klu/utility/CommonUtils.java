package com.klu.utility;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class CommonUtils {
	
	public static Pin getPinNumber(String colour) {
		Pin pin = null;
		if(colour.equals("red")) {
			pin= RaspiPin.GPIO_01;
		}
		else if(colour.equals("yellow")) {
			pin= RaspiPin.GPIO_02;
		}
		else if(colour.equals("green")) {
			pin= RaspiPin.GPIO_03;
		}
		return pin;
	}
}
