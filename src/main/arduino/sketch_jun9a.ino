#include <Adafruit_NeoPixel.h>

#define LED_PIN 13
#define LED_COUNT 12

Adafruit_NeoPixel leds(LED_COUNT, LED_PIN, NEO_GRB + NEO_KHZ800);

const uint32_t green = leds.Color(0, 255, 0);
const uint32_t orange = leds.Color(255, 80, 0);
const uint32_t red = leds.Color(255, 0, 0);

void setup() {
  Serial.begin(9600);
  leds.begin();
  leds.setBrightness(100); // brigthness of LED (0 - 255)
  leds.show();  // Initialize all LEDs to off
}

void loop() {
  if (Serial.available() >= 2) {
    int greenLights = Serial.read();
    int orangeLights = Serial.read();

    // Clear the serial buffer
    while (Serial.available() > 0) {
      Serial.read();
    }

    if(greenLights == 3){
      // User cracked the code!
      theaterChase(green);
    } else {
      // Light up the LEDs
    lightUpLEDs(greenLights, orangeLights);
    }
  }
  leds.clear();
  leds.show();
}

void lightUpLEDs(int greenLights, int orangeLights) {

  // Light up the LEDs based on the first value
  for (int i = 0; i < LED_COUNT; i++) {
    if(greenLights > 0 && i < greenLights*4){
      leds.setPixelColor(i, green );  // Green color
    } else if( i <greenLights*4+orangeLights*4){
      leds.setPixelColor(i, orange);  // Orange color
    } else {
      leds.setPixelColor(i, red);
    }
    if(i % 4 == 3){
      leds.show();  // Update the LED ring
      delay(300);
    }

  }
  leds.show();
  delay(3000);

}

void theaterChase(uint32_t color) {
  for(int a=0; a<20; a++) {  // Repeat 10 times...
    for(int b=0; b<3; b++) { //  'b' counts from 0 to 2...
      leds.clear();         //   Set all pixels in RAM to 0 (off)
      // 'c' counts up from 'b' to end of strip in steps of 3...
      for(int c=b; c<leds.numPixels(); c += 3) {
        leds.setPixelColor(c, color); // Set pixel 'c' to value 'color'
      }
      leds.show(); // Update strip with new contents
      delay(50);  // Pause for a moment
    }
  }
  delay(100);
}