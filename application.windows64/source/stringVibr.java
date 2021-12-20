import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class stringVibr extends PApplet {

float[] x;
float[] vx;

float[] y;
float[] vy;

float koef1 = 500;
float koef2 = 0.1f;
float koefStr = 100;
float time = 0;
float prewTime = 0;

public void setup() {
  
  x = new float[100];
  vx = new float[100];

  y = new float[100];
  vy = new float[100];

  for (int i = 0; i<100; i++) {
    vx[i] = 0;
    x[i] = i*5;

    vy[i] = 0;
    y[i] = 0;
  }
}


public void calc(float dt) {
  y[99] = 0;
  vy[99] = 0;
  //vy[0] = 0;
  
  vy[0] = vy[0]+10*dt*cos((float)1*millis()/10000);
  vx[99] = vx[99] + 5*dt*dt;
  
  for (int i = 1; i<99; i++) {
    vx[i] = vx[i] + dt*dt*(koef1*(x[i-1]-2*x[i]+x[i+1]));
    vy[i] = vy[i] + dt*dt*(koef1*(y[i-1]-2*y[i]+y[i+1]));
  }
  vx[99] = vx[99] + dt*dt*(koef1*(x[98]-x[99]+5));
  //vy[99] = vy[99] + dt*dt*(koef1*(y[98]-y[99]));
  
  y[99] = 0;
  //y[0] = 0;
  
  for (int i = 0; i<100; i++) {
    x[i] = x[i] + dt*koef2*vx[i];
    y[i] = y[i] + dt*koef2*vy[i];
  }
}

public void rend() {
  for (int i = 0; i<100; i++) {
    point(x[i], y[i]/3+150);
  }
}

public void draw() {
  background(255);
  time = millis();

  calc((time-prewTime)/1000);
  rend();

  prewTime = time;
}
  public void settings() {  size(600, 300); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "stringVibr" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
