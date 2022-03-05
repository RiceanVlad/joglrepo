package joglproj;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class House 
implements GLEventListener
{

	@Override
	public void display(GLAutoDrawable canvas) {
		final GL2 gl = canvas.getGL().getGL2();  
		
		///////////////////////////
		// SQUARE
		///////////////////////////

	    //Drawing top edge  
		gl.glBegin( GL2.GL_LINES ); 
		gl.glColor3f( 0.0f,1.0f,0.0f );   
		gl.glVertex2d(-0.4, 0.4);  
		gl.glVertex2d(0.4, 0.4);  
		gl.glEnd();  
		  
		//Drawing bottom edge  
		gl.glBegin( GL2.GL_LINES );  
		gl.glColor3f( 1.0f,1.0f,0.0f );   
		gl.glVertex2d(-0.4,-0.4);  
		gl.glVertex2d(0.4, -0.4);  
		gl.glEnd();  
		  
        //Drawing right edge  
		gl.glBegin( GL2.GL_LINES );  
		gl.glColor3f( 0.0f,1.0f,1.0f );   
		gl.glVertex2d(-0.4, 0.4);  
		gl.glVertex2d(-0.4, -0.4);  
		gl.glEnd();  
		  
        //Drawing left edge  
		gl.glBegin( GL2.GL_LINES );  
		gl.glColor3f( 1.0f,0.0f,0.0f );   
		gl.glVertex2d(0.4, 0.4);  
		gl.glVertex2d(0.4, -0.4);  
		gl.glEnd();  
		
		///////////////////////////
		// TRIANGLE
		///////////////////////////
		
	    //Right edge  
		gl.glBegin (GL2.GL_LINES);  
		gl.glVertex2d(0.4, 0.4);  
		gl.glVertex2d(0.0, 0.9);  
		gl.glEnd();  
		  
	    //Left edge  
		gl.glBegin (GL2.GL_LINES);  
		gl.glVertex2d(-0.4, 0.4);  
		gl.glVertex2d(0.0, 0.9);  
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