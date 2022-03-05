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
        
	      gl.glBegin (GL2.GL_LINES);//static field
	      gl.glVertex3f(0.50f,-0.50f,0);
	      gl.glVertex3f(-0.50f,0.50f,0);
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