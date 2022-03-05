package joglproj;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class Triangle
implements GLEventListener
{

	@Override
	public void display(GLAutoDrawable canvas) {
		final GL2 gl = canvas.getGL().getGL2();  
		  
		// Base edge  
		gl.glBegin (GL2.GL_LINES);  
		gl.glVertex2d(-0.65, -0.65);  
		gl.glVertex2d(0.65, -0.65);  
		gl.glEnd();  
		  
		      //Right edge  
		gl.glBegin (GL2.GL_LINES);  
		gl.glVertex2d(0, 0.65);  
		gl.glVertex2d(-0.65, -0.65);  
		gl.glEnd();  
		  
		      //Left edge  
		gl.glBegin (GL2.GL_LINES);  
		gl.glVertex2d(0, 0.65);  
		gl.glVertex2d(0.65, -0.65);  
		gl.glEnd();  
		gl.glFlush();  
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
}