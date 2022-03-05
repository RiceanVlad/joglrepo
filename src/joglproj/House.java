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
	
		
		///////////////////////////
		// SUN
		///////////////////////////

		for(double j=0.1; j < 0.9; j=j+ 0.1) {
			int numVertices = 20;
	        double radius = 0.1;
	        
	        // set color
	        gl.glColor3f( 1.0f,1.0f,0.0f );

	        // clear the window
//	        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

	        // approximate  a circle with a polygon
	        gl.glBegin(GL2.GL_POLYGON);
//	        gl.glBegin(GL2.GL_TRIANGLE_FAN);
	        {
	            double angle = 0;
	            double angleIncrement = 2 * Math.PI / numVertices;
	            for (int i = 0; i < numVertices; i++) {
	                angle = i * angleIncrement;
	                double x = radius * Math.cos(angle) -0.5 + j;
	                double y = radius * Math.sin(angle) + 0.85;
	                gl.glVertex2d(x, y);
	            }
	        }
	    	gl.glFlush();  
	    	gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
	        gl.glEnd();
		}
		
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