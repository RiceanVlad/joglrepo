package joglproj;

import javax.swing.JFrame;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.util.FPSAnimator;

public class MovingSun
extends JFrame
implements GLEventListener
{

private GLCanvas canvas;
private FPSAnimator animator;

// For specifying the positions of the clipping planes (increase/decrease the distance) modify this variable.
// It is used by the glOrtho method.

// Application main entry point
public static void main(String args[])
{
new MovingSun();
}

// Default constructor
public MovingSun()
{
super("Java OpenGL");

this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

this.setSize(1024, 720);

this.initializeJogl();

this.setVisible(true);
}

private void initializeJogl()
{
// Creating a new GL profile.
GLProfile glprofile = GLProfile.getDefault();
// Creating an object to manipulate OpenGL parameters.
GLCapabilities capabilities = new GLCapabilities(glprofile);

// Setting some OpenGL parameters.
capabilities.setHardwareAccelerated(true);
capabilities.setDoubleBuffered(true);

// Try to enable 2x anti aliasing. It should be supported on most hardware.
//capabilities.setNumSamples(2);
//capabilities.setSampleBuffers(true);

// Creating an OpenGL display widget -- canvas.
this.canvas = new GLCanvas();

// Adding the canvas in the center of the frame.
this.getContentPane().add(this.canvas);

// Adding an OpenGL event listener to the canvas.
this.canvas.addGLEventListener(this);

// Creating an animator that will redraw the scene 40 times per second.
this.animator = new FPSAnimator(30);

this.animator.add(this.canvas);

// Starting the animator.
this.animator.start();
}

double equation[] = { 1f, 1f, 1f, 1f};

public void init(GLAutoDrawable canvas)
{
// Obtaining the GL instance associated with the canvas.
GL2 gl = canvas.getGL().getGL2();

// Setting the clear color -- the color which will be used to erase the canvas.
gl.glClearColor(0, 0, 0, 0);

// Select the Projection matrix.
gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);

// Clear the projection matrix and set it to be the identity matrix.
gl.glLoadIdentity();

// Set the projection to be orthographic.
// It could have been as well chosen to be perspective.
// Select the view volume to be x in the range of 0 to 1, y from 0 to 1 and z from -1 to 1.
gl.glOrtho(0, 1, 0, 1, -1, 1);

}

public void display(GLAutoDrawable canvas)
{
	float sun = -1f;

GL2 gl = canvas.getGL().getGL2();

// Erasing the canvas -- filling it with the clear color.
gl.glClear(GL.GL_COLOR_BUFFER_BIT);
gl.glColor3f(1f,1f,1f);

gl.glPushMatrix();
gl.glLoadIdentity();
// Add your scene code here
gl.glPointSize(5f);

gl.glBegin(GL2.GL_POINTS);

gl.glColor3f(0,1,0);
//al 3-lea patrat stanga-sus
gl.glBegin(GL2.GL_QUADS);
    gl.glVertex2f(0.2f, 0.4f);
    gl.glVertex2f(0.2f, 0.6f);
    gl.glVertex2f(0.4f, 0.6f);
    gl.glVertex2f(0.4f, 0.4f);
gl.glEnd();

//triunghi
gl.glColor3f(1f,0,0);
gl.glBegin(GL2.GL_TRIANGLES);
    gl.glVertex2f(0.2f, 0.6f);
    gl.glVertex2f(0.3f, 0.7f);
    gl.glVertex2f(0.4f, 0.6f);
gl.glEnd();

gl.glPopMatrix();

//CERC

float speed = 0.005f;

if(sun < 0) {
    gl.glTranslatef(speed, 0, 0);
    sun += speed;
} else if(sun < 1){
    gl.glTranslatef(-speed,0,0);
    sun += speed;
} else {
    sun = -1f;
}
drawCircle(gl, 0, 1f, 0.09f);
//gl.glScalef(1.01f,1,1);



// Forcing the scene to be rendered.
gl.glFlush();
}


// Here we define the function for building a circle from line segments.
private void drawCircle(GL2 gl, float xCenter, float yCenter, float radius) {

double x = 0,y = 0, angle;
gl.glColor3f(1,1,0);
gl.glBegin(GL2.GL_TRIANGLES);
for (int i=0; i<=360; i++) {
    angle = Math.toRadians(i);
    gl.glVertex2d(xCenter + x, yCenter + y);
    x = radius * Math.cos(angle);
    y = radius * Math.sin(angle);
    gl.glVertex2d(xCenter + x, yCenter + y);
    gl.glVertex2d(xCenter, yCenter);
}
gl.glEnd();

}

public void reshape(GLAutoDrawable canvas, int left, int top, int width, int height)
{
GL2 gl = canvas.getGL().getGL2();

// Selecting the viewport -- the display area -- to be the entire widget.
gl.glViewport(0, 0, width, height);

// Determining the width to height ratio of the widget.
double ratio = (double) width / (double) height;

// Selecting the projection matrix.
gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);

gl.glLoadIdentity();

// Selecting the view volume to be x from 0 to 1, y from 0 to 1, z from -1 to 1.
// But we are careful to keep the aspect ratio and enlarging the width or the height.
if (ratio < 1)
    gl.glOrtho(0, 1, 0, 1 / ratio, -1, 1);
else
    gl.glOrtho(0, 1 * ratio, 0, 1, -1, 1);

// Selecting the modelview matrix.
gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
}

public void displayChanged(GLAutoDrawable canvas, boolean modeChanged, boolean deviceChanged)
{
return;
}

@Override
public void dispose(GLAutoDrawable arg0) {
// TODO Auto-generated method stub
}
}