package joglproj;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;

public class MainFrame
implements GLEventListener
{

	@Override
	public void display(GLAutoDrawable canvas) {
		final GL2 gl = canvas.getGL().getGL2();  
		  
	      //Drawing top edge  
	gl.glBegin( GL2.GL_LINES );  
	gl.glVertex2d(-0.4, 0.4);  
	gl.glVertex2d(0.4, 0.4);  
	gl.glEnd();  
	  
	//Drawing bottom edge  
	gl.glBegin( GL2.GL_LINES );  
	gl.glVertex2d(-0.4,-0.4);  
	gl.glVertex2d(0.4, -0.4);  
	gl.glEnd();  
	  
	      //Drawing right edge  
	gl.glBegin( GL2.GL_LINES );  
	gl.glVertex2d(-0.4, 0.4);  
	gl.glVertex2d(-0.4, -0.4);  
	gl.glEnd();  
	  
	      //Drawing left edge  
	gl.glBegin( GL2.GL_LINES );  
	gl.glVertex2d(0.4, 0.4);  
	gl.glVertex2d(0.4, -0.4);  
	gl.glEnd();  
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