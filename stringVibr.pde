float[] x;
float[] vx;

float[] y;
float[] vy;

float koef1 = 500;
float koef2 = 0.1;
float koefStr = 100;
float time = 0;
float prewTime = 0;

void setup() {
  size(600, 300);
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


void calc(float dt) {
  y[99] = 0;
  vy[99] = 0;
  vy[0] = 0;
  
  //vy[50] = vy[50]+10000*dt*cos((float)1*millis()/1000);
  //vx[99] = vx[99] + 500*dt*dt;
  
  for (int i = 1; i<99; i++) {
    vx[i] = vx[i] + dt*dt*(koef1*(x[i-1]-2*x[i]+x[i+1]));
    vy[i] = vy[i] + dt*dt*(koef1*(y[i-1]-2*y[i]+y[i+1]));
  }
  vx[99] = vx[99] + dt*dt*(koef1*(x[98]-x[99]+5));
  //vy[99] = vy[99] + dt*dt*(koef1*(y[98]-y[99]));
  
  y[99] = 0;
  y[0] = 0;
  
  for (int i = 0; i<100; i++) {
    x[i] = x[i] + dt*koef2*vx[i];
    y[i] = y[i] + dt*koef2*vy[i];
  }
}

void rend() {
  for (int i = 0; i<100; i++) {
    point(x[i], y[i]/3+150);
  }
}

void draw() {
  background(255);
  time = millis();

  calc((time-prewTime)/1000);
  rend();

  prewTime = time;
}
